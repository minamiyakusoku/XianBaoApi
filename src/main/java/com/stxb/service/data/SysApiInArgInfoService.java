package com.stxb.service.data;

import java.util.List;

import com.stxb.model.SysApiInArgInfo;
import com.stxb.model.SysCheck;
import com.stxb.model.SysEncode;
import com.stxb.model.SysApiInArgException;

/**
 * 入参相关数据业务
 * @author akku
 *
 */
public interface SysApiInArgInfoService {
	/**
	 * 保存入参
	 * @param inArgsInfo
	 * @return
	 */
	boolean save(SysApiInArgInfo inArgsInfo,SysEncode inArgsEncode,SysCheck inArgsCheck,SysApiInArgException inArgsException);
	/**
	 * 查询接口下的所有入参
	 * @param apiId 接口ID
	 * @return
	 */
	List<SysApiInArgInfo> get(int apiId);
	/**
	 * 查询指定入参
	 * @param id
	 * @return
	 */
	SysApiInArgInfo getById(int id);
	/**
	 * 修改
	 * @param inArgsInfo
	 * @return
	 */
	boolean update(SysApiInArgInfo inArgsInfo,SysEncode inArgsEncode,SysCheck inArgsCheck,SysApiInArgException inArgsException);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	boolean delete(int id);
	/**
	 * 检查时候指定接口是否存在同名入参
	 * @param pyName
	 * @param apiId
	 * @return
	 */
	List<SysApiInArgInfo> getElseSamePyName(String pyName,int apiId);
	
}
