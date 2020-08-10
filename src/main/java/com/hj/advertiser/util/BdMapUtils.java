package com.hj.advertiser.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BdMapUtils {
	
    static double DEF_PI = 3.14159265359; // PI
    static double DEF_2PI= 6.28318530712; // 2*PI
    static double DEF_PI180= 0.01745329252; // PI/180.0
    static double DEF_R =6370693.5; // radius of earth
	
	public static String adustPoiInputGpsSeq(String gpsSrc) {
		//TODO 调整为小数点6数
		String[] split = gpsSrc.trim().split(",");
		//格式为"纬度,经度"
		if (Float.valueOf(split[0]) < 100.0) {
			return new String(gpsSrc);
		} else {
			return split[1] + "," + split[0];
		}
	}
	
	public static List<String> getPoiBoundList(String leftBottom, String rightTop) {
		List<String> boundList = new ArrayList<>();
		//从左下角开始, 经纬度每次递增0.02, 距离大概增加2000米.
		BigDecimal startX = new BigDecimal(leftBottom.split(",")[1]);
		BigDecimal startY = new BigDecimal(leftBottom.split(",")[0]);
		BigDecimal endX = new BigDecimal(rightTop.split(",")[1]);
		BigDecimal endY = new BigDecimal(rightTop.split(",")[0]);
		BigDecimal step = new BigDecimal("0.02");
		BigDecimal lastEndX = endX.add(new BigDecimal(0.0d));
		BigDecimal lastEndY = startY.add(new BigDecimal(0.0d));
		DecimalFormat upFormat = new DecimalFormat("#.000000");
		upFormat.setRoundingMode(RoundingMode.UP);
		DecimalFormat downFormat = new DecimalFormat("#.000000");
		downFormat.setRoundingMode(RoundingMode.DOWN);
		//1. 确定Y
		while (lastEndY.compareTo(endY) == -1) {
			BigDecimal curStartY = lastEndY.add(new BigDecimal(0.0d));
			BigDecimal curEndY = curStartY.add(step);
			if (curEndY.compareTo(endY) != -1) {
				curEndY = endY;
			}
			//2. 确定X
			while (true) {
				//本次起点X
				BigDecimal curStartX = null;
				if (lastEndX.compareTo(endX) == 0) {
					curStartX = startX.add(new BigDecimal(0.0d));
				} else {
					curStartX = lastEndX.add(new BigDecimal(0.0d));
				}
				//本次终点X
				BigDecimal curEndX = curStartX.add(step);
				if (curEndX.compareTo(endX) != -1) {
					curEndX = endX;
				}
				
				//本次起点终点X和Y都确定好了
				String bound = 
						downFormat.format(curStartY) + "," 
						+ downFormat.format(curStartX) + "," 
						+ upFormat.format(curEndY) + "," 
						+ upFormat.format(curEndX);
				boundList.add(bound);
				System.out.println(bound.replace(",", ", "));
				
				//记录
				lastEndX = curEndX;
				//如果X到最右边了,本次横向移动就结束
				if (curEndX.compareTo(endX) == 0) {
					System.out.println("到最右边了, 本次横向移动结束");
					break;
				}
			}
			
			//记录
			lastEndY = curEndY;
		}
		
		return boundList;
	}
	
	public static List<String> getPoiBoundList(String south, String north, String west, String east) {
		//把纬度放前面
		south = BdMapUtils.adustPoiInputGpsSeq(south);
		north = BdMapUtils.adustPoiInputGpsSeq(north);
		west = BdMapUtils.adustPoiInputGpsSeq(west);
		east = BdMapUtils.adustPoiInputGpsSeq(east);
		//左下角: 最南边的纬度 + 最西边的经度 
		String leftBottom = south.split(",")[0] + "," + west.split(",")[1];
		//右上角: 最北边的纬度 + 最东边的经度
		String rightTop = north.split(",")[0] + "," + east.split(",")[1];
		return getPoiBoundList(leftBottom, rightTop);
	}
	
    public static double getDistance(double lat_a, double lng_a, double lat_b, double lng_b){
        double pk = 180 / 3.14169;
        double a1 = lat_a / pk;
        double a2 = lng_a / pk;
        double b1 = lat_b / pk;
        double b2 = lng_b / pk;
        double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
        double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
        double t3 = Math.sin(a1) * Math.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);
        return 6371000 * tt;
    }
            //适用于近距离
    public static double getShortDistance(double lon1, double lat1, double lon2, double lat2)
    {
        double ew1, ns1, ew2, ns2;
        double dx, dy, dew;
        double distance;
        // 角度转换为弧度
        ew1 = lon1 * DEF_PI180;
        ns1 = lat1 * DEF_PI180;
        ew2 = lon2 * DEF_PI180;
        ns2 = lat2 * DEF_PI180;
        // 经度差
        dew = ew1 - ew2;
        // 若跨东经和西经180 度，进行调整
        if (dew > DEF_PI)
        	dew = DEF_2PI - dew;
        else if (dew < -DEF_PI)
        	dew = DEF_2PI + dew;
        dx = DEF_R * Math.cos(ns1) * dew; // 东西方向长度(在纬度圈上的投影长度)
        dy = DEF_R * (ns1 - ns2); // 南北方向长度(在经度圈上的投影长度)
        // 勾股定理求斜边长
        distance = Math.sqrt(dx * dx + dy * dy);
        return distance;
    }
            //适用于远距离
    public static double getLongDistance(double lon1, double lat1, double lon2, double lat2)
    {
        double ew1, ns1, ew2, ns2;
        double distance;
        // 角度转换为弧度
        ew1 = lon1 * DEF_PI180;
        ns1 = lat1 * DEF_PI180;
        ew2 = lon2 * DEF_PI180;
        ns2 = lat2 * DEF_PI180;
        // 求大圆劣弧与球心所夹的角(弧度)
        distance = Math.sin(ns1) * Math.sin(ns2) + Math.cos(ns1) * Math.cos(ns2) * Math.cos(ew1 - ew2);
        // 调整到[-1..1]范围内，避免溢出
        if (distance > 1.0)
             distance = 1.0;
        else if (distance < -1.0)
              distance = -1.0;
        // 求大圆劣弧长度
        distance = DEF_R * Math.acos(distance);
        return distance;
    }
    
    public static void main(String[] args) {
    	
    	
//    	112.991041,28.217917
    	
		String north = "112.675197,27.865147";
		String south = "112.661792,27.836277";
    	
        double mLat1 = 27.865147; // point1纬度
        double mLon1 = 112.675197; // point1经度
        
        //每次递增0.02, 距离增加2000米左右
        
        double mLat2 = 27.836277;// point2纬度
        double mLon2 = 112.661792;// point2经度
        
        
        double distance = getShortDistance(mLon1, mLat1, mLon2, mLat2);
        System.out.println(distance);
        
        System.out.println(getDistance(mLat1, mLon1, mLat2, mLon2));
    	
//		 String south = "112.94,27.90";
//		 String north = "112.93,27.99";
//		 String west = "112.90,27.96";
//		 String east = "112.99,27.93";
//    	getPoiBoundList(south, north, west, east);
    }


}
