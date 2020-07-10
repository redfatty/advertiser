package com.hj.advertiser.util;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.hj.advertiser.base.constant.AdvertiserTag;

/**
 * 广告商标签工具类
 * @author huangjiong
 *
 */
public class AdTagUtils {
	public static boolean validateTags(String...tags) {
		if (tags == null || tags.length < 1) {
			return false;
		}
		
		for (String tagInput : tags) {
			if (tagInput == null || tagInput.length() < 1) {
				continue;
			}
			
			for (String tag : AdvertiserTag.ALL_TAGS) {
				if (tagInput.toUpperCase().indexOf(tag.toUpperCase()) > -1) {
					return true;
				}
			}
			
//			if (tag.indexOf(AdvertiserTag.GUANG_GAO) > -1
//					|| tag.indexOf(AdvertiserTag.TU_WEN) > -1
//					|| tag.indexOf(AdvertiserTag.KUAI_YIN) > -1
//					|| tag.indexOf(AdvertiserTag.FU_YIN) > -1
//					|| tag.indexOf(AdvertiserTag.DA_YIN) > -1
//					|| tag.indexOf(AdvertiserTag.WEN_HUA) > -1
//					|| tag.indexOf(AdvertiserTag.CHUAN_MEI) > -1
//					|| tag.indexOf(AdvertiserTag.ZHAO_PAI) > -1
//					|| tag.indexOf(AdvertiserTag.DEN_GUANG) > -1
//					|| tag.indexOf(AdvertiserTag.DEN_XIANG) > -1
//					|| tag.indexOf(AdvertiserTag.FA_GUANG) > -1
//					|| tag.indexOf(AdvertiserTag.XIAN_SHI_PING) > -1
//					|| tag.indexOf(AdvertiserTag.PING_MU) > -1
//					|| tag.indexOf(AdvertiserTag.DA_PING) > -1
//					|| tag.toUpperCase().indexOf(AdvertiserTag.LED.toUpperCase()) > -1) {
//				return true;
//			}
		}
		return false;
	}
	
}
