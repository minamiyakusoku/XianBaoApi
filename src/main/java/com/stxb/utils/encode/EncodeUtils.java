package com.stxb.utils.encode;

import java.util.Map;

import com.stxb.factory.EncodeFactory;
import com.stxb.factory.EncodeFactory.EncodeParam;
import com.stxb.ifs.encode.Encode;
import com.stxb.ifs.enums.EnumUtils.EncodeMode;

/**
 * 加密工具类
 * @author akku
 *
 */
public class EncodeUtils {
	/**
	 * 修改数据加密方式
	 * @param value 源数据 
	 * @param inMode 传入时加密方式
	 * @param inParams 传入时加密配置
	 * @param outMode 输出时加密方式
	 * @param outParams 输出是加密配置
	 * @return 加密过的数据
	 * @throws Exception
	 */
	public static String decode(String value,EncodeMode inMode,Map<EncodeParam,Object> inParams) throws Exception{
		Encode inEncode = EncodeFactory.getEncode(inMode, inParams);
		String re = inEncode.decode(value);
		return re;
		
	}
	
	public static String encode(String value ,EncodeMode outMode,Map<EncodeParam,Object> outParams) throws Exception{
		Encode outEncode = EncodeFactory.getEncode(outMode, outParams);
		String re = outEncode.encode(value);
		return re;
	}
}
