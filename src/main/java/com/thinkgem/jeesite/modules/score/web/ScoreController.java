package com.thinkgem.jeesite.modules.score.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;
/**
 * 成绩查询模块
 * @author xpy
 *
 */
@Controller
@RequestMapping(value="${adminPath}/score/score")
public class ScoreController extends BaseController{
	/**
	 * 查询所有成绩
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value ="find")
	public String find(HttpServletRequest request,Model model){
		System.out.println("走到这里");
		return "modules/score/scoreList";
	}
}
