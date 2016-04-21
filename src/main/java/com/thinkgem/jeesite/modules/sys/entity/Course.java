package com.thinkgem.jeesite.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.persistence.Page;

/**
 * 科目表
 * @author 心xingpeiyue
 *
 */
public class Course implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String time;
	private Integer special;
	private String note1;
	private String note2;
	private Integer status;
	
	/**
	 * 当前实体分页对象
	 */
	protected Page<Course> page;
	
	@JsonIgnore
	@XmlTransient
	public Page<Course> getPage() {
		if (page == null){
			page = new Page<Course>();
		}
		return page;
	}
	
	public Page<Course> setPage(Page<Course> page) {
		this.page = page;
		return page;
	}
	
	
	
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
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getSpecial() {
		return special;
	}
	public void setSpecial(Integer special) {
		this.special = special;
	}
	public String getNote1() {
		return note1;
	}
	public void setNote1(String note1) {
		this.note1 = note1;
	}
	public String getNote2() {
		return note2;
	}
	public void setNote2(String note2) {
		this.note2 = note2;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
