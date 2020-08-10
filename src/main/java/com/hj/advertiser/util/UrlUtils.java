package com.hj.advertiser.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * url工具类
 * @author huangjiong
 *
 */
public class UrlUtils {
	public static String joinParams(String url, Map<String, String> urlParams) {
//		url = url + "/?";
		url = url + "?";
		Iterator<Entry<String, String>> iterator = urlParams.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			url = url + entry.getKey() + "=" + entry.getValue() + "&";
		}
		url = url.substring(0, url.length() - 1);
		return url;
	}
	
	public static String joinParams(Map<String, Object> urlParams) {
		String urlfParamsStr = "";
		Iterator<Entry<String, Object>> iterator = urlParams.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			urlfParamsStr = urlfParamsStr + entry.getKey() + "=" + String.valueOf(entry.getValue()) + "&";
		}
		urlfParamsStr = urlfParamsStr.substring(0, urlfParamsStr.length() - 1);
		return urlfParamsStr;
	}
}
