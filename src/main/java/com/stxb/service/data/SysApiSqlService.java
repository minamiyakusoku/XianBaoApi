package com.stxb.service.data;

import com.stxb.model.SysApiSql;

public interface SysApiSqlService {
	/**
	 * 保存sql
	 * @param aqiSql
	 * @return
	 */
	boolean save(SysApiSql apiSql);
	/**
	 * 查询指定接口中的sql
	 * @param apiId 接口id
	 * @return
	 */
	SysApiSql get(int apiId);
	/**
	 * 查询指定sql
	 * @param id
	 * @return
	 */
	SysApiSql getById(int id);
	/**
	 * 修改
	 * @param apiSql
	 * @return
	 */
	boolean update(SysApiSql apiSql);
}
