package com.stxb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.stxb.model.SysApiPlatform;
/**
 * 
 * @author akku
 *
 */
@Repository
public interface SysApiPlatformDao {
	/**
	 * 保存平台
	 * @param apiPlatform
	 * @return
	 */
	Integer save(SysApiPlatform apiPlatform);
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
	Integer update(SysApiPlatform apiPlatform);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	Integer delete(int id);
	/**
	 * 恢复被删除的平台
	 * @param id
	 * @return
	 */
	Integer unDelete(int id);
}
