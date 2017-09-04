package com.stxb.dao;

import com.stxb.model.SysApiInArgException;

public interface SysApiInArgExceptionDao {
	/**
	 * 获取指定入参报错信息
	 * @param inArgId 入参ID
	 * @return 
	 */
	SysApiInArgException get(int inArgId);
	/**
	 * 保存
	 * @param e
	 * @return
	 */
	Integer save(SysApiInArgException e);
	/**
	 * 修改
	 * @param e
	 * @return
	 */
	Integer update(SysApiInArgException e);
	/**
	 * 删除
	 * @param e
	 * @return
	 */
	Integer delete(int id);
}
