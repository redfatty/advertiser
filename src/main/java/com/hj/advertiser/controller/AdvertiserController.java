package com.hj.advertiser.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.hj.advertiser.base.pojo.ResultModel;
import com.hj.advertiser.model.AdvertiserModel;
import com.hj.advertiser.model.UpdateAdvertiserImgPhoneInputModel;
import com.hj.advertiser.service.AdvertiserService;

/**
 * 广告商控制层
 * @author huangjiong
 *
 */
@Controller
public class AdvertiserController {
	
	@Autowired
	private AdvertiserService advertiserService;
	
	/**
	 * 更新广告商图片电话号码
	 * @param inputModel
	 * @return
	 */
	@RequestMapping("/updateImgTel")
	@ResponseBody
	public ResultModel updateImgPhone(@RequestBody UpdateAdvertiserImgPhoneInputModel inputModel) {
		return advertiserService.updateImgTel(inputModel);
		
	}
	
	@RequestMapping("/exportAdvertiserExcel")
	@ResponseBody
	public ResultModel exportAdvertiserExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ResultModel resultModel = new ResultModel();
//		response.setContentType("application/vnd.ms-excel;charset=utf-8");
//        response.setHeader("Content-Disposition", "attachment;filename="+ new String(("xx123" + ".xlsx").getBytes(), "iso-8859-1"));
		List<AdvertiserModel> selectAdvertiserTest = advertiserService.selectAdvertiserTest();
//		Sheet initSheet = new Sheet(1, 0);
//	      initSheet.setSheetName("sheet");
//	      //设置自适应宽度
//	      initSheet.setAutoWidth(Boolean.TRUE);
//	      
//	      OutputStream outputStream;
//	      ExcelWriter writer = null;
//		try {
//			outputStream = response.getOutputStream();
//		      writer = EasyExcelFactory.getWriter(outputStream);
//		      writer.write(selectAdvertiserTest,initSheet);
//		} catch (IOException e) {
//			resultModel.setCode(0);
//			resultModel.setMsg("导出异常:" + e.getMessage());
//			return resultModel;
//		} finally {
//	         if(writer != null){
//			   writer.finish();
//			}
//		}
		
		response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = "广告商" +  simpleDateFormat.format(new Date()) + ".xlsx";
     // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        fileName = URLEncoder.encode(fileName, "UTF-8");
        //这个在前端读取我不会
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        //前端导出时文件名fhruf使用这个响应头
        response.setHeader("filename", fileName);
        
//        Sheet initSheet = new Sheet(1, 0);
//	    initSheet.setSheetName("sheet");
//	    //设置自适应宽度
//	    initSheet.setAutoWidth(Boolean.TRUE);
//	    initSheet.setClazz(AdvertiserModel.class);
//	    ExcelWriter writer = EasyExcelFactory.getWriter(response.getOutputStream());
//	    writer.write(selectAdvertiserTest, initSheet);
	    
	    //easyExcel官网的导出方法
	    EasyExcel.write(response.getOutputStream(), AdvertiserModel.class).sheet("模板").doWrite(selectAdvertiserTest);
	    
//        EasyExcel.write(response.getOutputStream(), AdvertiserModel.class).sheet("模板").doWrite(selectAdvertiserTest);
	    
	    
		resultModel.setCode(1);
		resultModel.setMsg("成功");
		return resultModel;
	}
	
}
