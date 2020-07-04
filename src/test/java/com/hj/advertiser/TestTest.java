package com.hj.advertiser;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hj.advertiser.model.AdvertiserModel;
import com.hj.advertiser.util.ExcelUtils;
import com.hj.advertiser.util.UnicodeUtils;
import com.hj.advertiser.util.UrlUtils;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class TestTest {

	@Test
	void test() {
//		fail("Not yet implemented");
		System.out.println(new Date(1593355370341L));
		System.out.println(JSON.toJSONString(new Date(1592299464000L), true));
	}
	
	
	@Test
	void exportAdvertiserExcelTest() throws IOException {
		String changShaPath = "/Users/huangjiong/hjdb/develop/Projects/led-advertiser/src/main/resources/static/adJson/长沙";
		File changShaDir = new File(changShaPath);
		File[] listFiles = changShaDir.listFiles();
		List<AdvertiserModel> advertiserList = new ArrayList<>();
		for (File file : listFiles) {
			String string = FileUtils.readFileToString(file, "UTF-8");
			JSONObject parseObject = JSON.parseObject(string);
			String keywords = parseObject.getJSONObject("result").getString("wd");
			String auth = parseObject.getJSONObject("result").getString("auth");
			JSONArray jsonArray = parseObject.getJSONArray("content");
			for (Object object : jsonArray) {
				JSONObject jsonObject = (JSONObject) object;
				AdvertiserModel advertiserModel = new AdvertiserModel();
				try {
					advertiserModel.init(jsonObject, keywords);
				} catch (Exception e) {
					System.out.println(jsonObject.getString("uid"));
					System.out.println("");
				}
				advertiserList.add(advertiserModel);
			}
		}
		
		for (AdvertiserModel advertiserModel : advertiserList) {
			System.out.println(JSON.toJSONString(advertiserModel, true));
		}

		//调用广告商详情接口, 获取图片
		List<FutureTask<Object>> futureList = new ArrayList<>(advertiserList.size());
//		for (AdvertiserModel advertiserModel : advertiserList) {
//			
//			if (advertiserList.indexOf(advertiserModel) == 10) {
//				break;
//			}
//			
//			
////			url = URLEncoder.encode(url, "UTF-8");
//			/**
//			 * https://map.baidu.com/
//				?uid=03c79add94190a90d13ce30a
//				&ugc_type=3
//				&ugc_ver=1
//				&qt=detailConInfo
//				&device_ratio=2
//				&compat=1
//				&t=1593391457543
//				&auth=%40bX3x26f9cN%3DwaDQETYbxMKJZ0MHD7T0uxHTBBTxEEBtComRB199Ay1uVt1GgvPUDZYOYIZuEt2gz4yYxGccZcuVtPWv3Guzxt58Jv7uUvhgMZSguxzBEHLNRTVtcEWe1GD8zv7u%40ZPuTtk1dK84yDF2CpFWEkmCimB14822WQ148AwAYYK53u%3D%3D8x1
//			 */
//			
//			OkHttpClient okHttpClient = new OkHttpClient();
//			final Request request = new Request.Builder()
//					.url("")
//					.get()
//			        .build();
//			final Call call = okHttpClient.newCall(request);
//			try {
//				Response response = call.execute();
//				String bodyString = response.body().string();
////				System.out.println(bodyString);
//				JSONObject parseObject = JSON.parseObject(bodyString);
//				//content -> ext -> detail_info -> vs_content -> invisible -> bigdata -> photo_album[] -> url
//				JSONArray jsonArray = parseObject.getJSONObject("content")
//				.getJSONObject("ext")
//				.getJSONObject("detail_info")
//				.getJSONObject("vs_content")
//				.getJSONObject("invisible")
//				.getJSONObject("bigdata")
//				.getJSONArray("photo_album");
//				if (jsonArray != null) {
//					for (Object object : jsonArray) {
//						JSONObject jsonObject = (JSONObject) object;
//						String imgUrl = jsonObject.getString("url");
//						System.out.println(imgUrl);
//					}
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
////			new Thread(new Runnable() {
////			    @Override
////			    public void run() {
////			    }
////			}).start();
//			
////			FutureTask<Object> task = new FutureTask<>(()-> {
////				return null;
////			});
////			futureList.add(task);
//		}
		
		//异步执行所有获取广告商详情信息
		//注: 需要等到每家广告商的信息都获取到之后才往继续往下走
//		for (FutureTask<Object> futureTask : futureList) {
//			try {
//				futureTask.get();
//			} catch (InterruptedException | ExecutionException e) {
//				e.printStackTrace();
//			}
//		}
		
		String filePath = "/Users/huangjiong/Desktop/xx.xlsx";
		ExcelUtils.writeWithTemplate(filePath ,advertiserList);
		
	}
	
	
	@Test
	void getAdvertiserDetailTest() throws UnsupportedEncodingException  {
		String url = "https://map.baidu.com/?uid=3a194c3fc687fb08d4851f6e&ugc_type=3&ugc_ver=1&qt=detailConInfo&device_ratio=2&compat=1&t=1593355370341&auth=5XvI2FNFbVbz1YDRDvHESbS%40Lv74BNCUuxHTBBHHzEHtComRB199Ay1uVt1GgvPUDZYOYIZuxtdw8E62qvFu2gz4yYxGccZcuVtPWv3GuBLt%40jUIgHUvhgMZSguxzBEHLNRTVtcEWe1GD8zv7u%40ZPuxEtr2%3DGlnDjnCENZHHKKXRRBjnOOAEZzrZZWuN";
		url = "https://map.baidu.com/?uid=03c79add94190a90d13ce30a&ugc_type=3&ugc_ver=1&qt=detailConInfo&device_ratio=2&compat=1&t=1593386253632&auth=%40bX3x26f9cN%3DwaDQETYbxMKJZ0MHD7T0uxHTBBRLzzTtzljPyBYYxy1uVt1GgvPUDZYOYIZuVt1cv3uVtGccZcuVtPWv3GuztQZ3wWvUvhgMZSguxzBEHLNRTVtcEWe1GD8zv7u%40ZPuLtjAJzhjzgjyBKWEEUOBKWxwAYYK53fy9GUIsxA3wFkk0H3";
		
		String baseUrl = "https://map.baidu.com";
		Map<String, String> urlParams = new HashMap<>();
		String uid = "03c79add94190a90d13ce30a";
		String auth = "%40bX3x26f9cN%3DwaDQETYbxMKJZ0MHD7T0uxHTBBTxEEBtComRB199Ay1uVt1GgvPUDZYOYIZuEt2gz4yYxGccZcuVtPWv3Guzxt58Jv7uUvhgMZSguxzBEHLNRTVtcEWe1GD8zv7u%40ZPuTtk1dK84yDF2CpFWEkmCimB14822WQ148AwAYYK53u%3D%3D8x1";
		urlParams.put("uid", uid);
		urlParams.put("auth", auth);
		urlParams.put("ugc_type", "3");
		urlParams.put("ugc_ver", "1");
		urlParams.put("qt", "detailConInfo");
		urlParams.put("device_ratio", "2");
		urlParams.put("compat", "1");
		urlParams.put("t", String.valueOf(System.currentTimeMillis()));
		
		url = UrlUtils.joinParams(baseUrl, urlParams);
//		url = URLEncoder.encode(url, "UTF-8");
		
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
//			System.out.println(bodyString);
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
				System.out.println(imgUrl);
			}
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
	 * 将本地的百度搜索结果中的unicode转为中文
	 */
	@Test
	void bdResultUnicode2Cn() {
		
		String changShaPath = "/Users/huangjiong/hjdb/develop/Projects/led-advertiser/src/main/resources/static/adJson/长沙";
		File changShaDir = new File(changShaPath);
		File[] listFiles = changShaDir.listFiles();
		for (File file : listFiles) {
			String string = null;
			try {
				string = FileUtils.readFileToString(file, "UTF-8");
				string = UnicodeUtils.unicodeToString(string);
				FileUtils.write(file, string, "UTF-8");
			} catch (IOException e) {
				e.printStackTrace();
			}
//			String string2 = "[[\"\广\告\公\司\"], [\"\"], \"\", 0, \"\"]";
			String string3  = "[[\"广告公司\"], [\"\"], \"\", 0, \"\"]";
			
			System.out.println("完成: " + file.getName());
		}
		
		System.out.println("全部完成");
	}


}
