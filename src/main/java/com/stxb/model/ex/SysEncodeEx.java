package com.stxb.model.ex;

import java.util.ArrayList;
import java.util.List;

import com.stxb.model.SysEncode;

/**
 * 参数（入参/出参）加密方式封装类
 * @author akku
 *
 */
public class SysEncodeEx {
	private List<SysEncode> encodeList = new ArrayList<SysEncode>();

	public List<SysEncode> getEncodeList() {
		return encodeList;
	}

	public void setEncodeList(List<SysEncode> encodeList) {
		this.encodeList = encodeList;
	}

	public SysEncodeEx() {
		super();
	}

	public SysEncodeEx(List<SysEncode> encodeList) {
		super();
		this.encodeList = encodeList;
	}

	
	
}
