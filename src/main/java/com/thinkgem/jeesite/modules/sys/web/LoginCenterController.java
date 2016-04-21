package com.thinkgem.jeesite.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.dao.RegeistDao;
import com.thinkgem.jeesite.modules.sys.entity.Regeist;
@Controller
public class LoginCenterController extends BaseController{
	@Autowired
	private RegeistDao regeistDao;
	/**
	 * 跳转登陆页面
	 * @author xingpeiyue
	 */
	@RequestMapping(value = "login/login.do",method = RequestMethod.GET)
	public String loginTo(HttpServletRequest request,HttpServletResponse response,Model model){
		//点击登陆的时候也要将华师一和特长班区分开
		//获取值1：华师班报名    2：特长班报名
		String reg=request.getParameter("reg");
		if(reg.equals("1")){
			//将这个值传到页面上
			request.getSession().setAttribute("student", "1");
			//model.addAttribute("student", "1");
		}else{
			request.getSession().setAttribute("student", "2");
		}
		return "modules/bole/sysLogin";
	}
	/**
	 * 此方法用于登陆页面验证
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "login/login2.do",method = RequestMethod.POST)
	public String loginCenter(HttpServletRequest request,HttpServletResponse response,Model model){
		String name=request.getParameter("logname");
		String idcard=request.getParameter("idcard");
		String password=request.getParameter("password");
		//根据idcard查找用户信息
		Regeist regeist=regeistDao.findByIdCard(idcard);
		//当用户是华师班确点击特长班进入时。
		Integer special=Integer.valueOf((String) request.getSession().getAttribute("student"));
		//对用户进行判断
		if(regeist==null || regeist.getSpecial()!=special){
			model.addAttribute("msg", "身份证号不存在！");
			return "modules/bole/sysLogin";
		}
		if(!regeist.getLogname().equals(name)){
			model.addAttribute("msg", "姓名不正确！");
			return "modules/bole/sysLogin";
		}
		if(!regeist.getPassword().equals(password)){
			model.addAttribute("msg", "密码不正确！");
			return "modules/bole/sysLogin";
		}
		//登陆成功后将信息保存在session中方便其它页面使用
		request.getSession().setAttribute("user", regeist);
		return "modules/bole/personCenter";
	}
	
}
