package com.stxb.dao;

import com.stxb.model.SysApiResult;

public interface SysApiResultDao {
	/**
	 * 查询指定查询结果配置
	 * @param apiId
	 * @return
	 */
	SysApiResult get(int apiId);
	/**
	 * 保存
	 * @param result
	 * @return
	 */
	Integer save(SysApiResult result);
	/**
	 * 修改
	 * @param result
	 * @return
	 */
	Integer update(SysApiResult result);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	Integer delete(int id);
}
