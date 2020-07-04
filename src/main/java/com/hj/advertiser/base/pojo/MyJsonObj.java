package com.hj.advertiser.base.pojo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class MyJsonObj {
	
	private JSONObject innerJsonObject;
	
	public MyJsonObj(JSONObject jsonObject) {
		super();
		this.innerJsonObject = jsonObject == null ? new JSONObject() : jsonObject;
	}
	
	public String getString(String key) {
		return innerJsonObject.getString(key);
	}
	
	public Object getObject(String key) {
		return innerJsonObject.get(key);
	}
	
	public MyJsonObj getJSONObject(String key) {
		return new MyJsonObj(innerJsonObject.getJSONObject(key));
	}
	
	public JSONArray getJSONArray(String key) {
		return innerJsonObject.getJSONArray(key);
	}
	
}
