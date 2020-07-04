package com.hj.advertiser.service;

import com.hj.advertiser.base.pojo.ResultModel;
import com.hj.advertiser.model.bd.BdMapResultModel;
import com.hj.advertiser.model.bd.BdMapSearchParam;

/**
 * 百度地图业务层
 * @author huangjiong
 *
 */
public interface BdMapService {
	/**
	 * 从本地获取百度地图结果
	 * @return
	 */
	public ResultModel<BdMapResultModel> getBdMapResultFromLocal();
	
	/**
	 * 获取百度地图搜索结果
	 * @return
	 */
	public ResultModel<BdMapResultModel> searchBdMap(BdMapSearchParam param);
}
