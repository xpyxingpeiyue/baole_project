package com.thinkgem.jeesite.modules.sys.entity;

import java.io.Serializable;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

public class Imports implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idcard;//身份证
	private String logname;//姓名
	private String examination;
	private String sex;//性别
	private String nationl;//族别
	private String graduate;//毕业院校
	private String course1;
	private String course2;
	private String course3;
	private String other;
	public Imports() {
		super();
	}
	public Imports(String idcard, String logname, String examination, String sex, String nationl, String graduate,
			String course1, String course2, String course3, String other) {
		super();
		this.idcard = idcard;
		this.logname = logname;
		this.examination = examination;
		this.sex = sex;
		this.nationl = nationl;
		this.graduate = graduate;
		this.course1 = course1;
		this.course2 = course2;
		this.course3 = course3;
		this.other = other;
	}

	@ExcelField(title="身份证号", align=2, sort=45)
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	@ExcelField(title="姓名", align=2, sort=46)
	public String getLogname() {
		return logname;
	}
	public void setLogname(String logname) {
		this.logname = logname;
	}
	@ExcelField(title="考籍号", align=2, sort=47)
	public String getExamination() {
		return examination;
	}

	public void setExamination(String examination) {
		this.examination = examination;
	}
	@ExcelField(title="毕业院校", align=2, sort=48)
	public String getGraduate() {
		return graduate;
	}

	public void setGraduate(String graduate) {
		this.graduate = graduate;
	}

	@ExcelField(title="性别", align=2, sort=49)
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@ExcelField(title="族别", align=2, sort=50)
	public String getNationl() {
		return nationl;
	}
	public void setNationl(String nationl) {
		this.nationl = nationl;
	}
	@ExcelField(title="语文", align=2, sort=51)
	public String getCourse1() {
		return course1;
	}
	public void setCourse1(String course1) {
		this.course1 = course1;
	}
	@ExcelField(title="数学", align=2, sort=52)
	public String getCourse2() {
		return course2;
	}
	public void setCourse2(String course2) {
		this.course2 = course2;
	}
	@ExcelField(title="英语", align=2, sort=53)
	public String getCourse3() {
		return course3;
	}
	public void setCourse3(String course3) {
		this.course3 = course3;
	}
	@ExcelField(title="其他科目", align=2, sort=54)
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	
	
	
	

}
