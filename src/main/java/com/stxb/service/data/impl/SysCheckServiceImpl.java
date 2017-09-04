package com.stxb.service.data.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stxb.dao.SysCheckDao;
import com.stxb.database.DataSource;
import com.stxb.model.SysCheck;
import com.stxb.service.data.SysCheckService;

@Service
@DataSource(DataSource.GOODRABBIT)
public class SysCheckServiceImpl implements SysCheckService{
	@Autowired
	SysCheckDao dao;
	@Override
	public SysCheck get(int inArgId) {
		return dao.get(inArgId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean save(SysCheck check) {
		if(dao.save(check)>0)
			return true;
		else 
			throw new RuntimeException("数据校验类型添加失败");
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean update(SysCheck check) {
		if(dao.update(check)>0)
			return true;
		else 
			throw new RuntimeException("数据校验类型修改失败");
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean delete(int id) {
		if(dao.delete(id)>0)
			return true;
		else 
			throw new RuntimeException("数据校验类型删除失败");
	}

}
