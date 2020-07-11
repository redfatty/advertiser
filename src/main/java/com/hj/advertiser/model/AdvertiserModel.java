package com.hj.advertiser.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hj.advertiser.base.pojo.MyJsonObj;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 广告商数据模型
 * @author huangjiong
 *
 */
@ExcelIgnoreUnannotated
public class AdvertiserModel extends  BaseRowModel implements Serializable {
	private static final long serialVersionUID = -1235814356280938137L;
	
	private Long advertiserId;
	
	//百度地图唯一标识, content[0]->uid
	@ExcelProperty("百度uid")
	private String bdUid;  
	
	//百度auth, result->auth
	private String bdAuth;
	
	//搜索关键字, result->wd
	private String keywords;
	
	//名称, content[0]->name
	@ExcelProperty("名称")
	private String name;
	
	//默认电话, content[0]->tel
	@ExcelProperty("默认电话")
	private String tel;
	
	//图片电话, 需要从图片中手动识别
	@ExcelProperty("图片电话")
	private String imgTel;
	
	//默认电话, content[0]->addr
	@ExcelProperty("地址")
	private String addr; //简要地址信息
	
	//详细地址信息, content[0]->address_norm
	private String addressNorm; //详细地址信息, [湖南省(430000)|PROV|0|][长沙市(430100)|CITY|1|][雨花区(430111)|AREA|1|][朝晖路()|ROAD|1|]与怡园街交叉路口往西约100米(湖南华隆)
	
	//默认图片, content[0]->ext->detail_info->image
//	@ExcelProperty("默认图片")
	private String defaultImgUrl;
	
	//相册图片集合, content[0]->ext->detail_info->vs_content->infisible->bigdata->photo_album[0]->url
	private String[] albumImgUrls;
	
	//省, content[0]->api_admin_info->prov_name
	private String provinceName;
	
	//市, content[0]->api_admin_info->city_name
	private String cityName;
	
	//区, content[0]->api_admin_info->area_name
	private String areaName;
	
	//通用省编码, 暂无
	private String provinceCode;
	
	//通用市编码, content[0]->admin_info->city_id
	private String cityCode;
	
	//通用区县编码, content[0]->admin_info->area_id
	private String areaCode;
	
	//百度省编码, content[0]->api_admin_info->prov_code
	private String provinceCodeBd;
	
	//通用市编码, content[0]->api_admin_info->city_code
	private String cityCodeBd;
	
	//通用区县编码, content[0]->api_admin_info->area_code
	private String areaCodeBd;
	
	//所属地段别称, content[0]->aoi
	private String aoi;
	
//	//标签, content[0]->di_tag
//	private String diTag;

	public void init(JSONObject bdItemObj, String keywords, String auth) {
		MyJsonObj myJsonObj = new MyJsonObj(bdItemObj);
		this.keywords = keywords;
		this.bdAuth = auth;
		this.bdUid = myJsonObj.getString("uid");
		this.name = myJsonObj.getString("name");
		this.tel = myJsonObj.getString("tel");
		this.imgTel = myJsonObj.getString("imgTel");
		this.addr = myJsonObj.getString("addr");
		this.addressNorm = myJsonObj.getString("address_norm");
		this.defaultImgUrl = myJsonObj.getJSONObject("ext").getJSONObject("detail_info").getString("image");
		// 有些数据有问题, bigdata应该是个对象, 有些错误的返回了一个空数组
		MyJsonObj invisible = myJsonObj.getJSONObject("ext")
				.getJSONObject("detail_info")
				.getJSONObject("vs_content")
				.getJSONObject("invisible");
		Object bigdata = invisible.getObject("bigdata");
		if (invisible.getObject("bigdata") instanceof JSONObject) {
			//数据正确
			JSONArray jsonArray = invisible.getJSONObject("bigdata").getJSONArray("photo_album");
			if (jsonArray != null && jsonArray.size() > 0) {
				List<String> albumImgUrls = new ArrayList<>();
				for (Object object : jsonArray) {
					JSONObject jsonObject = (JSONObject) object;
					albumImgUrls.add(jsonObject.getString("url"));
				}
				this.albumImgUrls = albumImgUrls.toArray(new String[albumImgUrls.size()]);
				if (this.albumImgUrls instanceof String[]) {
					System.out.println(this.albumImgUrls);
				} else {
					System.out.println(this.albumImgUrls);
				}
			}
		} else {
			System.out.println(this.bdUid + "返回的数据中没有bigdata");
		}
		
		//通用省市区编码
		this.provinceCode = myJsonObj.getJSONObject("admin_info").getString("prov_id");//暂无
		this.cityCode = myJsonObj.getJSONObject("admin_info").getString("city_id");
		this.areaCode = myJsonObj.getJSONObject("admin_info").getString("area_id");
		//百度省市区编码
		this.provinceCodeBd = myJsonObj.getJSONObject("api_admin_info").getString("prov_code");
		this.cityCodeBd = myJsonObj.getJSONObject("api_admin_info").getString("city_code");
		this.areaCodeBd = myJsonObj.getJSONObject("api_admin_info").getString("area_code");
		//省市区名称
		this.provinceName = myJsonObj.getJSONObject("api_admin_info").getString("prov_name");
		this.cityName = myJsonObj.getJSONObject("api_admin_info").getString("city_name");
		this.areaName = myJsonObj.getJSONObject("api_admin_info").getString("area_name");
		this.aoi = myJsonObj.getString("aoi");
	}
	
	public Long getAdvertiserId() {
		return advertiserId;
	}

	public void setAdvertiserId(Long advertiserId) {
		this.advertiserId = advertiserId;
	}

	public String getBdUid() {
		return bdUid;
	}

	public void setBdUid(String bdUid) {
		this.bdUid = bdUid;
	}

	public String getBdAuth() {
		return bdAuth;
	}

	public void setBdAuth(String bdAuth) {
		this.bdAuth = bdAuth;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getImgTel() {
		return imgTel;
	}

	public void setImgTel(String imgTel) {
		this.imgTel = imgTel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAddressNorm() {
		return addressNorm;
	}

	public void setAddressNorm(String addressNorm) {
		this.addressNorm = addressNorm;
	}

	public String getDefaultImgUrl() {
//		if (this.bdUid.equals("311a264ad3dd0a3f0aa8d4ba")) {
//			System.out.println("xxx");
//		}
//		if (defaultImgUrl == null || defaultImgUrl.length() < 1) {
//			return "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1594259298235&di=7e85fceafb4f9268b5c711ad055974a7&imgtype=0&src=http%3A%2F%2Fimg3.imgtn.bdimg.com%2Fit%2Fu%3D2774391408%2C3578926483%26fm%3D214%26gp%3D0.jpg";
//		}
		return defaultImgUrl;
	}

	public void setDefaultImgUrl(String defaultImgUrl) {
		this.defaultImgUrl = defaultImgUrl;
	}

	public String[] getAlbumImgUrls() {
		return albumImgUrls;
	}

	public void setAlbumImgUrls(String[] albumImgUrls) {
		this.albumImgUrls = albumImgUrls;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAoi() {
		return aoi;
	}

	public void setAoi(String aoi) {
		this.aoi = aoi;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getProvinceCodeBd() {
		return provinceCodeBd;
	}

	public void setProvinceCodeBd(String provinceCodeBd) {
		this.provinceCodeBd = provinceCodeBd;
	}

	public String getCityCodeBd() {
		return cityCodeBd;
	}

	public void setCityCodeBd(String cityCodeBd) {
		this.cityCodeBd = cityCodeBd;
	}

	public String getAreaCodeBd() {
		return areaCodeBd;
	}

	public void setAreaCodeBd(String areaCodeBd) {
		this.areaCodeBd = areaCodeBd;
	}
	
//	private Long navi_update_time;
	
//	public static void main(String[] args) {
//		Date date = new Date(1590742906000l);
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(JSON.DEFFAULT_DATE_FORMAT);
//		String format = simpleDateFormat.format(date);
//		System.out.println(format);
//		
//	}
	
}
