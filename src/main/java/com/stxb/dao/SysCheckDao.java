package com.stxb.dao;

import com.stxb.model.SysCheck;

public interface SysCheckDao {
	/**
	 * 获取指定入参的验证逻辑
	 * @param inArgId 入参id
	 * @return
	 */
	SysCheck get(int inArgId);
	/**
	 * 保存
	 * @param check
	 * @return
	 */
	Integer save(SysCheck check);
	/**
	 * 修改
	 * @param check
	 * @return
	 */
	Integer update(SysCheck check);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	Integer delete(int id);
}
