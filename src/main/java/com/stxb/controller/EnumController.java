package com.stxb.controller;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stxb.factory.EncodeFactory.EncodeParam;
import com.stxb.ifs.enums.EnumUtils.ApiState;
import com.stxb.ifs.enums.EnumUtils.CheckType;
import com.stxb.ifs.enums.EnumUtils.DataType;
import com.stxb.ifs.enums.EnumUtils.EncodeMode;
import com.stxb.ifs.enums.EnumUtils.InArgMode;
import com.stxb.ifs.enums.EnumUtils.ResultArg;
import com.stxb.utils.common.ResponseCode;

/**
 * 枚举键值调用接口
 * 
 * @author akku
 *
 */
@Controller
@RequestMapping("/api/enum")
public class EnumController {
	@ResponseBody
	@RequestMapping(value = "/encodemode", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map getEncodeMode() {
		return this.commonResult(EncodeMode.class);
	}

	@ResponseBody
	@RequestMapping(value = "/encodeparam", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map getEncodeParam() {
		return this.commonResult(EncodeParam.class);
	}

	@ResponseBody
	@RequestMapping(value = "/datatype", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map getDataType() {
		return this.commonResult(DataType.class);
	}

	@ResponseBody
	@RequestMapping(value = "/resultarg", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map getResultArg() {
		return this.commonResult(ResultArg.class);
	}
	@ResponseBody
	@RequestMapping(value = "/inargmode", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map getInArgMode() {
		return this.commonResult(InArgMode.class);
	}

	@ResponseBody
	@RequestMapping(value = "/checktype", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map getCheckType() {
		return this.commonResult(CheckType.class);
	}

	@ResponseBody
	@RequestMapping(value = "/apistate", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map getApiState() {
		return this.commonResult(ApiState.class);
	}

	/**
	 * 通用返回方法
	 * 
	 * @param clazz
	 *            枚举类
	 * @return
	 */
	public Map<String, Object> commonResult(Class clazz) {
		Map<String, Object> re = new HashMap<String, Object>();
		Map<Integer, String> map = new HashMap<Integer, String>();
		int code = ResponseCode.Error.ERROR;

		try {
			EnumSet enumSet = EnumSet.allOf(clazz);
			Object[] arr = enumSet.toArray();
			for (int i = 0; i < arr.length; i++) {
				map.put(i, arr[i].toString());
			}

			re.put("data", map);
			code = ResponseCode.Success.OK;
		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}

		re.put(ResponseCode.RESPONSE_CODE, code);
		return re;
	}
}
