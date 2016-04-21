package com.thinkgem.jeesite.modules.sys.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.Page;

/**
 * 注册实体类
 * @author Administrator
 *
 */
public class Regeist implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String idcard;
	private String logname;
	private String password;
	private String sex;//1.女   2.男
	private String address;//户籍所在地
	private String national;
	private String fatherName;
	private String motherName;
	private String fatherWork;
	private String motherWork;
	private String fatherPhone;
	private String motherPhone;
	private String graduate;
	private String home;//家庭住址
	private Integer special;//是否特长生
	private Integer specialClass;//特长类别
	private Integer number;//考场号
	private String picture;
	private String examination;//考籍号
	private Integer zw;//座位号
	
	private Grade grade;
	private List<Grade> grades;
	private Special sp;
	
	
	
	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Special getSp() {
		return sp;
	}

	public void setSp(Special sp) {
		this.sp = sp;
	}
	/**
	 * 当前实体分页对象
	 */
	protected Page<Regeist> page;
	
	@JsonIgnore
	@XmlTransient
	public Page<Regeist> getPage() {
		if (page == null){
			page = new Page<Regeist>();
		}
		return page;
	}
	
	public Page<Regeist> setPage(Page<Regeist> page) {
		this.page = page;
		return page;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getLogname() {
		return logname;
	}
	public void setLogname(String logname) {
		this.logname = logname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNational() {
		return national;
	}
	public void setNational(String national) {
		this.national = national;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getFatherWork() {
		return fatherWork;
	}
	public void setFatherWork(String fatherWork) {
		this.fatherWork = fatherWork;
	}
	public String getMotherWork() {
		return motherWork;
	}
	public void setMotherWork(String motherWork) {
		this.motherWork = motherWork;
	}
	public String getFatherPhone() {
		return fatherPhone;
	}
	public void setFatherPhone(String fatherPhone) {
		this.fatherPhone = fatherPhone;
	}
	public String getMotherPhone() {
		return motherPhone;
	}
	public void setMotherPhone(String motherPhone) {
		this.motherPhone = motherPhone;
	}
	public String getGraduate() {
		return graduate;
	}
	public void setGraduate(String graduate) {
		this.graduate = graduate;
	}
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	public Integer getSpecial() {
		return special;
	}
	public void setSpecial(Integer special) {
		this.special = special;
	}
	
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public Integer getSpecialClass() {
		return specialClass;
	}
	public void setSpecialClass(Integer specialClass) {
		this.specialClass = specialClass;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	

	public Integer getZw() {
		return zw;
	}

	public void setZw(Integer zw) {
		this.zw = zw;
	}

	public String getExamination() {
		return examination;
	}

	public void setExamination(String examination) {
		this.examination = examination;
	}
	
}
