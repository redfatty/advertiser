package com.hj.advertiser.service;

import java.io.IOException;
import java.util.List;

import com.hj.advertiser.base.pojo.ResultModel;
import com.hj.advertiser.model.AdvertiserModel;
import com.hj.advertiser.model.UpdateAdvertiserImgPhoneInputModel;

public interface AdvertiserService {
	
	public List<AdvertiserModel> selectAdvertiserTest();

	public ResultModel updateImgTel(UpdateAdvertiserImgPhoneInputModel inputModel);
	
	/**
	 * 获取本地保存的广告商信息
	 * @return
	 * @throws IOException 
	 */
	public List<AdvertiserModel> getAdvertiserListFromLocal() throws IOException;
	
	/**
	 * 插入一条广告商信息
	 * @param advertiserModel
	 * @return
	 */
	public ResultModel insertAdvertiser(AdvertiserModel advertiserModel);
	
	/**
	 * 更新广告商信息
	 * @param advertiserModel
	 * @return
	 */
	public ResultModel updateSelectiveByBdUid(AdvertiserModel advertiserModel);
	
	/**
	 * 插入或更新一条广告商信息
	 * @param advertiserModel
	 * @return
	 */
	public ResultModel insertOrUpdateAdvertiser(AdvertiserModel advertiserModel);

}
