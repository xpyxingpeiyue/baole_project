/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.oa.entity.OaNotify;
import com.thinkgem.jeesite.modules.oa.service.OaNotifyService;
import com.thinkgem.jeesite.modules.sys.dao.CourseDao;
import com.thinkgem.jeesite.modules.sys.entity.Course;

/**
 * 通知通告Controller
 * @author ThinkGem
 * @version 2014-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaNotify")
public class OaNotifyController extends BaseController {

	@Autowired
	private OaNotifyService oaNotifyService;
	@Autowired
	private CourseDao courseDao;
	
	@ModelAttribute
	public OaNotify get(@RequestParam(required=false) String id) {
		OaNotify entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaNotifyService.get(id);
		}
		if (entity == null){
			entity = new OaNotify();
		}
		return entity;
	}
	
	//华师班科目    special=1
	@RequiresPermissions("oa:oaNotify:view")
	@RequestMapping(value = "special")
	public String list(Course course,HttpServletRequest request, HttpServletResponse response, Model model) {
		//Page<OaNotify> page = oaNotifyService.find(new Page<OaNotify>(request, response), new OaNotify());
		Page<Course> pages=new Page<Course>(request, response);
		course.setPage(pages);
		//判断special的状态
		course.setSpecial(1);
		request.getSession().setAttribute("hs", "1");
		pages.setList(courseDao.findList(course));
		model.addAttribute("page", pages);
		return "modules/oa/oaNotifyList";
	}
	//特长班可科目/oa/oaNotify/course  special=2
	@RequiresPermissions("oa:oaNotify:view")
	@RequestMapping(value ="course")
	public String listCourse(Course course,HttpServletRequest request, HttpServletResponse response, Model model) {
		//Page<OaNotify> page = oaNotifyService.find(new Page<OaNotify>(request, response), new OaNotify());
		Page<Course> pages=new Page<Course>(request, response);
		course.setPage(pages);
		String status=request.getParameter("status");
		model.addAttribute("status", status);
		course.setStatus(Integer.valueOf(status));
		request.getSession().setAttribute("tc", "2");
		pages.setList(courseDao.findListByTc(course));
		model.addAttribute("page", pages);
		return "modules/oa/oaNotifyList2";
	}
	/**
	 * 这段代码是Course表里的数据
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("oa:oaNotify:view")
	@RequestMapping(value = "form")
	public String form(HttpServletRequest request, Model model) {
//		if (StringUtils.isNotBlank(oaNotify.getId())){
//			oaNotify = oaNotifyService.getRecordList(oaNotify);
//		}
//		model.addAttribute("oaNotify", oaNotify);
		String id=request.getParameter("id");
		Course course=null;
		if(id==null || id.equals("")){
			course=new Course();
		}else{
			course=courseDao.findByCourseId(Integer.valueOf(id));
		}
		model.addAttribute("course",course);
		return "modules/oa/oaNotifyForm";
	}
  
	
	
	@RequiresPermissions("oa:oaNotify:view")
	@RequestMapping(value = "form2")
	public String form2(HttpServletRequest request, Model model) {
//		if (StringUtils.isNotBlank(oaNotify.getId())){
//			oaNotify = oaNotifyService.getRecordList(oaNotify);
//		}
//		model.addAttribute("oaNotify", oaNotify);
		//获取状态
		String status=request.getParameter("status");
		String id=request.getParameter("id");
		Course course=null;
		if(id==null || id.equals("")){
			course=new Course();
		}else{
			course=courseDao.findByCourseId(Integer.valueOf(id));
		}
		model.addAttribute("status", status);
		model.addAttribute("course",course);
		return "modules/oa/oaNotifyForm2";
	}
	
	//课程的添加修改走的是一个方法为华师班方法
	@RequiresPermissions("oa:oaNotify:edit")
	@RequestMapping(value = "save")
	public String save(HttpServletRequest request,Course course, Model model, RedirectAttributes redirectAttributes) {
//		if (!beanValidator(model, oaNotify)){
//			//return form(oaNotify, model);
//			//return form(req);
//		}
//		// 如果是修改，则状态为已发布，则不能再进行操作
//		if (StringUtils.isNotBlank(oaNotify.getId())){
//			OaNotify e = oaNotifyService.get(oaNotify.getId());
//			if ("1".equals(e.getStatus())){
//				addMessage(redirectAttributes, "已发布，不能操作！");
//				return "redirect:" + adminPath + "/oa/oaNotify/form?id="+oaNotify.getId();
//			}
//		}
//		oaNotifyService.save(oaNotify);
			//华师班
		String special=(String) request.getSession().getAttribute("hs");
	    //如果是修改则special不为null
		if(course.getSpecial()==null||course.getSpecial().equals("")){
			//special为空则为添加
			course.setSpecial(Integer.valueOf(special));
			courseDao.saveCourse(course);
		}else{
			//则为修改操作
			courseDao.updateByCourse(course);
		}
		addMessage(redirectAttributes, "保存通知'" + course.getName() + "'成功");
		return "redirect:" + adminPath + "/oa/oaNotify/special";
	}
	
	@RequiresPermissions("oa:oaNotify:edit")
	@RequestMapping(value = "save2")
	public String save2(HttpServletRequest request,Course course, Model model, RedirectAttributes redirectAttributes) {
//		if (!beanValidator(model, oaNotify)){
//			//return form(oaNotify, model);
//			//return form(req);
//		}
//		// 如果是修改，则状态为已发布，则不能再进行操作
//		if (StringUtils.isNotBlank(oaNotify.getId())){
//			OaNotify e = oaNotifyService.get(oaNotify.getId());
//			if ("1".equals(e.getStatus())){
//				addMessage(redirectAttributes, "已发布，不能操作！");
//				return "redirect:" + adminPath + "/oa/oaNotify/form?id="+oaNotify.getId();
//			}
//		}
//		oaNotifyService.save(oaNotify);
		String status=request.getParameter("status");
			//华师班
		String special=(String) request.getSession().getAttribute("tc");
	    //如果是修改则special不为null
		if(course.getSpecial()==null||course.getSpecial().equals("")){
			//special为空则为添加
			course.setSpecial(Integer.valueOf(special));
			course.setStatus(Integer.valueOf(status));;
			courseDao.saveCourse(course);
		}else{
			//则为修改操作
			courseDao.updateByCourse(course);
		}
		addMessage(redirectAttributes, "保存通知'" + course.getName() + "'成功");
		return "redirect:" + adminPath + "/oa/oaNotify/course?status="+status;
	}
	
	
	
	@RequiresPermissions("oa:oaNotify:edit")
	@RequestMapping(value = "delete")
	public String delete(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		//oaNotifyService.delete(oaNotify);
		String id=request.getParameter("id");
		courseDao.deleteById(Integer.valueOf(id));
		addMessage(redirectAttributes, "删除通知成功");
		return "redirect:" + adminPath + "/oa/oaNotify/special";
	}
	@RequiresPermissions("oa:oaNotify:edit")
	@RequestMapping(value = "delete2")
	public String delete2(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		//oaNotifyService.delete(oaNotify);
		String status=request.getParameter("status");
		String id=request.getParameter("id");
		courseDao.deleteById(Integer.valueOf(id));
		addMessage(redirectAttributes, "删除通知成功");
		return "redirect:" + adminPath + "/oa/oaNotify/course?status="+status;
	}
	/**
	 * 我的通知列表
	 */
	@RequestMapping(value = "self")
	public String selfList(OaNotify oaNotify, HttpServletRequest request, HttpServletResponse response, Model model) {
		oaNotify.setSelf(true);
		Page<OaNotify> page = oaNotifyService.find(new Page<OaNotify>(request, response), oaNotify); 
		model.addAttribute("page", page);
		return "modules/oa/oaNotifyList";
	}

	/**
	 * 我的通知列表-数据
	 */
	@RequiresPermissions("oa:oaNotify:view")
	@RequestMapping(value = "selfData")
	@ResponseBody
	public Page<OaNotify> listData(OaNotify oaNotify, HttpServletRequest request, HttpServletResponse response, Model model) {
		oaNotify.setSelf(true);
		Page<OaNotify> page = oaNotifyService.find(new Page<OaNotify>(request, response), oaNotify);
		return page;
	}
	
	/**
	 * 查看我的通知
	 */
	@RequestMapping(value = "view")
	public String view(OaNotify oaNotify, Model model) {
		if (StringUtils.isNotBlank(oaNotify.getId())){
			oaNotifyService.updateReadFlag(oaNotify);
			oaNotify = oaNotifyService.getRecordList(oaNotify);
			model.addAttribute("oaNotify", oaNotify);
			return "modules/oa/oaNotifyForm";
		}
		return "redirect:" + adminPath + "/oa/oaNotify/self?repage";
	}

	/**
	 * 查看我的通知-数据
	 */
	@RequestMapping(value = "viewData")
	@ResponseBody
	public OaNotify viewData(OaNotify oaNotify, Model model) {
		if (StringUtils.isNotBlank(oaNotify.getId())){
			oaNotifyService.updateReadFlag(oaNotify);
			return oaNotify;
		}
		return null;
	}
	
	/**
	 * 查看我的通知-发送记录
	 */
	@RequestMapping(value = "viewRecordData")
	@ResponseBody
	public OaNotify viewRecordData(OaNotify oaNotify, Model model) {
		if (StringUtils.isNotBlank(oaNotify.getId())){
			oaNotify = oaNotifyService.getRecordList(oaNotify);
			return oaNotify;
		}
		return null;
	}
	
	/**
	 * 课程数目
	 */
	@RequestMapping(value = "self/count")
	@ResponseBody
	public String selfCount(OaNotify oaNotify, Model model) {
		oaNotify.setSelf(true);
		oaNotify.setReadFlag("0");
		return String.valueOf(oaNotifyService.findCount(oaNotify));
		//return String.valueOf(courseDao.findCount(course));
	}
}