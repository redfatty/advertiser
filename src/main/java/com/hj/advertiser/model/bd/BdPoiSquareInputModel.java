package com.hj.advertiser.model.bd;
 
/**
 * 方形区域搜索poi入参
 * @author huangjiong
 * http://lbsyun.baidu.com/index.php?title=webapi/guide/webservice-placeapi
 */
public class BdPoiSquareInputModel {
	
	/**
	 * query
	 * 检索关键字。圆形区域检索和矩形区域内检索支持多个关键字并集检索，不同关键字间以$符号分隔，最多支持10个关键字检索。如:”银行$酒店”
如果需要按POI分类进行检索，请将分类通过query参数进行设置，如query=美食
	 * string(45)	天安门	必选
	 */
	private String query;
	
	/**
	 * tag
	 * 检索分类偏好，与q组合进行检索，多个分类以","分隔
（POI分类），如果需要严格按分类检索，请通过query参数设置
	 * string(50)	美食	可选
	 */
	private String tag;
	
	/**
	 * bounds
	 * 检索矩形区域，多组坐标间以","分隔
	 * string(50)	38.76623,116.43213,39.54321,116.46773 lat,lng(左下角坐标),lat,lng(右上角坐标)	必选
	 */
	private String bounds;
	
	/**
	 * extensions_adcode
	 * 是否召回国标行政区划编码，true（召回）、false（不召回）
	 * string(50) true、false 可选
	 */
	private String extensions_adcode;
	
	/**
	 * output
	 * 输出格式为json或者xml
	 * string(50)	json或xml	可选
	 */
	private String output;
	
	/**
	 * scope
	 * 检索结果详细程度。取值为1 或空，则返回基本信息；取值为2，返回检索POI详细信息
	 * string(50)	1、2 可选
	 */
	private String scope;
	
	/**
	 * page_size
	 * 单次召回POI数量，默认为10条记录，最大返回20条。多关键字检索时，返回的记录数为关键字个数*page_size。
	 * int	10	可选
	 */
	private String page_size;
	
	/**
	 * page_num
	 * 分页页码，默认为0,0代表第一页，1代表第二页，以此类推。常与page_size搭配使用。
	 * int	0、1、2	可选
	 */
	private String page_num;
	
	/**
	 * ak
	 * 开发者的访问密钥，必填项。v2之前该属性为key。
	 * string(50)		必选
	 */
	private String ak;
//	
//	private String xxx;
//	
//	private String xxx;
//	
//	private String xxx;
//	
//	private String xxx;
//	
//	private String xxx;
//	
//	private String xxx;
//	
//	private String xxx;
//	
//	private String xxx;
//	
//	private String xxx;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getBounds() {
		return bounds;
	}

	public void setBounds(String bounds) {
		this.bounds = bounds;
	}

	public String getExtensions_adcode() {
		return extensions_adcode;
	}

	public void setExtensions_adcode(String extensions_adcode) {
		this.extensions_adcode = extensions_adcode;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getPage_size() {
		return page_size;
	}

	public void setPage_size(String page_size) {
		this.page_size = page_size;
	}

	public String getPage_num() {
		return page_num;
	}

	public void setPage_num(String page_num) {
		this.page_num = page_num;
	}

	public String getAk() {
		return ak;
	}

	public void setAk(String ak) {
		this.ak = ak;
	}
	
}
