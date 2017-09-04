package com.stxb.service.data;

import java.util.List;

import com.stxb.model.SysApiOutArgInfo;
import com.stxb.model.SysEncode;
import com.stxb.model.SysFormat;

public interface SysApiOutArgInfoService {
	/**
	 * 保存出参
	 * @param outArgsInfo
	 * @return
	 */
	boolean save(SysApiOutArgInfo outArgsInfo, SysEncode outencode);
	/**
	 * 查询接口下的所有出参
	 * @param apiId 接口ID
	 * @return
	 */
	List<SysApiOutArgInfo> get(int apiId);
	/**
	 * 查询出参
	 * @param id
	 * @return
	 */
	SysApiOutArgInfo getById(int id);
	/**
	 * 更新
	 * @param inArgsInfo
	 * @return
	 */
	boolean update(SysApiOutArgInfo outArgsInfo, SysEncode outencode);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	boolean delete(int id);
	/**
	 * 添加新的出参
	 * @param inArgsInfo
	 * @return
	 */
	boolean insert(SysApiOutArgInfo outArgsInfo,SysEncode encode,SysFormat format);
	List<SysApiOutArgInfo> getElseSamePyName(String pyName,int apiId);
}
