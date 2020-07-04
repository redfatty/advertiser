package com.hj.advertiser.util;

import com.alibaba.fastjson.JSONObject;

public class FastJsonUtils {
	public static JSONObject getJsonObj(JSONObject srcJsonObj, String key) {
		JSONObject jsonObject = srcJsonObj.getJSONObject(key);
		return jsonObject == null ? new JSONObject() : jsonObject;
	}

}
