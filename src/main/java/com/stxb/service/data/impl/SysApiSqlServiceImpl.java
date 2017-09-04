package com.stxb.service.data.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stxb.dao.SysApiSqlDao;
import com.stxb.database.DataSource;
import com.stxb.model.SysApiSql;
import com.stxb.service.data.SysApiSqlService;

@Service
@DataSource(DataSource.GOODRABBIT)
public class SysApiSqlServiceImpl implements SysApiSqlService{
	@Autowired
	SysApiSqlDao dao;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean save(SysApiSql apiSql) {
		if(dao.save(apiSql)>0)
			return true;
		else 
			throw new RuntimeException("sql保存错误");
	}

	@Override
	public SysApiSql get(int apiId) {
		return dao.get(apiId);
	}

	@Override
	public SysApiSql getById(int id) {
		return dao.getById(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean update(SysApiSql apiSql) {
		if(dao.update(apiSql)>0)
			return true;
		else
			throw new RuntimeException("sql修改失败");
	}
}
