package com.thinkgem.jeesite.modules.sys.entity;

import java.io.Serializable;

public class KCJS implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Integer id;
private Integer number;//考场号
private Integer count;//统计此考场有多少人
private String status;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getNumber() {
	return number;
}
public void setNumber(Integer number) {
	this.number = number;
}
public Integer getCount() {
	return count;
}
public void setCount(Integer count) {
	this.count = count;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
}
