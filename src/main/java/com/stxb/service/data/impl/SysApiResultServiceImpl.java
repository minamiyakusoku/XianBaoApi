package com.stxb.service.data.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stxb.dao.SysApiResultDao;
import com.stxb.database.DataSource;
import com.stxb.ifs.enums.EnumUtils.ResultArg;
import com.stxb.model.SysApiResult;
import com.stxb.service.data.SysApiResultService;

@Service
@DataSource(DataSource.GOODRABBIT)
public class SysApiResultServiceImpl implements SysApiResultService{
	@Autowired
	SysApiResultDao dao;
	@Override
	public SysApiResult get(int apiId) {
		SysApiResult re = dao.get(apiId);
		//将数据库中json形式的MAP中的Stringkey转换为enum
		for(ResultArg r:ResultArg.values()){
			if(re.getCfgMap()==null)
				break;
			if(re.getCfgMap().get(r.toString())!=null){
				re.getCfgMap().put(r,re.getCfgMap().get(r.toString()));
				re.getCfgMap().remove(r.toString());
			}
		}
		return re;
	}

	@Override
	public boolean save(SysApiResult result) {
		if(dao.save(result)>0)
			return true;
		else
			throw new RuntimeException("<查询数据返回结果配置>添加失败");
	}

	@Override
	public boolean update(SysApiResult result) {
		if(dao.update(result)>0)
			return true;
		else
			throw new RuntimeException("<查询数据返回结果配置>修改失败");
	}

	@Override
	public boolean delete(int id) {
		if(dao.delete(id)>0)
			return true;
		else
			throw new RuntimeException("<查询数据返回结果配置>删除失败");
	}

}
