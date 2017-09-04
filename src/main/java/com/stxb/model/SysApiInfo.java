package com.stxb.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stxb.ifs.enums.EnumUtils.ApiState;


/**
 * 
 * @author Souls 修改 akku 2016年12月2日10:04:54
 *
 */
@JsonPropertyOrder
public class SysApiInfo {
	private int id;
	private int apiId;
	private int versionId;
	private String name;
	private String apiName;
	private int inArgsCount;
	private int outArgsCount;
	private String type;
	private boolean isEnCode;
	private Date createTime = new Date();
	private int pId;
	private ApiState state = ApiState.SERVICE;
	
	
	
	public int getId() {
		return id;
	}
	public ApiState getState() {
		return state;
	}
	public void setState(ApiState state) {
		this.state = state;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getApiId() {
		return apiId;
	}
	public void setApiId(int apiId) {
		this.apiId = apiId;
	}
	public int getVersionId() {
		return versionId;
	}
	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public int getInArgsCount() {
		return inArgsCount;
	}
	public void setInArgsCount(int inArgsCount) {
		this.inArgsCount = inArgsCount;
	}
	public int getOutArgsCount() {
		return outArgsCount;
	}
	public void setOutArgsCount(int outArgsCount) {
		this.outArgsCount = outArgsCount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	
	public SysApiInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public boolean isEnCode() {
		return isEnCode;
	}
	public void setEnCode(boolean isEnCode) {
		this.isEnCode = isEnCode;
	}
	public SysApiInfo(int id, int apiId, int versionId, String name,
			String apiName, int inArgsCount, int outArgsCount, String type,
			boolean isEnCode, Date createTime, int pId, ApiState state) {
		super();
		this.id = id;
		this.apiId = apiId;
		this.versionId = versionId;
		this.name = name;
		this.apiName = apiName;
		this.inArgsCount = inArgsCount;
		this.outArgsCount = outArgsCount;
		this.type = type;
		this.isEnCode = isEnCode;
		this.createTime = createTime;
		this.pId = pId;
		this.state = state;
	}
	public SysApiInfo(int apiId, int versionId, String name, String apiName,
			int inArgsCount, int outArgsCount, String type, boolean isEnCode,
			Date createTime, int pId, ApiState state) {
		super();
		this.apiId = apiId;
		this.versionId = versionId;
		this.name = name;
		this.apiName = apiName;
		this.inArgsCount = inArgsCount;
		this.outArgsCount = outArgsCount;
		this.type = type;
		this.isEnCode = isEnCode;
		this.createTime = createTime;
		this.pId = pId;
		this.state = state;
	}
	
	
	
	
	
	

	
}
