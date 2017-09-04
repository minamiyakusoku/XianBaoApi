package com.stxb.utils.common;
/**
 * 内部接口返回码
 * @author akku 修改人 TanXiaoYang 2017年1月3日 11:04:14
 * 修改说明 错误码改为200起始，错误码都定义在200后 成功码定义在200前 (0/200 400/600)
 */
public interface ResponseCode {
	/**
	 * 业务码名
	 */
	static final String RESPONSE_CODE = "code";
	static final String RESPONSE_LOGIC = "code";
	/**
	 * 返回数据集名
	 */
	static final String RESPONSE_DATA = "data";

	/**
	 * 成功状态
	 * @author Souls
	 *
	 */
	static interface Success{
		/**
		 * 执行成功
		 */
		static final int OK = 1;
	}
	
	/**
	 * 警告状态
	 * @author Souls
	 *
	 */
	static interface Warning{
		/**
		 * 执行成功但未修改数据
		 */
		static final int Error = 301;
		/**
		 * 执行成功但查无结果
		 */
		static final int NO_RESULT = 302;
	}
	
	/**
	 * 失败状态
	 * @author Souls
	 *
	 */
	static interface Error{
		/**
		 * 执行出现异常
		 */
		static final int ERROR = 200;
		

	}
	
	static interface Logic{
		/**
		 * 验证不通过
		 */
		static final int NOT_PASS = 0;
		/**
		 * 验证通过
		 */
		static final int PASS = 1;
	}
	
}
