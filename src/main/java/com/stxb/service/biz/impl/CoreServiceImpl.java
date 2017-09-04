package com.stxb.service.biz.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stxb.ifs.enums.EnumUtils.CheckType;
import com.stxb.ifs.enums.EnumUtils.SqlExecuteMode;
import com.stxb.exception.ArgCheckException;
import com.stxb.exception.EncodeException;
import com.stxb.exception.FormatException;
import com.stxb.exception.NotnullException;
import com.stxb.exception.common.SqlSearchException;
import com.stxb.exception.ApiException;
import com.stxb.model.SysApiInArgInfo;
import com.stxb.model.SysApiInfo;
import com.stxb.model.SysApiOutArgInfo;
import com.stxb.model.SysApiPlatformCACfg;
import com.stxb.model.SysApiSql;
import com.stxb.model.SysCheck;
import com.stxb.model.SysEncode;
import com.stxb.model.SysFormat;
import com.stxb.service.biz.CoreService;
import com.stxb.service.common.ResultService;
import com.stxb.service.common.SqlExecuteService;
import com.stxb.service.data.SysApiInArgInfoService;
import com.stxb.service.data.SysApiInfoService;
import com.stxb.service.data.SysApiOutArgInfoService;
import com.stxb.service.data.SysApiPlatformCACfgService;
import com.stxb.service.data.SysApiResultService;
import com.stxb.service.data.SysApiSqlService;
import com.stxb.service.data.SysCheckService;
import com.stxb.service.data.SysEncodeService;
import com.stxb.service.data.SysFormatService;
import com.stxb.service.biz.ApiService;
import com.stxb.utils.check.CheckHelper;
import com.stxb.utils.common.SqlHelper;
import com.stxb.utils.common.UrlHelper;
import com.stxb.utils.encode.EncodeHelper;
import com.stxb.utils.format.FormatHelper;

@Service
public class CoreServiceImpl implements CoreService {
	@Autowired
	ApiService ser;
	@Autowired
	SqlExecuteService searchSer;
	@Autowired
	SysApiInfoService apiSer;
	@Autowired
	SysApiInArgInfoService inSer;
	@Autowired
	SysApiOutArgInfoService outSer;
	@Autowired
	SysApiSqlService sqlSer;
	@Autowired
	SysEncodeService encodeSer;
	@Autowired
	SysFormatService formatSer;
	@Autowired
	ResultService resultSer;
	@Autowired
	SysApiResultService apiResultSer;
	@Autowired
	SysCheckService checkSer;
	@Autowired
	SysApiPlatformCACfgService pcacSer;

	@Override
	public List<Map<Object, Object>> searchSql(SysApiInfo info, HttpServletRequest request)
			throws EncodeException, NotnullException, FormatException, SqlSearchException, ApiException, ArgCheckException {
		SysApiSql apiSql;
		List<SysApiInArgInfo> inArgsList;
		List<SysApiOutArgInfo> outArgsList;

		List<Map<Object, Object>> re = new ArrayList<Map<Object, Object>>();
		SysApiPlatformCACfg pcac = pcacSer.get(info.getpId());
		
		// 匹配是否有该接口
		if (info == null) {
			if(pcac == null)
				throw new ApiException("api not found");
			else
				throw new ApiException(pcac.getNotFoundApiException());
		}
		apiSql = sqlSer.get(info.getId());
		inArgsList = inSer.get(info.getId());
		outArgsList = outSer.get(info.getId());
		String sql = apiSql.getSqlInfo();

		// 入参解密

		
		/**
		 * 修改人	: Souls
		 * 修改时间	: 2017-1-4 10:03:03
		 * 操作		: 提取方法到 UrlHelper
		 */
		// 提取参数
		
		
		
		Map<String, String> args = new HashMap<String, String>();
		if(pcac==null){
			int mode = 0;
			
			switch (mode) {
			case 0:
				args = UrlHelper.Request2Map(request);
				break;
			case 1:
				try {
					args = UrlHelper.Request2Map(request,"biz_content");
				} catch (IOException e1) {
				}
				break;
			default:
				break;
			}
		}else{
			switch (pcac.getInArgMode()) {
			case DEFAULT:
				args = UrlHelper.Request2Map(request);
				break;
			case JSON:
				try {
					args = UrlHelper.Request2Map(request,pcac.getInArgCfg().get("JSONKEY").toString().trim());
				} catch (IOException e1) {
				}
			default:
				break;
			}
		}
		
			
		
		
		// 错误信息收集
		List<SysApiInArgInfo> errorNotnullArgs = new ArrayList<SysApiInArgInfo>();
		List<SysApiInArgInfo> errorEncodeArgs = new ArrayList<SysApiInArgInfo>();
		List<SysApiInArgInfo> errorCheckArgs = new ArrayList<SysApiInArgInfo>();
		List<SysApiInArgInfo> errorFormatArgs = new ArrayList<SysApiInArgInfo>();
		//
		for (SysApiInArgInfo iac : inArgsList) {
			Object iacValue = args.get(iac.getCloName());
			//入参非空验证
			if (iacValue == null) {
				if (iac.isNull() == false) {
					errorNotnullArgs.add(iac);
				}
				continue;
			}
			//入参解密
			int iacEncodeCode = iac.getEncode();
			if (iacEncodeCode > 0) {
				SysEncode encode = encodeSer.get(iacEncodeCode);
				try {
					args.put(iac.getCloName(),EncodeHelper.decode(args.get(iac.getCloName()), encode));
				} catch (Exception e) {
					errorEncodeArgs.add(iac);
					continue;
				}
			}
			//入参效验
			SysCheck check = checkSer.get(iac.getId());
			if(check!=null){
				if (check.getType()!=CheckType.NONE) {
					try {
						if (CheckHelper.check(check,formatSer.get(iac.getFormat()),args.get(iac.getCloName())) == false){
							errorCheckArgs.add(iac);
						}
					} catch (Exception e) {
						errorCheckArgs.add(iac);
						continue;
					}
				}
			}
			
			
			//入参加密
			if (iacEncodeCode > 0) {
				SysEncode encode = encodeSer.get(iacEncodeCode);
				try {
					args.put(iac.getCloName(),EncodeHelper.encode(args.get(iac.getCloName()), encode));
				} catch (Exception e) {
					errorEncodeArgs.add(iac);
					continue;
				}
			}

			
			//入参格式化
			int iacFormartCode = iac.getFormat();
			if (iacFormartCode > 0) {
				SysFormat sysFormat = formatSer.get(iacFormartCode);
				try {
					args.put(iac.getCloName(),FormatHelper.format(sysFormat,args.get(iac.getCloName()).toString()));
				} catch (Exception e) {
					errorFormatArgs.add(iac);
					continue;
				}
			}
		}
		
		// 报错
		resultSer.throwInArgsError(errorNotnullArgs, errorEncodeArgs,errorCheckArgs, errorFormatArgs);
		


		// 参数传入sql
		sql = SqlHelper.toSearchSqlEx(args, sql);

		try {
			re = searchSer.sqlSearch(sql, SqlExecuteMode.SEARCH);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		
		
		// 报错信息收集
		List<SysApiOutArgInfo> errorNotnullArgsO = new ArrayList<SysApiOutArgInfo>();
		List<SysApiOutArgInfo> errorEncodeArgsO = new ArrayList<SysApiOutArgInfo>();
		List<SysApiOutArgInfo> errorFormatArgsO = new ArrayList<SysApiOutArgInfo>();
		
		if (re == null) {
			throw new SqlSearchException(apiResultSer.get(info.getId()).getExceptionCode());
		}
		//出参配置
		for (Map<Object, Object> data : re) {
			for (SysApiOutArgInfo oac : outArgsList) {
				Object oacValue = data.get(oac.getCloName());
				//出参非空验证
				if (oacValue == null) {
					if (oac.isNull() == false) {
						errorNotnullArgsO.add(oac);
					}
					continue;
				}
				//出参格式化
				int oacFormartCode = oac.getFormat();
				if (oacFormartCode > 0) {
					SysFormat sysFormat = formatSer.get(oacFormartCode);
					try {
						data.put(oac.getCloName(),FormatHelper.format(sysFormat,data.get(oac.getCloName()).toString()));
					} catch (Exception e) {
						errorFormatArgsO.add(oac);
						continue;
					}
				}
				
				
				
				// 出参解密（同时格式化&加密可能会有问题）
				int oacEncodeCode = oac.getEncode();
				if (oacEncodeCode > 0) {
					SysEncode encode = encodeSer.get(oacEncodeCode);
					try {
						data.put(oac.getCloName(),EncodeHelper.decode(data.get(oac.getCloName()).toString(),encode));
					} catch (Exception e) {
						errorEncodeArgsO.add(oac);
						continue;
					}
				}	
				//出参加密
				if (oacEncodeCode > 0) {
					SysEncode encode = encodeSer.get(oacEncodeCode);
					try {
						data.put(oac.getCloName(),EncodeHelper.encode(data.get(oac.getCloName()).toString(),encode));
					} catch (Exception e) {
						errorEncodeArgsO.add(oac);
						continue;
					}
				}	
			}
		}
		//ExceptionHelper.CheckOutArgsError(errorNotnullArgsO, errorEncodeArgsO, errorFormatArgsO);

		return re;
	}

}
