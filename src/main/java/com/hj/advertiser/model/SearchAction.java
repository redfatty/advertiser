package com.hj.advertiser.model;

import java.util.Date;

/**
 * 搜索记录
 * @author huangjiong
 *
 */
public class SearchAction {

	private Long searchActionId;
	private Long searchCenterId;
	private Long searchKeywordsId;
	private String createdBy;
	private Date createdTime;
	
	public Long getSearchActionId() {
		return searchActionId;
	}
	public void setSearchActionId(Long searchActionId) {
		this.searchActionId = searchActionId;
	}
	public Long getSearchCenterId() {
		return searchCenterId;
	}
	public void setSearchCenterId(Long searchCenterId) {
		this.searchCenterId = searchCenterId;
	}
	public Long getSearchKeywordsId() {
		return searchKeywordsId;
	}
	public void setSearchKeywordsId(Long searchKeywordsId) {
		this.searchKeywordsId = searchKeywordsId;
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
