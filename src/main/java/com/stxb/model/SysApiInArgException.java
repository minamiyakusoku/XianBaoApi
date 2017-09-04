package com.stxb.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * @author akku
 *
 */
@JsonPropertyOrder
public class SysApiInArgException {
	private int id;
	private String nullException;
	private String encodeException;
	private String formatException;
	private String argCheckException;
	public String getArgCheckException() {
		return argCheckException;
	}
	public void setArgCheckException(String argCheckException) {
		this.argCheckException = argCheckException;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNullException() {
		return nullException;
	}
	public void setNullException(String nullException) {
		this.nullException = nullException;
	}
	public String getEncodeException() {
		return encodeException;
	}
	public void setEncodeException(String encodeException) {
		this.encodeException = encodeException;
	}
	public String getFormatException() {
		return formatException;
	}
	public void setFormatException(String formatException) {
		this.formatException = formatException;
	}
	public SysApiInArgException(int id, String nullException,
			String encodeException, String formatException,String argCheckException) {
		super();
		this.id = id;
		this.nullException = nullException;
		this.encodeException = encodeException;
		this.formatException = formatException;
		this.argCheckException = argCheckException;
	}
	public SysApiInArgException() {
		super();
	}
	
}
