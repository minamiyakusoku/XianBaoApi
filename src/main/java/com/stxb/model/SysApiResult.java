package com.stxb.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stxb.ifs.enums.EnumUtils.ResultArg;

import java.util.HashMap;
import java.util.Map;

/**
 * 接口返回配置
 * @author akku
 *
 */
@JsonPropertyOrder
public class SysApiResult {
	private int id;
	private String resultName;
	private String exceptionCode;
	private String template;
	private Map<ResultArg,Object> cfgMap = new HashMap<ResultArg,Object>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getResultName() {
		return resultName;
	}
	public void setResultName(String resultName) {
		this.resultName = resultName;
	}
	public String getExceptionCode() {
		return exceptionCode;
	}
	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public Map<ResultArg, Object> getCfgMap() {
		return cfgMap;
	}
	public void setCfgMap(Map<ResultArg, Object> cfgMap) {
		this.cfgMap = cfgMap;
	}
	public SysApiResult(int id, String resultName, String exceptionCode,
			String template, Map<ResultArg, Object> cfgMap) {
		super();
		this.id = id;
		this.resultName = resultName;
		this.exceptionCode = exceptionCode;
		this.template = template;
		this.cfgMap = cfgMap;
	}
	public SysApiResult() {
		super();
	}
	
}
