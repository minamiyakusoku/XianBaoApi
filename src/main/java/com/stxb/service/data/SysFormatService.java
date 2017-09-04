package com.stxb.service.data;

import java.util.List;

import com.stxb.ifs.enums.EnumUtils.DataType;
import com.stxb.model.SysFormat;

public interface SysFormatService {
	/**
	 * 新增格式化方式
	 * @param Format
	 * @return
	 * 修改人：TanXiaoYang 2017-1-17 17:43:17 修改返回值为Integer（储存后返回主键）
	 */
	boolean save(SysFormat Format);
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
	List<SysFormat> getByType(DataType type);
	/**
	 * 查询全部格式化方法
	 * @return
	 */
	List<SysFormat> getAll();
	/**
	 * 修改
	 * @return
	 */
	boolean update(SysFormat format);
}
