package com.thinkgem.jeesite.modules.sys.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.OaNotify;
import com.thinkgem.jeesite.modules.sys.entity.Course;
import com.thinkgem.jeesite.modules.sys.entity.Grade;
/**
 * 课程科目表
 * @author xingpeiyue
 *
 */
@MyBatisDao
public interface CourseDao extends CrudDao<Course>{
	public List<Course> findBySpecialId(Integer special);
	//分局idcard来查找课程（特长班学生）
	public List<Course> findByIdCard(String idcard);
	//根据考籍号查询成绩
	public List<Grade> findByExamination(String examination);
	//查询所有课程
	public List<Course> findCourseList();
	//查询条数
	public Long findCount(Course course);
	//查询根据id
	public Course findByCourseId(Integer id);
	public void updateByCourse(Course course);
	//根据id删除
	public void deleteById(Integer id);
	//保存course
	public void saveCourse(Course course);
	
	public List<Course> findListByTc(Course course);
	public List<Course> findByStatus(int sc);
	public List<Grade> findByExaminationId(String examination);
}
