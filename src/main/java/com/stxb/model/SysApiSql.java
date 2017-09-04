package com.stxb.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author akku
 */
@JsonPropertyOrder
public class SysApiSql {
	private int id;
	private int apiId;
	private String sqlInfo;
	public int getId() {
		return id;
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
	public String getSqlInfo() {
		return sqlInfo;
	}
	public void setSqlInfo(String sqlInfo) {
		this.sqlInfo = sqlInfo;
	}
	
	public SysApiSql(int apiId, String sqlInfo) {
		super();
		this.apiId = apiId;
		this.sqlInfo = sqlInfo;
	}
	public SysApiSql(int id, int apiId, String sqlInfo) {
		super();
		this.id = id;
		this.apiId = apiId;
		this.sqlInfo = sqlInfo;
	}
	public SysApiSql() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
