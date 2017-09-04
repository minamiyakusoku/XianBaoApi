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

import com.stxb.model.SysFormat;
import com.stxb.service.data.SysFormatService;
import com.stxb.utils.common.ResponseCode;

/**
 * 参数格式化配置相关接口
 * 
 * @author akku
 *
 */
@Controller
@RequestMapping("/api/format")
public class FormatController {
	@Autowired
	SysFormatService ser;

	/**
	 * 获取指定格式化配置
	 * 
	 * @param id
	 *            标识id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> get(@PathVariable int id) {
		Map<String, Object> re = new HashMap<String, Object>();
		SysFormat result;
		int code = -1;
		try {
			result = ser.get(id);
			if (result != null)
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

	/**
	 * 获取全部格式化配置
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> getAll() {
		Map<String, Object> re = new HashMap<String, Object>();

		List<SysFormat> result;
		int code = -1;
		try {
			result = ser.getAll();
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

	/**
	 * 添加格式化
	 * 
	 * @param format
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> saveFormat(@ModelAttribute("format") SysFormat format) {
		Map<String, Object> re = new HashMap<String, Object>();
		int code = -1;
		try {
			ser.save(format);
			SysFormat f = ser.get(format.getId());
			if(f != null){
				re.put(ResponseCode.RESPONSE_DATA, f);
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

	@InitBinder("format")
	public void initBinderForDataFormat(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("format.");
	}
}
