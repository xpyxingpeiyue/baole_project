package com.thinkgem.jeesite.modules.sys.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.modules.sys.dao.RegeistDao;
import com.thinkgem.jeesite.modules.sys.entity.KCJS;
import com.thinkgem.jeesite.modules.sys.entity.KCJStc;
import com.thinkgem.jeesite.modules.sys.entity.KaoChang;
import com.thinkgem.jeesite.modules.sys.entity.Regeist;

@Service
public class RegeisterService {
	@Autowired
	private RegeistDao regeistDao;
	@Transactional
    public  void insertIntoTable(Regeist regeist){
	//判断是否为特长班
    if(regeist.getSpecialClass()!=null){
    	//查询特长班所有的考场号
    	int count=regeistDao.findByjstc();
    	//先到备份表查看有没有数据
		List<KaoChang> kcbf=regeistDao.findByKCBFtc(regeist.getSpecialClass());
		if(kcbf!=null&&kcbf.size()>0){
			KaoChang kcb=kcbf.get(0);//获取第一个
			//将本备份表数据拿出放入插入表
			regeistDao.insertToKC(kcb);
			//拿出考籍号
			regeist.setExamination(kcb.getKsh());//设置考籍
			regeist.setZw(kcb.getZw());//座位号
			regeist.setNumber(kcb.getNumber());//考场号
			regeistDao.insert(regeist);//保存到数据库
			//删除备份表的此数据
			regeistDao.deleteByBF(kcb);
		}else{
			//特长班 首先查询specialClass
        	List<KCJStc> kcjStc=regeistDao.findByjs(regeist.getSpecialClass());
			//备份表没有数据，则再进入统计数表
			if(kcjStc!=null&&kcjStc.size()>0){
        		
        		if(kcjStc.get(kcjStc.size()-1).getCount()==30){
        			//满30换考场
        			KCJStc kc=new KCJStc();
	        		kc.setNumber(200+count);
	        		kc.setCount(1);
	        		kc.setStatus(regeist.getSpecialClass()+"");
	        		regeistDao.insertJStc(kc);
	        		//考场表的插入
	        		KaoChang kch=new KaoChang();
	        		kch.setTc(regeist.getSpecialClass());//特长号
	        		kch.setZw(kc.getCount());//统计到多少就是第几个
	        		kch.setNumber(kc.getNumber());
	        		kch.setKsh(new Date().getTime()+"");//考籍号
	        		//插入到考场表
	        		regeistDao.insertToKCNotId(kch);
	        		//保存到注册表
	        		regeist.setNumber(kch.getNumber());
	        		regeist.setZw(kch.getZw());
	        		regeist.setExamination(kch.getKsh());
	        		regeistDao.insert(regeist);//将信息保存到数据库
	        		
        		}else{
        			KCJStc kc=new KCJStc();
        			kc.setId(kcjStc.get(kcjStc.size()-1).getId());
	        		kc.setNumber(kcjStc.get(kcjStc.size()-1).getNumber());
	        		kc.setCount(kcjStc.get(kcjStc.size()-1).getCount()+1);
	        		kc.setStatus(regeist.getSpecialClass()+"");
	        		//更新统计表的统计数据
	        		regeistDao.updateJStc(kc);
	        		//考场表的插入
	        		KaoChang kch=new KaoChang();
	        		kch.setTc(regeist.getSpecialClass());//特长号
	        		kch.setZw(kc.getCount());//统计到多少就是第几个
	        		kch.setNumber(kc.getNumber());
	        		kch.setKsh(new Date().getTime()+"");//考籍号
	        		//插入到考场表
	        		regeistDao.insertToKCNotId(kch);
	        		//保存到注册表
	        		regeist.setNumber(kch.getNumber());
	        		regeist.setZw(kch.getZw());
	        		regeist.setExamination(kch.getKsh());
	        		regeistDao.insert(regeist);//将信息保存到数据库
	        		
	        		
        		}
        	}else{
        		//统计表没有数据时，此时初始化表
        		KCJStc kc=new KCJStc();
        		kc.setNumber(200+count);
        		kc.setCount(1);
        		kc.setStatus(regeist.getSpecialClass()+"");
        		regeistDao.insertJStc(kc);
        		//然后添加一条记录
        		//到备份表中查找有没有 此数据
        		//List<KCBF> kcbf=regeistDao.findByKCH();
        		//到kaochang插入一条记录
        		KaoChang kchang=new KaoChang();
        		kchang.setTc(regeist.getSpecialClass());
        		kchang.setKsh(new Date().getTime()+"");//考籍号
        		kchang.setZw(kc.getCount());
        		kchang.setNumber(kc.getNumber());
        		regeistDao.insertToKCNotId(kchang);
        		
        		//保存到注册表
        		regeist.setNumber(kchang.getNumber());
        		regeist.setZw(kchang.getZw());
        		regeist.setExamination(kchang.getKsh());
        		regeistDao.insert(regeist);//将信息保存到数据库
        		
        	}
		}
    	
    	
    }else{
    	//华师班报名100开头
    	regeist.setSpecialClass(0);
    	//先到备份表查看有没有数据此时hs为0
    	int count=regeistDao.findByjsHS();
    	//先到备份表查看有没有数据(华师班统一用0表示)
		List<KaoChang> kcbf=regeistDao.findByKCBFtc(0);
		if(kcbf!=null&&kcbf.size()>0){
			KaoChang kcb=kcbf.get(0);//获取第一个
			//将本备份表数据拿出放入插入表（id 不变）
			
			//再检查下sys_kc_info有没有这条数据，有的话删除
			KaoChang kc=regeistDao.findByKcId(kcb.getId());
			if(kc!=null){
				regeistDao.deleteByKcInfoId(kcb.getId());
			}
			
			regeistDao.insertToKC(kcb);
			//拿出考籍号
			regeist.setExamination(kcb.getKsh());//设置考籍
			regeist.setZw(kcb.getZw());//座位号
			regeist.setNumber(kcb.getNumber());//考场号
			regeistDao.insert(regeist);//保存到数据库
			//删除备份表的此条数据
			regeistDao.deleteByBF(kcb);
		}else{
			//华师班 首先查询specialClass,华师版的status为0
        	List<KCJS> kcjS=regeistDao.findByHS(0);
			//备份表没有数据，则再进入统计数表
			if(kcjS!=null&&kcjS.size()>0){
        		//保证是最后一个。
        		if(kcjS.get(kcjS.size()-1).getCount()==30){
        			//满30换考场
        			KCJS kc=new KCJS();
	        		kc.setNumber(100+count);
	        		kc.setCount(1);
	        		kc.setStatus(0+"");
	        		//插入到华师统计表
	        		regeistDao.insertJS(kc);
	        		//考场表的插入
	        		KaoChang kch=new KaoChang();
	        		kch.setTc(0);//特长号
	        		kch.setZw(kc.getCount());//统计到多少就是第几个
	        		kch.setNumber(kc.getNumber());
	        		kch.setKsh(new Date().getTime()+"");//考籍号
	        		//插入到考场表
	        		regeistDao.insertToKCNotId(kch);
	        		//保存到注册表
	        		regeist.setNumber(kch.getNumber());
	        		regeist.setZw(kch.getZw());
	        		regeist.setExamination(kch.getKsh());
	        		regeistDao.insert(regeist);//将信息保存到数据库
	        		
        		}else{
        			//为满30的座位排序方式
        			KCJS kc=new KCJS();
        			kc.setId(kcjS.get(kcjS.size()-1).getId());
	        		kc.setNumber(kcjS.get(kcjS.size()-1).getNumber());
	        		kc.setCount(kcjS.get(kcjS.size()-1).getCount()+1);
	        		kc.setStatus(0+"");
	        		//更新统计表的统计数据
	        		regeistDao.updateJS(kc);
	        		//考场表的插入
	        		KaoChang kch=new KaoChang();
	        		kch.setTc(0);//特长号
	        		kch.setZw(kc.getCount());//统计到多少就是第几个
	        		kch.setNumber(kc.getNumber());
	        		kch.setKsh(new Date().getTime()+"");//考籍号
	        		//插入到考场表
	        		regeistDao.insertToKCNotId(kch);
	        		//保存到注册表
	        		regeist.setNumber(kch.getNumber());
	        		regeist.setZw(kch.getZw());
	        		regeist.setExamination(kch.getKsh());
	        		regeistDao.insert(regeist);//将信息保存到数据库
	        		
	        		
        		}
        	}else{
        		//统计表没有数据时，此时初始化表
        		KCJS kc=new KCJS();
        		kc.setNumber(100+count);
        		kc.setCount(1);
        		kc.setStatus(0+"");
        		regeistDao.insertJS(kc);
        		//然后添加一条记录
        		//到备份表中查找有没有 此数据
        		//List<KCBF> kcbf=regeistDao.findByKCH();
        		//到kaochang插入一条记录
        		KaoChang kchang=new KaoChang();
        		kchang.setTc(0);
        		kchang.setKsh(new Date().getTime()+"");//考籍号
        		kchang.setZw(kc.getCount());
        		kchang.setNumber(kc.getNumber());
        		regeistDao.insertToKCNotId(kchang);
        		
        		//保存到注册表
        		regeist.setNumber(kchang.getNumber());
        		regeist.setZw(kchang.getZw());
        		regeist.setExamination(kchang.getKsh());
        		regeistDao.insert(regeist);//将信息保存到数据库
        		
        	}
		}
    	
    	
    }
}
}
