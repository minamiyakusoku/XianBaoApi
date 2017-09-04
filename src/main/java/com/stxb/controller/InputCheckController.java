package com.stxb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stxb.model.SysApiInArgInfo;
import com.stxb.model.SysApiInfo;
import com.stxb.model.SysApiOutArgInfo;
import com.stxb.model.SysApiPlatform;
import com.stxb.service.data.SysApiInArgInfoService;
import com.stxb.service.data.SysApiInfoService;
import com.stxb.service.data.SysApiOutArgInfoService;
import com.stxb.service.data.SysApiPlatformService;
import com.stxb.utils.common.ResponseCode;
/**
 * 页面录入数据验证相关接口
 * @author akku
 *
 */
@Controller
@RequestMapping("/api/inputcheck")
public class InputCheckController {
	@Autowired
	SysApiPlatformService pSer;
	@Autowired
	SysApiInfoService infoSer;
	@Autowired
	SysApiInArgInfoService inSer;
	@Autowired
	SysApiOutArgInfoService outSer;
	
	/**
	 * 平台url是否存在验证
	 * @param url
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "platform/occupy", method = {RequestMethod.GET,RequestMethod.POST})
	public Map<String, Object> platformOccupy(String url){
		Map<String, Object> re = new HashMap<String, Object>();
		SysApiPlatform s;
		int code = ResponseCode.Error.ERROR;
		try {
			s  = pSer.get(url);
			if(s!=null)
				code = ResponseCode.Logic.PASS;
			else
				code = ResponseCode.Logic.NOT_PASS;
		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}
		
		re.put(ResponseCode.RESPONSE_LOGIC,code);
		return re;
	}
	/**
	 * 要新增的api所在平台下是否已存在该url 验证
	 * @param url
	 * @param pid 平台标识id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "occupy", method = {RequestMethod.GET,RequestMethod.POST})
	public Map<String, Object> occupy(String url,int pId,String apiId){
		int apiIdI = 0;
		try{
			apiIdI = Integer.valueOf(apiId);
		}catch(Exception e){
			
		}
		Map<String, Object> re = new HashMap<String, Object>();
		List<SysApiInfo> s;
		int code = ResponseCode.Error.ERROR;
		try {
			if(apiIdI==0)
				s  = infoSer.getByPIdAndApiNameIncludeDeleted(url, pId);
			else{
				SysApiInfo apiInfo = new SysApiInfo();
				apiInfo.setApiId(apiIdI);
				apiInfo.setpId(pId);
				apiInfo.setApiName(url);
				s = infoSer.getElseSameApiName(apiInfo);
			}
			if(s!=null&&s.size()>0)
				code = ResponseCode.Logic.PASS;
			else
				code = ResponseCode.Logic.NOT_PASS;
		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}
		
		re.put(ResponseCode.RESPONSE_LOGIC,code);
		return re;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/in/occupy", method = {RequestMethod.GET,RequestMethod.POST})
	public Map<String, Object> inOccupy(String pyName,int apiId){
		Map<String, Object> re = new HashMap<String, Object>();
		List<SysApiInArgInfo> s ;
		int code = ResponseCode.Error.ERROR;
		try {
			s  = inSer.getElseSamePyName(pyName, apiId);
			if(s!=null&&s.size()>0)
				code = ResponseCode.Logic.PASS;
			else
				code = ResponseCode.Logic.NOT_PASS;
		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}
		
		re.put(ResponseCode.RESPONSE_LOGIC,code);
		return re;
	}
	@ResponseBody
	@RequestMapping(value = "/out/occupy", method = {RequestMethod.GET,RequestMethod.POST})
	public Map<String, Object> outOccupy(String pyName,int apiId){
		Map<String, Object> re = new HashMap<String, Object>();
		List<SysApiOutArgInfo> s;
		int code = ResponseCode.Error.ERROR;
		try {
			s  = outSer.getElseSamePyName(pyName, apiId);
			if(s!=null&&s.size()>0)
				code = ResponseCode.Logic.PASS;
			else
				code = ResponseCode.Logic.NOT_PASS;
		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}
		
		re.put(ResponseCode.RESPONSE_LOGIC,code);
		return re;
	}
}
