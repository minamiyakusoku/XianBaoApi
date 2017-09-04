package com.stxb.service.data.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stxb.dao.SysEncodeDao;
import com.stxb.database.DataSource;
import com.stxb.model.SysEncode;
import com.stxb.service.data.SysEncodeService;

@Service
@DataSource(DataSource.GOODRABBIT)
public class SysEncodeServiceImpl implements SysEncodeService{
	@Autowired
	SysEncodeDao dao;
	@Override
	public SysEncode get(int id) {
		return dao.get(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean save(SysEncode encode) {
		if(dao.save(encode)>0)
			return true;
		else
			throw new RuntimeException("加密方式保存失败");
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean update(SysEncode encode) {
		if(dao.update(encode)>0)
			return true;
		else
			throw new RuntimeException("加密方式修改失败");
	}



}
