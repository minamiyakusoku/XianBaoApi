package com.stxb.service.data.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stxb.dao.SysApiInfoDao;
import com.stxb.dao.SysApiOutArgInfoDao;
import com.stxb.dao.SysEncodeDao;
import com.stxb.dao.SysFormatDao;
import com.stxb.database.DataSource;
import com.stxb.model.SysApiInfo;
import com.stxb.model.SysApiOutArgInfo;
import com.stxb.model.SysEncode;
import com.stxb.model.SysFormat;
import com.stxb.service.data.SysApiOutArgInfoService;

@Service
@DataSource(DataSource.GOODRABBIT)
public class SysApiOutArgInfoServiceImpl implements SysApiOutArgInfoService{
	@Autowired
	SysApiOutArgInfoDao dao;
	@Autowired
	SysApiInfoDao apiDao;
	@Autowired
	SysEncodeDao encodeDao;
	@Autowired
	SysFormatDao formatDao;
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean save(SysApiOutArgInfo outArgsInfo ,SysEncode outencode) {
		boolean flag = false;
		SysApiInfo api = apiDao.getById(outArgsInfo.getApiId());
		if(api==null)
			return false;
		api.setOutArgsCount(api.getOutArgsCount()+1);
		try {
			encodeDao.save(outencode);
			outArgsInfo.setEncode(outencode.getId());
			//保存出参
			dao.save(outArgsInfo);
			
			
			//更新该接口出参个数
			apiDao.update(api);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag =false;
			throw new RuntimeException("出参添加失败");
		}
		return flag;
	}

	@Override
	public List<SysApiOutArgInfo> get(int apiId) {
		return dao.get(apiId);
	}

	@Override
	public SysApiOutArgInfo getById(int id) {
		return dao.getById(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean update(SysApiOutArgInfo outArgsInfo, SysEncode outencode) {
		boolean flag = false;
		try {
			
			dao.update(outArgsInfo);
			outencode.setId(outArgsInfo.getId());
			encodeDao.update(outencode);
			flag =true;
		} catch (Exception e) {
			e.printStackTrace();
			flag =false;
			throw new RuntimeException("出参修改失败");
		}
		return flag;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean delete(int id) {
		boolean flag = false;
		try {
			SysApiOutArgInfo i = dao.getById(id);
			//更新接口出参格个数
			SysApiInfo info = apiDao.getById(i.getApiId());
			info.setOutArgsCount(info.getOutArgsCount() - 1);
			dao.delete(id);
			apiDao.update(info);
			encodeDao.delete(i.getEncode());
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
			throw new RuntimeException("出参删除失败");
		}
		return flag;
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean insert(SysApiOutArgInfo outArgsInfo,SysEncode encode,SysFormat format) {
		SysApiInfo info = apiDao.getById(outArgsInfo.getApiId());
		info.setInArgsCount(info.getOutArgsCount()+1);
		int re = 0;
		re += encodeDao.save(encode);
		outArgsInfo.setEncode(encode.getId());
		outArgsInfo.setFormat(format.getId());
		re += dao.save(outArgsInfo);
		re += apiDao.update(info);
		if(re == 3)
			return true;
		else
			throw new RuntimeException("新增出参失败");
	}

	@Override
	public List<SysApiOutArgInfo> getElseSamePyName(String pyName, int apiId) {
		return dao.getByPyNameAndApiId(pyName,apiId);
	}

}
