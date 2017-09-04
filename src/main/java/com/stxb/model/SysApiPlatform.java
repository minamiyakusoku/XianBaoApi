package com.stxb.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@JsonPropertyOrder
public class SysApiPlatform {
	private int id;
	private String name;
	private String code;
	private String apiName;
	private String apiUrl;
	private Date createTime ;
	private boolean isDelete = false ;
	
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public boolean isDelete() {
		return isDelete;
	}
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public String getApiUrl() {
		return apiUrl;
	}
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	public SysApiPlatform(int id, String name, String code, String apiName,
			String apiUrl, Date createTime, boolean isDelete) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.apiName = apiName;
		this.apiUrl = apiUrl;
		this.createTime = createTime;
		this.isDelete = isDelete;
	}
	public SysApiPlatform(String name, String code, String apiName,
			String apiUrl, Date createTime, boolean isDelete) {
		super();
		this.name = name;
		this.code = code;
		this.apiName = apiName;
		this.apiUrl = apiUrl;
		this.createTime = createTime;
		this.isDelete = isDelete;
	}
	public SysApiPlatform() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
