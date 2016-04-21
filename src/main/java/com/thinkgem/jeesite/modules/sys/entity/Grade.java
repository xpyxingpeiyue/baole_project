package com.thinkgem.jeesite.modules.sys.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

public class Grade implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String examination;
	private String idcard;
	private String name;
	private String score;
	
	private String status;
	private Regeist reg;
	private List<Regeist> regeist;
	private Course course;
	
	public Grade() {
		super();
	}
	public Grade(String examination,String idcard, String name, String score,String status) {
		super();
		this.examination=examination;
		this.idcard=idcard;
		this.name = name;
		this.score = score;
		this.status=status;
	}
	
	/**
	 * 当前实体分页对象
	 */
	protected Page<Grade> page;
	
	@JsonIgnore
	@XmlTransient
	public Page<Grade> getPage() {
		if (page == null){
			page = new Page<Grade>();
		}
		return page;
	}
	
	public Page<Grade> setPage(Page<Grade> page) {
		this.page = page;
		return page;
	}
	
	
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public Regeist getReg() {
		return reg;
	}
	public void setReg(Regeist reg) {
		this.reg = reg;
	}
	public List<Regeist> getRegeist() {
		return regeist;
	}
	public void setRegeist(List<Regeist> regeist) {
		this.regeist = regeist;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@ExcelField(title="ID", type=1, align=2, sort=1)
	public Integer getId() {
		return id;
	}
	@ExcelField(title="学籍", align=10, sort=38)
	public String getExamination() {
		return examination;
	}
	@ExcelField(title="课程名", align=10, sort=40)
	public String getName() {
		return name;
	}
	@ExcelField(title="成绩", align=2, sort=45)
	public String getScore() {
		return score;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setExamination(String examination) {
		this.examination = examination;
	}
	
	public void setScore(String score) {
		this.score = score;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
