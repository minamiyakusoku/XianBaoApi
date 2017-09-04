package com.stxb.model.ex;

import java.util.ArrayList;
import java.util.List;

import com.stxb.model.SysApiInArgInfo;

/**
 * 入参集合封装类
 * @author akku
 *
 */
public class SysApiInArgInfoEx {
	private List<SysApiInArgInfo> inArgsList = new ArrayList<SysApiInArgInfo>();

	public List<SysApiInArgInfo> getInArgsList() {
		return inArgsList;
	}

	public void setInArgsList(List<SysApiInArgInfo> inArgsList) {
		this.inArgsList = inArgsList;
	}

	public SysApiInArgInfoEx(List<SysApiInArgInfo> inArgsList) {
		super();
		this.inArgsList = inArgsList;
	}

	public SysApiInArgInfoEx() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
