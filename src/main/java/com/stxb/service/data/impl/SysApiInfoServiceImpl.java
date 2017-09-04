package com.stxb.service.data.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stxb.dao.SysApiInfoDao;
import com.stxb.database.DataSource;
import com.stxb.model.SysApiInfo;
import com.stxb.service.data.SysApiInfoService;
@Service
@DataSource(DataSource.GOODRABBIT)
public class SysApiInfoServiceImpl implements SysApiInfoService{
	@Autowired
	SysApiInfoDao dao;
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean save(SysApiInfo apiInfo) {
		if(dao.save(apiInfo)>0)
			return true;
		else
			throw new RuntimeException("接口新增失败");
	}

	@Override
	public SysApiInfo getByApiName(String apiName) {
		return dao.getByApiName(apiName);
	}

	@Override
	public SysApiInfo getByApiNameIncludeDeleted(String apiName) {
		return dao.getByApiNameIncludeDeleted(apiName);
	}

	@Override
	public SysApiInfo getByPIdAndApiName(String apiName, int pId) {
		return dao.getByPIdAndApiName(apiName, pId);
	}

	@Override
	public List<SysApiInfo> getByPIdAndApiNameIncludeDeleted(String apiName, int pId) {
		return dao.getByPIdAndApiNameIncludeDeleted(apiName, pId);
	}

	@Override
	public SysApiInfo getById(int id) {
		return dao.getById(id);
	}

	@Override
	public SysApiInfo getByIdIncludeDeleted(int id) {
		return dao.getByIdIncludeDeleted(id);
	}

	@Override
	public List<SysApiInfo> getAll() {
		return dao.getAll();
	}

	@Override
	public List<SysApiInfo> getAllIncludeDeleted() {
		return dao.getAllIncludeDeleted();
	}

	@Override
	public List<SysApiInfo> getAllOnlyDeleted() {
		return dao.getAllOnlyDeleted();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean update(SysApiInfo apiInfo) {
		if(dao.update(apiInfo)>0)
			return true;
		else
			throw new RuntimeException("接口更新失败");
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean delete(int id) {
		if(dao.delete(id)>0)
			return true;
		else
			throw new RuntimeException("接口删除失败");
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean unDelete(int id) {
		if(dao.unDelete(id)>0)
			return true;
		else
			throw new RuntimeException("接口恢复失败");
	}

	@Override
	public List<SysApiInfo> getAllOnlyService() {
		return dao.getAllOnlyService();
	}

	@Override
	public List<SysApiInfo> getAllOnlyOff() {
		return dao.getAllOnlyOff();
	}

	@Override
	public List<SysApiInfo> getElseSameApiName(SysApiInfo apiInfo) {
		return dao.getElseSameApiName(apiInfo);
	}
	

}
