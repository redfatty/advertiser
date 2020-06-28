package com.hj.advertiser.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 广告商数据模型
 * @author huangjiong
 *
 */
public class AdvertiserModel extends  BaseRowModel implements Serializable {
	private static final long serialVersionUID = -1235814356280938137L;
	
	private String uid;
	
	@ExcelProperty("名称")
	private String name;
	@ExcelProperty("电话")
	private String tel;
	@ExcelProperty("地址")
	private String addr; //简要地址信息
	private String address_norm; //详细地址信息, [湖南省(430000)|PROV|0|][长沙市(430100)|CITY|1|][雨花区(430111)|AREA|1|][朝晖路()|ROAD|1|]与怡园街交叉路口往西约100米(湖南华隆)
	@ExcelProperty("图片")
	private String displayImageUrl; //列表展示图片地址, ext -> detail_info -> image
	/**
	 * 市,区信息, city_id,city_name,area_id,area_name 
	 */
	private Map<String, Object> admin_info;
	private List<String> alias;
	private String aoi;
	private String di_tag;
	private Long navi_update_time;
	private JSONObject ext;
	private String detailUrl; //详情url
	private List<String> imageUrls; //详情页图片
	
	public void setExt(JSONObject ext) {
		this.setDisplayImageUrl(ext.getJSONObject("detail_info").getString("image"));
	}
	
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getAddress_norm() {
		return address_norm;
	}
	public void setAddress_norm(String address_norm) {
		this.address_norm = address_norm;
	}
	public Map<String, Object> getAdmin_info() {
		return admin_info;
	}
	public void setAdmin_info(Map<String, Object> admin_info) {
		this.admin_info = admin_info;
	}
	public List<String> getAlias() {
		return alias;
	}
	public void setAlias(List<String> alias) {
		this.alias = alias;
	}
	public String getAoi() {
		return aoi;
	}
	public void setAoi(String aoi) {
		this.aoi = aoi;
	}
	public String getDi_tag() {
		return di_tag;
	}
	public void setDi_tag(String di_tag) {
		this.di_tag = di_tag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getNavi_update_time() {
		return navi_update_time;
	}
	public void setNavi_update_time(Long navi_update_time) {
		this.navi_update_time = navi_update_time;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getDisplayImageUrl() {
		return displayImageUrl;
	}
	public void setDisplayImageUrl(String displayImageUrl) {
		this.displayImageUrl = displayImageUrl;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
		
		//
	}

	public List<String> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}

	public String getDetailUrl() {
		return detailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}
}
