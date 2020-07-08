package com.hj.advertiser;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hj.advertiser.base.constant.AdvertiserTag;
import com.hj.advertiser.base.pojo.ResultModel;
import com.hj.advertiser.model.AdvertiserModel;
import com.hj.advertiser.service.AdvertiserService;

@SpringBootTest
class LedAdvertiserApplicationTests {
	
	@Autowired
	private AdvertiserService advertiserService;

	@Test
	void contextLoads() {
	}
	
	@Test
	void foo() {
		List<AdvertiserModel> list = advertiserService.getAdvertiserListFromLocal();
		for (AdvertiserModel advertiserModel : list) {
			ResultModel resultModel = advertiserService.insertOrUpdateAdvertiser(advertiserModel);
			System.out.println(JSON.toJSONString(resultModel));
		}
	}
	
	

}
