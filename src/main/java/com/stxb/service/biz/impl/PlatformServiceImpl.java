package com.stxb.service.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stxb.database.DataSource;
import com.stxb.model.SysApiPlatform;
import com.stxb.model.SysApiPlatformCACfg;
import com.stxb.service.biz.PlatformService;
import com.stxb.service.data.SysApiPlatformCACfgService;
import com.stxb.service.data.SysApiPlatformService;
/**
 * 平台业务相关接口
 * @author akku
 *
 */
@Service
public class PlatformServiceImpl implements PlatformService{
	@Autowired
	SysApiPlatformService pSer;
	@Autowired
	SysApiPlatformCACfgService pcSer;
	
	@Override
	@DataSource(DataSource.GOODRABBIT)
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean save(SysApiPlatform p, SysApiPlatformCACfg pCACfg) {
		boolean flag = false;
		try {
			pSer.save(p);
			pCACfg.setId(p.getId());
			pcSer.save(pCACfg);
			flag = true;
		} catch (Exception e) {
			flag = false;
			throw new RuntimeException();
		}
		return flag;
	}

	@Override
	@DataSource(DataSource.GOODRABBIT)
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean update(SysApiPlatform p, SysApiPlatformCACfg pCACfg) {
		boolean flag = false;
		try {
			pSer.update(p);
			pCACfg.setId(p.getId());
			pcSer.update(pCACfg);
			flag = true;
		} catch (Exception e) {
			flag = false;
			throw new RuntimeException();
		}
		return flag;
	}

}
