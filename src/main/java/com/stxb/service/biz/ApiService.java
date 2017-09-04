package com.stxb.service.biz;


import java.util.List;

import com.stxb.model.SysApiInArgInfo;
import com.stxb.model.SysApiInfo;
import com.stxb.model.SysApiOutArgInfo;
import com.stxb.model.SysApiResult;
import com.stxb.model.SysApiSql;
import com.stxb.model.SysCheck;
import com.stxb.model.SysEncode;
import com.stxb.model.SysApiInArgException;

/**
 * api数据相关业务接口
 * @author akku
 *
 */
public interface ApiService {
	
	
	/**
	 * 保存接口信息
	 * @param info
	 * @param inArgs
	 * @param outArgs
	 * @param sql
	 * @return
	 */
	boolean saveApi(SysApiInfo apiInfo, List<SysApiInArgInfo> inArgs,List<SysEncode> inEncodeList,List<SysCheck> inCheckList,List<SysApiInArgException> inExceptionList,
			List<SysApiOutArgInfo> outArgs,List<SysEncode> outEncodeList, SysApiSql sql,SysApiResult reCfg);
	
	/**
	 * 初始化测试接口
	 * @return
	 */
	boolean initApi(int id);

	boolean update(SysApiInfo apiInfo);

	boolean updateAll(SysApiInfo apiInfo, List<SysApiInArgInfo> inArgs,
			List<SysEncode> inEncodeList, List<SysCheck> inCheckList,
			List<SysApiInArgException> inExceptionList,
			List<SysApiOutArgInfo> outArgs, List<SysEncode> outEncodeList,
			SysApiSql sql, SysApiResult reCfg);
}
