package com.stxb.service.common;

import java.util.List;
import java.util.Map;

import com.stxb.exception.ArgCheckException;
import com.stxb.exception.EncodeException;
import com.stxb.exception.FormatException;
import com.stxb.exception.NotnullException;
import com.stxb.exception.ApiException;
import com.stxb.ifs.enums.EnumUtils.ResultArg;
import com.stxb.model.SysApiInArgInfo;
import com.stxb.model.SysApiInfo;

/**
 * 接口返回数据类
 * @author akku
 *
 * @param <T>返回类型
 */
public interface ResultService {
	/**
	 * 返回项目规范的结果集
	 * @param result 查询结果
	 * @param args 入参
	 * @return
	 */
	Map<ResultArg,Object> dataResult(Object result,Map<?, ?> args);
	/**
	 * 返回项目规范的错误结果
	 * @param exceptionCode 错误码
	 * @return
	 */
	Map<ResultArg, Object> exceptionResult(String exceptionCode);
	/**
	 * 检查入参
	 * @param errorNotnullArgs 不能为空的入参集合 
	 * @param errorEncodeArgs 加密/解密失败的入参集合
	 * @param errorFormatArgs 格式化失败的入参集合
	 * @throws NotnullException 非空报错
	 * @throws EncodeException 加密/解密报错报错
	 * @throws FormatException 格式化报错
	 * @throws ApiException 接口错误
	 */
	void throwInArgsError(List<SysApiInArgInfo> errorNotnullArgs,List<SysApiInArgInfo> errorEncodeArgs,List<SysApiInArgInfo> errorCheckArgs,List<SysApiInArgInfo> errorFormatArgs) throws NotnullException, EncodeException, FormatException,ApiException,ArgCheckException;
	/**
	 * 按照接口配置封装结果集
	 * @param resultMap 结果集
	 * @param info 接口
	 * @return
	 */
	Map<String, Map<String,Object>> dataResultFormat(Map<ResultArg,Object> resultMap,SysApiInfo info);
	/**
	 * 按照接口配置封装错误结果集
	 * @param exceptionResult
	 * @param info
	 * @return
	 */
	Map<String, Map<String,Object>> exceptionResultFormat(Map<ResultArg,Object> exceptionResult,SysApiInfo info);
}
