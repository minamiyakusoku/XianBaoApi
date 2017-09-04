package com.stxb.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stxb.ifs.enums.EnumUtils.CheckType;

/**
 * 数据校验类
 * @author Souls
 *
 */
@JsonPropertyOrder
public class SysCheck {
	private int id;
	private CheckType type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setType(CheckType type) {
		this.type = type;
	}
	public CheckType getType() {
		return type;
	}
	public void setCheckType(CheckType type) {
		this.type = type;
	}
	public SysCheck(int id, CheckType type) {
		super();
		this.id = id;
		this.type = type;
	}
	public SysCheck() {
		super();
	}
	public SysCheck(CheckType type) {
		super();
		this.type = type;
	}
	
	
	
}
