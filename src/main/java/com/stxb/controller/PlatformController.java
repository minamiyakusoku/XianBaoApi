package com.stxb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stxb.model.SysApiPlatform;
import com.stxb.model.SysApiPlatformCACfg;
import com.stxb.service.biz.PlatformService;
import com.stxb.service.data.SysApiPlatformCACfgService;
import com.stxb.service.data.SysApiPlatformService;
import com.stxb.utils.common.ParseUtils;
import com.stxb.utils.common.ResponseCode;

/**
 * 平台controller
 * 
 * @author akku
 *
 */
@Controller
@RequestMapping("/api")
public class PlatformController {
	@Autowired
	SysApiPlatformService ser;
	@Autowired
	PlatformService pSer;
	@Autowired
	SysApiPlatformCACfgService pcSer;

	@ResponseBody
	@RequestMapping(value = "platform", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> getAll(String mode) {
		int modex = 0;
		Map<String, Object> re = new HashMap<String, Object>();
		try {
			modex = Integer.parseInt(mode);
		} catch (Exception e) {
			modex = 0;
		}
		List<SysApiPlatform> result;
		int code = ResponseCode.Error.ERROR;
		try {
			switch (modex) {
			case 0:
				result = ser.getAll();
				break;
			case 1:
				result = ser.getAllIncludeDeleted();
				break;
			case 2:
				result = ser.getAllOnlyDeleted();
				break;
			default:
				result = ser.getAll();
				break;
			}
			if (result != null && result.size() > 0)
				code = ResponseCode.Success.OK;
			else
				code = ResponseCode.Warning.NO_RESULT;
			re.put(ResponseCode.RESPONSE_DATA, result);
		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}
		re.put(ResponseCode.RESPONSE_CODE, code);
		return re;
	}

	@ResponseBody
	@RequestMapping(value = "platform/{id}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> get(@PathVariable int id,String mode) {
		int modeX = 0;
		Map<String, Object> re = new HashMap<String, Object>();
		int code = ResponseCode.Error.ERROR;
		try {
			modeX = Integer.parseInt(mode);
		} catch (Exception e) {
		}
		
		switch(modeX){
		case 0:
			try {
				SysApiPlatform result;
				result = ser.getByIdIncludeDeleted(id);
				if (result != null)
					code = ResponseCode.Success.OK;
				else
					code = ResponseCode.Warning.NO_RESULT;
				re.put(ResponseCode.RESPONSE_DATA, result);
				
			} catch (Exception e) {
				e.printStackTrace();
				code = ResponseCode.Error.ERROR;
			}
			break;
		case 1:
			try {
				Map<String,Object> details = new HashMap<String,Object>();
				details.put("platform", ser.getByIdIncludeDeleted(id));
				details.put("platformcacfg", pcSer.get(id));
				if (details != null && details.size()>0)
					code = ResponseCode.Success.OK;
				else
					code = ResponseCode.Warning.NO_RESULT;
				
				re.put(ResponseCode.RESPONSE_DATA, details);
			} catch (Exception e) {
				e.printStackTrace();
				code = ResponseCode.Error.ERROR;
			}
			break;
		case 2:
			try {
				Map<String,Object> detailsX = new HashMap<String,Object>();
				for(Map.Entry<String, Object> m :ParseUtils.obj2Map(ser.getByIdIncludeDeleted(id)).entrySet()){
					detailsX.put("platform."+m.getKey(), m.getValue());
				}
				for(Map.Entry<String, Object> m :ParseUtils.obj2Map(pcSer.get(id)).entrySet()){
					detailsX.put("platformcacfg."+m.getKey(), m.getValue());
				}
				if (detailsX != null && detailsX.size()>0)
					code = ResponseCode.Success.OK;
				else
					code = ResponseCode.Warning.NO_RESULT;
				re.put(ResponseCode.RESPONSE_DATA, detailsX);
			} catch (Exception e) {
				e.printStackTrace();
				code = ResponseCode.Error.ERROR;
			}
			break;
		}
		
		re.put(ResponseCode.RESPONSE_CODE, code);
		return re;
	}

	@ResponseBody
	@RequestMapping(value = "platform/save", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> save(@ModelAttribute("platform") SysApiPlatform platform,@ModelAttribute("platformcacfg") SysApiPlatformCACfg platformcacfg) {
		Map<String, Object> re = new HashMap<String, Object>();
		int code = ResponseCode.Error.ERROR;
		try {
			if(pSer.save(platform,platformcacfg)==true){
				code = ResponseCode.Success.OK;
			}else{
				code = ResponseCode.Warning.Error;
			}
		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}
		re.put(ResponseCode.RESPONSE_CODE, code);
		return re;
	}

	@ResponseBody
	@RequestMapping(value = "platform/update", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> update(@ModelAttribute("platform") SysApiPlatform platform,@ModelAttribute("platformcacfg") SysApiPlatformCACfg platformcacfg) {
		Map<String, Object> re = new HashMap<String, Object>();
		int code = ResponseCode.Error.ERROR;
		try {
			if(pSer.update(platform,platformcacfg)==true)
				code = ResponseCode.Success.OK;
			else
				code = ResponseCode.Warning.Error;
		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}
		re.put(ResponseCode.RESPONSE_CODE, code);
		return re;
	}

	@ResponseBody
	@RequestMapping(value = "platform/delete", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> delete(int id) {
		Map<String, Object> re = new HashMap<String, Object>();
		int code = ResponseCode.Error.ERROR;
		try {
			if(ser.delete(id)==true)
				code = ResponseCode.Success.OK;
			else
				code = ResponseCode.Warning.Error;

		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}

		re.put(ResponseCode.RESPONSE_CODE, code);
		return re;
	}

	@ResponseBody
	@RequestMapping(value = "platform/undelete", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> undelete(int id) {

		Map<String, Object> re = new HashMap<String, Object>();
		int code = ResponseCode.Error.ERROR;
		try {
			if(ser.unDelete(id)==true)
				code = ResponseCode.Success.OK;
			else
				code = ResponseCode.Warning.Error;

		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}

		re.put(ResponseCode.RESPONSE_CODE, code);
		return re;

	}

	@InitBinder("platform")
	public void initBinderForPlatform(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("platform.");
	}
	@InitBinder("platformcacfg")
	public void initBinderForPlatformCACfg(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("platformcacfg.");
	}
}
