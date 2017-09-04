package com.stxb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.stxb.model.SysApiInfo;

/**
 * 
 * @author akku
 *
 */
@Repository
public interface SysApiInfoDao {
	/**
	 * 保存api
	 * @param apiInfo
	 * @return
	 */
	Integer save(SysApiInfo apiInfo);
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
	 * 获取除本身外其他同url的api
	 * @param apiInfo
	 * @return
	 */
	List<SysApiInfo> getElseSameApiName(SysApiInfo apiInfo);
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
	Integer update(SysApiInfo apiInfo);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	Integer delete(int id);
	/**
	 * 恢复被删除的文件
	 * @param id
	 * @return
	 */
	Integer unDelete(int id);
	/**
	 * 删除平台下所有接口
	 * @param pId 平台ID
	 * @return
	 */
	Integer deleteByPId(int pId);
	/**
	 * 恢复平台下所有接口
	 * @param pId 平台ID
	 * @return
	 */
	Integer unDeleteByPId(int pId);
	/**
	 * 获取最新的api逻辑id
	 * @param pId
	 * @return
	 */
	Integer getLastApiId(int pId);
	/**
	 * 修改指定平台的不同版本平台的url和所在平台一致
	 * @param id
	 * @return
	 */
	Integer updateApiNameByP(SysApiInfo info);
	
}
