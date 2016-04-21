package com.thinkgem.jeesite.modules.sys.dao;

import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.Course;
import com.thinkgem.jeesite.modules.sys.entity.KCBF;
import com.thinkgem.jeesite.modules.sys.entity.KCJS;
import com.thinkgem.jeesite.modules.sys.entity.KCJStc;
import com.thinkgem.jeesite.modules.sys.entity.KaoChang;
import com.thinkgem.jeesite.modules.sys.entity.Regeist;

/**
 * 注册dao接口
 * @author xingpeiyue
 *
 */
@MyBatisDao
public interface RegeistDao extends CrudDao<Regeist>{
	//按省份证号查询
	public Regeist findByIdCard(String idcard);
	//public Regeist selectBySpecialId(Integer id);
	//public List<Course> findBySpecialId(Integer special);
	public String findBySpecialName(Integer id);
	
	public Regeist findByRId(Integer id);
	
	public void deleteById(Integer id);
	//查询所有的数据根据是否特长
	public List<Regeist> findBySpecialId(Integer id);
	
	
	//查询有无特长
	public List<KCJStc> findByjs(Integer status);
	public int findByjstc();
	public void insertJStc(KCJStc kc);
	public void updateJStc(KCJStc kc);
	
	public void insertToKC(KaoChang kchang);
	
	public void insertToKCNotId(KaoChang kchang);
	//根据类别查询备份表
	public List<KaoChang> findByKCBFtc(Integer tc);
	public void deleteByBF(KaoChang kcb);
	public int findByjsHS();
	public void insertJS(KCJS kc);
	public List<KCJS> findByHS(int i);
	public void updateJS(KCJS kc);
	public void deleteByExam(String exam);
	public KaoChang findByExamKC(String exam);
	public void insertToBF(KaoChang kc);
	public List<Regeist> findBySpecialClass(Regeist regeist);
	public List<Regeist> findListByClass(Regeist regeist);
	public List<Map<String, Object>> findListByGrade(Regeist regeist);
	public void saveAll(Regeist regeist);
	
	public void deleteBySpecial(Integer special);
	public void deleteByKcBf(int st);
	public void deleteByKcInfo(int st);
	public void deleteByKcJs(String st);
	public void deleteByKcJsTc(String st);
	public void deleteByKcBfIn();
	public void deleteByKcInfoIn();
	public void deleteByKcJsTcIn();
	public KaoChang findByKcId(Integer id);
	public void deleteByKcInfoId(Integer id);
}
