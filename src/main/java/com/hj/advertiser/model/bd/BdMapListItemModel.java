package com.hj.advertiser.model.bd;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 百度地图列表项目数据模型
 * @author huangjiong
 *
 */
public class BdMapListItemModel {
	private Long acc_flag;
	/**地址*/
	private String addr;
	//省, 市,区(县),道路信息, 例: [湖南省(430000)|PROV|0|][长沙市(430100)|CITY|0|][雨花区(430111)|AREA|0|][人民东路()|ROAD|1|276$]
	private String address_norm;
	
	//市,区(县)信息, {area_id: 430111, area_name: "雨花区", city_id: 430100, city_name: "长沙市"}
	private JSONObject admin_info;
	
	//店铺别称
	private List<String> alias;
	
	//地段信息, 德政园;高桥;人民东路
	private String aoi;
	
	//省市区信息, {area_code: 8138, area_name: "雨花区", city_code: 158, city_name: "长沙市", prov_code: 26, prov_name: "湖南省"}
	private JSONObject api_admin_info;
	
	//区域编码
	private String area;
	
	//区域名称, 长沙市雨花区
	private String area_name;
	
	//0
	private String biz_type;
	
	//null
	private String brand_id;
	
	//124
	private String catalogID;
	
//	private String xx;
//	private String xx;
//	private String xx;
//	private String xx;
//	private String xx;
//	private String xx;
}
