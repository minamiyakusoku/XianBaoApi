package com.stxb.ifs.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public interface EnumUtils {
	/**
	 * 加密方式枚举
	 * @author akku
	 *
	 */
	public enum EncodeMode{NONE,DES,DES3_ECB,DES3_CBC,RSA,MD5}
	/**
	 * 数据类型转换枚举
	 * @author akku
	 *
	 */
	public enum DataType{STRING,DATE,MONEY,INT,DOUBLE}
	/**
	 * sql操作类型
	 * @author akku
	 *
	 */
	public enum SqlExecuteMode{SEARCH,SAVE,UPDATE,DELETE};
	/**
	 * 返回数据包含元素
	 * @author akku
	 *
	 */
	public enum ResultArg{API_CODE,DATA,INARGS,EXCEPTION_CODE}
	/**
	 * 数据有效检查枚举
	 * @author Souls
	 *
	 */
	public enum CheckType{NONE,IDCARD,TIME,NAME,MOBILE,CHINESE,EMAIL};
	/**
	 * 接口状态枚举
	 * @author akku
	 *
	 */
	public enum ApiState{
		SERVICE(0),OFF(1),DELETE(2),STOP(3);
		
		
		private ApiState(Integer id) {
			this.id = id;
		}

		private Integer id;
		@JsonValue
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}
		
	}
	/**
	 * 入参传参方式
	 * @author akku
	 *
	 */
	public enum InArgMode{DEFAULT,JSON}
	/***
	 * 入参传参配置项
	 */
	public enum InArgCfg{JSONKEY}
	
	
}
