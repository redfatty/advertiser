package com.hj.advertiser.model.bd;

import java.io.Serializable;
import java.util.List;

public class BdMapResultModel implements Serializable {
	private static final long serialVersionUID = 4790127506687981956L;

	private List<BdMapListItemModel> content;

	public List<BdMapListItemModel> getContent() {
		return content;
	}

	public void setContent(List<BdMapListItemModel> content) {
		this.content = content;
	}
	
}
