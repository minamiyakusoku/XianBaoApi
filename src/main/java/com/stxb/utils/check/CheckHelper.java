package com.stxb.utils.check;

import com.stxb.model.SysCheck;
import com.stxb.model.SysFormat;

/**
 * 校验帮助类
 * @author Souls
 *
 */
public class CheckHelper {
	/**
	 * 检验数据有效性
	 * @param check 	检查类型
	 * @param format	格式化信息集合
	 * @param value		检查的值
	 * @return 逻辑型结果是否满足条件
	 */
	public static boolean check(SysCheck check, SysFormat format,String value){
		switch (check.getType()) {
		case IDCARD:
			return CheckUtils.isIDCard(value);
		case TIME:
			return CheckUtils.isTime(format.getInPattern(), value);
		case NAME:
			return CheckUtils.isName(value);
		default:
			return false;
		}
	}
}
