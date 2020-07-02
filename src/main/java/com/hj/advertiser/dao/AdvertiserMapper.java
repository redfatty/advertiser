package com.hj.advertiser.dao;

import java.util.List;

import com.hj.advertiser.model.AdvertiserModel;
import com.hj.advertiser.model.UpdateAdvertiserImgPhoneInputModel;

public interface AdvertiserMapper {
	
	public List<AdvertiserModel> selectAdvertiserTest();

	public int updateImgPhone(UpdateAdvertiserImgPhoneInputModel inputModel);
}
