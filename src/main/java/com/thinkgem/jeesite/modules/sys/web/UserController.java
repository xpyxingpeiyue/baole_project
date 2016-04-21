/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.junit.experimental.theories.PotentialAssignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.dao.CourseDao;
import com.thinkgem.jeesite.modules.sys.dao.GradeDao;
import com.thinkgem.jeesite.modules.sys.dao.RegeistDao;
import com.thinkgem.jeesite.modules.sys.dao.SpecialDao;
import com.thinkgem.jeesite.modules.sys.entity.Course;
import com.thinkgem.jeesite.modules.sys.entity.Grade;
import com.thinkgem.jeesite.modules.sys.entity.Imports;
import com.thinkgem.jeesite.modules.sys.entity.KaoChang;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Regeist;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.Special;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 用户Controller
 * @author ThinkGem
 * @version 2013-8-29
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/user")
public class UserController extends BaseController {

	@Autowired
	private SystemService systemService;
	@Autowired
	private RegeistDao regeistDao;
	@Autowired
	private SpecialDao specialDao;
	@Autowired
	private GradeDao gradeDao;
	@Autowired
	private CourseDao courseDao;
	@ModelAttribute
	public Regeist get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return regeistDao.findByRId(Integer.valueOf(id));
		}else{
			return new Regeist();
		}
	}

	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = {"index"})
	public String index(User user, Model model) {
		return "modules/sys/userIndex";
	}

	/**
	 * 学生成绩导入和查询
	 * 需要进行处理
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "grade")
	public String grade(Grade grade,Regeist regeist,HttpServletRequest request,HttpServletResponse response,Model model){
//		String idcard=request.getParameter("idcard");
//		String logname=request.getParameter("logname");
      	String status=request.getParameter("status");
//		regeist.setIdcard(idcard);
//		regeist.setLogname(logname);
		
		//Page<Grade> pages=new Page<Grade>(request, response);
		//grade.setPage(pages);
		Page<Regeist> pages=new Page<Regeist>(request, response);
		regeist.setPage(pages);
		//多对多的关系
		if(status.equals("0")){
			//华师班学生，首先到注册表查询学生
			regeist.setSpecialClass(0);
			model.addAttribute("status", "0");
		}else if(status.equals("1")){
			regeist.setSpecialClass(1);
			model.addAttribute("status", "1");
		}else if(status.equals("2")){
			regeist.setSpecialClass(2);
			model.addAttribute("status", "2");
		}else if(status.equals("3")){
			regeist.setSpecialClass(3);
			model.addAttribute("status", "3");
		}else if(status.equals("4")){
			regeist.setSpecialClass(4);
			model.addAttribute("status", "4");
		}else if(status.equals("5")){
			regeist.setSpecialClass(5);
			model.addAttribute("status", "5");
		}
		
		//查询成绩表有多少条记录
		List<Grade> listGrade=new ArrayList<Grade>();
		List<String> ls=gradeDao.findListAllIdcard(status);
		List<Regeist> grades=null;
		//是否有成绩
		if(ls!=null&& ls.size()>0){
			for(String s:ls){
				listGrade.add(new Grade(null,s,null, null,null));
			}
			regeist.setGrades(listGrade);
			grades=new ArrayList<Regeist>();
			List<Map<String, Object>> listMap=regeistDao.findListByGrade(regeist);
			for(Map<String, Object> m:listMap){
				Regeist r=new Regeist();
				r.setId(Integer.valueOf(String.valueOf(m.get("id"))));
				r.setIdcard(m.get("idcard").toString());
				r.setLogname(m.get("logname").toString());
				r.setSex(m.get("sex").toString());
				r.setNational(m.get("national").toString());
				r.setGraduate(m.get("graduate").toString());
				r.setExamination(m.get("examination").toString());
				grades.add(r);
			}
			//找到所有分页后的人员后开始找对应的成绩全部对象
			 for(Regeist r:grades){
				r.setGrades(gradeDao.findListByIdcard(r.getIdcard()));
			 }
			List<Grade> Lg=grades.get(0).getGrades();
			model.addAttribute("courses", Lg);
			
		}else{
			if(status.equals("0")){
			List<Course> courses=courseDao.findBySpecialId(1);
			model.addAttribute("courses", courses);
			
				}else{
					List<Course> courses=courseDao.findByStatus(Integer.valueOf(status));
					model.addAttribute("courses", courses);
				}
	}
		
		/**
		 * 
		grade.setRegeist(list);
		//将考籍号和map对应
		Map<String, Regeist> map=new HashMap<String, Regeist>();
		List<Grade> grades=null;
		if(list!=null&&list.size()>0){
			for(Regeist r:list){
				map.put(r.getExamination(), r);
			}
			List<Map<String,Object>> mapReg=gradeDao.findListByExam(grade);
			grades=new ArrayList<Grade>();
			for(Map<String,Object> m:mapReg){
				grade=new Grade();
				grade.setId((Integer) m.get("id"));
				grade.setExamination((String) m.get("examination"));
				grade.setName((String) m.get("name"));
				grade.setScore((String) m.get("score"));
				grade.setReg(map.get((String) m.get("examination")));
				grades.add(grade);
			}
		}
		 */
		pages.setList(grades);
		//model.addAttribute("regeist", regeist);
		model.addAttribute("page", pages);
		return "modules/sys/gradeList";
	}
	
	
	/**
	 * 学生成绩修改
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "formGrade")
	public String formGrade(HttpServletRequest request, HttpServletResponse response, Model model){
		
		String idcard=request.getParameter("idcard");
		String id=request.getParameter("id");
		Regeist regeist=regeistDao.findByIdCard(idcard);
		Grade grade=gradeDao.findByid(Integer.valueOf(id));
		grade.setReg(regeist);
		model.addAttribute("grade", grade);
		return "modules/sys/gradeForm";
	}
	
	
	/**
	 * 学生成绩保存
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "saveGrade")
	public String saveGrade(Grade grade,HttpServletRequest request, HttpServletResponse response, Model model){
		String status=request.getParameter("status");
		gradeDao.updateGrade(grade);
		return "redirect:" + adminPath + "/sys/user/grade?status="+status;
	}
	/**
	 * 学生成绩删除
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "deleteGrade")
	public String deleteGrade( HttpServletRequest request, HttpServletResponse response, Model model){
		String status=request.getParameter("status");
		//String id=request.getParameter("id");
		String idcard=request.getParameter("idcard");
		//删除的是这个学生下的所有成绩
		gradeDao.deleteByIdcard(idcard);
		//gradeDao.deleteById(Integer.valueOf(id));
		return "redirect:" + adminPath + "/sys/user/grade?status="+status;
	}
	
	
	
	
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = {"list", ""})
	public String list(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<User> page = systemService.findUser(new Page<User>(request, response), user);
        model.addAttribute("page", page);
		return "modules/sys/userList";
	}
	
	//华师班的学生
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "hs")
	public String hsList(Regeist regeist,HttpServletRequest request,HttpServletResponse response,Model model){
		//华师班为1
		Page<Regeist> pages=new Page<Regeist>(request, response);
		regeist.setPage(pages);
		//判断special的状态
		//获取status
		String status=request.getParameter("status");
		if(status.equals("1")){
			regeist.setSpecial(1);
			model.addAttribute("status", "1");
		}else{
			regeist.setSpecial(2);
			model.addAttribute("status", "2");
		}
		pages.setList(regeistDao.findList(regeist));
		model.addAttribute("page", pages);
		return "modules/sys/userList";
	}
	//华师学生信息列表
	
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "hsDetail")
	public String hsDetail(HttpServletRequest request,HttpServletResponse response,Model model){
		//获取华师班学生id
		String id=request.getParameter("id");
		//获取regeist对象
		Regeist regeist=regeistDao.findByRId(Integer.valueOf(id));
		//是否华师班进行判断
		Integer specialClass=regeist.getSpecialClass();
		if(specialClass!=null){
			Special special=specialDao.findBySpecialId(specialClass);
			model.addAttribute("status", "2");
			model.addAttribute("special",special);
		}else{
			model.addAttribute("status", "1");
		}
		model.addAttribute("regeist", regeist);
		return "modules/sys/userForm";
	}
	
	//查看详细信息
	
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "userDetail")
	public String userDetail(HttpServletRequest request,HttpServletResponse response,Model model){
		String idcard=request.getParameter("idcard");
		Regeist regeist=regeistDao.findByIdCard(idcard);
		Special special=specialDao.findBySpecialId(regeist.getSpecialClass());
		model.addAttribute("special",special);
		model.addAttribute("regeist", regeist);
		return "modules/sys/userGrade";
	}
	
	
	
	//删除华师班
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "deleteHS")
	public String deleteHS(HttpServletRequest request,HttpServletResponse response,Model model){
		String id=request.getParameter("id");
		String status=request.getParameter("status");
		
		//删除后学生的做好要放在删除备份表。根据学籍号来删除
		//获取删除的对象
		Regeist regeist=regeistDao.findByRId(Integer.valueOf(id));
		//获取考籍号
		String exam=regeist.getExamination();
		
		//删除的数据要保存到备份表中，首先要查找到这条数据
	    KaoChang kc=regeistDao.findByExamKC(exam);
		
		//删除坐号表的数据
		regeistDao.deleteByExam(exam);
		
		//插入到备份表中
		regeistDao.insertToBF(kc);
		
		regeistDao.deleteById(Integer.valueOf(id));
		
		return "redirect:" + adminPath + "/sys/user/hs?status="+status;
		
	}
	
	//特长班的学生
		@RequiresPermissions("sys:user:view")
		@RequestMapping(value = "tc")
		public String tcList(Regeist regeist,HttpServletRequest request,HttpServletResponse response,Model model){
			//华师班为1
			Page<Regeist> pages=new Page<Regeist>(request, response);
			regeist.setPage(pages);
			//判断special的状态
			regeist.setSpecial(2);
			pages.setList(regeistDao.findList(regeist));
			model.addAttribute("page", pages);
			return "modules/sys/userList";
		}
	
	
	@ResponseBody
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = {"listData"})
	public Page<User> listData(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<User> page = systemService.findUser(new Page<User>(request, response), user);
		return page;
	}

	/**
	 * 华师班信息修改
	 * @param regeist
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "updateHS")
	public String updateHS(HttpServletRequest request,HttpServletResponse response,Model model){
		String status=request.getParameter("status");
		String id=request.getParameter("id");
		Regeist regeist=regeistDao.findByRId(Integer.valueOf(id));
		model.addAttribute("regeist", regeist);
		model.addAttribute("status", status);
		return "modules/sys/userFormHT";
		
	}
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "form")
	public String form(User user, Model model) {
		if (user.getCompany()==null || user.getCompany().getId()==null){
			user.setCompany(UserUtils.getUser().getCompany());
		}
		if (user.getOffice()==null || user.getOffice().getId()==null){
			user.setOffice(UserUtils.getUser().getOffice());
		}
		model.addAttribute("user", user);
		model.addAttribute("allRoles", systemService.findAllRole());
		return "modules/sys/userForm";
	}

	
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "saveAll")
	public String saveAll(Regeist regeist,HttpServletRequest request,HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
		String status=request.getParameter("status");
		regeistDao.saveAll(regeist);
		addMessage(redirectAttributes, "保存用户'" + regeist.getLogname() + "'成功");
		return "redirect:" + adminPath + "/sys/user/hs?status="+status;
	}
	
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "save")
	public String save(User user, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
		// 修正引用赋值问题，不知道为何，Company和Office引用的一个实例地址，修改了一个，另外一个跟着修改。
		user.setCompany(new Office(request.getParameter("company.id")));
		user.setOffice(new Office(request.getParameter("office.id")));
		// 如果新密码为空，则不更换密码
		if (StringUtils.isNotBlank(user.getNewPassword())) {
			user.setPassword(SystemService.entryptPassword(user.getNewPassword()));
		}
		if (!beanValidator(model, user)){
			return form(user, model);
		}
		if (!"true".equals(checkLoginName(user.getOldLoginName(), user.getLoginName()))){
			addMessage(model, "保存用户'" + user.getLoginName() + "'失败，登录名已存在");
			return form(user, model);
		}
		// 角色数据有效性验证，过滤不在授权内的角色
		List<Role> roleList = Lists.newArrayList();
		List<String> roleIdList = user.getRoleIdList();
		for (Role r : systemService.findAllRole()){
			if (roleIdList.contains(r.getId())){
				roleList.add(r);
			}
		}
		user.setRoleList(roleList);
		// 保存用户信息
		systemService.saveUser(user);
		// 清除当前用户缓存
		if (user.getLoginName().equals(UserUtils.getUser().getLoginName())){
			UserUtils.clearCache();
			//UserUtils.getCacheMap().clear();
		}
		addMessage(redirectAttributes, "保存用户'" + user.getLoginName() + "'成功");
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}
	
	/**
	 * 删除所有学生德成绩
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(HttpServletRequest request, HttpServletResponse response){
		String status=request.getParameter("status");
		//删除学生信息
		regeistDao.deleteBySpecial(Integer.valueOf(status));
		int st=0;
		if("1".equals(status)){
			st=0;
			regeistDao.deleteByKcBf(st);
			regeistDao.deleteByKcInfo(st);
			regeistDao.deleteByKcJs(String.valueOf(st));
		}else{
			regeistDao.deleteByKcBfIn();
			regeistDao.deleteByKcInfoIn();
			regeistDao.deleteByKcJsTcIn();
		}
		
		return "redirect:" + adminPath + "/sys/user/hs?status="+status;
		
	}
	
	/**
	 * 删除成绩表
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "deleteAllGrade")
	public String deleteAllGrade(HttpServletRequest request, HttpServletResponse response){
		String status =request.getParameter("status");
		gradeDao.deleteAllStatus(status);
		return "redirect:" + adminPath + "/sys/user/grade?status="+status;
	}
	
	
	
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "delete")
	public String delete(User user, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
		if (UserUtils.getUser().getId().equals(user.getId())){
			addMessage(redirectAttributes, "删除用户失败, 不允许删除当前用户");
		}else if (User.isAdmin(user.getId())){
			addMessage(redirectAttributes, "删除用户失败, 不允许删除超级管理员用户");
		}else{
			systemService.deleteUser(user);
			addMessage(redirectAttributes, "删除用户成功");
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}
	
	/**
	 * 导出用户数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(Regeist regeist, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String status=request.getParameter("status");
		try {
            String fileName = "学生信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
           // Page<User> page = systemService.findUser(new Page<User>(request, response, -1), user);
          //查找到所有的华师班
        	List<Regeist> listr=regeistDao.findBySpecialId(Integer.valueOf(status));
        	   List<String> headerList=this.head();
        	   String name="华师班学生信息列表";
        	   if(status.equals("2")){
        		   name="特长班学生信息列表";
        	   }
            	ExportExcel ee = new ExportExcel(name, headerList);
            	export(listr,ee,headerList);
    		ee.write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出用户失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/hs?status="+status;
    }

	/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file,  HttpServletRequest request,RedirectAttributes redirectAttributes) {
		String status=request.getParameter("status");
		//进入导入先报该栏目下的成绩表清空
		gradeDao.deleteAllStatus(status);
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/user/grade";
		}
		try {
			int successNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			//获取行号
			
			//获取最后一个列号
			int CellNum=ei.getLastCellNum();
			//获取最后一个行号
			int RowNum=ei.getLastDataRowNum();
			for(int i=2;i<RowNum;i++){
				successNum++;
				for(int j=0;j<CellNum;j++){
					if(j>=6){
						//考籍号
						String examination=String.valueOf(ei.getCellValue(ei.getRow(i), 2));
						//身份证号
						String idcard=String.valueOf(ei.getCellValue(ei.getRow(i), 0));
						//科目
						String name= String.valueOf(ei.getCellValue(ei.getRow(1), j));
						//分数
						String score=String.valueOf(ei.getCellValue(ei.getRow(i), j));
						double d=Double.valueOf(score);
						score=String.valueOf((int)d);
						gradeDao.insertGrade(new Grade(examination, idcard, name, score,status));
						
					}
					
				}
			}
			//都是从零开始
	//	Object obj=ei.getCellValue(ei.getRow(1), 4);
			//obj.toString().length();
			//List<Imports> list = ei.getDataList(Imports.class);
//			for (Imports imports : list){
//				//判断数据库有没有此人，否则不能导入
//				Regeist regeist=regeistDao.findByIdCard(imports.getIdcard());
//				if(regeist!=null){
//					Grade grade=new Grade();
//					grade.setExamination(regeist.getExamination());
//					grade.setName(imports.getName());
//					grade.setScore(imports.getScore());
//					gradeDao.insertGrade(grade);
//					successNum++;
//				}
//				//System.out.println(imports);
//				
//			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条学生成绩"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入成绩失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/grade?status="+status;
    }
	
	/**
	 * 下载导入用户数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "成绩导入模板.xlsx";
    		List<Imports> list = Lists.newArrayList(); list.add(new Imports("411521199006160256", "admin", "192564125841", "男","汉", "武汉大学", "80", "82", "60", ""));
    		new ExportExcel("成绩导入", Imports.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/grade";
    }

	/**
	 * 验证登录名是否有效
	 * @param oldLoginName
	 * @param loginName
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "checkLoginName")
	public String checkLoginName(String oldLoginName, String loginName) {
		if (loginName !=null && loginName.equals(oldLoginName)) {
			return "true";
		} else if (loginName !=null && systemService.getUserByLoginName(loginName) == null) {
			return "true";
		}
		return "false";
	}

	/**
	 * 用户信息显示及保存
	 * @param user
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "info")
	public String info(User user, HttpServletResponse response, Model model) {
		User currentUser = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getName())){
			if(Global.isDemoMode()){
				model.addAttribute("message", "演示模式，不允许操作！");
				return "modules/sys/userInfo";
			}
			currentUser.setEmail(user.getEmail());
			currentUser.setPhone(user.getPhone());
			currentUser.setMobile(user.getMobile());
			currentUser.setRemarks(user.getRemarks());
			currentUser.setPhoto(user.getPhoto());
			systemService.updateUserInfo(currentUser);
			model.addAttribute("message", "保存用户信息成功");
		}
		model.addAttribute("user", currentUser);
		model.addAttribute("Global", new Global());
		return "modules/sys/userInfo";
	}

	/**
	 * 返回用户信息
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "infoData")
	public User infoData() {
		return UserUtils.getUser();
	}
	
	/**
	 * 修改个人用户密码
	 * @param oldPassword
	 * @param newPassword
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "modifyPwd")
	public String modifyPwd(String oldPassword, String newPassword, Model model) {
		User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(oldPassword) && StringUtils.isNotBlank(newPassword)){
			if(Global.isDemoMode()){
				model.addAttribute("message", "演示模式，不允许操作！");
				return "modules/sys/userModifyPwd";
			}
			if (SystemService.validatePassword(oldPassword, user.getPassword())){
				systemService.updatePasswordById(user.getId(), user.getLoginName(), newPassword);
				model.addAttribute("message", "修改密码成功");
			}else{
				model.addAttribute("message", "修改密码失败，旧密码错误");
			}
		}
		model.addAttribute("user", user);
		return "modules/sys/userModifyPwd";
	}
	
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String officeId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<User> list = systemService.findUserByOfficeId(officeId);
		for (int i=0; i<list.size(); i++){
			User e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", "u_"+e.getId());
			map.put("pId", officeId);
			map.put("name", StringUtils.replace(e.getName(), " ", ""));
			mapList.add(map);
		}
		return mapList;
	}
	
	//设置表头方法
	public List<String> head(){
		List<String> headerList = Lists.newArrayList();
    	headerList.add("编号");
    	headerList.add("身份证");
    	headerList.add("姓名");
    	headerList.add("密码");
    	headerList.add("性别");
    	headerList.add("户籍");
    	headerList.add("民族");
    	headerList.add("父亲姓名");
    	headerList.add("母亲姓名");
    	headerList.add("父亲工作单位");
    	headerList.add("母亲工作单位");
    	headerList.add("父亲电话");
    	headerList.add("母亲电话");
    	headerList.add("毕业院校");
    	headerList.add("家庭地址");
    	headerList.add("是否特长生");
    	headerList.add("特长类别");
    	headerList.add("考场号");
    	headerList.add("考籍号");
    	headerList.add("座位号");
    	return headerList;
	}
    //导出方法
	public void export(List<Regeist> listr,ExportExcel ee,List<String> headerList){
		
    	
    	//存入集合
    	List<Map<Integer, String>> listMap=new ArrayList<Map<Integer,String>>();
    	Map<Integer, String> map=null;
    	int count=1;
    	for(Regeist r:listr){
    		map=new HashMap<Integer, String>();
    		map.put(0,String.valueOf(count));
    		map.put(1, r.getIdcard());
    		map.put(2, r.getLogname());
    		map.put(3, r.getPassword());
    		if(r.getSex().equals("1")){
    			map.put(4, "女");
    		}else{
    			map.put(4, "男");
    		}
    		map.put(5, r.getAddress());
    		map.put(6, r.getNational());
    		map.put(7, r.getFatherName());
    		map.put(8, r.getMotherName());
    		map.put(9, r.getFatherWork());
    		map.put(10, r.getMotherWork());
    		map.put(11, r.getFatherPhone());
    		map.put(12, r.getMotherPhone());
    		map.put(13, r.getGraduate());
    		map.put(14, r.getHome());
    		if(r.getSpecial()==1){
    			//华师
    			map.put(15, "否");
    		}else{
    			map.put(15, "是");
    		}
    		if(r.getSpecialClass()!=null){
    			//特长生
    			map.put(16, regeistDao.findBySpecialName(r.getSpecialClass()));
    		}else{
    			map.put(16, " ");
    		}
    		
    		map.put(17, r.getNumber().toString());
    		map.put(18, r.getExamination().toString());
    		map.put(19, r.getZw().toString());
    		count++;
    		listMap.add(map);
    		
    	}
    	
    	//行
 		  for (int i = 0; i < listMap.size(); i++) {
  		  Row row = ee.addRow();
  		//列
 			for (int j = 0; j < headerList.size(); j++) {
 				ee.addCell(row, j, listMap.get(i).get(j));
 			}
 		}
	}
//	@InitBinder
//	public void initBinder(WebDataBinder b) {
//		b.registerCustomEditor(List.class, "roleList", new PropertyEditorSupport(){
//			@Autowired
//			private SystemService systemService;
//			@Override
//			public void setAsText(String text) throws IllegalArgumentException {
//				String[] ids = StringUtils.split(text, ",");
//				List<Role> roles = new ArrayList<Role>();
//				for (String id : ids) {
//					Role role = systemService.getRole(Long.valueOf(id));
//					roles.add(role);
//				}
//				setValue(roles);
//			}
//			@Override
//			public String getAsText() {
//				return Collections3.extractToString((List) getValue(), "id", ",");
//			}
//		});
//	}
}
