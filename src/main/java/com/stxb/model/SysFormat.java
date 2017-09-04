package com.stxb.model;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stxb.ifs.enums.EnumUtils.DataType;

/**
 *数据格式化类
 * @author akku
 *
 */
@JsonPropertyOrder
public class SysFormat {
	private int id;
	private DataType type;
	private String inPattern;
	private String outPattern;
	public SysFormat(int id, DataType type, String inPattern, String outPattern) {
		super();
		this.id = id;
		this.type = type;
		this.inPattern = inPattern;
		this.outPattern = outPattern;
	}
	public SysFormat(DataType type, String inPattern, String outPattern) {
		super();
		this.type = type;
		this.inPattern = inPattern;
		this.outPattern = outPattern;
	}
	public SysFormat() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public DataType getType() {
		return type;
	}
	public void setType(DataType type) {
		this.type = type;
	}
	public String getInPattern() {
		return inPattern;
	}
	public void setInPattern(String inPattern) {
		this.inPattern = inPattern;
	}
	public String getOutPattern() {
		return outPattern;
	}
	public void setOutPattern(String outPattern) {
		this.outPattern = outPattern;
	}
	
	
	
}
