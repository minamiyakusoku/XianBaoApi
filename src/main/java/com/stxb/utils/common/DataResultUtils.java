package com.stxb.utils.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.stxb.ifs.enums.EnumUtils.EncodeMode;

/**
 * 页面返回数据工具类
 * @author akku
 *
 */
public class DataResultUtils {
	public static Map<Object,Object> DataResult(String result,Object resultData){
		Map<Object,Object> re = new HashMap<Object,Object>();
		re.put("message", result);
		Map<Object,Object> rex = (Map<Object,Object>)(((ArrayList)resultData).get(0));
		rex.put("xxstate",EncodeMode.DES3_CBC.ordinal());

		rex.put("xxbool",false);
		
		if(resultData!=null)
			re.put("Data", resultData);
		return re;
		
		
	}
	public static Map<Object,Object> DataResult(String result){
		Map<Object,Object> re = new HashMap<Object,Object>();
		re.put("message", result);
		return re;
	}
}
