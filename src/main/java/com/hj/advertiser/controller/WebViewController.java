package com.hj.advertiser.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hj.advertiser.model.AdvertiserModel;
import com.hj.advertiser.service.AdvertiserService;
import com.hj.advertiser.util.UrlUtils;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Controller
public class WebViewController {
	
	@Autowired
	private AdvertiserService advertiserService;

	@RequestMapping("/webview")
	public ModelAndView home(ModelAndView mView) {
		mView.setViewName("index.html");
		Map<String, Object> page = new HashMap<>();
		page.put("list", this.getList());
		page.put("navigatepageNums", 1);
		page.put("pageNum", 1);
		page.put("pageSize", 1);
		mView.addObject("page", page);
		return mView;
	}

	/**
	 * @return
	 */
	/**
	 * @return
	 */
	private Object getList() {
		
		boolean debug = true;
		
		if (debug) {
			List<AdvertiserModel> selectAdvertiserTest = advertiserService.getAdvertiserListFromLocal();
			return selectAdvertiserTest;
		}
		
		
		String changShaPath = "/Users/huangjiong/hjdb/develop/Projects/led-advertiser/src/main/resources/static/adJson/长沙";
		File changShaDir = new File(changShaPath);
		File[] listFiles = changShaDir.listFiles();
		List<AdvertiserModel> advertiserList = new ArrayList<>();
		for (File file : listFiles) {
			String string = null;
			try {
				string = FileUtils.readFileToString(file, "UTF-8");
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			
			JSONObject parseObject = JSON.parseObject(string);
			String auth = parseObject.getJSONObject("result").getString("auth");
			JSONArray jsonArray = parseObject.getJSONArray("content");
			for (Object object : jsonArray) {
				((JSONObject)object).put("auth", auth);
			}
			advertiserList.addAll(jsonArray.toJavaList(AdvertiserModel.class));
		}
		
		

		//调用广告商详情接口, 获取图片
		List<FutureTask<Object>> futureList = new ArrayList<>(advertiserList.size());
		for (AdvertiserModel advertiserModel : advertiserList) {
			
//			advertiserModel.setImageUrls(new ArrayList<>());
//			advertiserModel.getImageUrls().add("https://taojin-his.cdn.bcebos.com/d6ca7bcb0a46f21f9c147c87fd246b600d33aec5.jpg");
//			advertiserModel.getImageUrls().add("https://taojin-his.cdn.bcebos.com/7a899e510fb30f24dc3fe23cc695d143ad4b0363.jpg");
//			advertiserModel.getImageUrls().add("https://taojin-his.cdn.bcebos.com/7c1ed21b0ef41bd56b79dcac5fda81cb39db3dbb.jpg");
//			advertiserModel.getImageUrls().add("https://taojin-his.cdn.bcebos.com/2fdda3cc7cd98d10d6d8aeab2d3fb80e7bec9018.jpg");
			
//			if (advertiserList.indexOf(advertiserModel) == 0) {
//				break;
//			}
//			
//			advertiserModel.setImageUrls(new ArrayList<>());
//			String url = "https://map.baidu.com/?uid=3a194c3fc687fb08d4851f6e&ugc_type=3&ugc_ver=1&qt=detailConInfo&device_ratio=2&compat=1&t=1593355370341&auth=5XvI2FNFbVbz1YDRDvHESbS%40Lv74BNCUuxHTBBHHzEHtComRB199Ay1uVt1GgvPUDZYOYIZuxtdw8E62qvFu2gz4yYxGccZcuVtPWv3GuBLt%40jUIgHUvhgMZSguxzBEHLNRTVtcEWe1GD8zv7u%40ZPuxEtr2%3DGlnDjnCENZHHKKXRRBjnOOAEZzrZZWuN";
//			url = "https://map.baidu.com/?uid=03c79add94190a90d13ce30a&ugc_type=3&ugc_ver=1&qt=detailConInfo&device_ratio=2&compat=1&t=1593386253632&auth=%40bX3x26f9cN%3DwaDQETYbxMKJZ0MHD7T0uxHTBBRLzzTtzljPyBYYxy1uVt1GgvPUDZYOYIZuVt1cv3uVtGccZcuVtPWv3GuztQZ3wWvUvhgMZSguxzBEHLNRTVtcEWe1GD8zv7u%40ZPuLtjAJzhjzgjyBKWEEUOBKWxwAYYK53fy9GUIsxA3wFkk0H3";
//			String baseUrl = "https://map.baidu.com";
//			Map<String, String> urlParams = new HashMap<>();
//			String uid = advertiserModel.getUid();
//			String auth = advertiserModel.getAuth();
//			urlParams.put("uid", advertiserModel.getUid());
//			urlParams.put("auth", advertiserModel.getAuth());
//			urlParams.put("ugc_type", "3");
//			urlParams.put("ugc_ver", "1");
//			urlParams.put("qt", "detailConInfo");
//			urlParams.put("device_ratio", "2");
//			urlParams.put("compat", "1");
//			urlParams.put("t", String.valueOf(System.currentTimeMillis()));
//			
//			url = UrlUtils.joinParams(baseUrl, urlParams);
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
//					.url(url)
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
//						advertiserModel.getImageUrls().add(imgUrl);
//					}
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			new Thread(new Runnable() {
//			    @Override
//			    public void run() {
//			    }
//			}).start();
			
//			FutureTask<Object> task = new FutureTask<>(()-> {
//				return null;
//			});
//			futureList.add(task);
		}
		
		//异步执行所有获取广告商详情信息
		//注: 需要等到每家广告商的信息都获取到之后才往继续往下走
//		for (FutureTask<Object> futureTask : futureList) {
//			try {
//				futureTask.get();
//			} catch (InterruptedException | ExecutionException e) {
//				e.printStackTrace();
//			}
//		}
		
		System.out.println(JSON.toJSONString(advertiserList, true));
		return advertiserList;
	}
}
