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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.stat.TableStat.Mode;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.dao.NoticeDao;
import com.thinkgem.jeesite.modules.sys.dao.RegeistDao;
import com.thinkgem.jeesite.modules.sys.entity.KCBF;
import com.thinkgem.jeesite.modules.sys.entity.KCJS;
import com.thinkgem.jeesite.modules.sys.entity.KCJStc;
import com.thinkgem.jeesite.modules.sys.entity.KaoChang;
import com.thinkgem.jeesite.modules.sys.entity.Notice;
import com.thinkgem.jeesite.modules.sys.entity.Regeist;
import com.thinkgem.jeesite.modules.sys.service.RegeisterService;
/**
 * 
 * @author xpy
 *用于注册
 */
@Controller
public class RegeisterController extends BaseController{
	@Autowired
	private RegeistDao regeistDao;
	@Autowired
	private NoticeDao noticeDao;
	@Autowired
	private RegeisterService rService;
	/**
	 * 注册转向
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value ="regeist/regeist.do",method = RequestMethod.POST)
	public String regeist(HttpServletRequest request, HttpServletResponse response, Model model){
		
		return "modules/bole/register";
	}
	/**
	 * 注册转向
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value ="regeist/regeist.do",method = RequestMethod.GET)
	public String regeistGet(HttpServletRequest request, HttpServletResponse response, Model model){
		
		return "modules/bole/register";
	}
	
	
	/**
	 * 考生须知页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value ="regeist/notice.do",method = RequestMethod.GET)
	public String notice(HttpServletRequest request,HttpServletResponse response,Model model){
				//做一个判断，是否为华师/特长
				//获取值1：华师班报名    2：特长班报名
		       List<Notice> notice=null;
				String reg=request.getParameter("reg");
				if(reg.equals("1")){
					//通过后台查找注册须知内容
					notice=noticeDao.findListByid(reg);
					
					//将这个值传到页面上
					request.getSession().setAttribute("student", "1");
					//model.addAttribute("student", "1");
				}else{
					notice=noticeDao.findListByid("2");
					request.getSession().setAttribute("student", "2");
					model.addAttribute("student","2");
				}
				//前台显示
				model.addAttribute("notice", notice);
				
		return "modules/bole/notice";
	}
	/**
	 * 返回首页
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value ="/index.do")
	public String index(HttpServletRequest request,HttpServletResponse response,Model model){
		//销毁session
		request.getSession().invalidate();
		return "redirect:index.jsp";
	}
	/**
	 * 上传图片
	 * @param request
	 * @param response
	 * @param model
	 * @param pic
	 * @param regeist
	 * @return
	 */
	@RequestMapping(value ="regeist/regcenter.do",method = RequestMethod.POST)
	public  String  center(HttpServletRequest request,HttpServletResponse response,Model model,MultipartFile pic,Regeist regeist){
		//logger.debug(regeist.getAddress());
		//首先查找regesit是否存在
		Regeist reg=(Regeist) request.getSession().getAttribute("user");
		if(reg!=null){
			return "modules/bole/personCenter";
		}
		
		String path = request.getSession().getServletContext().getRealPath("regcenter"); 
			//拿到图片的原始名称
		String 	fileName=pic.getOriginalFilename();
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
	        //将对象保存到数据库中
	        String special=(String) request.getSession().getAttribute("student");
	        regeist.setSpecial(Integer.valueOf(special));
	        //在这里分配座位号和考场号学籍号
	        rService.insertIntoTable(regeist);
	        
	        //model.addAttribute("fileUrl", request.getContextPath()+"/regcenter/"+fileName);
	        //转发到页面显示
	       request.getSession().setAttribute("user", regeist);
		return "modules/bole/personCenter";
	}
	/**
	 * 修改注册信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="regeist/regcard.do",method = RequestMethod.POST)
	@ResponseBody
	public String regIdcard(HttpServletRequest request,HttpServletResponse response,Model model){
		String idcard=request.getParameter("idcard");
		//拿到session看idcard是否不变
		Regeist regeist2=(Regeist) request.getSession().getAttribute("user");
		
		//判断是否找到此人
		Regeist regeist=regeistDao.findByIdCard(idcard);
		if(regeist2!=null){
			if(regeist2.getIdcard().equals(idcard)){
				//没做修改idcard
				return "2";
			}
		}
		if(regeist!=null){
			//此人存在，不能修改
			return "2";
		}
		return "1";
	}
	
	
}
