package com.hj.advertiser.model.bd;

/**
 * 
 * @author huangjiong
 * 百度文档: http://lbsyun.baidu.com/index.php?title=webapi/guide/webservice-placeapi
 *
 */
public class BdPoiCircleInputModel {
	
	/**
	 * query 检索关键字。
	 * 圆形区域检索和矩形区域内检索支持多个关键字并集检索，不同关键字间以$符号分隔，最多支持10个关键字检索。如:”银行$酒店”
	如果需要按POI分类进行检索，请将分类通过query参数进行设置，如query=美食
	string(45)	例如:天安门 必选
	 */
	private String query;
	
	/**
	 * tag	检索分类偏好，与q组合进行检索，多个分类以","分隔
	（POI分类），如果需要严格按分类检索，请通过query参数设置
	string(50)	美食	可选
	 */
	private String tag;
	
	/**
	 * location	圆形区域检索中心点，不支持多个点	string(50)	38.76623,116.43213
lat纬度,lng经度 必选
	 */
	private String location;
	
	/**
	 * radius	圆形区域检索半径，单位为米。(当半径过大，超过中心点所在城市边界时，会变为城市范围检索，检索范围为中心点所在城市）	
	 * string(50)	1000（默认）	可选
	 */
	private String radius;
	
	/**
	 * radius_limit
是否严格限定召回结果在设置检索半径范围内。true（是），false（否）。设置为true时会影响返回结果中total准确性及每页召回poi数量，我们会逐步解决此类问题。
string(50) false	可选
	 */
	private String radius_limit;
	
	/**
	 * extensions_adcode
是否召回国标行政区划编码，true（召回）、false（不召回）
string(50) true、false 可选
	 */
	private String extensions_adcode;
	
	/**
	 * output	输出格式为json或者xml	
	 * string(50)	json或xml	可选
	 */
	private String output;
	
	/**
	 * scope 检索结果详细程度。取值为1 或空，则返回基本信息；取值为2，返回检索POI详细信息	
	 * string(50)	1、2	 可选
	 */
	private String scope;
	
	/**
	 * coord_type	坐标类型，
	 * 1（wgs84ll即GPS经纬度），
	 * 2（gcj02ll即国测局经纬度坐标），
	 * 3（bd09ll即百度经纬度坐标），
	 * 4（bd09mc即百度米制坐标）
注："ll为小写LL"
坐标详细说明	
int	1、2、3(默认)、4	可选
	 */
	private String coord_type;
	
	/**
	 * ret_coordtype	可选参数，添加后POI返回国测局经纬度坐标
坐标详细说明	
string(50)	gcj02ll	可选
	 */
	private String ret_coordtype;
	
	/**
	 * page_size	单次召回POI数量，默认为10条记录，最大返回20条。
	 * 多关键字检索时，返回的记录数为关键字个数*page_size。多关键词检索时，单页返回总数=关键词数量*page_size	
	 * int	10	可选
	 */
	private String page_size;
	
	/**
	 * page_num	分页页码，默认为0,0代表第一页，1代表第二页，以此类推。
常与page_size搭配使用。
int	0、1、2	可选
	 */
	private String page_num;
	
	/**
	 * ak	开发者的访问密钥，必填项。v2之前该属性为key。
申请秘钥
string(50)		必选
	 */
	private String ak;
	
	/**
	 * sn	开发者的权限签名。
string(50)		可选，若开发者所用AK的校验方式为SN校验时该参数必须。
	 */
	private String sn;
	
	/**
	 * timestamp	设置sn后该值必填。	string(50)		设置sn后该值必填。
	 */
	private String timestamp;
	
	/**
	 * filter 检索过滤条件。当scope取值为2时，可以设置filter进行排序。
industry_type：行业类型，注意：设置该字段可提高检索速度和过滤精度，取值有： hotel（宾馆）；cater（餐饮）；life（生活娱乐） 
sort_name：排序字段，根据industry_type字段的值而定。 1、industry_type为hotel时，sort_name取值有： default（默认）；price（价格）；total_score（好评）；level（星级）；health_score（卫生）；distance（距离排序，只有圆形区域检索有效） 2、industry_type为cater时，sort_name取值有： default（默认）；taste_rating（口味）；price（价格）；overall_rating（好评）；service_rating（服务）；distance（距离排序，只有圆形区域检索有效） 3、industry_type为life时，sort_name取值有： default（默认）；price（价格）；overall_rating（好评）；comment_num（服务）；distance（距离排序，只有圆形区域检索有效）
sort_rule：排序规则：0（从高到低），1（从低到高）
price_section：价格区间
groupon：是否有团购：1（有），0（无）
discount：是否有打折：1（有），0（无）
string(50)	sort_name:distance|sort_rule:1	可选
	 */
	private String filter;

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	public String getRadius_limit() {
		return radius_limit;
	}

	public void setRadius_limit(String radius_limit) {
		this.radius_limit = radius_limit;
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

	public String getCoord_type() {
		return coord_type;
	}

	public void setCoord_type(String coord_type) {
		this.coord_type = coord_type;
	}

	public String getRet_coordtype() {
		return ret_coordtype;
	}

	public void setRet_coordtype(String ret_coordtype) {
		this.ret_coordtype = ret_coordtype;
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

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}
}
