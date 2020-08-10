package com.hj.advertiser;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hj.advertiser.base.constant.Constant;
import com.hj.advertiser.model.bd.BdPoiCircleInputModel;
import com.hj.advertiser.model.bd.BdPoiModel;
import com.hj.advertiser.model.bd.BdPoiSquareInputModel;
import com.hj.advertiser.util.BdMapUtils;
import com.hj.advertiser.util.ExcelUtils;
import com.hj.advertiser.util.UrlUtils;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BdMapServiceTest {
	
	public static final List<String> KEYWORDS = Arrays.asList(
			"广告", "图文", "招牌", "打印", "复印", 
			"发光字", "灯光", "亮化", "电子显示屏"
			);

	/**
	 * 圆形区域获取poi信息
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	@Test
	void getBdPoiFromCircleTest() throws InterruptedException, IOException  {
		
		//web服务接口说明: http://lbsyun.baidu.com/index.php?title=webapi/guide/webservice-placeapi
		//poi分类说明: http://lbsyun.baidu.com/index.php?title=lbscloud/poitags
		
		String townListFilePath = "/Users/huangjiong/hjdb/develop/Projects/led-advertiser/src/main/resources/广告商-新/湘潭市/湘潭县/000湘潭县乡镇列表.json";
		JSONObject townFileJson = JSON.parseObject(FileUtils.readFileToString(new File(townListFilePath), "UTF-8"));
		
		String city = townFileJson.getString("city");
		String area = townFileJson.getString("area");
		
		for (Object object : townFileJson.getJSONArray("town_list")) {
			JSONObject townJsonObject = (JSONObject)object;
			String center = townJsonObject.getString("town");
			boolean is_gov_resident = townJsonObject.getBooleanValue("is_gov_resident");
			String centerGps = townJsonObject.getString("center_point").trim();
			String[] split = centerGps.split(",");
			//格式为"纬度,经度"
			if (Float.valueOf(split[0]) > 100.0) {
				centerGps = split[1] + "," + split[0];
			}
			
			String basePath = "/Users/huangjiong/hjdb/develop/Projects/led-advertiser/src/main/resources/广告商-新";
			String dirPath = basePath + "/" + city + "/" + area;
			File dir = new File(dirPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			
			Map<String, String> param = new HashMap<>();
			param.put("center", center);
			param.put("center_gps", centerGps);
			param.put("keywords", StringUtils.join(KEYWORDS, ","));
			
			BdPoiCircleInputModel circleInputModel = new BdPoiCircleInputModel();
			String query = StringUtils.join(KEYWORDS, "$");
			circleInputModel.setQuery(URLEncoder.encode(query, "UTF-8"));
			circleInputModel.setLocation(URLEncoder.encode(centerGps, "UTF-8"));
			//普通乡镇搜索半径为4公里, 县政府驻地所在乡镇的搜索半径为10公里
			circleInputModel.setRadius(is_gov_resident ? "8000" : "4000");
			circleInputModel.setRadius_limit(Boolean.TRUE.toString());
			circleInputModel.setOutput("json");
			circleInputModel.setScope("2");//2表示返回详情
			//按距离排序之后,导致每页返回的数据条数都不对了.
//			circleInputModel.setFilter(URLEncoder.encode("sort_name:distance|sort_rule:1", "UTF-8"));
			Integer pageSize = 10;
			Integer pageNum = 0;
			circleInputModel.setPage_size(String.valueOf(pageSize));
			circleInputModel.setPage_num(String.valueOf(pageNum));
			circleInputModel.setAk(Constant.BD_AK);
			String baseUrl = "http://api.map.baidu.com/place/v2/search";
			String url = null;
			
			OkHttpClient okHttpClient = new OkHttpClient();
			boolean hasMore = true;
			while (hasMore) {
				System.out.println("防止超过并发先等待0.5秒");
				Thread.sleep(500);
				System.out.println("=====================");
				System.out.println("准备请求" + "center第" + circleInputModel.getPage_num() + "页");
				url = baseUrl + "?" + UrlUtils.joinParams(JSON.parseObject(JSON.toJSONString(circleInputModel)));
				final Request request = new Request.Builder()
						.url(url)
						.get()
				        .build();
				final Call call = okHttpClient.newCall(request);
				try {
					Response response = call.execute();
					String bodyString = response.body().string();
					JSONObject parseObject = JSON.parseObject(bodyString);
					if (parseObject.getIntValue("status") == 401) {
						//超过并发
						System.err.println("超过并发等待1.5秒再继续");
						Thread.sleep(1500);
						//TODO 应该使用本次参数重新请求一次
						continue;
					}
					
					if (parseObject.getInteger("status") != 0) {
						System.err.println("请求返回失败" + JSON.toJSONString(parseObject));
					}
					
					Integer total = parseObject.getInteger("total");
					System.out.println("总条数:" + total);
					if (total == null || total < 1) {
						break;
					}

					JSONArray results = parseObject.getJSONArray("results");
					if (results != null) {
						System.out.println("本次条数:" + results.size());
						//json文件编号
						String fileNum = null;
						if (pageNum + 1 < 10) {
							fileNum = "0" + (pageNum + 1);
						} else {
							fileNum = "" + (pageNum + 1);
						}
						//是否还有下一页数据
						if (pageSize * pageNum + results.size() < total) {
							hasMore = true;
							pageNum++;
							circleInputModel.setPage_num(String.valueOf(pageNum));
						} else {
							hasMore = false;
						}
						
						if (results.size() > 0) {
							
							for (int i = results.size()-1; i >= 0; i--) {
								JSONObject jsonObject = (JSONObject)(results.get(i));
								if (!city.equals(jsonObject.getString("city")) 
										|| !area.equals(jsonObject.getString("area"))) {
									results.remove(i);
								}
							}
							
							parseObject.put("results", results);
							//写入到json文件
							parseObject.put("param", param);
//							System.out.println(JSON.toJSONString(parseObject, true));
							File poiJsonFile = new File(dir, center + fileNum + ".json");
							try {
								FileUtils.write(poiJsonFile , JSON.toJSONString(parseObject, true), "UTF-8");
								System.out.println("文件写入成功:" + poiJsonFile.getName());
							} catch (Exception e) {
								// TODO: handle exception
								System.err.println("文件写入失败:" + poiJsonFile.getName());
							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		

//		new Thread(new Runnable() {
//		    @Override
//		    public void run() {
//		    }
//		}).start();
	}
	
	/**
	 * 获取方形区域内的poi数据
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Test
	void getBdPoiFromSquareTest() throws InterruptedException, IOException  {
		String city = "湘潭市";
		String area = "雨湖区";
		String north = "112.787563,28.079640";
		String south = "112.892464,27.805600";
		String west = "112.655458,27.846866";
		String east = "113.014482,27.957942";
		
		//json文件路径
		String basePath = "/Users/huangjiong/hjdb/develop/Projects/led-advertiser/src/main/resources/广告商-新";
		String dirPath = basePath + "/" + city + "/" + area;
		File dir = new File(dirPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		int fileNum = 0;
 
		//分小块搜索
		List<String> boundList = BdMapUtils.getPoiBoundList(south, north, west, east);
		for (String bound : boundList) {
			Integer pageSize = 20;
			Integer pageNum = -1;
			BdPoiSquareInputModel squareInputModel = new BdPoiSquareInputModel();
			String query = StringUtils.join(KEYWORDS, "$");
			squareInputModel.setQuery(URLEncoder.encode(query, "UTF-8"));
			squareInputModel.setBounds(URLEncoder.encode(bound, "UTF-8"));
			squareInputModel.setOutput("json");
			squareInputModel.setScope("2");
			squareInputModel.setPage_size(String.valueOf(pageSize));
			squareInputModel.setPage_num(String.valueOf(pageNum));
			squareInputModel.setAk(Constant.BD_AK);
			
			String baseUrl = "http://api.map.baidu.com/place/v2/search";
			String url = null;
			//每一小块区域内的所有数据都写入同一个文件中
			List<JSONObject> poiBoundList = new ArrayList<>();
			int poiCountTotal = 0;
			while (true) {
				Thread.sleep(500);
				System.out.println("==============");
				pageNum += 1;
				System.out.println("第" + pageNum + "页");
				squareInputModel.setPage_num(String.valueOf(pageNum));
				url = baseUrl + "?" + UrlUtils.joinParams(JSON.parseObject(JSON.toJSONString(squareInputModel)));
				String resultString = doGet(url);
				JSONObject parseObject = JSON.parseObject(resultString);
				Integer status = parseObject.getInteger("status");
				if (401 == status) {
					//超过并发
					System.err.println("超过并发等待1.5秒再继续");
					Thread.sleep(1500);
					pageNum -= 1; //页数复位
					continue;
				}
				
				JSONArray jsonArray = parseObject.getJSONArray("results");
				if (jsonArray == null || jsonArray.size() == 0) {
					//没有下一页数据了
					break;
					//TODO 第二次没有数据返回时才结束
				} else {
					int size = jsonArray.size();
					poiCountTotal += size;
					for(int i = jsonArray.size() - 1; i >= 0; i--) {
						JSONObject poiJsonObject = (JSONObject) jsonArray.get(i);
						if (!city.equals(poiJsonObject.getString("city")) || !area.equals(poiJsonObject.getString("area"))) {
							jsonArray.remove(i);
						} else {
							//获取相册
//							poiJsonObject.put("photo_album", getPhotoAlbumFromPrivateTest(poiJsonObject.getString("uid")));
							poiBoundList.add(poiJsonObject);
						}
					}
				}
			}
			//将每个小块区域的poi数据写入文件
			if (poiBoundList.size() > 0) {
				//本次bound内的请求参数及结果
				Map<String, Object> poiParam = new HashMap<>();
				poiParam.put("bounds", bound);//左下角纬度经度-右上角纬度经度
				poiParam.put("keywords", StringUtils.join(KEYWORDS, ","));
				poiParam.put("city", city);
				poiParam.put("area", area);
				//
				Map<String, Object> poiResult = new HashMap<>();
				poiResult.put("poi_count_total", poiCountTotal);
				poiResult.put("poi_count_valid", poiBoundList.size());
				poiResult.put("poi_list", poiBoundList);
				//
				JSONObject datas = new JSONObject();
				datas.put("poi_param", poiParam);
				datas.put("poi_result", poiResult);
				fileNum += 1;
				String fileName = area + (fileNum < 10 ? "0" : "") + fileNum + ".json";
				File poiJsonFile = new File(dir, fileName);
				try {
					//TODO 把当前区域的数据写入文件
					FileUtils.write(poiJsonFile , JSON.toJSONString(datas, true), "UTF-8");
					System.out.println("文件写入成功:" + poiJsonFile.getName());
				} catch (Exception e) {
					System.err.println("文件写入失败:" + poiJsonFile.getName());
					throw e;
				}
			}
		}
	}
	
	
	/**
	 * 调用开放接口获取poi详情信息
	 * @throws UnsupportedEncodingException
	 */
	@Test
	void getPoiDetailTest() throws UnsupportedEncodingException  {
		
		
		String baseUrl = "http://api.map.baidu.com/place/v2/detail";
		
		//web服务接口说明: http://lbsyun.baidu.com/index.php?title=webapi/guide/webservice-placeapi
		//poi分类说明: http://lbsyun.baidu.com/index.php?title=lbscloud/poitags
		Map<String, String> urlParams = new HashMap<>();
		
		urlParams.put("uid", "b17c84566202f12708ccddca");
		urlParams.put("extensions_adcode", Boolean.TRUE.toString());
		urlParams.put("output", "json");
		urlParams.put("scope", "2");
		urlParams.put("ak", "Cw3L3kVmsEXWVgAXbeH4XTaGZgMYL5eZ");
		
		String url = UrlUtils.joinParams(baseUrl, urlParams);
//		url = URLEncoder.encode(url, "UTF-8");
		
		OkHttpClient okHttpClient = new OkHttpClient();
		final Request request = new Request.Builder()
				.url(url)
				.get()
		        .build();
		final Call call = okHttpClient.newCall(request);
		
		try {
			Response response = call.execute();
			String bodyString = response.body().string();
//			System.out.println(bodyString);
			JSONObject parseObject = JSON.parseObject(bodyString);
			System.out.println(JSON.toJSONString(parseObject, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
//		new Thread(new Runnable() {
//		    @Override
//		    public void run() {
//		    }
//		}).start();
	}
	
	/**
	 * 从json文件中导出
	 * @throws IOException 
	 * 
	 */
	@Test
	void exportBdPoiFromJsonFileTest() throws IOException  {
		File dir = new File("/Users/huangjiong/hjdb/develop/Projects/led-advertiser/src/main/resources/广告商-新/湘潭市/湘潭县");
		File[] listFiles = dir.listFiles();
		List<BdPoiModel> poiModelList = new ArrayList<>();
		for (File file : listFiles) {
			if (!file.getName().endsWith(".json")) {
				continue;
			}
			String readFileToString = FileUtils.readFileToString(file, "UTF-8");
			JSONObject parseObject = JSON.parseObject(readFileToString);
			JSONArray jsonArray = parseObject.getJSONArray("results");
			if (jsonArray != null && jsonArray.size() > 0) {
				List<BdPoiModel> javaList = jsonArray.toJavaList(BdPoiModel.class);
				poiModelList.addAll(javaList);
			}
		}
		
		String excelPath = "/Users/huangjiong/Desktop/xx.xlsx";
		ExcelUtils.writeWithTemplate(excelPath ,poiModelList);
	}
	
	/**
	 * 请求poi详情中的detail_url
	 * @throws IOException
	 */
	@Test
	void getDetailByUrlTest() throws IOException  {
		String url = "http://api.map.baidu.com/place/detail?uid=94d714e4eb8cbe36132935d6&output=html&source=placeapi_v2";
		OkHttpClient okHttpClient = new OkHttpClient();
		final Request request = new Request.Builder()
				.url(url)
				.get()
		        .build();
		final Call call = okHttpClient.newCall(request);
		
		try {
			Response response = call.execute();
	
			String bodyString = response.body().string();
			System.out.println(response.toString());
			System.out.println(bodyString);
			String path = "/Users/huangjiong/hjdb/develop/Projects/led-advertiser/target/classes/广告商-新/湘潭市/xx.html";
//			FileUtils.write(new File(path), bodyString, "UTF-8");
//			JSONObject parseObject = JSON.parseObject(bodyString);
//			System.out.println(JSON.toJSONString(parseObject, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private List<String> getPhotoAlbumFromPrivateTest(String uid) {
		
		List<String> photoAlbum = new ArrayList<>();
//		uid = "a16fec05828435f411882423";
		String baseUrl = "https://map.baidu.com";
		Map<String, String> urlParams = new HashMap<>();
//		String auth = "%40bX3x26f9cN%3DwaDQETYbxMKJZ0MHD7T0uxHTBBTxEEBtComRB199Ay1uVt1GgvPUDZYOYIZuEt2gz4yYxGccZcuVtPWv3Guzxt58Jv7uUvhgMZSguxzBEHLNRTVtcEWe1GD8zv7u%40ZPuTtk1dK84yDF2CpFWEkmCimB14822WQ148AwAYYK53u%3D%3D8x1";
		urlParams.put("uid", uid);
//		urlParams.put("auth", auth);
		urlParams.put("ugc_type", "3");
		urlParams.put("ugc_ver", "1");
		urlParams.put("qt", "detailConInfo");
		urlParams.put("device_ratio", "2");
		urlParams.put("compat", "1");
		urlParams.put("t", String.valueOf(System.currentTimeMillis()));
		String url = UrlUtils.joinParams(baseUrl, urlParams);
		/**
		 * https://map.baidu.com/
			?uid=03c79add94190a90d13ce30a
			&ugc_type=3
			&ugc_ver=1
			&qt=detailConInfo
			&device_ratio=2
			&compat=1
			&t=1593391457543
			&auth=%40bX3x26f9cN%3DwaDQETYbxMKJZ0MHD7T0uxHTBBTxEEBtComRB199Ay1uVt1GgvPUDZYOYIZuEt2gz4yYxGccZcuVtPWv3Guzxt58Jv7uUvhgMZSguxzBEHLNRTVtcEWe1GD8zv7u%40ZPuTtk1dK84yDF2CpFWEkmCimB14822WQ148AwAYYK53u%3D%3D8x1
		 */
		OkHttpClient okHttpClient = new OkHttpClient();
		final Request request = new Request.Builder()
				.url(url)
				.get()
		        .build();
		final Call call = okHttpClient.newCall(request);
		try {
			Response response = call.execute();
			String bodyString = response.body().string();
			JSONObject parseObject = JSON.parseObject(bodyString);
			//content -> ext -> detail_info -> vs_content -> invisible -> bigdata -> photo_album[] -> url
			JSONArray jsonArray = parseObject.getJSONObject("content")
			.getJSONObject("ext")
			.getJSONObject("detail_info")
			.getJSONObject("vs_content")
			.getJSONObject("invisible")
			.getJSONObject("bigdata")
			.getJSONArray("photo_album");
			for (Object object : jsonArray) {
				JSONObject jsonObject = (JSONObject) object;
				String imgUrl = jsonObject.getString("url");
				photoAlbum.add(imgUrl);
			}
		} catch (Exception e) {
			System.out.println(uid + "获取相册失败:" + e.getMessage() + ", " + e.getLocalizedMessage());
		}
		
		return photoAlbum;
	}
	
	private String doGet(String url) {
		OkHttpClient okHttpClient = new OkHttpClient();
		final Request request = new Request.Builder()
				.url(url)
				.get()
		        .build();
		final Call call = okHttpClient.newCall(request);
		try {
			Response response = call.execute();
			String bodyString = response.body().string();
			return bodyString;
		} catch (Exception e) {
			System.err.println("GET请求异常-> " + url);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 分割湖南省边界数据
	 * @throws IOException
	 */
	@Test
	public void splitHunanBoundsTest() throws IOException {
		//湖南省边界
		String north = "110.832899,30.135414";
		String south = "111.541195,24.630605";
		String west = "108.804597,27.077474";
		String east = "114.273193,28.317934";
		//共75624条数据
		List<String> poiBoundList = BdMapUtils.getPoiBoundList(south, north, west, east);
//		List<String> poiBoundList = Arrays.asList("1", "2", "3", "4");
		int total = poiBoundList.size();
		int fromIndex = 0;
		int perSize = 1000;
		int fileNum = 1;
		while (true) {
			boolean breakFlag = false;
			int toIndex = fromIndex + perSize;
			if (toIndex >= total) {
				breakFlag = true;
				toIndex = total;
			}
			List<String> subList = poiBoundList.subList(fromIndex, toIndex);
			JSONObject jsonObject = new JSONObject(true);
			jsonObject.put("desc", "湖南省");
			jsonObject.put("finished", false);
			jsonObject.put("bounds", subList);
			
			
			String dir = "/Users/huangjiong/hjdb/develop/Projects/led-advertiser/src/main/resources/广告商-新/00湖南省边界分割";
			String name = "bounds" + String.format("%03d", fileNum) + ".json";
			File file = new File(dir, name);
			System.out.println(JSON.toJSONString(subList));
			FileUtils.writeStringToFile(file, JSON.toJSONString(jsonObject, true), "UTF-8");
			if (breakFlag) {
				break;
			} else {
				fileNum += 1;
				fromIndex = toIndex;
			}
		}
		
		System.out.println("分割湖南省边界数据成功");
	}
	
}
