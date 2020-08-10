package com.hj.advertiser.model.bd;

import java.util.List;
import java.util.Map;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.fastjson.JSONObject;

public class BdPoiModel extends  BaseRowModel {
	
	private String uid;
	private String province;
	@ExcelProperty("市")
	private String city;
	@ExcelProperty("区/县")
	private String area;
	@ExcelProperty("名称")
	private String name;
	@ExcelProperty("地址")
	private String address;
	@ExcelProperty("电话")
	private String telephone;
	@ExcelProperty("门头电话")
	private String telephone_image;//图片中的门头电话
	private Map<String, Object> location;
	
	private JSONObject detail_info;
	//以下信息在详情detail_info里面
	@ExcelProperty("地图链接")
	private String detail_url;
//	@ExcelProperty("距离")
	private Long distance;
	private String tag;
	private String type;
	private Integer image_num;
	
	//相册, 需要调另外一个接口获取
	private List<String> photo_album;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getTelephone_image() {
		return telephone_image;
	}
	public void setTelephone_image(String telephone_image) {
		this.telephone_image = telephone_image;
	}
	public Map<String, Object> getLocation() {
		return location;
	}
	public void setLocation(Map<String, Object> location) {
		this.location = location;
	}
	public String getDetail_url() {
		return detail_url;
	}
	public void setDetail_url(String detail_url) {
		this.detail_url = detail_url;
	}
	public Long getDistance() {
		return distance;
	}
	public void setDistance(Long distance) {
		this.distance = distance;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getImage_num() {
		return image_num;
	}
	public void setImage_num(Integer image_num) {
		this.image_num = image_num;
	}
	public List<String> getPhoto_album() {
		return photo_album;
	}
	public void setPhoto_album(List<String> photo_album) {
		this.photo_album = photo_album;
	}
	public Map<String, Object> getDetail_info() {
		return detail_info;
	}
	public void setDetail_info(JSONObject detail_info) {
		this.detail_info = detail_info;
		if (detail_info != null) {
			this.setDetail_url(detail_info.getString("detail_url"));
			this.setDistance(detail_info.getLong("distance"));
		}
	}

}
