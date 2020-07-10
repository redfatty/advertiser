package com.hj.advertiser.model;

import java.util.Date;

/**
 * 搜索结果
 * @author huangjiong
 *
 */
public class SearchResult {
	private Long searchResultId;
	private Long searchActionId;
	private String bdUid;
	private String advertiserName;
	private Integer distance;
	private String createdBy;
	private Date createdTime;
	
	public Long getSearchResultId() {
		return searchResultId;
	}
	public void setSearchResultId(Long searchResultId) {
		this.searchResultId = searchResultId;
	}
	public Long getSearchActionId() {
		return searchActionId;
	}
	public void setSearchActionId(Long searchActionId) {
		this.searchActionId = searchActionId;
	}
	public String getBdUid() {
		return bdUid;
	}
	public void setBdUid(String bdUid) {
		this.bdUid = bdUid;
	}
	public String getAdvertiserName() {
		return advertiserName;
	}
	public void setAdvertiserName(String advertiserName) {
		this.advertiserName = advertiserName;
	}
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	} 
}
