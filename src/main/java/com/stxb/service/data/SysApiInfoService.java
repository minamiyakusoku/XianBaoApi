package com.stxb.service.data;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.stxb.model.SysApiInfo;

public interface SysApiInfoService {
	/**
	 * 保存api
	 * @param apiInfo
	 * @return
	 */
	boolean save(SysApiInfo apiInfo);
	/**
	 * 根据接口名（URL）查询接口 不包括已被删除的接口
	 * @param apiName
	 * @return
	 */
	SysApiInfo getByApiName(String apiName);
	/**
	 * 根据接口名（URL）查询接口 包括已被删除的接口
	 * @param apiName
	 * @return
	 */
	SysApiInfo getByApiNameIncludeDeleted(String apiName);
	/**
	 * 根据接口名（URL）和所在平台查询指定接口 不包括已被删除的接口
	 * @param apiName
	 * @param pId 平台ID
	 * @return
	 */
	SysApiInfo getByPIdAndApiName(@Param("apiName")String apiName,@Param("pId")int pId);
	/**
	 * 根据接口名（URL）和所在平台查询指定接口 包括已被删除的接口
	 * @param apiName
	 * @param pId
	 * @return
	 */
	List<SysApiInfo> getByPIdAndApiNameIncludeDeleted(@Param("apiName")String apiName,@Param("pId")int pId);
	/**
	 * 查询指定接口 不包括已被删除的接口
	 * @param id 
	 * @return
	 */
	SysApiInfo getById(int id);
	/**
	 * 查询指定接口 包括已被删除的接口
	 * @param id
	 * @return
	 */
	SysApiInfo getByIdIncludeDeleted(int id);
	/**
	 * 获取全部接口（未被删除）
	 * @return
	 */
	List<SysApiInfo> getAll();
	/**
	 * 获取全部接口（包括已被删除的接口）
	 * @return
	 */
	List<SysApiInfo> getAllIncludeDeleted();
	/**
	 * 获取全部已被删除的接口
	 * @return
	 */
	List<SysApiInfo> getAllOnlyDeleted();
	/**
	 * 修改
	 * @param apiInfo
	 * @return
	 */
	boolean update(SysApiInfo apiInfo);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	boolean delete(int id);
	/**
	 * 恢复被删除的文件
	 * @param id
	 * @return
	 */
	boolean unDelete(int id);
	/**
	 * 获取全部接口（运行中的接口）
	 * @return
	 */
	List<SysApiInfo> getAllOnlyService();
	/**
	 * 获取全部接口（禁用的接口）
	 * @return
	 */
	List<SysApiInfo> getAllOnlyOff();
	/**
	 * 获取除本身外其他同url的api
	 * @param apiInfo
	 * @return
	 */
	List<SysApiInfo> getElseSameApiName(SysApiInfo apiInfo);
	
}
