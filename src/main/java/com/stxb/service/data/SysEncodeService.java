package com.stxb.service.data;

import com.stxb.model.SysEncode;

public interface SysEncodeService {
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
	boolean save(SysEncode encode);
	/**
	 * 修改
	 * @return
	 */
	boolean update(SysEncode encode);
}
