package com.hj.advertiser.dao;

import java.util.List;

import com.hj.advertiser.model.AdvertiserModel;
import com.hj.advertiser.model.UpdateAdvertiserImgPhoneInputModel;

public interface AdvertiserMapper {
	
	public List<AdvertiserModel> selectAdvertiserTest();

	public int updateImgTel(UpdateAdvertiserImgPhoneInputModel inputModel);
	
	/**
	 * 插入一条广告商信息
	 * @param advertiserModel
	 * @return
	 */
	public int insertAdvertiser(AdvertiserModel advertiserModel);
}
