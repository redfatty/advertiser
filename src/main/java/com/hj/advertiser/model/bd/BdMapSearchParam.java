package com.hj.advertiser.model.bd;

import java.io.Serializable;

public class BdMapSearchParam implements Serializable {
	private static final long serialVersionUID = 1157660511486240907L;
	private String keywords;

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
}
