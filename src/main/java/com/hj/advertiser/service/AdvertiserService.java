package com.hj.advertiser.service;

import java.util.List;

import com.hj.advertiser.base.pojo.ResultModel;
import com.hj.advertiser.model.AdvertiserModel;
import com.hj.advertiser.model.UpdateAdvertiserImgPhoneInputModel;

public interface AdvertiserService {
	
	public List<AdvertiserModel> selectAdvertiserTest();

	public ResultModel updateImgPhone(UpdateAdvertiserImgPhoneInputModel inputModel);

}
