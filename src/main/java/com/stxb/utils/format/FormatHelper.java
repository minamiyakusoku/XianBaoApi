package com.stxb.utils.format;

import java.math.BigDecimal;

import com.stxb.model.SysFormat;

/**
 * 格式化辅助类
 * @author Souls
 *
 */
public class FormatHelper {
	/**
	 * 格式化数据
	 * @param format	SysFormat类格式化集合信息
	 * @param value		要格式化的值
	 * @return	
	 */
	public static String format(SysFormat format,String value){
		switch (format.getType()) {
		case DATE:
			value = FormatUtils.formatStrTime(format.getInPattern(),format.getOutPattern(),value);
			break;
		case MONEY:
			value = FormatUtils.parseMoney(
					format.getOutPattern(),
					new BigDecimal(value));
			break;
		default:
			break;
		}
		return value;
	}
}
