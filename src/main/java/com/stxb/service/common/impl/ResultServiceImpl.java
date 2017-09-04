package com.stxb.service.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stxb.database.DataSource;
import com.stxb.exception.ArgCheckException;
import com.stxb.exception.EncodeException;
import com.stxb.exception.FormatException;
import com.stxb.exception.NotnullException;
import com.stxb.exception.ApiException;
import com.stxb.ifs.enums.EnumUtils.ResultArg;
import com.stxb.model.SysApiInArgInfo;
import com.stxb.model.SysApiInfo;
import com.stxb.model.SysApiResult;
import com.stxb.model.SysApiInArgException;
import com.stxb.service.common.ResultService;
import com.stxb.service.data.SysApiInfoService;
import com.stxb.service.data.SysApiResultService;
import com.stxb.service.data.SysApiInArgExceptionService;

@Service
@DataSource(DataSource.GOODRABBIT)
public class ResultServiceImpl implements ResultService{
	@Autowired
	SysApiInfoService apiSer;
	@Autowired
	SysApiInArgExceptionService eSer;
	@Autowired
	SysApiResultService apiResultSer;
	@Override
	public Map<ResultArg,Object> dataResult(Object result,Map inArgs) {
		Map<ResultArg,Object> items = new HashMap<ResultArg,Object>();
		items.put(ResultArg.API_CODE, 1);
		items.put(ResultArg.DATA, result);
		items.put(ResultArg.INARGS, inArgs);
		
		return items;
	}

	@Override
	public Map<ResultArg,Object> exceptionResult(String exceptionCode) {
		Map<ResultArg,Object> items = new HashMap<ResultArg,Object>();
		items.put(ResultArg.API_CODE, 0);
		items.put(ResultArg.EXCEPTION_CODE, exceptionCode.toString());
		return items;
	}

	@Override
	public void throwInArgsError(List<SysApiInArgInfo> errorNotnullArgs,
			List<SysApiInArgInfo> errorEncodeArgs,List<SysApiInArgInfo> errorCheckArgs,
			List<SysApiInArgInfo> errorFormatArgs) throws NotnullException,
			EncodeException, FormatException,ApiException,ArgCheckException {
		if(errorNotnullArgs.size()>0){
			for(SysApiInArgInfo i :errorNotnullArgs){
				SysApiInArgException e =eSer.get(i.getId());
				String eCode = e.getNullException();
				if(e!=null){
					throw new NotnullException(eCode);
				}else
					throw new ApiException(eCode);
			}
		}

		if(errorEncodeArgs.size()>0){
			for(SysApiInArgInfo i :errorEncodeArgs){
				SysApiInArgException e =eSer.get(i.getId());
				String eCode = e.getEncodeException();
				if(e!=null){
					throw new EncodeException(eCode);
				}else
					throw new ApiException(eCode);
			}
		}

		if(errorCheckArgs.size()>0){
			for(SysApiInArgInfo i :errorCheckArgs){
				SysApiInArgException e =eSer.get(i.getId());
				String eCode = e.getArgCheckException();
				if(e!=null){
					throw new ArgCheckException(eCode);
				}else
					throw new ApiException(eCode);
			}
		}
		if(errorFormatArgs.size()>0){
			for(SysApiInArgInfo i :errorFormatArgs){
				SysApiInArgException e =eSer.get(i.getId());
				String eCode = e.getFormatException();
				if(e!=null){
					throw new FormatException(eCode);
				}else
					throw new ApiException(eCode);
			}
		}
	}

	@Override
	public Map<String, Map<String, Object>> dataResultFormat(
			 Map<ResultArg, Object> resultMap,SysApiInfo info) {
		
		//无配置按默认出结果
		if(info==null){
			Map<String, Map<String, Object>> re = new HashMap<String, Map<String, Object>>();
			Map<String,Object> data = new HashMap<String,Object>();
			for(Map.Entry<ResultArg, Object> i : resultMap.entrySet()){
				data.put(i.getKey().toString(), i.getValue());
			}
			
			re.put("result", data);
			return re;
		}
		SysApiResult apiResult = apiResultSer.get(info.getId());
		if(apiResult==null){
			Map<String, Map<String, Object>> re = new HashMap<String, Map<String, Object>>();
			Map<String,Object> data = new HashMap<String,Object>();
			for(Map.Entry<ResultArg, Object> i : resultMap.entrySet()){
				data.put(i.getKey().toString(), i.getValue());
			}
			
			re.put("result", data);
			return re;
		}
		
		
		//获取业务结果码
		int code = (int) resultMap.get(ResultArg.API_CODE);
		
		//获取配置
		Map<ResultArg,Object> cfg = apiResult.getCfgMap();
	
		//获取返回集合名称
		String resultName = apiResult.getResultName();
		
		Map<String, Map<String, Object>> re = new HashMap<String, Map<String, Object>>();
		Map<String,Object> data = new HashMap<String,Object>();
		re.put(resultName,data);
		
		
		for(Map.Entry<ResultArg, Object> cfgItem : cfg.entrySet()){
			Object value = cfgItem.getValue();
			if(value instanceof String){
				String v = value.toString().trim();
				if(v.equalsIgnoreCase("base".trim())){
					System.out.println(cfgItem.getKey());
					if(ResultArg.valueOf(cfgItem.getKey().toString()) == ResultArg.DATA){
						List<Map<String,Object>> rp = (List<Map<String,Object>>)resultMap.get(cfgItem.getKey());
						if(rp==null)
							continue;
						if(rp.size()==0)
							continue;
						//如果结果不唯一 配置返回数据
						if(rp.size()>1){
							for(int i =0;i<rp.size();i++){
								for(Map.Entry<String,Object> obj : (rp.get(i).entrySet())){
									re.get(resultName).put(obj.getKey()+"["+i+"]", obj.getValue() 	);
								}
							}
						//如果结果唯一配置返回数据
						}else{
							for(Map.Entry<String,Object> obj : (rp.get(0).entrySet())){
								re.get(resultName).put(obj.getKey(), obj.getValue() 	);
							}
						}
					}
					if(ResultArg.valueOf(cfgItem.getKey().toString())  == ResultArg.INARGS){
						for(Map.Entry<String,Object> i : ((Map<String,Object>)resultMap.get(cfgItem.getKey())).entrySet()){
							re.get(resultName).put(i.getKey(), i.getValue() 	);
						}
					}
					continue;
				}else if(v.equalsIgnoreCase("null".trim())){
					
					continue;
				}else{
					
					if(ResultArg.valueOf(cfgItem.getKey().toString()) != ResultArg.EXCEPTION_CODE)
						re.get(resultName).put(v, resultMap.get(cfgItem.getKey()));
				}
				//data.put(value.toString(), resultMap.get(cfgItem.getKey()));
			}
			
			
		}
		//强行翻译
		re.get(resultName).put("msg",code==0?"业务处理失败":"成功");
		return re;
	}

	@Override
	public Map<String, Map<String, Object>> exceptionResultFormat(
			Map<ResultArg, Object> exceptionResult, SysApiInfo info) {
		if(info==null){
			Map<String, Map<String, Object>> re = new HashMap<String, Map<String, Object>>();
			Map<String,Object> data = new HashMap<String,Object>();
			for(Map.Entry<ResultArg, Object> i : exceptionResult.entrySet()){
				data.put(i.getKey().toString(), i.getValue());
			}
			
			re.put("result", data);
			return re;
		}
		
		
		SysApiResult apiResult = apiResultSer.get(info.getId());
		//无配置按默认出结果
		if(apiResult==null){
			Map<String, Map<String, Object>> re = new HashMap<String, Map<String, Object>>();
			Map<String,Object> data = new HashMap<String,Object>();
			for(Map.Entry<ResultArg, Object> i : exceptionResult.entrySet()){
				data.put(i.getKey().toString(), i.getValue());
			}
			
			re.put("result", data);
			return re;
		}
		//获取业务结果码
		int code = (int) exceptionResult.get(ResultArg.API_CODE);
		
		//获取配置
		Map<ResultArg,Object> cfg = apiResult.getCfgMap();
	
		//获取返回集合名称
		String resultName = apiResult.getResultName();
		
		Map<String, Map<String, Object>> re = new HashMap<String, Map<String, Object>>();
		Map<String,Object> data = new HashMap<String,Object>();
		re.put(resultName,data);
		//添加业务码
		re.get(resultName).put(cfg.get(ResultArg.API_CODE)==null?ResultArg.API_CODE.toString():cfg.get(ResultArg.API_CODE).toString(), exceptionResult.get(ResultArg.API_CODE));
	
		//添加错误信息
		re.get(resultName).put(cfg.get(ResultArg.EXCEPTION_CODE)==null?ResultArg.EXCEPTION_CODE.toString():cfg.get(ResultArg.EXCEPTION_CODE).toString(), exceptionResult.get(ResultArg.EXCEPTION_CODE));
		
		//强行翻译
		re.get(resultName).put("msg",code==0?"业务处理失败":"成功");
		String eCode = exceptionResult.get(ResultArg.EXCEPTION_CODE).toString().trim();
		if(eCode.equalsIgnoreCase("isv.invalid-method".trim()))
			re.get(resultName).put("sub_msg","接口名称验证不通过，method为空或无效");
		if(eCode.equalsIgnoreCase("isv.invalid-idcard".trim()))
			re.get(resultName).put("sub_msg","idcard验证不通过，idcard为空或无效");
		if(eCode.equalsIgnoreCase("isv.invalid-name".trim()))
			re.get(resultName).put("sub_msg","name验证不通过，name为空或无效");
		if(eCode.equalsIgnoreCase("isv.invalid-start_time".trim()))
			re.get(resultName).put("sub_msg","start_time验证不通过，start_time为空或无效（格式不对）");
		if(eCode.equalsIgnoreCase("isv.invalid-end_time".trim()))
			re.get(resultName).put("sub_msg","end_time验证不通过，end_time为空或无效（格式不对）");
		if(eCode.equalsIgnoreCase("isv.unknow-error".trim()))
			re.get(resultName).put("sub_msg","ISV系统异常");
		
		return re;
	}
	
	
	
	
	
	

}
