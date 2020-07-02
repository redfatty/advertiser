package com.hj.advertiser.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hj.advertiser.base.pojo.ResultModel;
import com.hj.advertiser.dao.AdvertiserMapper;
import com.hj.advertiser.model.AdvertiserModel;
import com.hj.advertiser.model.UpdateAdvertiserImgPhoneInputModel;
import com.hj.advertiser.service.AdvertiserService;

@Service
public class AdvertiserServiceImpl implements AdvertiserService {
	
	@Autowired
	private AdvertiserMapper advertiserMapper;

	@Override
	public List<AdvertiserModel> selectAdvertiserTest() {
		return advertiserMapper.selectAdvertiserTest();
	}

	@Override
	public ResultModel updateImgPhone(UpdateAdvertiserImgPhoneInputModel inputModel) {
		int rows = advertiserMapper.updateImgPhone(inputModel);
		return new ResultModel(rows > 0 ? 1 : 9999, "更新图片电话失败", null);
	}
}
