package com.thinkgem.jeesite.modules.sys.entity;

import java.io.Serializable;

public class KaoChang implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;//id自增
	private Integer tc;//特长（0 1 2  3  4 5）
	private Integer number;//考场号
	private Integer zw;//座位号
	private String ksh;//考籍号
	private Integer status;//状态预留
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTc() {
		return tc;
	}
	public void setTc(Integer tc) {
		this.tc = tc;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getZw() {
		return zw;
	}
	public void setZw(Integer zw) {
		this.zw = zw;
	}
	
	public String getKsh() {
		return ksh;
	}
	public void setKsh(String ksh) {
		this.ksh = ksh;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	

}
