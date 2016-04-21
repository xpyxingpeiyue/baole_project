package com.thinkgem.jeesite.modules.sys.entity;

import java.io.Serializable;

/**
 * 特长班学生
 * @author xingpeiyue
 *
 */
public class Special implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
