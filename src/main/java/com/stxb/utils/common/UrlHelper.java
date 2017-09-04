package com.stxb.utils.common;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Url帮助类
 * @author Souls
 * 时间：2017-1-4 09:58:33
 */
public class UrlHelper {

	/**
	 * 提取URL传递Json参数
	 * @param request	Request
	 * @param node		参数节点 如：&biz={**:**}中biz
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static Map<String, String> Request2Map(HttpServletRequest request,
			String node) throws JsonParseException, JsonMappingException,
			IOException {
		Map<String, String> args = new ObjectMapper().readValue(request.getParameter(node),
				Map.class);
		return args;
	}

	/**
	 * 提取URL中传递参数
	 * @param request
	 * @return
	 */
	public static Map<String, String> Request2Map(HttpServletRequest request) {
		Map<String, String> args = new HashMap<String, String>();
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			args.put(paraName, request.getParameter(paraName));
		}
		return args;
	}

}
