package com.thinkgem.jeesite.modules.cms.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.Course;
import com.thinkgem.jeesite.modules.sys.entity.Notice;

@MyBatisDao
public interface NoticeDao extends CrudDao<Notice>{
	//查询所有须知
	public List<Notice> findCourseList();
	//根据id查询
	public Notice findById(Integer id);
	//保存notice
	public void saveNotice(Notice notice);
	//修改notice
	public void updateNotice(Notice notice);
	//删除id
	public void deleteById(Integer id);
	
	//考试
	public List<Notice> findListExam(Notice notice);
	public void saveNoticeByExam(Notice notice);
	public void updateNoticeByExam(Notice notice);
	public Notice findByExamId(Integer id);
	public void deleteByExamId(Integer id);
	//注册须知和考试须知通用
	public List<Notice> findListByid(String classid);
	public List<Notice> findListByExamId(String classid);
	public List<Notice> findListByStartId(String classid);
	public List<Notice> findListByStart(Notice notice);
	public void saveStartNotice(Notice notice);
	public void updateStartNotice(Notice notice);
	public void deleteStart(Integer valueOf);
	public Notice findByStartId(Integer valueOf);
}
