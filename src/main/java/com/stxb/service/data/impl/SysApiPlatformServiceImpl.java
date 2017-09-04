package com.stxb.service.data.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stxb.dao.SysApiInfoDao;
import com.stxb.dao.SysApiPlatformDao;
import com.stxb.database.DataSource;
import com.stxb.model.SysApiPlatform;
import com.stxb.service.data.SysApiPlatformService;

@Service
@DataSource(DataSource.GOODRABBIT)
public class SysApiPlatformServiceImpl implements SysApiPlatformService{
	@Autowired
	SysApiPlatformDao dao;
	@Autowired
	SysApiInfoDao apiDao;
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean save(SysApiPlatform apiPlatform) {
		if(apiPlatform.getCreateTime()==null)
			apiPlatform.setCreateTime(new Date());
		if(dao.save(apiPlatform)>0)
			return true;
		else 
			throw new RuntimeException("新增平台失败");
	}

	@Override
	public SysApiPlatform get(String apiUrl) {
		return dao.get(apiUrl);
	}

	@Override
	public SysApiPlatform getIncludeDeleted(String apiUrl) {
		return dao.getIncludeDeleted(apiUrl);
	}

	@Override
	public SysApiPlatform getById(int id) {
		return dao.getById(id);
	}

	@Override
	public SysApiPlatform getByIdIncludeDeleted(int id) {
		return dao.getByIdIncludeDeleted(id);
	}

	@Override
	public List<SysApiPlatform> getAll() {
		return dao.getAll();
	}

	@Override
	public List<SysApiPlatform> getAllIncludeDeleted() {
		return dao.getAllIncludeDeleted();
	}

	@Override
	public List<SysApiPlatform> getAllOnlyDeleted() {
		return dao.getAllOnlyDeleted();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean update(SysApiPlatform apiPlatform) {
		if(dao.update(apiPlatform)>0)
			return true;
		else 
			return false;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean delete(int id) {
		int re = 0;
		re += dao.delete(id);
		if(re >0){
			try {
				re += apiDao.deleteByPId(id);
			} catch (Exception e) {
				throw new RuntimeException("删除平台失败");
			}
			return true;
		}else 
			throw new RuntimeException("删除平台失败");
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean unDelete(int id) {
		int re = 0;
		re += dao.unDelete(id);
		if(re >0){
			try {
				re += apiDao.unDeleteByPId(id);
			} catch (Exception e) {
				throw new RuntimeException("恢复平台失败");
			}
			return true;
		}else 
			throw new RuntimeException("恢复平台失败");
	}

}
