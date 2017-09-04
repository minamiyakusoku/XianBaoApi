package com.stxb.factory;

import java.util.Map;

import com.stxb.ifs.encode.Encode;
import com.stxb.ifs.encode.impl.Des3CBCEncode;
import com.stxb.ifs.encode.impl.Des3ECBEncode;
import com.stxb.ifs.encode.impl.NoneEncode;
import com.stxb.ifs.enums.EnumUtils.EncodeMode;

/**
 * 加密方法工厂
 * @author akku
 *
 */
public class EncodeFactory {
	public enum EncodeParam{KEY,IV,PUBLIC_KEY,PRIVATE_KEY}
	/**
	 * 加密方法工厂
	 * @param mode 加密方式
	 * @param params 参数
	 * @return
	 */
	public static Encode getEncode(EncodeMode mode,Map<EncodeParam, Object> params) {
		
		switch(mode){
		case NONE:
			return new NoneEncode();
		case DES:
			return null;
		case DES3_ECB:
			return new Des3ECBEncode(params.get(EncodeParam.KEY)!=null?params.get(EncodeParam.KEY).toString():params.get(EncodeParam.KEY.toString()).toString());
		case DES3_CBC:
			return new Des3CBCEncode(params.get(EncodeParam.KEY)!=null?params.get(EncodeParam.KEY).toString():params.get(EncodeParam.KEY.toString()).toString(),params.get(EncodeParam.IV)!=null?params.get(EncodeParam.IV).toString():params.get(EncodeParam.IV.toString()).toString());
		default:
			return null;
		}
	}
	
	
}
