package com.stxb.service.data;

import com.stxb.model.SysCheck;


public interface SysCheckService{
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
	boolean save(SysCheck check);
	/**
	 * 修改
	 * @param check
	 * @return
	 */
	boolean update(SysCheck check);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	boolean delete(int id);

}
