package com.stxb.service.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stxb.dao.SysApiInArgInfoDao;
import com.stxb.dao.SysApiInfoDao;
import com.stxb.dao.SysApiOutArgInfoDao;
import com.stxb.dao.SysApiPlatformDao;
import com.stxb.dao.SysApiResultDao;
import com.stxb.dao.SysApiSqlDao;
import com.stxb.dao.SysCheckDao;
import com.stxb.dao.SysEncodeDao;
import com.stxb.dao.SysFormatDao;
import com.stxb.dao.SysApiInArgExceptionDao;
import com.stxb.database.DataSource;
import com.stxb.factory.EncodeFactory.EncodeParam;
import com.stxb.ifs.enums.EnumUtils.ApiState;
import com.stxb.ifs.enums.EnumUtils.CheckType;
import com.stxb.ifs.enums.EnumUtils.DataType;
import com.stxb.ifs.enums.EnumUtils.EncodeMode;
import com.stxb.ifs.enums.EnumUtils.ResultArg;
import com.stxb.model.SysApiInArgInfo;
import com.stxb.model.SysApiInfo;
import com.stxb.model.SysApiOutArgInfo;
import com.stxb.model.SysApiPlatform;
import com.stxb.model.SysApiResult;
import com.stxb.model.SysApiSql;
import com.stxb.model.SysCheck;
import com.stxb.model.SysEncode;
import com.stxb.model.SysFormat;
import com.stxb.model.SysApiInArgException;
import com.stxb.service.biz.ApiService;
import com.stxb.utils.common.SqlHelper;

/**
 * 
 * @author akku
 *
 */
@Service
public class ApiServiceImpl implements ApiService{
	@Autowired
	SysApiSqlDao sqlDao;
	@Autowired
	SysApiInfoDao infoDao;
	@Autowired
	SysApiInArgInfoDao inArgsDao;
	@Autowired
	SysApiOutArgInfoDao outArgsDao;
	@Autowired
	SysApiPlatformDao platformDao;
	@Autowired
	SysFormatDao formatDao;
	@Autowired
	SysEncodeDao encodeDao;
	@Autowired
	SysApiResultDao arDao;
	@Autowired
	SysApiInArgExceptionDao exDao;
	@Autowired
	SysCheckDao checkDao;
	@Autowired
	SysApiResultDao reDao;
	
	
	
	/**
	 * 添加api
	 * @param apiInfo api基本信息
	 * @param inArgs 入参基本信息列表
	 * @param inEncodeList 入参加密方式列表
	 * @param inCheckList 入参数据检查规则列表
	 * @param inExceptionList 入参错误码列表
	 * @param outArgs 出参基本信息列表
	 * @param outEncodeList 出参加密方式列表
	 * @param sql api对应sql
	 * @param reCfg api返回数据格式配置
	 * @return
	 */
	@Override
	@DataSource(DataSource.GOODRABBIT)
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean saveApi(SysApiInfo apiInfo, List<SysApiInArgInfo> inArgs,List<SysEncode> inEncodeList,List<SysCheck> inCheckList,List<SysApiInArgException> inExceptionList,
			List<SysApiOutArgInfo> outArgs,List<SysEncode> outEncodeList, SysApiSql sql ,SysApiResult reCfg) {
			boolean flag = false;
			//检查是否存在该接口
			if(infoDao.getByPIdAndApiNameIncludeDeleted(apiInfo.getApiName(), apiInfo.getpId()).size()!=0){
				return false;
			}
			
			
			try {
				int apiStartId = 0;
				try {
					apiStartId = infoDao.getLastApiId(apiInfo.getpId());
				}catch(Exception e){
					
				}
				//初始化api逻辑id
				apiInfo.setApiId(apiStartId+1);
				//修正api属性
				apiInfo.setInArgsCount(inArgs.size());
				apiInfo.setOutArgsCount(outArgs.size());
				apiInfo.setCreateTime(new Date());
				apiInfo.setState(ApiState.OFF);
				//保存接口基本信息
				infoDao.save(apiInfo);
				//保存入参信息
				for (int i = 0;i<inArgs.size();i++) {
					//保存加密方式
					encodeDao.save(inEncodeList.get(i));
					//提取接口id
					inArgs.get(i).setApiId(apiInfo.getId());
					//提取加密方式id
					inArgs.get(i).setEncode(inEncodeList.get(i).getId());
					//保存入参基本信息
					inArgsDao.save(inArgs.get(i));
					//提取数据检查信息并添加标识id
					inCheckList.get(i).setId(inArgs.get(i).getId());
					//保存数据检查信息
					checkDao.save(inCheckList.get(i));
					//提取错误码并为其添加标识id
					inExceptionList.get(i).setId(inArgs.get(i).getId());
					//保存入参错误码
					exDao.save(inExceptionList.get(i));	
				}
				//保存出参信息
				for (int i = 0;i<outArgs.size();i++) {
					//保存加密方式
					encodeDao.save(outEncodeList.get(i));
					//提取接口id
					outArgs.get(i).setApiId(apiInfo.getId());
					//提取加密方式id
					outArgs.get(i).setEncode(outEncodeList.get(i).getId());
					//保存出参
					outArgsDao.save(outArgs.get(i));
					
				}
				//提取接口id
				sql.setApiId(apiInfo.getId());
				//格式化sql
				sql.setSqlInfo(SqlHelper.toSelectSqlEx(inArgs, outArgs, sql));
				//保存sql
				sqlDao.save(sql);
				
				//提取接口id
				reCfg.setId(apiInfo.getId());
				//保存接口返回数据配置
				reDao.save(reCfg);
				
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag =false;
				throw new RuntimeException();
				
			}
			return flag;
	}
	/**
	 * 添加api
	 * @param apiInfo api基本信息
	 * @param inArgs 入参基本信息列表
	 * @param inEncodeList 入参加密方式列表
	 * @param inCheckList 入参数据检查规则列表
	 * @param inExceptionList 入参错误码列表
	 * @param outArgs 出参基本信息列表
	 * @param outEncodeList 出参加密方式列表
	 * @param sql api对应sql
	 * @param reCfg api返回数据格式配置
	 * @return
	 */
	@Override
	@DataSource(DataSource.GOODRABBIT)
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateAll(SysApiInfo apiInfo, List<SysApiInArgInfo> inArgs,List<SysEncode> inEncodeList,List<SysCheck> inCheckList,List<SysApiInArgException> inExceptionList,
			List<SysApiOutArgInfo> outArgs,List<SysEncode> outEncodeList, SysApiSql sql ,SysApiResult reCfg) {
			boolean flag = false;
			//检查是否存在该接口
			if(infoDao.getById(apiInfo.getId())==null){
				return false;
			}
			
			try {
				
				//修正api属性
				apiInfo.setInArgsCount(inArgs.size());
				apiInfo.setOutArgsCount(outArgs.size());
				apiInfo.setState(ApiState.OFF);
				int infoId = apiInfo.getId();
				//保存接口基本信息
				infoDao.update(apiInfo);
				//获取旧的入参列表并删除
				List<SysApiInArgInfo> oldInList = inArgsDao.get(infoId);
				for(SysApiInArgInfo i : oldInList){
					encodeDao.delete(i.getEncode());
					checkDao.delete(i.getId());
					exDao.delete(i.getId());
					inArgsDao.delete(i.getId());
				}
				//获取旧的出参列表并删除
				List<SysApiOutArgInfo> oldOutList = outArgsDao.get(infoId);
				for(SysApiOutArgInfo o : oldOutList){
					encodeDao.delete(o.getEncode());
					outArgsDao.delete(o.getId());
				}
				
				
				//保存入参信息
				for (int i = 0;i<inArgs.size();i++) {
					//保存加密方式
					encodeDao.save(inEncodeList.get(i));
					//提取接口id
					inArgs.get(i).setApiId(apiInfo.getId());
					//提取加密方式id
					inArgs.get(i).setEncode(inEncodeList.get(i).getId());
					//保存入参基本信息
					inArgsDao.save(inArgs.get(i));
					//提取数据检查信息并添加标识id
					inCheckList.get(i).setId(inArgs.get(i).getId());
					//保存数据检查信息
					checkDao.save(inCheckList.get(i));
					//提取错误码并为其添加标识id
					inExceptionList.get(i).setId(inArgs.get(i).getId());
					//保存入参错误码
					exDao.save(inExceptionList.get(i));	
				}
				//保存出参信息
				for (int i = 0;i<outArgs.size();i++) {
					//保存加密方式
					encodeDao.save(outEncodeList.get(i));
					//提取接口id
					outArgs.get(i).setApiId(apiInfo.getId());
					//提取加密方式id
					outArgs.get(i).setEncode(outEncodeList.get(i).getId());
					//保存出参
					outArgsDao.save(outArgs.get(i));
					
				}
				//提取接口id
				sql.setApiId(infoId);
				//格式化sql
				sql.setSqlInfo(SqlHelper.toSelectSqlEx(inArgs, outArgs, sql));
				sql.setId(sqlDao.get(infoId).getId());
				//保存sql
				sqlDao.update(sql);
				
				//提取接口id
				reCfg.setId(infoId);
				//保存接口返回数据配置
				reDao.update(reCfg);
				
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag =false;
				throw new RuntimeException();
				
			}
			return flag;
			

			/**
			* @author akku
			* 直接修改（暂时废弃）
			*/
//			try {
//				
//				//修正api属性
//				apiInfo.setInArgsCount(inArgs.size());
//				apiInfo.setOutArgsCount(outArgs.size());
//				apiInfo.setCreateTime(new Date());
//				apiInfo.setState(ApiState.OFF);
//				//保存接口基本信息
//				infoDao.update(apiInfo);
//				//保存入参信息
//				for (int i = 0;i<inArgs.size();i++) {
//					//保存加密方式
//					encodeDao.update(inEncodeList.get(i));
//					//保存入参基本信息
//					inArgsDao.update(inArgs.get(i));
//					//保存数据检查信息
//					checkDao.update(inCheckList.get(i));
//					//保存入参错误码
//					exDao.update(inExceptionList.get(i));	
//				}
//				//保存出参信息
//				for (int i = 0;i<outArgs.size();i++) {
//					//保存加密方式
//					encodeDao.update(outEncodeList.get(i));
//					//保存出参
//					outArgsDao.update(outArgs.get(i));
//					
//				}
//				//格式化sql
//				sql.setSqlInfo(SqlHelper.toSelectSqlEx(inArgs, outArgs, sql));
//				//保存sql
//				sqlDao.update(sql);
//				
//				//保存接口返回数据配置
//				reDao.update(reCfg);
//				
//				flag = true;
//			} catch (Exception e) {
//				e.printStackTrace();
//				flag =false;
//				throw new RuntimeException();
//				
//			}
//			return flag;
	}
	/**
	 * 修改api基本信息
	 * @param apiInfo
	 * @return
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean update(SysApiInfo apiInfo){
		
		if(infoDao.getElseSameApiName(apiInfo).size()!=0)
			return false;
		boolean flag = false;
		try {
			infoDao.update(apiInfo);
//			@akku
//			2017年1月11日10:43:53
//			暂时弃用
//			//统一更新该接口所有版本的url和所在平台
//			apiInfo.setpId(infoDao.getById(apiInfo.getId()).getpId()); 
//			infoDao.updateApiNameByP(apiInfo);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag =false;
			throw new RuntimeException();
		}
		
		
		return flag;
	}
	
	
	
	
	//添加平台测试方法
	public boolean initx(){
		//测试平台
		SysApiPlatform p = new SysApiPlatform("测试平台","code","测试平台","scIsvGatewayx",new Date(),false);
		//测试接口
		SysApiInfo api = new SysApiInfo(2,4,"测试接口","testapi",2,9,"type",false,new Date(),2,ApiState.SERVICE);
		
		//测试返回配置
		Map<ResultArg,Object> arp = new HashMap<ResultArg, Object>();
		arp.put(ResultArg.API_CODE, "code");
		arp.put(ResultArg.DATA, "base");
		arp.put(ResultArg.INARGS, "base");
		arp.put(ResultArg.EXCEPTION_CODE, "sub_code");
		SysApiResult ar = new SysApiResult(api.getId(),"isv.af.deposit.info.query","isv.unknow-error",null,arp);
		
		
		//入参
		SysApiInArgInfo i1 = new SysApiInArgInfo(api.getId(),"idcard","idcard",DataType.STRING,false,0,0);
		SysApiInArgInfo i2 = new SysApiInArgInfo(api.getId(),"name","name",DataType.STRING,false,0,0);
		List<SysApiInArgInfo> ilist = new ArrayList<SysApiInArgInfo>();
		ilist.add(i1);
		ilist.add(i2);
		
		//入参错误码
		SysApiInArgException ie1 = new SysApiInArgException(i1.getId(),"isv.invalid-idcard","isv.invalid-idcard","isv.invalid-idcard","isv.invalid-idcard");
		SysApiInArgException ie2 = new SysApiInArgException(i2.getId(),"isv.invalid-name","isv.invalid-name","isv.invalid-name","isv.invalid-name");
		List<SysApiInArgException> iexlist =new ArrayList<SysApiInArgException>();
		iexlist.add(ie1);
		iexlist.add(ie2);
		
		
		//入参加密配置
		Map<EncodeParam,Object> eim = new HashMap<EncodeParam, Object>();
		eim.put(EncodeParam.KEY, "akkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakku");
		SysEncode e =new SysEncode(EncodeMode.DES3_ECB,eim,EncodeMode.NONE,null);
		SysEncode e2 =new SysEncode(EncodeMode.DES3_ECB,null,EncodeMode.DES3_ECB,eim);
		List<SysEncode> ielist  = new ArrayList<SysEncode>();
		ielist.add(e);
		ielist.add(e2);
		
		//入参数据检查配置
		SysCheck ic1 = new SysCheck();
		ic1.setCheckType(CheckType.CHINESE);
		SysCheck ic2 = new SysCheck();
		ic2.setCheckType(CheckType.EMAIL);
		List<SysCheck> iclist = new ArrayList<SysCheck>();
		iclist.add(ic1);
		iclist.add(ic2);
		
		//出参
		SysApiOutArgInfo o1 = new SysApiOutArgInfo(api.getId(),"accu_found_no","accu_found_no",DataType.STRING,false,0,0);
		SysApiOutArgInfo o2 = new SysApiOutArgInfo(api.getId(),"deposit_unit","deposit_unit",DataType.STRING,false,0,0);
		SysApiOutArgInfo o3 = new SysApiOutArgInfo(api.getId(),"last_deposit_date","last_deposit_date",DataType.DATE,false,0,0);
		SysApiOutArgInfo o4 = new SysApiOutArgInfo(api.getId(),"unit_deposit","unit_deposit",DataType.MONEY,false,0,0);
		List<SysApiOutArgInfo> olist = new ArrayList<SysApiOutArgInfo>();
		olist.add(o1);
		olist.add(o2);
		olist.add(o3);
		olist.add(o4);
		//出参加密配置
		SysEncode oe =new SysEncode(EncodeMode.NONE,null,EncodeMode.NONE,null);
		List<SysEncode> oelist = new ArrayList<SysEncode>();
		oelist.add(oe);
		oelist.add(oe);
		oelist.add(oe);
		oelist.add(oe);
		
		//sql编写
		SysApiSql sql = new SysApiSql(api.getId(),"SELECT name,idcard,accu_found_no,deposit_unit,last_deposit_date,unit_deposit,person_deposit,deposit_status,total as sum FROM af_info WHERE af_info.NAME='{name}' AND af_info.idcard = '{idcard}'");
		
		boolean re = saveApi(api,ilist,ielist,iclist,iexlist,olist,oelist,sql,ar);
		
		
		return re;
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean initApi(int id) {
		if(id == 10)
			return initx();
		//公积金个人缴存信息
		if(id == 1){
				//平台
				SysApiPlatform p = new SysApiPlatform("县域平台","code","县域平台","scIsvGateway",new Date(),false);
				platformDao.save(p);
				
				//接口
				SysApiInfo api = new SysApiInfo(1,2,"个人缴存信息","isv.af.deposit.info.query",2,9,"type",false,new Date(),p.getId(),ApiState.SERVICE);
				infoDao.save(api);
				
				//结果配置
				Map<ResultArg,Object> arp = new HashMap<ResultArg, Object>();
				arp.put(ResultArg.API_CODE, "code");
				arp.put(ResultArg.DATA, "base");
				arp.put(ResultArg.INARGS, "base");
				arp.put(ResultArg.EXCEPTION_CODE, "sub_code");
				SysApiResult ar = new SysApiResult(api.getId(),"isv.af.deposit.info.query","isv.unknow-error",null,arp);
				arDao.save(ar);
				
				//入参加密配置
				Map<EncodeParam,Object> eim = new HashMap<EncodeParam, Object>();
				eim.put(EncodeParam.KEY, "akkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakku");
				SysEncode e =new SysEncode(EncodeMode.DES3_ECB,eim,EncodeMode.NONE,null);
				encodeDao.save(e);
				SysEncode e2 =new SysEncode(EncodeMode.DES3_ECB,null,EncodeMode.DES3_ECB,eim);
				encodeDao.save(e2);
				
				
				//格式化配置
				SysFormat f2 = new SysFormat(DataType.DATE,"yyyy-M-d","yyyyMMdd");
				formatDao.save(f2);
				
				//入参
				SysApiInArgInfo i1 = new SysApiInArgInfo(api.getId(),"idcard","idcard",DataType.STRING,false,e.getId(),0);
				inArgsDao.save(i1);
				SysApiInArgException ie1 = new SysApiInArgException(i1.getId(),"isv.invalid-idcard","isv.invalid-idcard","isv.invalid-idcard","isv.invalid-idcard");
				exDao.save(ie1);
				SysApiInArgInfo i2 = new SysApiInArgInfo(api.getId(),"name","name",DataType.STRING,false,0,0);
				inArgsDao.save(i2);
				SysApiInArgException ie2 = new SysApiInArgException(i2.getId(),"isv.invalid-name","isv.invalid-name","isv.invalid-name","isv.invalid-name");
				exDao.save(ie2);
				
				//出参
				SysApiOutArgInfo o1 = new SysApiOutArgInfo(api.getId(),"accu_found_no","accu_found_no",DataType.STRING,false,0,0);
				outArgsDao.save(o1);
				SysApiOutArgInfo o2 = new SysApiOutArgInfo(api.getId(),"deposit_unit","deposit_unit",DataType.STRING,false,0,0);
				outArgsDao.save(o2);
				SysApiOutArgInfo o3 = new SysApiOutArgInfo(api.getId(),"last_deposit_date","last_deposit_date",DataType.DATE,false,0,f2.getId());
				outArgsDao.save(o3);
				SysApiOutArgInfo o4 = new SysApiOutArgInfo(api.getId(),"unit_deposit","unit_deposit",DataType.MONEY,false,0,0);
				outArgsDao.save(o4);
				SysApiOutArgInfo o5 = new SysApiOutArgInfo(api.getId(),"person_deposit","person_deposit",DataType.MONEY,false,0,0);
				outArgsDao.save(o5);
				SysApiOutArgInfo o6 = new SysApiOutArgInfo(api.getId(),"sum","sum",DataType.MONEY,false,0,0);
				outArgsDao.save(o6);
				SysApiOutArgInfo o7 = new SysApiOutArgInfo(api.getId(),"deposit_status","deposit_status",DataType.STRING,false,0,0);
				outArgsDao.save(o7);
				SysApiOutArgInfo o8 = new SysApiOutArgInfo(api.getId(),"idcard","idcard",DataType.STRING,false,e2.getId(),0);
				outArgsDao.save(o8);
				SysApiOutArgInfo o9 = new SysApiOutArgInfo(api.getId(),"name","name",DataType.STRING,false,0,0);
				outArgsDao.save(o9);
				
				//sql
				SysApiSql sql = new SysApiSql(api.getId(),"SELECT name,idcard,accu_found_no,deposit_unit,last_deposit_date,unit_deposit,person_deposit,deposit_status,total as sum FROM af_info WHERE af_info.NAME='{name}' AND af_info.idcard = '{idcard}'");
				sqlDao.save(sql);
				
		}
		//个人缴存明细
		if(id == 2){
			//平台
			SysApiPlatform p = platformDao.get("scIsvGateway");
			
			//接口
			SysApiInfo api = new SysApiInfo(1,2,"个人缴存信息","isv.af.deposit.detail.query",4,9,"type",false,new Date(),p.getId(),ApiState.SERVICE);
			infoDao.save(api);
			
			//结果配置
			Map<ResultArg,Object> arp = new HashMap<ResultArg, Object>();
			arp.put(ResultArg.API_CODE, "code");
			arp.put(ResultArg.DATA, "detail");
			arp.put(ResultArg.INARGS, "null");
			arp.put(ResultArg.EXCEPTION_CODE, "sub_code");
			SysApiResult ar = new SysApiResult(api.getId(),"isv.af.deposit.detail.query","isv.unknow-error",null,arp);
			arDao.save(ar);
			
			//加密配置
			Map<EncodeParam,Object> eim = new HashMap<EncodeParam, Object>();
			eim.put(EncodeParam.KEY, "akkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakku");
			SysEncode e =new SysEncode(EncodeMode.DES3_ECB,eim,EncodeMode.NONE,null);
			encodeDao.save(e);
			SysEncode e2 =new SysEncode(EncodeMode.DES3_ECB,null,EncodeMode.DES3_ECB,eim);
			encodeDao.save(e2);
			
			
			//格式化配置
			SysFormat f = new SysFormat(DataType.DATE,"yyyyMM","yyyy-M-d");
			formatDao.save(f);
			SysFormat f2 = new SysFormat(DataType.DATE,"yyyy-M-d","yyyyMMdd");
			formatDao.save(f2);
			
			//入参
			SysApiInArgInfo i1 = new SysApiInArgInfo(api.getId(),"idcard","idcard",DataType.STRING,false,e.getId(),0);
			inArgsDao.save(i1);
			SysApiInArgException ie1 = new SysApiInArgException(i1.getId(),"isv.invalid-idcard","isv.invalid-idcard","isv.invalid-idcard","isv.invalid-idcard");
			exDao.save(ie1);
			SysApiInArgInfo i2 = new SysApiInArgInfo(api.getId(),"name","name",DataType.STRING,false,0,0);
			inArgsDao.save(i2);
			SysApiInArgException ie2 = new SysApiInArgException(i2.getId(),"isv.invalid-name","isv.invalid-name","isv.invalid-name","isv.invalid-name");
			exDao.save(ie2);
			SysApiInArgInfo i3 = new SysApiInArgInfo(api.getId(),"start_time","start_time",DataType.DATE,false,0,f.getId());
			inArgsDao.save(i3);
			SysApiInArgException ie3 = new SysApiInArgException(i3.getId(),"isv.invalid-start_time","isv.invalid-start_time","isv.invalid-start_time","isv.invalid-start_time");
			exDao.save(ie3);
			SysApiInArgInfo i4 = new SysApiInArgInfo(api.getId(),"end_time","end_time",DataType.DATE,false,0,f.getId());
			inArgsDao.save(i4);
			SysApiInArgException ie4 = new SysApiInArgException(i4.getId(),"isv.invalid-end_time","isv.invalid-end_time","isv.invalid-end_time","isv.invalid-end_time");
			exDao.save(ie4);
			
			
			//出参
			SysApiOutArgInfo o2 = new SysApiOutArgInfo(api.getId(),"deposit_date","deposit_date",DataType.DATE,false,0,f2.getId());
			outArgsDao.save(o2);
			SysApiOutArgInfo o3 = new SysApiOutArgInfo(api.getId(),"deposit_unit","deposit_unit",DataType.STRING,false,0,0);
			outArgsDao.save(o3);
			SysApiOutArgInfo o4 = new SysApiOutArgInfo(api.getId(),"deposit_base","deposit_base",DataType.MONEY,false,0,0);
			outArgsDao.save(o4);
			SysApiOutArgInfo o5 = new SysApiOutArgInfo(api.getId(),"unit_deposit","unit_deposit",DataType.MONEY,false,0,0);
			outArgsDao.save(o5);
			SysApiOutArgInfo o6 = new SysApiOutArgInfo(api.getId(),"person_deposit","person_deposit",DataType.MONEY,false,0,0);
			outArgsDao.save(o6);
			SysApiOutArgInfo o7 = new SysApiOutArgInfo(api.getId(),"sum","sum",DataType.MONEY,false,0,0);
			outArgsDao.save(o7);
			SysApiOutArgInfo o8 = new SysApiOutArgInfo(api.getId(),"is_booked","is_booked",DataType.STRING,false,0,0);
			outArgsDao.save(o8);
			SysApiOutArgInfo o9 = new SysApiOutArgInfo(api.getId(),"idcard","idcard",DataType.STRING,false,e2.getId(),0);
			outArgsDao.save(o9);
			SysApiOutArgInfo o10 = new SysApiOutArgInfo(api.getId(),"name","name",DataType.STRING,false,0,0);
			outArgsDao.save(o10);
			
			//sql
			SysApiSql sql = new SysApiSql(api.getId(),"SELECT af_details.name,af_details.idcard,af_details.deposit_base,af_details.deposit_date,af_details.deposit_unit,af_details.unit_deposit,af_details.total as 'sum',af_details.person_deposit,af_details.is_booked FROM af_details WHERE (deposit_date > '{start_time}' and deposit_date < '{end_time}' ) and af_details.NAME='{name}' AND af_details.idcard = '{idcard}'");
			sqlDao.save(sql);
	}
		
		
		return true;
		
	}
	


}
