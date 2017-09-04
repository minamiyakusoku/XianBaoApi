package com.stxb.model.ex;

import java.util.ArrayList;
import java.util.List;

import com.stxb.model.SysApiOutArgInfo;
/**
 * 出参集合封装类
 * @author akku
 *
 */
public class SysApiOutArgInfoEx {

	private List<SysApiOutArgInfo> outArgsList = new ArrayList<SysApiOutArgInfo>();

	public List<SysApiOutArgInfo> getOutArgsList() {
		return outArgsList;
	}

	public void setOutArgsList(List<SysApiOutArgInfo> outArgsList) {
		this.outArgsList = outArgsList;
	}

	public SysApiOutArgInfoEx(List<SysApiOutArgInfo> outArgsList) {
		super();
		this.outArgsList = outArgsList;
	}

	public SysApiOutArgInfoEx() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
