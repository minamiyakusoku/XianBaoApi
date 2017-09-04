package com.stxb.service.data;

import com.stxb.model.SysApiResult;
public interface SysApiResultService {
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
	boolean save(SysApiResult result);
	/**
	 * 修改
	 * @param result
	 * @return
	 */
	boolean update(SysApiResult result);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	boolean delete(int id);
}
