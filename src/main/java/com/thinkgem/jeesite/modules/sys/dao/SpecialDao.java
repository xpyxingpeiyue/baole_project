package com.thinkgem.jeesite.modules.sys.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.Course;
import com.thinkgem.jeesite.modules.sys.entity.Special;
@MyBatisDao
public interface SpecialDao extends  CrudDao<Course>{
//根据id查询
	public Special findBySpecialId(Integer id);
	public void deleteById(Integer id);
}
