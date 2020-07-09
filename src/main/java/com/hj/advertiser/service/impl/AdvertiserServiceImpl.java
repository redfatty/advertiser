package com.hj.advertiser.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.FutureTask;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hj.advertiser.base.constant.AdvertiserTag;
import com.hj.advertiser.base.pojo.ResultModel;
import com.hj.advertiser.dao.AdvertiserMapper;
import com.hj.advertiser.model.AdvertiserModel;
import com.hj.advertiser.model.UpdateAdvertiserImgPhoneInputModel;
import com.hj.advertiser.service.AdvertiserService;
import com.hj.advertiser.util.AdTagUtils;

@Service
public class AdvertiserServiceImpl implements AdvertiserService {
	
	@Autowired
	private AdvertiserMapper advertiserMapper;

	@Override
	public List<AdvertiserModel> selectAdvertiserTest() {
		return advertiserMapper.selectAdvertiserTest();
	}

	@Override
	public ResultModel updateImgTel(UpdateAdvertiserImgPhoneInputModel inputModel) {
		int rows = advertiserMapper.updateImgTel(inputModel);
		String bdUid = inputModel.getBdUid();
		String imgTel = inputModel.getImgTel();
		//更新本地json文件
		try {
			ClassPathResource classPathResource = new ClassPathResource("广告商");
			List<File> adFileList = (List<File>) FileUtils.listFiles(classPathResource.getFile(), new String[]{"json"}, true);
			for (File file : adFileList) {
				String string = null;
				boolean didUpadate = false;
				try {
					string = FileUtils.readFileToString(file, "UTF-8");
				} catch (IOException e1) {
					e1.printStackTrace();
					continue;
				}
				JSONObject fileJsonObject = JSON.parseObject(string);
				JSONArray jsonArray = fileJsonObject.getJSONArray("content");
				for (Object object : jsonArray) {
					JSONObject adJsonObject = (JSONObject) object;
					if (bdUid.equals(adJsonObject.getString("uid"))) {
						//更新
						adJsonObject.put("imgTel", imgTel);
						didUpadate = true;
					}
				}
				if (didUpadate) {
					fileJsonObject.put("content", jsonArray);
					//重新写入
					FileUtils.write(file, JSON.toJSONString(fileJsonObject, SerializerFeature.PrettyFormat, SerializerFeature.SortField, SerializerFeature.MapSortField), "UTF-8");
				}
			}
		} catch (Exception e) {
			
		}
		return new ResultModel(rows > 0 ? 1 : 9999, "更新图片电话失败", null);
	}

	/**
	 * 获取本地保存的广告商信息
	 * @return
	 * @throws IOException 
	 */
	@Override
	public List<AdvertiserModel> getAdvertiserListFromLocal() throws IOException {
		ClassPathResource classPathResource = new ClassPathResource("广告商");
		File adJsonDir = classPathResource.getFile();
		List<File> listFiles = (List<File>)FileUtils.listFiles(adJsonDir, new String[]{"json"}, true);
		List<AdvertiserModel> advertiserList = new ArrayList<>();
		for (File file : listFiles) {
			String string = null;
			try {
				string = FileUtils.readFileToString(file, "UTF-8");
			} catch (IOException e1) {
				e1.printStackTrace();
				continue;
			}
			JSONObject parseObject = JSON.parseObject(string);
			String keywords = parseObject.getJSONObject("result").getString("wd");
			String auth = parseObject.getJSONObject("result").getString("auth");
			JSONArray jsonArray = parseObject.getJSONArray("content");
			for (Object object : jsonArray) {
				JSONObject jsonObject = (JSONObject) object;
				if (!AdTagUtils.validateTags(jsonObject.getString("name"),//有些标签不相符, 但名称包含广告等词语
						jsonObject.getString(AdvertiserTag.KEY_TAG),
						jsonObject.getString(AdvertiserTag.KEY_DI_TAG),
						jsonObject.getString(AdvertiserTag.KEY_STD_TAG))) {
					//不是广告商
					System.out.println("不是广告商: " + jsonObject.getString("name") + "," + jsonObject.getString("uid"));
					continue;
				}
				try {
					AdvertiserModel advertiserModel = new AdvertiserModel();
					advertiserModel.init(jsonObject, keywords, auth);
					advertiserList.add(advertiserModel);
				} catch (Exception e) {
					System.out.println("初始化广告商数据模型出错: " + jsonObject.getString("name") + ", " + jsonObject.getString("uid"));
				}
			}
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
		return advertiserList;
	}

	/**
	 * 插入一条广告商信息
	 * @param advertiserModel
	 * @return
	 */
	@Override
	public ResultModel insertAdvertiser(AdvertiserModel advertiserModel) {
		ResultModel resultModel = new ResultModel<>();
		int rows = 0;
		try {
			rows = advertiserMapper.insertAdvertiser(advertiserModel);
			if (rows > 0) {
				resultModel.setCode(1);
				resultModel.setMsg("添加成功");
			} else {
				resultModel.setCode(0);
				resultModel.setMsg("添加失败");
			}
		} catch (DuplicateKeyException e) {
			resultModel.setCode(0);
			resultModel.setMsg("广告商已经存在:" + advertiserModel.getBdUid() + ", " + ExceptionUtils.getStackTrace(e));
		} catch (Exception e) {
			resultModel.setCode(0);
			resultModel.setMsg("广告商添加异常:" + advertiserModel.getBdUid() + ", " + e.getMessage());
			System.err.println("广告商添加异常:" + advertiserModel.getBdUid() + ", " + ExceptionUtils.getStackTrace(e));
		}
		return resultModel;
	}
	

	@Override
	public ResultModel updateSelectiveByBdUid(AdvertiserModel advertiserModel) {
		int rows = 0;
		try {
			rows = advertiserMapper.updateSelectiveByBdUid(advertiserModel);
		} catch (Exception e) {
			System.err.println(advertiserModel.getBdUid() + "更新失败:" + ExceptionUtils.getStackTrace(e));
		}
		
		ResultModel resultModel = new ResultModel<>();
		if (rows > 0) {
			resultModel.setCode(1);
			resultModel.setMsg("更新成功");
		} else {
			resultModel.setCode(2);
			resultModel.setMsg("更新失败");
		}
		return resultModel;
	}

	@Override
	public ResultModel insertOrUpdateAdvertiser(AdvertiserModel advertiserModel) {
		int count = advertiserMapper.selectCountByBdUid(advertiserModel);
		if (count > 0) {
			//更新
			return updateSelectiveByBdUid(advertiserModel);
		} else {
			//新增
			return insertAdvertiser(advertiserModel);
		}
	}

}
