package com.hj.advertiser.model;

import java.io.Serializable;

public class UpdateAdvertiserImgPhoneInputModel implements Serializable {
	private static final long serialVersionUID = 2198552330705175503L;
	private Long advertiserId;
	private String imgPhone;
	
	public Long getAdvertiserId() {
		return advertiserId;
	}
	public void setAdvertiserId(Long advertiserId) {
		this.advertiserId = advertiserId;
	}
	public String getImgPhone() {
		return imgPhone;
	}
	public void setImgPhone(String imgPhone) {
		this.imgPhone = imgPhone;
	}
	
}
