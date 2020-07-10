package com.hj.advertiser.model;

import java.util.Date;

/**
 * 搜索关键字
 * @author huangjiong
 *
 */
public class SearchKeywords {

	private Long searchKeywordsId;
	
	private String keywords;
	private String createdBy;
	private String updatedBy;
	private Date createdTime;
	private Date updatedTime;
	
	public Long getSearchKeywordsId() {
		return searchKeywordsId;
	}
	public void setSearchKeywordsId(Long searchKeywordsId) {
		this.searchKeywordsId = searchKeywordsId;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
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
