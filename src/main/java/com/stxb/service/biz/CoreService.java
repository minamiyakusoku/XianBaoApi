package com.stxb.service.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.stxb.exception.ArgCheckException;
import com.stxb.exception.EncodeException;
import com.stxb.exception.FormatException;
import com.stxb.exception.NotnullException;
import com.stxb.exception.common.SqlSearchException;
import com.stxb.exception.ApiException;
import com.stxb.model.SysApiInfo;

/**
 * 核心业务
 * @author akku
 *
 */
public interface CoreService {
	/**
	 * 根据接口进行查询并对数据做必要的加密解密以及格式化
	 * @param info api信息
	 * @param request 
	 * @return
	 * @throws EncodeException 加密错误
	 * @throws NotnullException 非空错误
	 * @throws FormatException 格式化错误
	 * @throws SqlSearchException sql查询错误
	 * @throws ApiException api错误
	 * @throws ArgCheckException 参数效验错误
	 */
	List searchSql(SysApiInfo info,HttpServletRequest request) throws EncodeException, NotnullException, FormatException, SqlSearchException, ApiException, ArgCheckException ;
	
}
