/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cms.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.dao.NoticeDao;
import com.thinkgem.jeesite.modules.cms.entity.Comment;
import com.thinkgem.jeesite.modules.cms.service.CommentService;
import com.thinkgem.jeesite.modules.sys.entity.Course;
import com.thinkgem.jeesite.modules.sys.entity.Notice;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 注册和考试须知修改
 * 
 * 评论Controller
 * @author ThinkGem
 * @version 2013-3-23
 */
@Controller
@RequestMapping(value = "${adminPath}/cms/comment")
public class CommentController extends BaseController {

	@Autowired
	private CommentService commentService;
	@Autowired
	private NoticeDao noticeDao;
	
	@ModelAttribute
	public Comment get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return commentService.get(id);
		}else{
			return new Comment();
		}
	}
	
	///cms/comment/?status=2  注册
	//华师注册1  华师考试2
	//..特长班考试须知
	//..特长班注册须知
	
	@RequiresPermissions("cms:comment:view")
	@RequestMapping(value = "regeist")
	public String listRegeist(Notice notice, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String status =request.getParameter("status");
		if(status.equals("1")){
			//华师班注册
			notice.setClassid("1");
		}else{
			//特长班注册
			notice.setClassid("2");
		}
		Page<Notice> pages=new Page<Notice>(request,response);
		notice.setPage(pages);
		pages.setList(noticeDao.findList(notice));
        model.addAttribute("page", pages);
		return "modules/cms/commentRegeist"+notice.getClassid();
	}
	@RequiresPermissions("cms:comment:view")
	@RequestMapping(value = "start")
	public String listStart(Notice notice, HttpServletRequest request, HttpServletResponse response, Model model){
		String status=request.getParameter("status");
		if(status.equals("1")){
			notice.setClassid(status);
			model.addAttribute("status", "1");
		}else{
			notice.setClassid(status);
			model.addAttribute("status", "2");
		}
		Page<Notice> pages=new Page<Notice>(request,response);
		notice.setPage(pages);
		pages.setList(noticeDao.findListByStart(notice));
        model.addAttribute("page", pages);
		return "modules/cms/commentStart1";
	}
	
	
	
	/**
	 * 添加华师注册须知
	 * @param comment
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("cms:comment:view")
	@RequestMapping(value = "formRegeist1")
	public String formRegeist1(Notice notice,HttpServletRequest request,Model model){
		//获取id
		String id=request.getParameter("id");
		if(id==null||id.equals("")){
			//则为添加
			notice=new Notice();
		}else{
			//则为修改
			notice=noticeDao.findById(Integer.valueOf(id));
		}
		model.addAttribute("notice", notice);
		return "modules/cms/commentRegeistForm1";
	}
	/**
	 * 华师注册须知保存(1)
	 * @param notice
	 * @param rqHttpServletRequest
	 * @param model
	 * @return
	 */
	@RequiresPermissions("cms:comment:view")
	@RequestMapping(value = "saveRegeist1")
	public String saveRegeist1(Notice notice,HttpServletRequest rqHttpServletRequest,Model model, RedirectAttributes redirectAttributes){
		//华师的classid=1
		//根据classid来判断是保存还是修改
		
		String content=notice.getContent().replaceAll("&lt;", "<");
		content=content.replaceAll("&gt;",">" );
		content=content.replaceAll("&quot;", "\"");
		content=content.replaceAll("&amp;#39;", "'");
		content=content.replaceAll("&amp;nbsp;", "&nbsp;");
	    notice.setContent(content);
		
		
		if(notice.getClassid()==null||notice.getClassid().equals("")){
			//为保存
			notice.setClassid("1");
			noticeDao.saveNotice(notice);
		}else{
			//为修改
			noticeDao.updateNotice(notice);
		}
		addMessage(redirectAttributes, "保存 成功");
		return "redirect:" + adminPath + "/cms/comment/regeist?status=1";
	}
	
	/**
	 * 开始须知添加
	 * @param notice
	 * @param rqHttpServletRequest
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	
	@RequiresPermissions("cms:comment:view")
	@RequestMapping(value = "formStart")
	public String formStartZ(Notice notice,HttpServletRequest request,Model model, RedirectAttributes redirectAttributes){
		String status=request.getParameter("status");		
		//根据classid来判断是保存还是修改
		String id=request.getParameter("id");
		if(id==null||id.equals("")){
			//则为添加
			notice=new Notice();
		}else{
			//则为修改
			notice=noticeDao.findByStartId(Integer.valueOf(id));
		}
		model.addAttribute("status", status);
		model.addAttribute("notice", notice);
		return "modules/cms/commentStartForm1";
	}
	/**
	 * 删除开始须知
	 * @param request
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("cms:comment:view")
	@RequestMapping(value = "deleteStart")
	public String deleteStart(HttpServletRequest request,Model model,RedirectAttributes redirectAttributes){
		String status=request.getParameter("status");
		String id=request.getParameter("id");
		//删除
		noticeDao.deleteStart(Integer.valueOf(id));
		return "redirect:" + adminPath + "/cms/comment/start?status="+status;
	}
	
	@RequiresPermissions("cms:comment:view")
	@RequestMapping(value = "saveStart")
	public String saveStart(Notice notice,HttpServletRequest request,Model model,RedirectAttributes redirectAttributes){
		String status=request.getParameter("status");
		
		if(notice.getClassid()==null||notice.getClassid().equals("")){
			//为保存
			//华师还是特长
			notice.setClassid(status);
			noticeDao.saveStartNotice(notice);
		}else{
			//为修改
			noticeDao.updateStartNotice(notice);
		} 
		
		
		return "redirect:" + adminPath + "/cms/comment/start?status="+status;
	}
	
	
	@RequiresPermissions("cms:comment:view")
	@RequestMapping(value = "deleteRegeist1")
	public String deleteRegeist1(HttpServletRequest request,Model model,RedirectAttributes redirectAttributes){
		String id=request.getParameter("id");
		noticeDao.deleteById(Integer.valueOf(id));
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/cms/comment/regeist?status=1";
	}
	
	
	/**
	 * 添加华师注册须知
	 * @param comment
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("cms:comment:view")
	@RequestMapping(value = "formRegeist2")
	public String formRegeist2(Notice notice,HttpServletRequest request,Model model){
		//获取id
		String id=request.getParameter("id");
		if(id==null||id.equals("")){
			//则为添加
			notice=new Notice();
		}else{
			//则为修改
			notice=noticeDao.findById(Integer.valueOf(id));
		}
		model.addAttribute("notice", notice);
		return "modules/cms/commentRegeistForm2";
	}
	/**
	 * 特长注册须知保存(1)
	 * @param notice
	 * @param rqHttpServletRequest
	 * @param model
	 * @return
	 */
	@RequiresPermissions("cms:comment:view")
	@RequestMapping(value = "saveRegeist2")
	public String saveRegeist2(Notice notice,HttpServletRequest rqHttpServletRequest,Model model, RedirectAttributes redirectAttributes){
		//华师的classid=2
		//根据classid来判断是保存还是修改
		String content=notice.getContent().replaceAll("&lt;", "<");
		content=content.replaceAll("&gt;",">" );
		content=content.replaceAll("&quot;", "\"");
		content=content.replaceAll("&amp;#39;", "'");
		content=content.replaceAll("&amp;nbsp;", "&nbsp;");
	    notice.setContent(content);
		
		
		if(notice.getClassid()==null||notice.getClassid().equals("")){
			//为保存
			notice.setClassid("2");
			noticeDao.saveNotice(notice);
		}else{
			//为修改
			noticeDao.updateNotice(notice);
		}
		addMessage(redirectAttributes, "保存 成功");
		return "redirect:" + adminPath + "/cms/comment/regeist?status=2";
	}
	
	@RequiresPermissions("cms:comment:view")
	@RequestMapping(value = "deleteRegeist2")
	public String deleteRegeist2(HttpServletRequest request,Model model,RedirectAttributes redirectAttributes){
		String id=request.getParameter("id");
		noticeDao.deleteById(Integer.valueOf(id));
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/cms/comment/regeist?status=2s";
	}
	
	/**
	 * 华师班考试须知
	 * @param comment
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */	
	@RequiresPermissions("cms:comment:view")
	@RequestMapping(value = "exam")
	public String listExam(Notice notice, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String status =request.getParameter("status");
		if(status.equals("1")){
			//华师班注册
			notice.setClassid("1");
		}else{
			//特长班注册
			notice.setClassid("2");
		}
		Page<Notice> pages=new Page<Notice>(request,response);
		notice.setPage(pages);
		pages.setList(noticeDao.findListExam(notice));
        model.addAttribute("page", pages);
		return "modules/cms/commentExam"+notice.getClassid();
	}
	
	/**
	 * 添加或者修改到考试须知
	 * @param notice
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("cms:comment:view")
	@RequestMapping(value = "formExam1")
	public String formExam1(Notice notice,HttpServletRequest request,Model model){
		//获取id
		String id=request.getParameter("id");
		if(id==null||id.equals("")){
			//则为添加
			notice=new Notice();
		}else{
			//则为修改
			notice=noticeDao.findByExamId(Integer.valueOf(id));
		}
		model.addAttribute("notice", notice);
		return "modules/cms/commentExamForm1";
	}
	
	
	@RequiresPermissions("cms:comment:view")
	@RequestMapping(value = "saveExam1")
	public String saveExam1(Notice notice,HttpServletRequest request,Model model, RedirectAttributes redirectAttributes){
		//华师的classid=2
		//根据classid来判断是保存还是修改
		//String content=request.getParameter("content");
		String content=notice.getContent().replaceAll("&lt;", "<");
		content=content.replaceAll("&gt;",">" );
		content=content.replaceAll("&quot;", "\"");
		content=content.replaceAll("&amp;#39;", "'");
		content=content.replaceAll("&amp;nbsp;", "&nbsp;");
	    notice.setContent(content);

		if(notice.getClassid()==null||notice.getClassid().equals("")){
			//为保存
			notice.setClassid("1");
			noticeDao.saveNoticeByExam(notice);
		}else{
			//为修改
			noticeDao.updateNoticeByExam(notice);
		}
		addMessage(redirectAttributes, "保存 成功");
		return "redirect:" + adminPath + "/cms/comment/exam?status=1";
	}
	
	/**
	 * 华师班考试删除
	 * @param request
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("cms:comment:view")
	@RequestMapping(value = "deleteExam1")
	public String deleteExam1(HttpServletRequest request,Model model,RedirectAttributes redirectAttributes){
		String id=request.getParameter("id");
		noticeDao.deleteByExamId(Integer.valueOf(id));
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/cms/comment/exam?status=1";
	}
	
	/**
	 * 特长班考试开始
	 * @param notice
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequiresPermissions("cms:comment:view")
	@RequestMapping(value = "formExam2")
	public String formExam2(Notice notice,HttpServletRequest request,Model model){
		//获取id
		String id=request.getParameter("id");
		if(id==null||id.equals("")){
			//则为添加
			notice=new Notice();
		}else{
			//则为修改
			notice=noticeDao.findByExamId(Integer.valueOf(id));
		}
		model.addAttribute("notice", notice);
		return "modules/cms/commentExamForm2";
	}
	
	
	@RequiresPermissions("cms:comment:view")
	@RequestMapping(value = "saveExam2")
	public String saveExam2(Notice notice,HttpServletRequest rqHttpServletRequest,Model model, RedirectAttributes redirectAttributes){
		//华师的classid=2
		//根据classid来判断是保存还是修改
		String content=notice.getContent().replaceAll("&lt;", "<");
		content=content.replaceAll("&gt;",">" );
		content=content.replaceAll("&quot;", "\"");
		content=content.replaceAll("&amp;#39;", "'");
		content=content.replaceAll("&amp;nbsp;", "&nbsp;");
	    notice.setContent(content);
		
		if(notice.getClassid()==null||notice.getClassid().equals("")){
			//为保存
			notice.setClassid("2");
			noticeDao.saveNoticeByExam(notice);
		}else{
			//为修改
			noticeDao.updateNoticeByExam(notice);
		}
		addMessage(redirectAttributes, "保存 成功");
		return "redirect:" + adminPath + "/cms/comment/exam?status=2";
	}
	
	/**
	 * 华师班考试删除
	 * @param request
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("cms:comment:view")
	@RequestMapping(value = "deleteExam2")
	public String deleteExam2(HttpServletRequest request,Model model,RedirectAttributes redirectAttributes){
		String id=request.getParameter("id");
		noticeDao.deleteByExamId(Integer.valueOf(id));
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/cms/comment/exam?status=2";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@RequiresPermissions("cms:comment:edit")
	@RequestMapping(value = "save")
	public String save(Comment comment, RedirectAttributes redirectAttributes) {
		if (beanValidator(redirectAttributes, comment)){
			if (comment.getAuditUser() == null){
				comment.setAuditUser(UserUtils.getUser());
				comment.setAuditDate(new Date());
			}
			comment.setDelFlag(Comment.DEL_FLAG_NORMAL);
			commentService.save(comment);
			addMessage(redirectAttributes, DictUtils.getDictLabel(comment.getDelFlag(), "cms_del_flag", "保存")
					+"评论'" + StringUtils.abbr(StringUtils.replaceHtml(comment.getContent()),50) + "'成功");
		}
		return "redirect:" + adminPath + "/cms/comment/?repage&delFlag=2";
	}
	
	@RequiresPermissions("cms:comment:edit")
	@RequestMapping(value = "delete")
	public String delete(Comment comment, @RequestParam(required=false) Boolean isRe, RedirectAttributes redirectAttributes) {
		commentService.delete(comment, isRe);
		addMessage(redirectAttributes, (isRe!=null&&isRe?"恢复审核":"删除")+"评论成功");
		return "redirect:" + adminPath + "/cms/comment/?repage&delFlag=2";
	}

}
