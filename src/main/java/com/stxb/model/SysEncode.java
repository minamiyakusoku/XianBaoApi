package com.stxb.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stxb.factory.EncodeFactory.EncodeParam;
import com.stxb.ifs.enums.EnumUtils.EncodeMode;

import java.util.HashMap;
import java.util.Map;

/**
 * 解密->>使用->>加密 该过程配置类
 * @author akku
 *
 */
@JsonPropertyOrder
public class SysEncode {
	private int id;
	private EncodeMode inType;
	private Map<EncodeParam,Object> inParams = new HashMap<EncodeParam,Object>();
	private EncodeMode outType;
	private Map<EncodeParam,Object> outParams = new HashMap<EncodeParam,Object>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public EncodeMode getInType() {
		return inType;
	}
	public void setInType(EncodeMode inType) {
		this.inType = inType;
	}
	public Map<EncodeParam, Object> getInParams() {
		return inParams;
	}
	public void setInParams(Map<EncodeParam, Object> inParams) {
		this.inParams = inParams;
	}
	public EncodeMode getOutType() {
		return outType;
	}
	public void setOutType(EncodeMode outType) {
		this.outType = outType;
	}
	public Map<EncodeParam, Object> getOutParams() {
		return outParams;
	}
	public void setOutParams(Map<EncodeParam, Object> outParams) {
		this.outParams = outParams;
	}
	public SysEncode(EncodeMode inType, Map<EncodeParam, Object> inParams,
			EncodeMode outType, Map<EncodeParam, Object> outParams) {
		super();
		this.inType = inType;
		this.inParams = inParams;
		this.outType = outType;
		this.outParams = outParams;
	}
	public SysEncode() {
		super();
	}
	
	
}
