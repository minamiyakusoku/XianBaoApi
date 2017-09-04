package com.stxb.utils.format;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 格式化工具类
 * @author Souls
 *
 */
public class FormatUtils {

	/**
	 * 
	 * @param pattern	格式化格式
	 * @param value		要格式化的值
	 * @return
	 */
	public static String formatMoney(String pattern,double value){
        DecimalFormat df = new DecimalFormat(pattern);
		return df.format(value) ; 
	}

	/**
	 * 
	 * @param inPattern 	传入参数时间格式
	 * @param outPattern 	输出参数时间格式
	 * @param value			要处理的值
	 * @return
	 */
	public static String formatStrTime(String inPattern,String outPattern,String value){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(inPattern);
		Date date = null;
        try {
			date = simpleDateFormat.parse(value);
		} catch (ParseException e) {
		}
		return new SimpleDateFormat(outPattern).format(date);
	}
	
	public static String parseMoney(String pattern,BigDecimal bd){
		return new DecimalFormat(pattern).format(bd);
	}
}
