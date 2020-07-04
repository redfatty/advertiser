package com.hj.advertiser.service.impl;

import org.springframework.stereotype.Service;

import com.hj.advertiser.base.pojo.ResultModel;
import com.hj.advertiser.model.bd.BdMapResultModel;
import com.hj.advertiser.model.bd.BdMapSearchParam;
import com.hj.advertiser.service.BdMapService;

@Service
public class BdMapServiceImpl implements BdMapService {

	/**
	 * 从本地获取百度地图结果
	 * @return
	 */
	@Override
	public ResultModel<BdMapResultModel> getBdMapResultFromLocal() {
		return null;
	}

	/**
	 * 获取百度地图搜索结果
	 * @return
	 */
	@Override
	public ResultModel<BdMapResultModel> searchBdMap(BdMapSearchParam param) {
		return null;
	}

}
