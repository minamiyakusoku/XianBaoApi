package com.stxb.service.data.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stxb.dao.SysApiPlatformCACfgDao;
import com.stxb.database.DataSource;
import com.stxb.model.SysApiPlatformCACfg;
import com.stxb.service.data.SysApiPlatformCACfgService;
@Service
@DataSource(DataSource.GOODRABBIT)
public class SysApiPlatformCACfgServiceImpl implements SysApiPlatformCACfgService{
	@Autowired
	SysApiPlatformCACfgDao dao;
	@Override
	public SysApiPlatformCACfg get(int id) {
		return dao.get(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean update(SysApiPlatformCACfg cfg) {
		boolean flag = false;
		try {
			dao.update(cfg);
			flag =true;
		} catch (Exception e) {
			e.printStackTrace();
			flag =false;
			throw new RuntimeException("平台接口配置修改失败");
		}
		return flag;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean save(SysApiPlatformCACfg cfg) {
		boolean flag = false;
		try {
			dao.save(cfg);
			flag =true;
		} catch (Exception e) {
			e.printStackTrace();
			flag =false;
			throw new RuntimeException("平台接口配置添加失败");
		}
		return flag;
	}

}
