package com.thinkgem.jeesite.modules.sys.web;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.dao.NoticeDao;
import com.thinkgem.jeesite.modules.sys.dao.CourseDao;
import com.thinkgem.jeesite.modules.sys.dao.GradeDao;
import com.thinkgem.jeesite.modules.sys.dao.RegeistDao;
import com.thinkgem.jeesite.modules.sys.entity.Course;
import com.thinkgem.jeesite.modules.sys.entity.Grade;
import com.thinkgem.jeesite.modules.sys.entity.Notice;
import com.thinkgem.jeesite.modules.sys.entity.Regeist;
@Controller
public class UserCenterController extends BaseController{
	@Autowired
	private RegeistDao regeistDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private NoticeDao noticeDao;
	@Autowired
	private GradeDao gradeDao;
	/**
	 * 个人信息修改页面
	 * @param request
	 * @param response
	 * @param model
	 * @param pic
	 * @param regeist
	 * @return
	 */
	@RequestMapping(value="user/userinfo.do")
	public String update(HttpServletRequest request,HttpServletResponse response,Model model,Regeist regeist){
		
		regeistDao.update(regeist);
		//查询此学生，重新拿到学生信息
		regeist=regeistDao.findByIdCard(regeist.getIdcard());
		request.getSession().setAttribute("user", regeist);
		model.addAttribute("st", "1");
		return "modules/bole/personCenter";
		
	}
	/**
	 * 
	
	public String update(HttpServletRequest request,HttpServletResponse response,Model model,MultipartFile pic,Regeist regeist){
		String path = request.getSession().getServletContext().getRealPath("regcenter");
		String 	fileName=pic.getOriginalFilename();
		if(fileName==null||fileName.length()==0){
			//没有更改图片
			Regeist regeist2=(Regeist) request.getSession().getAttribute("user");
			regeist.setPicture(regeist2.getPicture());
			
		}else{
			int index=fileName.lastIndexOf(".");
			//截取图片后缀
			 fileName=new Date().getTime()+fileName.substring(index, fileName.length());
			 File targetFile = new File(path,fileName);  
		        if(!targetFile.exists()){  
		            targetFile.mkdirs();  
		        }  
		        //保存  
		        try {  
		            pic.transferTo(targetFile);  
		        } catch (Exception e) {  
		            e.printStackTrace();  
		        }  
		        //将图片地址保存到数据库
		        regeist.setPicture(request.getContextPath()+"/regcenter/"+fileName);
		}
		//注册时ID随机生成的，所以拿不到id,可以拿到考籍号
		regeistDao.update(regeist);
		//查询此学生，重新拿到学生信息
		regeist=regeistDao.findByIdCard(regeist.getIdcard());
		request.getSession().setAttribute("user", regeist);
		model.addAttribute("st", "1");
		return "modules/bole/personCenter";
	}
	 */
	
	/**
	 * 直接跳转到个人中心页面，前提是已经登陆成功
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "user/redirectCenter.do")
	public String redirectCenter(HttpServletRequest request,HttpServletResponse response,Model model){
		
		return "modules/bole/personCenter";
	}
	/**
	 * 点击开始须知，根据session的判断特长班来查询信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "user/startCenter.do")
	public String startCenter(HttpServletRequest request,HttpServletResponse response,Model model){
		//获取session,要进行拦截
		String id=(String) request.getSession().getAttribute("student");
		//进入数据库查询
		
		//考试须知要显示注册须知和考试须知
		List<Notice> notice=noticeDao.findListByid(id);
		
		//开始须知
		List<Notice> notice1=noticeDao.findListByExamId(id);
		
		
		//List<Notice> notice=noticeDao.findListByStartId(id);
		//Notice notice=userCenterDao.findById(Integer.valueOf(id));
		model.addAttribute("notice", notice);
		model.addAttribute("notice1", notice1);
		return "modules/bole/start_notice";
	}
	/**
	 * 进入打印准考证页面，有些功能尚未实现
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "user/printCenter.do")
	public  String printCenter(HttpServletRequest request,HttpServletResponse response,Model model){
		//首先将开始内容转发过去
		String specialId=(String) request.getSession().getAttribute("student");
		//查询用户id
		Regeist regeist=(Regeist) request.getSession().getAttribute("user");
		
		//是否华师班查询课程
		//regeist=(Regeist) regeistDao.findByIdCard(idcard);
		List<Course> course=null;
		
		if(specialId.equals("1")){
			//华师班学生
			course=courseDao.findBySpecialId(regeist.getSpecial());
		}else{
			int sc=regeist.getSpecialClass();
			//course=courseDao.findByIdCard(idcard);
			course=courseDao.findByStatus(sc);
		}
		//将course传过去循环输出
		model.addAttribute("course", course);
		//进入数据库查询考试须知
		//Notice notice=userCenterDao.findById(Integer.valueOf(specialId));
		List<Notice> notice=noticeDao.findListByExamId(specialId);
		model.addAttribute("exam", notice);
		return "modules/bole/print";
	}
	/**
	 * 登陆成绩查询模块
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "user/gradeCenter.do")
	public String gradeCenter(HttpServletRequest request,HttpServletResponse response,Model model){
		//不同的人进入查询的成绩是不同的
		//获取用户细信息
		Regeist regeist=(Regeist) request.getSession().getAttribute("user");
		//取出特长类别
		Integer specialClass=regeist.getSpecialClass();
		String specialName=null;
		if(specialClass==0){
			specialName="华师班";
		}else{
			specialName=regeistDao.findBySpecialName(specialClass);
			
		}
		//List<Grade> grade=courseDao.findByExamination(regeist.getExamination());
		//直接根据考籍号来获取多少门课程
		//获取是什么特长，展示在前台
		String idcard=regeist.getIdcard();
		//查询所有的成绩
		List<Grade> list=gradeDao.findListByIdcard(idcard);
		
		//List<Grade> grade=courseDao.findByExaminationId(regeist.getExamination());
		model.addAttribute("spname", specialName);
		model.addAttribute("grade", list);
		return "modules/bole/gradeQuery";
	}
}
