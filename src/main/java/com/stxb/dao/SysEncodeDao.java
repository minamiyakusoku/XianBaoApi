package com.stxb.dao;

import org.springframework.stereotype.Repository;

import com.stxb.model.SysEncode;
/**
 * 
 * @author akku
 *
 */
@Repository
public interface SysEncodeDao {
	/**
	 * 查询指定加密方式
	 * @param id
	 * @return
	 */
	SysEncode get(int id);
	/**
	 * 保存新的加密方式
	 * @param encode
	 * @return
	 */
	Integer save(SysEncode encode);
	/**
	 * 修改
	 * @return
	 */
	Integer update(SysEncode encode);
	/**
	 * 删除
	 * @return
	 */
	Integer delete(int id);
}
