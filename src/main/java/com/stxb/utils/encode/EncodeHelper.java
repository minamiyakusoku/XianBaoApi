package com.stxb.utils.encode;

import com.stxb.model.SysEncode;

/**
 * 加密辅助类
 * @author akku
 *
 */
public class EncodeHelper {
	/**
	 * 解密
	 * @param value 数据
	 * @param encode 配置
	 * @return
	 * @throws Exception
	 */
	public static String decode(String value,SysEncode encode) throws Exception{
		return EncodeUtils.decode(value, encode.getInType(), encode.getInParams());
	}
	/**
	 * 加密
	 * @param value 数据
	 * @param encode 配置
	 * @return
	 * @throws Exception
	 */
	public static String encode(String value,SysEncode encode) throws Exception{
		return EncodeUtils.encode(value, encode.getOutType(), encode.getOutParams());
	}
}
