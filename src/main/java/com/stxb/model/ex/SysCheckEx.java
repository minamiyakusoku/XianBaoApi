package com.stxb.model.ex;

import java.util.ArrayList;
import java.util.List;

import com.stxb.model.SysCheck;

/**
 * 数据检查配置集合增强类
 * @author akku
 *
 */
public class SysCheckEx {
	private List<SysCheck> checkList = new ArrayList<SysCheck>();

	public List<SysCheck> getCheckList() {
		return checkList;
	}

	public void setCheckList(List<SysCheck> checkList) {
		this.checkList = checkList;
	}

	public SysCheckEx() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SysCheckEx(List<SysCheck> checkList) {
		super();
		this.checkList = checkList;
	}
	
}
