package com.stxb.service.data.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stxb.dao.SysApiInArgInfoDao;
import com.stxb.dao.SysApiInfoDao;
import com.stxb.dao.SysCheckDao;
import com.stxb.dao.SysEncodeDao;
import com.stxb.dao.SysFormatDao;
import com.stxb.dao.SysApiInArgExceptionDao;
import com.stxb.database.DataSource;
import com.stxb.model.SysApiInArgInfo;
import com.stxb.model.SysApiInfo;
import com.stxb.model.SysCheck;
import com.stxb.model.SysEncode;
import com.stxb.model.SysApiInArgException;
import com.stxb.service.data.SysApiInArgInfoService;
@Service
@DataSource(DataSource.GOODRABBIT)
public class SysApiInArgInfoServiceImpl implements SysApiInArgInfoService{
	@Autowired
	SysApiInArgInfoDao dao;
	@Autowired
	SysApiInfoDao apiDao;
	@Autowired
	SysEncodeDao encodeDao;
	@Autowired
	SysFormatDao formatDao;
	@Autowired
	SysApiInArgExceptionDao eDao;
	@Autowired
	SysCheckDao cDao;
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean save(SysApiInArgInfo inArgsInfo,SysEncode inArgsEncode,SysCheck inArgsCheck,SysApiInArgException inArgsException) {
		boolean flag = false;
		SysApiInfo api = apiDao.getById(inArgsInfo.getApiId());
		if(api==null)
			return false;
		api.setInArgsCount(api.getInArgsCount()+1);
		try {
			encodeDao.save(inArgsEncode);
			inArgsInfo.setEncode(inArgsEncode.getId());
			//保存入参
			dao.save(inArgsInfo);
			
			inArgsCheck.setId(inArgsInfo.getId());
			cDao.save(inArgsCheck);
			inArgsException.setId(inArgsInfo.getId());
			eDao.save(inArgsException);
			//更新该接口入参个数
			apiDao.update(api);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag =false;
			throw new RuntimeException("入参添加失败");
		}
		return flag;
	}

	@Override
	public List<SysApiInArgInfo> get(int apiId) {
		return dao.get(apiId);
	}

	@Override
	public SysApiInArgInfo getById(int id) {
		return dao.getById(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean update(SysApiInArgInfo inArgsInfo,SysEncode inArgsEncode,SysCheck inArgsCheck,SysApiInArgException inArgsException) {
		boolean flag = false;
		try {
			dao.update(inArgsInfo);
			inArgsEncode.setId(inArgsInfo.getId());
			encodeDao.update(inArgsEncode);
			inArgsCheck.setId(inArgsInfo.getId());
			cDao.update(inArgsCheck);
			inArgsException.setId(inArgsInfo.getId());
			eDao.update(inArgsException);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
			throw new RuntimeException("入参修改失败");
		}
		return flag;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean delete(int id) {
		boolean flag = false;
		try {
			SysApiInArgInfo i = dao.getById(id);
			//更新接口入参格个数
			SysApiInfo info = apiDao.getById(i.getApiId());
			info.setInArgsCount(info.getInArgsCount() - 1);
			dao.delete(id);
			apiDao.update(info);
			encodeDao.delete(i.getEncode());
			eDao.delete(id);
			cDao.delete(id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
			throw new RuntimeException("入参删除失败");
		}
		return flag;
	}

	@Override
	public List<SysApiInArgInfo> getElseSamePyName(String pyName, int apiId) {
		return dao.getByPyNameAndApiId(pyName, apiId);
	}

	

}
