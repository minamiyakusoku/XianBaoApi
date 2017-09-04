package com.stxb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.stxb.model.SysFormat;

/**
 * 
 * @author akku
 *
 */
@Repository
public interface SysFormatDao {
	/**
	 * 新增格式化方式
	 * @param Format
	 * @return
	 */
	Integer save(SysFormat Format);
	/**
	 * 查询指定格式化方式
	 * @param id
	 * @return
	 */
	SysFormat get(int id);
	/**
	 * 查询指定数据类型的所有格式化方式
	 * @param enumType 数据类型（枚举int值）
	 * @return
	 */
	List<SysFormat> getByType(int enumType);
	/**
	 * 查询所有格式化方法
	 * @return
	 */
	List<SysFormat> getAll();
	/**
	 * 修改
	 * @return
	 */
	Integer update(SysFormat format);
	/**
	 * 删除
	 * @return
	 */
	Integer delete(int id);
}
