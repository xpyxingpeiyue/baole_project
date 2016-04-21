package com.thinkgem.jeesite.modules.sys.dao;
/**
 * 个人中心页面
 */
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.Notice;
@MyBatisDao
public interface UserCenterDao extends CrudDao<Notice>{
public Notice findById(Integer id);
}
