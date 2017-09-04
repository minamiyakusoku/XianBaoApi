package com.stxb.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stxb.ifs.enums.EnumUtils.InArgCfg;
import com.stxb.ifs.enums.EnumUtils.InArgMode;

/**
 * 平台下接口配置
 * @author akku
 *
 */
@JsonPropertyOrder
public class SysApiPlatformCACfg {
	private int id;
	private InArgMode inArgMode;
	private Map<InArgCfg,Object> inArgCfg = new HashMap<InArgCfg,Object> ();
	private String notFoundApiException;
	public int getId() {
		return id;
	}
	
	
	public InArgMode getInArgMode() {
		return inArgMode;
	}


	public void setInArgMode(InArgMode inArgMode) {
		this.inArgMode = inArgMode;
	}


	public Map getInArgCfg() {
		return inArgCfg;
	}


	public void setInArgCfg(Map inArgCfg) {
		this.inArgCfg = inArgCfg;
	}


	public String getNotFoundApiException() {
		return notFoundApiException;
	}


	public void setNotFoundApiException(String notFoundApiException) {
		this.notFoundApiException = notFoundApiException;
	}


	public void setId(int id) {
		this.id = id;
	}


	public SysApiPlatformCACfg(int id, InArgMode inArgMode, Map inArgCfg,
			String notFoundApiException) {
		super();
		this.id = id;
		this.inArgMode = inArgMode;
		this.inArgCfg = inArgCfg;
		this.notFoundApiException = notFoundApiException;
	}
	public SysApiPlatformCACfg() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
