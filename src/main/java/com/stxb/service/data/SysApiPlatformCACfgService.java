package com.stxb.service.data;

import com.stxb.model.SysApiPlatformCACfg;

public interface SysApiPlatformCACfgService {
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
	boolean update(SysApiPlatformCACfg cfg);
	/**
	 * 添加
	 * @param cfg
	 * @return
	 */
	boolean save(SysApiPlatformCACfg cfg);
}
