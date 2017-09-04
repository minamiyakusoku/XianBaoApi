package com.stxb.model.ex;

import java.util.ArrayList;
import java.util.List;

import com.stxb.model.SysApiInArgException;

/**
 * 入参异常码列表增强类
 * @author akku
 *
 */
public class SysApiInArgExceptionEx {
	private List<SysApiInArgException> exceptionList  = new ArrayList<SysApiInArgException>();

	public List<SysApiInArgException> getExceptionList() {
		return exceptionList;
	}

	public void setExceptionList(List<SysApiInArgException> exceptionList) {
		this.exceptionList = exceptionList;
	}

	public SysApiInArgExceptionEx() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SysApiInArgExceptionEx(List<SysApiInArgException> exceptionList) {
		super();
		this.exceptionList = exceptionList;
	}
	
}
