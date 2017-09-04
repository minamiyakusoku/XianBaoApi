package com.stxb.service.data;

import java.util.List;

import com.stxb.model.SysApiPlatform;


public interface SysApiPlatformService{
	/**
	 * 保存平台
	 * @param apiPlatform
	 * @return
	 */
	boolean save(SysApiPlatform apiPlatform);
	/**
	 * 根据平台url查询平台 不包括已被删除的平台
	 * @param apiUrl
	 * @return
	 */
	SysApiPlatform get(String apiUrl);
	/**
	 * 根据平台url查询平台 包括已被删除的平台
	 * @param apiUrl
	 * @return
	 */
	SysApiPlatform getIncludeDeleted(String apiUrl);
	/**
	 * 查询指定平台 不包括已被删除的平台
	 * @param id
	 * @return
	 */
	SysApiPlatform getById(int id);
	/**
	 * 查询指定平台 包括已被删除的平台
	 * @param id
	 * @return
	 */
	SysApiPlatform getByIdIncludeDeleted(int id);
	/**
	 * 获取全部平台（未被删除）
	 * @return
	 */
	List<SysApiPlatform> getAll();
	/**
	 * 获取全部平台（包括被删除的平台）
	 * @return
	 */
	List<SysApiPlatform> getAllIncludeDeleted();
	/**
	 * 获取全部已被删除的平台
	 * @return
	 */
	List<SysApiPlatform> getAllOnlyDeleted();
	/**
	 * 修改平台信息
	 * @param apiPlatform 修改后的数据
	 * @return
	 */
	boolean update(SysApiPlatform apiPlatform);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	boolean delete(int id);
	/**
	 * 恢复被删除的平台
	 * @param id
	 * @return
	 */
	boolean unDelete(int id);
}
