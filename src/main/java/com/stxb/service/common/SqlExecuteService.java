package com.stxb.service.common;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.stxb.ifs.enums.EnumUtils.SqlExecuteMode;

/**
 * sql查询接口
 * @author akku
 *
 */
public interface SqlExecuteService {
	/**
	 * 查询多条数据
	 * @param sql
	 * @param mode
	 * @return
	 * @throws SQLException
	 */
	List<Map<Object, Object>> sqlSearch(String sql,SqlExecuteMode mode) throws SQLException;
	/**
	 * 查询单挑数据
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	Map<Object, Object> sqlSearchUnique(String sql)throws SQLException;
}
