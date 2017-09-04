package com.stxb.dao;

import com.stxb.model.SysApiPlatformCACfg;

public interface SysApiPlatformCACfgDao {
	/**
	 * 获取指定配置
	 * @param id
	 * @return
	 */
	SysApiPlatformCACfg get(int id);
	/**
	 * 修改
	 * @param cfg
	 * @return
	 */
	Integer update(SysApiPlatformCACfg cfg);
	/**
	 * 添加
	 * @param cfg
	 * @return
	 */
	Integer save(SysApiPlatformCACfg cfg);
}
