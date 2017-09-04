package com.stxb.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stxb.ifs.enums.EnumUtils.DataType;

/**
 * 
 * @author Souls
 *
 */
@JsonPropertyOrder
public class SysApiOutArgInfo {
	private int id;
	private int apiId; 
	private String cloName; 
	private String pyName; 
	private DataType type;  
	private boolean isNull;
	private int encode;
	private int format;
	public SysApiOutArgInfo(int id, int apiId, String cloName, String pyName,
			DataType type, boolean isNull, int encode, int format) {
		super();
		this.id = id;
		this.apiId = apiId;
		this.cloName = cloName;
		this.pyName = pyName;
		this.type = type;
		this.isNull = isNull;
		this.encode = encode;
		this.format = format;
	}
	public SysApiOutArgInfo(int apiId, String cloName, String pyName,
			DataType type, boolean isNull, int encode, int format) {
		super();
		this.apiId = apiId;
		this.cloName = cloName;
		this.pyName = pyName;
		this.type = type;
		this.isNull = isNull;
		this.encode = encode;
		this.format = format;
	}
	public SysApiOutArgInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	public String getCloName() {
		return cloName;
	}
	public void setCloName(String cloName) {
		this.cloName = cloName;
	}
	public String getPyName() {
		return pyName;
	}
	public void setPyName(String pyName) {
		this.pyName = pyName;
	}
	public DataType getType() {
		return type;
	}
	public void setType(DataType type) {
		this.type = type;
	}
	public boolean isNull() {
		return isNull;
	}
	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}
	public int getEncode() {
		return encode;
	}
	public void setEncode(int encode) {
		this.encode = encode;
	}
	public int getFormat() {
		return format;
	}
	public void setFormat(int format) {
		this.format = format;
	}
	
	
	
	
}
