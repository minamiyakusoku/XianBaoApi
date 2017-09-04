package com.stxb.controller.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.stxb.dao.SysApiPlatformCACfgDao;
import com.stxb.exception.ArgCheckException;
import com.stxb.exception.EncodeException;
import com.stxb.exception.FormatException;
import com.stxb.exception.NotnullException;
import com.stxb.exception.common.SqlSearchException;
import com.stxb.exception.ApiException;
import com.stxb.factory.EncodeFactory.EncodeParam;
import com.stxb.ifs.enums.EnumUtils.ApiState;
import com.stxb.ifs.enums.EnumUtils.CheckType;
import com.stxb.ifs.enums.EnumUtils.DataType;
import com.stxb.ifs.enums.EnumUtils.EncodeMode;
import com.stxb.ifs.enums.EnumUtils.ResultArg;
import com.stxb.model.SysApiInArgInfo;
import com.stxb.model.SysApiInfo;
import com.stxb.model.SysApiOutArgInfo;
import com.stxb.model.SysApiPlatform;
import com.stxb.model.SysApiResult;
import com.stxb.model.SysApiSql;
import com.stxb.model.SysCheck;
import com.stxb.model.SysEncode;
import com.stxb.model.SysApiInArgException;
import com.stxb.service.biz.CoreService;
import com.stxb.service.common.ResultService;
import com.stxb.service.data.SysApiInfoService;
import com.stxb.service.data.SysApiPlatformCACfgService;
import com.stxb.service.data.SysApiPlatformService;
import com.stxb.service.data.SysApiResultService;
import com.stxb.service.biz.ApiService;
import com.stxb.utils.common.DataResultUtils;

/**
 * 通用测试类
 * @author akku
 *
 */
@Controller
@RequestMapping("/test")
public class CommonTestController {
	@Autowired
	ApiService ser;
	@Autowired
	CoreService coreSer;
	@Autowired
	SysApiPlatformService pSer;
	@Autowired
	SysApiInfoService infoSer;
	@Autowired
	ResultService resultSer;
	@Autowired
	SysApiResultService apiResultSer;
	@Autowired
	SysApiPlatformCACfgService pcacSer;
	
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
	@RequestMapping(value = "/api/{platformName}/{apiName}", method = RequestMethod.GET)
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
	 */
	@ResponseBody
	@RequestMapping(value = "/api/{platformName}", method = RequestMethod.GET)
	public Object toApi(@PathVariable String platformName,
			HttpServletRequest request) {
		String methodName;
		SysApiPlatform platform = pSer.get(platformName);
		if (platform != null) {
			methodName = request.getParameter("method");
			SysApiInfo info = infoSer.getByPIdAndApiName(methodName, platform.getId());
			System.out.println("apiinfo"+info);
			if(info==null)
				return resultSer.exceptionResultFormat(resultSer.exceptionResult("api not found"),null);
			List re = null;
			try {
				re = coreSer.searchSql(info, request);
				
			} catch (EncodeException | NotnullException | FormatException
					| SqlSearchException | ApiException |ArgCheckException e) {
				e.printStackTrace();
				return resultSer.exceptionResultFormat(resultSer.exceptionResult(e.getMessage()),info);
			} catch (Exception e){
				e.printStackTrace();
				return resultSer.exceptionResultFormat(resultSer.exceptionResult(apiResultSer.get(info.getId()).getExceptionCode()),info);
			}
			
			//返回入参
			Map<String, String> args = new HashMap<String, String>();
			Enumeration<String> enu = request.getParameterNames();
			while (enu.hasMoreElements()) {
				String paraName = (String) enu.nextElement();
				args.put(paraName, request.getParameter(paraName));
			}
			
//			Map<String, String> args = new HashMap<String, String>();
//			try {
//				args = new ObjectMapper().readValue(request.getParameter("biz_content"), Map.class);
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
			args.remove("method");
			
			
			
			
			
			return resultSer.dataResultFormat(resultSer.dataResult(re,args),info);
			 
		}

		return resultSer.exceptionResultFormat(resultSer.exceptionResult("api not found"),null);
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/initapi", method = RequestMethod.GET)
	public Object initApi(){
		
		return ser.initApi(10);
	}
	
	@ResponseBody
	@RequestMapping(value = "/akku", method = RequestMethod.GET)
	public Object akku(){
		return pcacSer.get(17);
	}

	
}
