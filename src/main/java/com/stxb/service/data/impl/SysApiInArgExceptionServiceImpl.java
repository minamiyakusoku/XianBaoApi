package com.stxb.service.data.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stxb.dao.SysApiInArgExceptionDao;
import com.stxb.database.DataSource;
import com.stxb.model.SysApiInArgException;
import com.stxb.service.data.SysApiInArgExceptionService;
@Service
@DataSource(DataSource.GOODRABBIT)
public class SysApiInArgExceptionServiceImpl implements SysApiInArgExceptionService{
	@Autowired
	SysApiInArgExceptionDao dao;
	@Override
	public SysApiInArgException get(int inArgId) {
		return dao.get(inArgId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean save(SysApiInArgException e) {
		if(dao.save(e)>0)
			return true;
		else
			throw new RuntimeException("保存入参错误代码失败");
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean update(SysApiInArgException e) {
		if(dao.update(e)>0)
			return true;
		else
			throw new RuntimeException("修改入参错误代码失败");
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean delete(int id) {
		if(dao.delete(id)>0)
			return true;
		else
			throw new RuntimeException("删除入参错误代码失败");
	}

}
