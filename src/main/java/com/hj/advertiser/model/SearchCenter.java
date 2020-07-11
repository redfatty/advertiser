package com.hj.advertiser.model;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hj.advertiser.base.pojo.MyJsonObj;

/**
 * 搜索中心点
 * @author huangjiong
 *
 */
public class SearchCenter {
	
	private Long searchCenterId;
	private String centerBdUid;
	private String centerName;
	private String centerAddr;
	private String centerAddressNorm;
	private String centerAreaId;
	private String centerAreaName;
	private String centerCityId;
	private String createdBy;
	private String updatedBy;
	private Date createdTime;
	private Date updatedTime;
	
	public boolean init(MyJsonObj centerObj) {
		MyJsonObj poiJsonObj = null;
		try {
			JSONArray jsonArray = centerObj.getJSONArray("poi");
			if (jsonArray == null || jsonArray.size() == 0) {
				return false;
			}
			
			for (Object object : jsonArray) {
				if (object != null) {
					poiJsonObj = new MyJsonObj((JSONObject) object);
					break;
				}
			}
			if (poiJsonObj == null) {
				return false;
			}
			
			this.centerBdUid = poiJsonObj.getString("uid");
			this.centerName = poiJsonObj.getString("name");
			this.centerAddr = poiJsonObj.getString("addr");
			this.centerAddressNorm = poiJsonObj.getString("address_norm");
			this.centerAreaId = poiJsonObj.getString("area");
			this.centerAreaName = poiJsonObj.getString("area_name");
			this.centerCityId = poiJsonObj.getString("city_id");
			return true;
		} catch (Exception e) {
			if (poiJsonObj == null) {
				poiJsonObj = new MyJsonObj(null);
			}
			System.out.println("初始化搜索中心点错误," + poiJsonObj.getString("name") + "," + poiJsonObj.getString("uid") + "," + e.getMessage());
			return false;
		}
	}
	
	public Long getSearchCenterId() {
		return searchCenterId;
	}
	public void setSearchCenterId(Long searchCenterId) {
		this.searchCenterId = searchCenterId;
	}
	
	public String getCenterBdUid() {
		return centerBdUid;
	}

	public void setCenterBdUid(String centerBdUid) {
		this.centerBdUid = centerBdUid;
	}

	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getCenterAddr() {
		return centerAddr;
	}
	public void setCenterAddr(String centerAddr) {
		this.centerAddr = centerAddr;
	}
	public String getCenterAddressNorm() {
		return centerAddressNorm;
	}
	public void setCenterAddressNorm(String centerAddressNorm) {
		this.centerAddressNorm = centerAddressNorm;
	}
	public String getCenterAreaId() {
		return centerAreaId;
	}
	public void setCenterAreaId(String centerAreaId) {
		this.centerAreaId = centerAreaId;
	}
	public String getCenterAreaName() {
		return centerAreaName;
	}
	public void setCenterAreaName(String centerAreaName) {
		this.centerAreaName = centerAreaName;
	}
	public String getCenterCityId() {
		return centerCityId;
	}
	public void setCenterCityId(String centerCityId) {
		this.centerCityId = centerCityId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

}
