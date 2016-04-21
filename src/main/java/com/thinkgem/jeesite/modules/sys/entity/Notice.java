package com.thinkgem.jeesite.modules.sys.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.Page;

public class Notice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String classid;
	private String content;//注册须知
	
	
	/**
	 * 当前实体分页对象
	 */
	protected Page<Notice> page;
	
	@JsonIgnore
	@XmlTransient
	public Page<Notice> getPage() {
		if (page == null){
			page = new Page<Notice>();
		}
		return page;
	}
	
	public Page<Notice> setPage(Page<Notice> page) {
		this.page = page;
		return page;
	}
	
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getClassid() {
		return classid;
	}
	public void setClassid(String classid) {
		this.classid = classid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
