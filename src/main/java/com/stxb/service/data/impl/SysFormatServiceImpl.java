package com.stxb.service.data.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.stxb.dao.SysFormatDao;
import com.stxb.database.DataSource;
import com.stxb.ifs.enums.EnumUtils.DataType;
import com.stxb.model.SysFormat;
import com.stxb.service.data.SysFormatService;

@Service
@DataSource(DataSource.GOODRABBIT)
public class SysFormatServiceImpl implements SysFormatService{
	
	@Autowired
	SysFormatDao dao;
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean save(SysFormat format) {
		boolean reslut;
		try {
			reslut = dao.save(format) == 1 ? true : false;
		}catch (Exception e) {
			throw new RuntimeException("保存格式化方法失败");
		}
		return reslut;
	}

	@Override
	public SysFormat get(int id) {
		return dao.get(id);
	}

	@Override
	public List<SysFormat> getByType(DataType type) {
		return dao.getByType(type.ordinal());
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean update(SysFormat format) {
		boolean flag = false;
		try {
			dao.update(format);
			flag = true;
		} catch (Exception e) {
			throw new RuntimeException("保存格式化方法失败");
		}
		return flag;
	}

	@Override
	public List<SysFormat> getAll() {
		return dao.getAll();
	}

	

}
