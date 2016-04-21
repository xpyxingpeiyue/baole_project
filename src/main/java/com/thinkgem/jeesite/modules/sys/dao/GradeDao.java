package com.thinkgem.jeesite.modules.sys.dao;

import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.Course;
import com.thinkgem.jeesite.modules.sys.entity.Grade;
@MyBatisDao
public interface GradeDao extends CrudDao<Course>{
public void saveGrade(Grade grade);

public List<Map<String, Object>> findListByExam(Grade grade);

public Grade findByid(Integer valueOf);

public void updateGrade(Grade grade);

public void deleteById(Integer valueOf);

public void insertGrade(Grade grade);

public List<Grade> findListByIdcard(String idcard);

public void deleteByIdcard(String idcard);

public List<String> findListAllIdcard(String status);

public void deleteAllStatus(String status);
}
