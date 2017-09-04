package com.stxb.service.data;

import com.stxb.model.SysApiInArgException;

public interface SysApiInArgExceptionService {
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
	boolean save(SysApiInArgException e);
	/**
	 * 修改
	 * @param e
	 * @return
	 */
	boolean update(SysApiInArgException e);
	/**
	 * 删除
	 * @param e
	 * @return
	 */
	boolean delete(int id);
}
