package com.stxb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stxb.exception.ArgCheckException;
import com.stxb.exception.EncodeException;
import com.stxb.exception.FormatException;
import com.stxb.exception.NotnullException;
import com.stxb.exception.common.SqlSearchException;
import com.stxb.exception.ApiException;
import com.stxb.model.SysApiInfo;
import com.stxb.model.SysApiPlatform;
import com.stxb.service.biz.CoreService;
import com.stxb.service.data.SysApiInfoService;
import com.stxb.service.data.SysApiPlatformService;
import com.stxb.service.biz.ApiService;
import com.stxb.utils.common.DataResultUtils;
/**
 * 核心功能接口
 * @author akku
 *
 */
@Controller
@RequestMapping("/")
public class CoreController {
	@Autowired
	ApiService ser;
	@Autowired
	CoreService coreSer;
	@Autowired
	SysApiPlatformService pSer;
	@Autowired
	SysApiInfoService infoSer;
	
	/**
	 * "servername:port/../平台接口/接口名?params=...” 此类接口拦截器
	 * @param platformName
	 * @param apiName
	 * @param request
	 * @return
	 * @throws EncodeException 
	 * @throws NotnullException 
	 * @throws FormatException 
	 * @throws ApiException 
	 * @throws SqlSearchException 
	 * @throws ArgCheckException 
	 */
	@ResponseBody
	@RequestMapping(value = "/c/api/{platformName}/{apiName}", method = RequestMethod.GET)
	public Object toApi(@PathVariable String platformName,@PathVariable String apiName,
			HttpServletRequest request) throws EncodeException, NotnullException, FormatException, SqlSearchException, ApiException, ArgCheckException {
		SysApiPlatform platform = pSer.get(platformName);
		if (platform != null) {
			
			SysApiInfo info = infoSer.getByPIdAndApiName(apiName, platform.getId());
			if(info==null)
				return DataResultUtils.DataResult("没有这个接口");
			List re = coreSer.searchSql(info, request);
			if(re!=null&&re.size()>0)
				return DataResultUtils.DataResult("成功", re);
			else
				return DataResultUtils.DataResult("失败");
		}
		return DataResultUtils.DataResult("没有这个api");
	}
	
	
	/**
	 * "servername:port/../平台接口?method=接口名&params=...” 此类接口拦截器
	 * @param platformName
	 * @param request
	 * @return
	 * @throws EncodeException 
	 * @throws NotnullException 
	 * @throws FormatException 
	 * @throws ApiException 
	 * @throws SqlSearchException 
	 * @throws ArgCheckException 
	 */
	@ResponseBody
	@RequestMapping(value = "/c/api/{platformName}", method = RequestMethod.GET)
	public Object toApi(@PathVariable String platformName,
			HttpServletRequest request) throws EncodeException, NotnullException, FormatException, SqlSearchException, ApiException, ArgCheckException {
		String methodName;
		SysApiPlatform platform = pSer.get(platformName);
		if (platform != null) {
			methodName = request.getParameter("method");
			SysApiInfo info = infoSer.getByPIdAndApiName(methodName, platform.getId());
			if(info==null)
				return DataResultUtils.DataResult("没有这个接口");
			List re = coreSer.searchSql(info, request);
			if(re!=null&&re.size()>0)
				return DataResultUtils.DataResult("成功", re);
			else
				return DataResultUtils.DataResult("失败");
		}
		return DataResultUtils.DataResult("没有这个接口");
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public void index(){}
}
