package com.stxb.controller;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stxb.model.SysApiInArgInfo;
import com.stxb.model.SysApiInfo;
import com.stxb.model.SysApiOutArgInfo;
import com.stxb.model.SysApiResult;
import com.stxb.model.SysApiSql;
import com.stxb.model.SysCheck;
import com.stxb.model.SysEncode;
import com.stxb.model.ex.SysApiInArgInfoEx;
import com.stxb.model.ex.SysApiOutArgInfoEx;
import com.stxb.model.ex.SysCheckEx;
import com.stxb.model.ex.SysEncodeEx;
import com.stxb.model.SysApiInArgException;
import com.stxb.model.ex.SysApiInArgExceptionEx;
import com.stxb.service.biz.CoreService;
import com.stxb.service.common.SqlExecuteService;
import com.stxb.service.data.SysApiInArgInfoService;
import com.stxb.service.data.SysApiInfoService;
import com.stxb.service.data.SysApiOutArgInfoService;
import com.stxb.service.data.SysApiPlatformService;
import com.stxb.service.data.SysApiResultService;
import com.stxb.service.data.SysApiSqlService;
import com.stxb.service.data.SysCheckService;
import com.stxb.service.data.SysEncodeService;
import com.stxb.service.data.SysFormatService;
import com.stxb.service.biz.ApiService;
import com.stxb.service.data.SysApiInArgExceptionService;
import com.stxb.utils.common.ParseUtils;
import com.stxb.utils.common.ResponseCode;

@Controller
@RequestMapping("/api")
/**
 * api相关接口
 * @author akku  修改人 TanXiaoYang 2017-1-3 15:46:28
 * 修改说明 : 删除冗余代码
 */
public class ApiController {
	@Autowired
	ApiService ser;
	@Autowired
	SqlExecuteService sqlSer;
	@Autowired
	CoreService coreSer;
	@Autowired
	SysApiPlatformService pSer;
	@Autowired
	SysFormatService formatSer;
	@Autowired
	SysApiInfoService infoSer;
	@Autowired
	SysApiInArgInfoService inSer;
	@Autowired
	SysEncodeService encodeSer;
	@Autowired
	SysApiInArgExceptionService exSer;
	@Autowired
	SysCheckService checkSer;
	@Autowired
	SysApiOutArgInfoService outSer;
	@Autowired
	SysApiResultService reSer;
	@Autowired
	SysApiSqlService aSqlSer;

	/**
	 * 添加api
	 * 
	 * @param info
	 *            基本信息
	 * @param inArgs
	 *            入参基本信息列表
	 * @param inEncode
	 *            入参加密规则列表
	 * @param incheck
	 *            入参数据检查规则列表
	 * @param inexception
	 *            入参错误码列表
	 * @param outArgs
	 *            出参基本信息列表
	 * @param outEncode
	 *            出参加密规则列表
	 * @param sql
	 *            sql语句
	 * @param apiResult
	 *            api返回数据格式配置
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Map<String, Object> saveApi(
			@ModelAttribute("apiinfo") SysApiInfo info,
			@ModelAttribute("inargslist") SysApiInArgInfoEx inArgs,
			@ModelAttribute("inencodelist") SysEncodeEx inEncode,
			@ModelAttribute("inchecklist") SysCheckEx incheck,
			@ModelAttribute("inexceptionlist") SysApiInArgExceptionEx inexception,
			@ModelAttribute("outargslist") SysApiOutArgInfoEx outArgs,
			@ModelAttribute("outencodelist") SysEncodeEx outEncode,
			@ModelAttribute("apisql") SysApiSql sql,
			@ModelAttribute("apiresult") SysApiResult apiResult) {
		
		
		Map<String, Object> re = new HashMap<String, Object>();
		int code = ResponseCode.Error.ERROR;
		try {
			if(ser.saveApi(info, inArgs.getInArgsList(),
					inEncode.getEncodeList(), incheck.getCheckList(),
					inexception.getExceptionList(), outArgs.getOutArgsList(),
					outEncode.getEncodeList(), sql, apiResult)){
				code = ResponseCode.Success.OK;
			}else{
				code = ResponseCode.Warning.Error;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("e:"+e.getMessage());
			code = ResponseCode.Error.ERROR;
		}
		re.put(ResponseCode.RESPONSE_CODE , code);
		return re;
	}
	/**
	 * 修改api完整信息
	 * 
	 * @param info
	 *            基本信息
	 * @param inArgs
	 *            入参基本信息列表
	 * @param inEncode
	 *            入参加密规则列表
	 * @param incheck
	 *            入参数据检查规则列表
	 * @param inexception
	 *            入参错误码列表
	 * @param outArgs
	 *            出参基本信息列表
	 * @param outEncode
	 *            出参加密规则列表
	 * @param sql
	 *            sql语句
	 * @param apiResult
	 *            api返回数据格式配置
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateall", method = RequestMethod.POST)
	public Map<String, Object> updateAll(
			@ModelAttribute("apiinfo") SysApiInfo info,
			@ModelAttribute("inargslist") SysApiInArgInfoEx inArgs,
			@ModelAttribute("inencodelist") SysEncodeEx inEncode,
			@ModelAttribute("inchecklist") SysCheckEx incheck,
			@ModelAttribute("inexceptionlist") SysApiInArgExceptionEx inexception,
			@ModelAttribute("outargslist") SysApiOutArgInfoEx outArgs,
			@ModelAttribute("outencodelist") SysEncodeEx outEncode,
			@ModelAttribute("apisql") SysApiSql sql,
			@ModelAttribute("apiresult") SysApiResult apiResult) {
		System.out.print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.print(info);
		
		Map<String, Object> re = new HashMap<String, Object>();
		int code = ResponseCode.Error.ERROR;
		try {
			if(ser.updateAll(info, inArgs.getInArgsList(),
					inEncode.getEncodeList(), incheck.getCheckList(),
					inexception.getExceptionList(), outArgs.getOutArgsList(),
					outEncode.getEncodeList(), sql, apiResult)){
				code = ResponseCode.Success.OK;
			}else{
				code = ResponseCode.Warning.Error;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("e:"+e.getMessage());
			code = ResponseCode.Error.ERROR;
		}
		re.put(ResponseCode.RESPONSE_CODE , code);
		return re;
	}

	/**
	 * 获取指定接口基本信息
	 * 
	 * @param id
	 *            接口标识id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> get(@PathVariable int id,String mode) {
		int modeX = 0;
		Map<String, Object> re = new HashMap<String, Object>();
		int code = ResponseCode.Error.ERROR;
		
		try {
			modeX = Integer.parseInt(mode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		switch(modeX){
		case 0:
			SysApiInfo result;
			try {
				result = infoSer.getByIdIncludeDeleted(id);
				if (result != null)
					code = ResponseCode.Success.OK;
				else
					code = ResponseCode.Warning.NO_RESULT;
				re.put(ResponseCode.RESPONSE_DATA, result);
			} catch (Exception e) {
				code = ResponseCode.Error.ERROR;
			}
			break;
		case 1:
			Map<String,Object> details = new HashMap<>();
			try {
				//查询基本信息
				details.put("apiinfo", infoSer.getByIdIncludeDeleted(id));
				//查询入参基本信息列表
				List<SysApiInArgInfo> inList = inSer.get(id);
				details.put("inargslist",inList);
				//查询入参加密配置列表
				SysEncodeEx enList = new SysEncodeEx();
				for(SysApiInArgInfo i : inList){
					enList.getEncodeList().add(encodeSer.get(i.getEncode()));
				}
				details.put("inencodelist",enList);
				//查询入校验配置参			
				SysCheckEx ckList = new SysCheckEx();
				for(SysApiInArgInfo i : inList){
					ckList.getCheckList().add(checkSer.get(i.getId()));
				}
				details.put("inchecklist",ckList);
				//查询入参错误码配置
				SysApiInArgExceptionEx exList = new SysApiInArgExceptionEx();
				for(SysApiInArgInfo i : inList){
					exList.getExceptionList().add(exSer.get(i.getId()));
				}
				details.put("inexceptionlist", exList);
				//查询出参基本信息列表
				List<SysApiOutArgInfo> outList = outSer.get(id);
				details.put("outargslist", outList);
				//查询入参加密配置列表
				SysEncodeEx oenList = new SysEncodeEx();
				for(SysApiOutArgInfo o : outList){
					oenList.getEncodeList().add(encodeSer.get(o.getEncode()));
				}
				details.put("outencodelist",oenList);
				//查询sql配置信息
				details.put("apisql",aSqlSer.get(id));
				//查询接口返回配置
				details.put("apiresult",reSer.get(id));
				if (details != null&&details.size() >0)
					code = ResponseCode.Success.OK;
				else
					code = ResponseCode.Warning.NO_RESULT;
				re.put(ResponseCode.RESPONSE_DATA, details);
			} catch (Exception e) {
				e.printStackTrace();
				code = ResponseCode.Error.ERROR;
			}
			break;
		case 2:
			Map<String,Object> detailsXX= new HashMap<>();
			try {
				//查询基本信息
				for(Map.Entry<String, Object> m :ParseUtils.obj2Map(infoSer.getByIdIncludeDeleted(id)).entrySet()){
					detailsXX.put("apiinfo."+m.getKey(),m.getValue());
				}
				//查询入参基本信息列表
				List<SysApiInArgInfo> inList = inSer.get(id);
				for(int i =0;i<inList.size();i++){
					for(Map.Entry<String, Object> m :ParseUtils.obj2Map(inList.get(i)).entrySet()){
						detailsXX.put("inargslist.inArgsList["+i+"]."+m.getKey(),m.getValue());
					}
				}
				//查询入参加密配置列表
				SysEncodeEx enList = new SysEncodeEx();
				for(SysApiInArgInfo i : inList){
					enList.getEncodeList().add(encodeSer.get(i.getEncode()));
				}
				for(int i =0;i<enList.getEncodeList().size();i++){
					for(Map.Entry<String, Object> m :ParseUtils.obj2Map(enList.getEncodeList().get(i)).entrySet()){
						detailsXX.put("inencodelist.encodeList["+i+"]."+m.getKey(),m.getValue());
					}
				}
				//查询入校验配置参			
				SysCheckEx ckList = new SysCheckEx();
				for(SysApiInArgInfo i : inList){
					ckList.getCheckList().add(checkSer.get(i.getId()));
				}
				for(int i =0;i<ckList.getCheckList().size();i++){
					for(Map.Entry<String, Object> m :ParseUtils.obj2Map(ckList.getCheckList().get(i)).entrySet()){
						detailsXX.put("inchecklist.checkList["+i+"]."+m.getKey(),m.getValue());
					}
				}
				//查询入参错误码配置
				SysApiInArgExceptionEx exList = new SysApiInArgExceptionEx();
				for(SysApiInArgInfo i : inList){
					exList.getExceptionList().add(exSer.get(i.getId()));
				}
				for(int i =0;i<exList.getExceptionList().size();i++){
					for(Map.Entry<String, Object> m :ParseUtils.obj2Map(exList.getExceptionList().get(i)).entrySet()){
						detailsXX.put("inexceptionlist.exceptionList["+i+"]."+m.getKey(),m.getValue());
					}
				}
				//查询出参基本信息列表
				List<SysApiOutArgInfo> outList = outSer.get(id);
				for(int i =0;i<outList.size();i++){
					for(Map.Entry<String, Object> m :ParseUtils.obj2Map(outList.get(i)).entrySet()){
						detailsXX.put("outargslist.outArgsList["+i+"]."+m.getKey(),m.getValue());
					}
				}
				//查询入参加密配置列表
				SysEncodeEx oenList = new SysEncodeEx();
				for(SysApiOutArgInfo o : outList){
					oenList.getEncodeList().add(encodeSer.get(o.getEncode()));
				}
				for(int i =0;i<oenList.getEncodeList().size();i++){
					for(Map.Entry<String, Object> m :ParseUtils.obj2Map(oenList.getEncodeList().get(i)).entrySet()){
						detailsXX.put("outencodelist.encodeList["+i+"]."+m.getKey(),m.getValue());
					}
				}
				//查询sql配置信息
				for(Map.Entry<String, Object> m :ParseUtils.obj2Map(aSqlSer.get(id)).entrySet()){
					detailsXX.put("apisql."+m.getKey(),m.getValue());
				}
				//查询接口返回配置
				for(Map.Entry<String, Object> m :ParseUtils.obj2Map(reSer.get(id)).entrySet()){
					detailsXX.put("apiresult."+m.getKey(),m.getValue());
				}
				if (detailsXX != null&&detailsXX.size() >0)
					code = ResponseCode.Success.OK;
				else
					code = ResponseCode.Warning.NO_RESULT;
				re.put(ResponseCode.RESPONSE_DATA, detailsXX);
			} catch (Exception e) {
				e.printStackTrace();
				code = ResponseCode.Error.ERROR;
			}
			
			
			
			
			
			
			
			
			
			
			break;
		default:
			SysApiInfo resultD = null;
			try {
				result = infoSer.getByIdIncludeDeleted(id);
				if (result != null)
					code = ResponseCode.Success.OK;
				else
					code = ResponseCode.Warning.NO_RESULT;
				re.put(ResponseCode.RESPONSE_DATA, resultD);
			} catch (Exception e) {
				code = ResponseCode.Error.ERROR;
			}
			break;
		}
		
		

		re.put(ResponseCode.RESPONSE_CODE , code);
		return re;
	}

	/**
	 * 修改api接口，如果修改了url或pid 会对该接口所有不同版本一起进行修改
	 * 
	 * @param id
	 *            接口标识id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> update(@ModelAttribute("apiinfo") SysApiInfo info) {
		info = infoSer.getById(18);
		Map<String, Object> re = new HashMap<String, Object>();
		
		int code = ResponseCode.Error.ERROR;
		try {
			ser.update(info);
			code = ResponseCode.Success.OK;

		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}
		re.put(ResponseCode.RESPONSE_CODE , code);
		return re;
	}

	/**
	 * 删除指定接口
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> delete(int id) {
		Map<String, Object> re = new HashMap<String, Object>();

		int code = ResponseCode.Error.ERROR;
		try {
			infoSer.delete(id);
			code = ResponseCode.Success.OK;

		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}
		re.put(ResponseCode.RESPONSE_CODE , code);
		return re;
	}

	/**
	 * 恢复指定接口（恢复后默认状态为禁用）
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/undelete", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> undelete(int id) {
		Map<String, Object> re = new HashMap<String, Object>();

		int code = ResponseCode.Error.ERROR;
		try {
			infoSer.unDelete(id);
			code = ResponseCode.Success.OK;

		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}
		re.put(ResponseCode.RESPONSE_CODE , code);
		return re;
	}

	/**
	 * 获取指定接口的入参列表
	 * 
	 * @param id
	 *            接口标识id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}/getinlist", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> getInArgsList(@PathVariable int id) {
		Map<String, Object> re = new HashMap<String, Object>();
		List<SysApiInArgInfo> result;
		int code = ResponseCode.Error.ERROR;
		try {
			result = inSer.get(id);
			if (result != null && result.size() > 0)
				code = ResponseCode.Success.OK;
			else
				code = ResponseCode.Warning.NO_RESULT;
			re.put(ResponseCode.RESPONSE_DATA, result);
		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}

		re.put(ResponseCode.RESPONSE_CODE , code);
		return re;
	}

	/**
	 * 获取指定接口的出参列表
	 * 
	 * @param id
	 *            接口id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}/getoutlist", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> getOutArgsList(@PathVariable int id) {
		Map<String, Object> re = new HashMap<String, Object>();
		List<SysApiOutArgInfo> result;
		int code = ResponseCode.Error.ERROR;
		try {
			result = outSer.get(id);
			if (result != null && result.size() > 0)
				code = ResponseCode.Success.OK;
			else
				code = ResponseCode.Warning.NO_RESULT;
			re.put(ResponseCode.RESPONSE_DATA, result);
		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}

		re.put(ResponseCode.RESPONSE_CODE , code);
		return re;
	}

	/**
	 * 获取指定入参的基本信息
	 * 
	 * @param id
	 *            入参标识id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getin", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> getIn(int id) {
		Map<String, Object> re = new HashMap<String, Object>();
		SysApiInArgInfo result;
		int code = ResponseCode.Error.ERROR;
		try {
			result = inSer.getById(id);
			if (result != null)
				code = ResponseCode.Success.OK;
			else
				code = ResponseCode.Warning.NO_RESULT;
			re.put(ResponseCode.RESPONSE_DATA, result);
		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}

		re.put(ResponseCode.RESPONSE_CODE , code);
		return re;
	}

	/**
	 * 获取指定出参的基本id
	 * 
	 * @param id
	 *            出参标识id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getout", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> getOut(int id) {
		Map<String, Object> re = new HashMap<String, Object>();
		SysApiOutArgInfo result;
		int code = ResponseCode.Error.ERROR;
		try {
			result = outSer.getById(id);
			if (result != null)
				code = ResponseCode.Success.OK;
			else
				code = ResponseCode.Warning.NO_RESULT;
			re.put(ResponseCode.RESPONSE_DATA, result);
		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}

		re.put(ResponseCode.RESPONSE_CODE , code);
		return re;
	}

	/**
	 * 获取指定入参的加密信息
	 * 
	 * @param id
	 *            入参标识id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getinencode", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> getInEncode(int id) {
		Map<String, Object> re = new HashMap<String, Object>();
		SysEncode result;
		int code = ResponseCode.Error.ERROR;
		try {
			result = encodeSer.get(inSer.getById(id).getEncode());
			if (result != null)
				code = ResponseCode.Success.OK;
			else
				code = ResponseCode.Warning.NO_RESULT;
			re.put(ResponseCode.RESPONSE_DATA, result);
		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}

		re.put(ResponseCode.RESPONSE_CODE , code);
		return re;
	}

	/**
	 * 获取指定出参的加密方法
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getoutencode", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> getOutEncode(int id) {
		Map<String, Object> re = new HashMap<String, Object>();
		SysEncode result;
		int code = ResponseCode.Error.ERROR;
		try {
			result = encodeSer.get(outSer.getById(id).getEncode());
			if (result != null)
				code = ResponseCode.Success.OK;
			else
				code = ResponseCode.Warning.NO_RESULT;
			re.put(ResponseCode.RESPONSE_DATA, result);
		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}

		re.put(ResponseCode.RESPONSE_CODE , code);
		return re;
	}

	/**
	 * 获取指定入参的错误码信息
	 * 
	 * @param id
	 *            入参标识id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getinexception", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> getInEx(int id) {
		Map<String, Object> re = new HashMap<String, Object>();
		SysApiInArgException result;
		int code = ResponseCode.Error.ERROR;
		try {
			result = exSer.get(id);
			if (result != null)
				code = ResponseCode.Success.OK;
			else
				code = ResponseCode.Warning.NO_RESULT;
			re.put(ResponseCode.RESPONSE_DATA, result);
		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}

		re.put(ResponseCode.RESPONSE_CODE , code);
		return re;
	}

	/**
	 * 获取指定入参的数据检查方法
	 * 
	 * @param id
	 *            入参标识id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getincheck", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> getInCheck(int id) {
		Map<String, Object> re = new HashMap<String, Object>();
		SysCheck result;
		int code = ResponseCode.Error.ERROR;
		try {
			result = checkSer.get(id);
			if (result != null)
				code = ResponseCode.Success.OK;
			else
				code = ResponseCode.Warning.NO_RESULT;
			re.put(ResponseCode.RESPONSE_DATA, result);
		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}

		re.put(ResponseCode.RESPONSE_CODE , code);
		return re;
	}

	/**
	 * 获取接口的返回数据格式配置
	 * 
	 * @param id
	 *            接口标识id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}/getrecfg", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> getResultCfg(@PathVariable int id) {
		Map<String, Object> re = new HashMap<String, Object>();
		SysApiResult result;
		int code = ResponseCode.Error.ERROR;
		try {
			result = reSer.get(id);
			if (result != null)
				code = ResponseCode.Success.OK;
			else
				code = ResponseCode.Warning.NO_RESULT;
			re.put(ResponseCode.RESPONSE_DATA, result);
		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}

		re.put(ResponseCode.RESPONSE_CODE , code);
		return re;
	}

	/**
	 * 修改接口的返回数据格式配置
	 * 
	 * @param recfg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updaterecfg", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> updateIn(@ModelAttribute("apiresult") SysApiResult recfg) {
		Map<String, Object> re = new HashMap<String, Object>();
		
		int code = ResponseCode.Error.ERROR;
		try {
			reSer.update(recfg);
			code = ResponseCode.Success.OK;

		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}
		re.put(ResponseCode.RESPONSE_CODE , code);
		return re;
	}

	/**
	 * 获取接口对应的sql语句
	 * 
	 * @param id
	 *            接口标识id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}/getsql", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> getSql(@PathVariable int id) {
		Map<String, Object> re = new HashMap<String, Object>();
		SysApiSql result;
		int code = ResponseCode.Error.ERROR;
		try {
			result = aSqlSer.get(id);
			if (result != null)
				code = ResponseCode.Success.OK;
			else
				code = ResponseCode.Warning.NO_RESULT;
			re.put(ResponseCode.RESPONSE_DATA, result);
		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}

		re.put(ResponseCode.RESPONSE_CODE , code);
		return re;
	}

	/**
	 * 获取全部接口信息
	 * 
	 * @param mode
	 *            null/默认->没有删除的所有接口 0->只包括在运行中的接口 1->只包括禁用的接口 2->只包括已被删除的接口
	 *            3->没有被删除的所有接口 4->所有接口
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> getAll(String mode) {
		int modex = 3;
		Map<String, Object> re = new HashMap<String, Object>();
		try {
			modex = Integer.parseInt(mode);
		} catch (Exception e) {
			modex = 0;
		}
		List<SysApiInfo> result;
		int code = ResponseCode.Error.ERROR;
		try {
			switch (modex) {
			case 0:
				result = infoSer.getAllOnlyService();
				break;
			case 1:
				result = infoSer.getAllOnlyOff();
				break;
			case 2:
				result = infoSer.getAllOnlyDeleted();
				break;
			case 3:
				result = infoSer.getAll();
				break;
			case 4:
				result = infoSer.getAllIncludeDeleted();
				break;
			default:
				result = infoSer.getAll();
				break;
			}
			if (result != null && result.size() > 0)
				code = ResponseCode.Success.OK;
			else
				code = ResponseCode.Warning.NO_RESULT;
			re.put(ResponseCode.RESPONSE_DATA, result);
		} catch (Exception e) {
			e.printStackTrace();
			code = ResponseCode.Error.ERROR;
		}

		re.put(ResponseCode.RESPONSE_CODE , code);
		return re;
	}

	@ResponseBody
	@RequestMapping(value = "/updatein", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> updateIn(@ModelAttribute("inarg") SysApiInArgInfo inarg,
			@ModelAttribute("inencode") SysEncode inencode,
			@ModelAttribute("incheck") SysCheck incheck,
			@ModelAttribute("inexception") SysApiInArgException inexception) {
		Map<String, Object> re = new HashMap<String, Object>();

		int code = ResponseCode.Error.ERROR;
		try {
			inSer.update(inarg, inencode, incheck, inexception);
			code = ResponseCode.Success.OK;

		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}
		re.put(ResponseCode.RESPONSE_CODE , code);
		return re;
	}

	@ResponseBody
	@RequestMapping(value = "/savein", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> saveIn(@ModelAttribute("inarg") SysApiInArgInfo inarg,
			@ModelAttribute("inencode") SysEncode inencode,
			@ModelAttribute("incheck") SysCheck incheck,
			@ModelAttribute("inexception") SysApiInArgException inexception) {
		Map<String, Object> re = new HashMap<String, Object>();

		int code = ResponseCode.Error.ERROR;
		try {
			inSer.save(inarg, inencode, incheck, inexception);
			code = ResponseCode.Success.OK;

		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}
		re.put(ResponseCode.RESPONSE_CODE , code);
		return re;
	}

	@ResponseBody
	@RequestMapping(value = "/deletein", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> deleteIn(int id) {
		Map<String, Object> re = new HashMap<String, Object>();

		int code = ResponseCode.Error.ERROR;
		try {
			inSer.delete(id);
			code = ResponseCode.Success.OK;

		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}
		re.put(ResponseCode.RESPONSE_CODE , code);
		return re;
	}

	@ResponseBody
	@RequestMapping(value = "/updateout", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> updateOut(@ModelAttribute("outarg") SysApiOutArgInfo outarg,
			@ModelAttribute("outencode") SysEncode outencode) {
		Map<String, Object> re = new HashMap<String, Object>();

		int code = ResponseCode.Error.ERROR;
		try {
			outSer.update(outarg, outencode);
			code = ResponseCode.Success.OK;

		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}
		re.put(ResponseCode.RESPONSE_CODE , code);
		return re;
	}

	@ResponseBody
	@RequestMapping(value = "/saveOut", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> saveIn(@ModelAttribute("outarg") SysApiOutArgInfo outarg,
			@ModelAttribute("outencode") SysEncode outencode) {
		Map<String, Object> re = new HashMap<String, Object>();

		int code = ResponseCode.Error.ERROR;
		try {
			outSer.save(outarg, outencode);
			code = ResponseCode.Success.OK;
		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}
		re.put(ResponseCode.RESPONSE_CODE , code);
		return re;
	}

	@ResponseBody
	@RequestMapping(value = "/deleteOut", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> saveIn(int id) {
		Map<String, Object> re = new HashMap<String, Object>();

		int code = ResponseCode.Error.ERROR;
		try {
			outSer.delete(id);
			code = ResponseCode.Success.OK;

		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}
		re.put(ResponseCode.RESPONSE_CODE , code);
		return re;
	}

	@ResponseBody
	@RequestMapping(value = "/updatesql", method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> updateSql(@ModelAttribute("apisql") SysApiSql sql) {
		Map<String, Object> re = new HashMap<String, Object>();
		int code = ResponseCode.Error.ERROR;
		try {
			aSqlSer.update(sql);
			code = ResponseCode.Success.OK;

		} catch (Exception e) {
			code = ResponseCode.Error.ERROR;
		}
		re.put(ResponseCode.RESPONSE_CODE , code);
		return re;
	}

	// 以下是参数适配器

	@InitBinder("apiinfo")
	public void initBinderForInfo(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("apiinfo.");
	}

	@InitBinder("inargslist")
	public void initBinderForInArgs(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("inargslist.");
	}

	@InitBinder("outargslist")
	public void initBinderForOutArgs(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("outargslist.");
	}

	@InitBinder("apisql")
	public void initBinderForSql(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("apisql.");
	}

	@InitBinder("inencodelist")
	public void initBinderForInEncodes(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("inencodelist.");
	}

	@InitBinder("inchecklist")
	public void initBinderForInChecks(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("inchecklist.");
	}

	@InitBinder("inexceptionlist")
	public void initBinderForInExceptions(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("inexceptionlist.");
	}

	@InitBinder("apiresult")
	public void initBinderForApiResult(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("apiresult.");
	}

	@InitBinder("outencodelist")
	public void initBinderForOutEncodes(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("outencodelist.");
	}

	@InitBinder("format")
	public void initBinderForDataFormat(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("format.");
	}

	@InitBinder("inarg")
	public void initBinderForInArg(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("inarg.");
	}

	@InitBinder("inencode")
	public void initBinderForInEncode(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("inencode.");
	}

	@InitBinder("incheck")
	public void initBinderForInCheck(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("incheck.");
	}

	@InitBinder("inexception")
	public void initBinderForInException(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("inexception.");
	}

	@InitBinder("outarg")
	public void initBinderForOutArg(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("outarg.");
	}

	@InitBinder("outencode")
	public void initBinderForOutEncode(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("outencode.");
	}
	
}