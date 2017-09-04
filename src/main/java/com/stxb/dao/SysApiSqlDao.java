package com.stxb.dao;


import org.springframework.stereotype.Repository;

import com.stxb.model.SysApiSql;
@Repository
public interface SysApiSqlDao {
	/**
	 * 保存sql
	 * @param aqiSql
	 * @return
	 */
	Integer save(SysApiSql apiSql);
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
	Integer update(SysApiSql apiSql);
}
