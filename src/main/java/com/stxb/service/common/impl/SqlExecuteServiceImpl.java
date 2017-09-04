package com.stxb.service.common.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stxb.dao.common.SqlExecuteDao;
import com.stxb.ifs.enums.EnumUtils.SqlExecuteMode;
import com.stxb.database.DataSource;
import com.stxb.service.common.SqlExecuteService;


/**
 * 
 * @author akku
 *
 */
@Service

@DataSource(DataSource.LOCAL)
public class SqlExecuteServiceImpl implements SqlExecuteService{
	@Autowired
	SqlExecuteDao dao;

	
	public List<Map<Object, Object>> sqlSearch(String sql,SqlExecuteMode mode) throws SQLException{
	
		Map<String,String> sqlMap = new HashMap<String, String>();
		sqlMap.put("sql", sql);
		System.out.println(dao.sqlSearch(sqlMap));
			switch (mode) {
			case SEARCH:
				return dao.sqlSearch(sqlMap);
			default:
				throw new SQLException("sql无法解析");
			}
		
	}


	@Override
	public Map<Object, Object> sqlSearchUnique(String sql) throws SQLException {
		Map<String,String> sqlMap = new HashMap<String, String>();
		sqlMap.put("sql", sql);
		Map<Object, Object> re= dao.sqlSearch(sqlMap).get(0);
		if(re==null)
			throw new SQLException("没有查到结果");
		return re;
	
	}
}
