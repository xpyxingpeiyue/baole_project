<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!doctype html>
<html lang="zh-cmn-Hans">
<head>
    <!-- 声明文档使用的编码 -->
    <meta charset="UTF-8">
    <!-- 优先使用最新版的IE和Chrome浏览器 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <!-- 360浏览器使用webkit内核 -->
    <meta name="renderer" content="webkit"/>
    <!-- 禁止Google浏览器提示翻译 -->
    <meta name="google" content="notranslate"/>
    <!-- 添加favicon -->
    <link rel="shortcut icon" type="image/ico" href="/favicon.ico"/>

    <!-- SEO开始 -->
    <title>个人中心-基本信息</title>
    <meta name="keywords" content="">
    <!--网站关键字-->
    <meta name="description" content="不超过150个字符">
    <!--网站描述-->
    <meta name="author" content="xzxw1115@126.com">
    <meta name="robots" content="index,follow">
    <!--如果您不想搜索引擎追踪此网页上的链接，且不传递链接的权重，请将此元标记置入网页的 <HEAD> 部分-->
    <!-- SEO结束 -->

    <!-- base共用样式 -->
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/bole/styles/base.css"/>

<script src="${ctxStatic}/jquery/jquery-1.9.1.js" type="text/javascript" ></script>
  
   <script src="${ctxStatic}/bole/js/jquery.validate.js" type="text/javascript"></script>
    <script src="${ctxStatic}/bole/js/additional-methods.js" type="text/javascript"></script>

    <!--personalCenter个人中心-->
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/bole/styles/personalCenter.css"/>
    <style>
        html {
            height: 100%;
            background: url(${ctxStatic}/bole/images/personalCenter-bg.jpg) no-repeat scroll center center;
            background-attachment: fixed;
            background-size: cover;
        }
        
    </style>
    <style type="text/css">  
			    label.error {  
			    color: red;  
			    font-size: 12px;
			    display: block;
    			text-align: right;
				}  
				</style>
</head>

<body>
<!--header-->
<div class="register-header">
    <div class="register-container">
        <img src="${ctxStatic}/bole/images/register-logo.png" class="register-logo" width="622" height="23"
             alt="华师一附中博乐分校(博乐市高级中学)新高一网上报名">
        <a href="/index.do" class="register-header-a">
            <img src="${ctxStatic}/bole/images/icon-exit.png" width="16" height="16" alt="退出图标">退出
        </a>
        <span class="fr" style="padding-right: 30px;">您好，${user.logname}</span>
    </div>
</div>

<!--container开始-->
<div class="personalCenter-container">
    <!--title-->
    <div class="personalCenter-title">
        <img src="${ctxStatic}/bole/images/personalCenter-title.png" width="154" height="35" alt="个人中心">
    </div>

    <!--menu开始-->
    <div class="personalCenter-menu">
        <ul>
            <li><a href="/user/redirectCenter.do"><img src="${ctxStatic}/bole/images/personalCenter-icon1.jpg"></a></li>
            <li><a href="/user/startCenter.do"><img src="${ctxStatic}/bole/images/personalCenter-icon2.jpg"></a></li>
            <li><a href="/user/printCenter.do"><img src="${ctxStatic}/bole/images/personalCenter-icon3.jpg"></a></li>
            <li><a href="/user/gradeCenter.do"><img src="${ctxStatic}/bole/images/personalCenter-icon4.jpg"></a></li>
        </ul>
    </div>
    <!--menu结束-->
    <div style="clear: both;"></div>

    <!--main开始-->
    <div class="personalCenter-main">
        <!--选中-->
        <div class="personalCenter-down1"><img src="${ctxStatic}/bole/images/personalCenter-down1.png" width="21" height="11" alt="选中图标">
        </div>

        <!--基本信息personalCenterMessage开始-->
        <div class="personalCenterMain personalCenterMessage">
            <!--table开始-->
            <form id="commentForm" class="form" action="/user/userinfo.do" method="POST" >
            <c:if test="${st=='1'}">
            <div id="divbox" style="border: 1px #CCC solid; width: 327px;height: 42px; margin-top: -11px;margin-left: 170px;text-align: center;line-height: 42px;position: fixed;color: red;background-color: #34A0DE;">
            		保存成功
            </div>
            </c:if>
                <table class="personalCenterMessage-table" >
                    <thead>
                    
                    
                    <tr>
                        <td colspan="4">学生基本信息</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>姓名：</td>
                        <td><input type="text" name="logname" id="logname" value="${user.logname}" readonly="readonly" style="border: 0px;"/></td>
                        <td colspan="2" rowspan="6" align="right" style="vertical-align: top;">
                        	<a href="javascript:void(0);" style="color:black">
                            <img id="preview" class="fr" src="${user.picture}" width="120" height="160" alt="登记照">
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td>身份证号码：</td>
                        <td ><input type="text" name="idcard" id="idCard" value="${user.idcard}" readonly="readonly" style="border: 0px;"/><span id="card" style="color:red"></span></td>
                    
                    </tr>
                    <input type="hidden" name="examination" value="${user.examination}">
                    <tr>
                        <td>密码：</td>
                        <td><input type="text" name="password" value="${user.password}" style="border: 0px;"/></td>
                    </tr>
                    <tr>
                        <td>性别：</td>
                        <td>&nbsp;&nbsp;<input type="radio" id="girl" disabled="disabled" name="sex" value="1" <c:if test="${user.sex==1}">checked</c:if>>&nbsp;女&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="sex" disabled="disabled" readonly="readonly" value="2" <c:if test="${user.sex==2}">checked</c:if> >&nbsp;男</td>
                        
                    </tr>
                    <tr>
                        <td>族别：</td>
                        <td><input type="text" name="national" value="${user.national }" style="border: 0px;"/></td>
                    </tr>
                    <tr>
                        <td>户籍所在地：</td>
                        <td><input type="text" name="address" value="${user.address }" style="border: 0px;"/></td>
                    </tr>
                    <tr>
                        <td style="width: 134px;float: left" >父亲联系电话：</td>
                        <td><input type="text" name="fatherPhone" value="${user.fatherPhone}" style="border: 0px;"/></td>
                        <td style="width: 160px;">母亲联系电话：</td>
                        <td><input type="text" name="motherPhone" value="${user.motherPhone}" style="border: 0px;"/></td>
                    </tr>
                    <tr>
                        <td>父亲姓名：</td>
                        <td><input type="text" name="fatherName" value="${user.fatherName}" style="border: 0px;"/></td>
                        <td style="width: 160px;float: left;">父亲工作单位：</td>
                        <td><input type="text"  name="fatherWork" value="${user.fatherWork}" style="border: 0px;"/></td>
                    </tr>
                    <tr>
                        <td>母亲姓名：</td>
                        <td><input type="text" name="motherName" value="${user.motherName }" style="border: 0px;"/></td>
                        <td style="width: 160px;float: left;">母亲工作单位：</td>
                        <td><input type="text" name="motherWork" value="${user.motherWork }" style="border: 0px;"/></td>
                    </tr>
                    <tr>
                        <td>毕业学校：</td>
                        <td><input type="text" name="graduate" value="${user.graduate }" style="border: 0px;"/></td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>家庭住址：</td>
                        <td width="270"><input name="home" type="text" value="${user.home}" style="border: 0px;"/></td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                    <c:if test="${student==2}">
                    <tr>
                        <td>特长类型：</td>
                        <td >
                        <select id="xpy" name="specialClass" disabled="disabled" style="border: 0px;">
                    <option value="">请选择类型</option>
                    <option value="1" <c:if test="${user.specialClass==1}">selected</c:if>>音乐特长</option>
                    <option value="2"<c:if test="${user.specialClass==2}">selected</c:if>>美术特长</option>
                    <option value="3"<c:if test="${user.specialClass==3}">selected</c:if>>篮球特长</option>
                    <option value="4"<c:if test="${user.specialClass==4}">selected</c:if>>田径特长</option>
                    <option value="5"<c:if test="${user.specialClass==5}">selected</c:if>>主持人特长</option>
                		</select>
                       </td> 
                    </tr>
                    </c:if>
                    </tbody>
                </table>
            </form>
            <!--table结束-->

            <!--btn-->
            <div class="tc" style="margin-top: 20px;">
                <button id="btn" type="button" class="btn btn-primary" style="margin-right:20px;" onclick="save()">保存</button>
                <button id="reset" type="reset" class="btn btn-danger" >重置</button>
            </div>
        </div>
        <!--基本信息personalCenterMessage结束-->
    </div>
    <!--main结束-->
</div>
<!--container结束-->

<div style="height: 136px;"></div>

</body>

<script type="text/javascript">
function save(){
	$("#commentForm").submit();
}

	//表单重置
	$("#reset").bind("click",function(){
		
		$(".form").find(":input").not(":button,:submit,:reset,:hidden").not($("#idCard")).not($("#xpy")).not($("#logname")).val("");
		//$("#idCard").val(text);
		//$("#girl").attr("checked","checked");
	})
	
</script>

<script type="text/javascript">
//自定义身份证验证格式

$.validator.addMethod("zb", function(value,element,params) {  
    if(params.test(value)){
    	//填写正确
    	return true;
    }
    
}, "族别必须为汉字");

$.validator.addMethod("add", function(value,element,params) {  
	
    if(params.test(value)){
    	//填写正确
    	return true;
    }
    
}, "汉字数字字母");

$.validator.addMethod("phone", function(value,element) {  

	var mobileRule=/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
	var telphoneRule=/^\d{3,4}-?\d{7,9}$/;
    if(mobileRule.test(value)||telphoneRule.test(value)){
    	//填写正确
    	$("#father").html("");
    	return true;
    }
    
},"手机号码格式不正确");

 $.validator.addMethod("cname", function(value,element,params) {  
		
	    if(params.test(value)){
	    	//填写正确
	    	return true;
	    }
	    
	}, "姓名为2-4位");
 $.validator.addMethod("ad", function(value,element,params) {  
		
	    if(params.test(value)){
	    	//填写正确
	    	return true;
	    }
	    
	}, "必须为汉字");
 
 $.validator.addMethod("hden", function(value,element,params) {  
	
	    if(params.test(value)){
	    	//填写正确
	    	return true;
	    }
	    
	}, "请上传正确图片");

$().ready(function() {  
    
$("#commentForm").validate({  
            rules: {  
            	logname: {  
                        required:true,  
                        minlength:2  
                },  
                password: {  
                    required: true,  
                    minlength: 6  
                }, 
                national:{
                	zb:/^[\u4E00-\u9FA5]+$/,
                },
                home:{
                	add:/^[\u4E00-\u9FA5A-Za-z0-9]+$/,
                },
                fatherPhone:{
                	phone:/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/ || /^(\(\d{3,4}-)|\d{3.4}-)?\d{7,8}$/,
                },
                motherPhone:{
                	phone:/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/,
                },
                fatherName:{
                	cname:/^[\u4E00-\u9FA5]{2,4}$/,
                },
                motherName:{
                	cname:/^[\u4E00-\u9FA5]{2,4}$/,
                },
                fatherWork:{
                	add:/^[\u4E00-\u9FA5A-Za-z0-9]+$/,
                },
                hid:{
                	hden:/^[\u4E00-\u9FA5]{2,4}$/,
                },
                
                motherWork:{
                	add:/^[\u4E00-\u9FA5A-Za-z0-9]+$/,
                },
                graduate:{
                	ad:/^[\u4E00-\u9FA5]+$/,
                },
                address:{
                	ad:/^[\u4E00-\u9FA5]+$/,
                },
               special:{
            	   required:true
               }
                
                
            },  
            messages: {  
            	logname: {  
                        required:"请输入用户名",  
                        minlength:"至少2个字符"
                },  
               
                password: {  
                    required: "请输入密码",  
                    minlength: "密码不能少于6个字符"  
                },  
                special:{
             	   required:"请至少选择一个"
                }
            }  
    });  
}); 


</script>


<script language="javascript">
function codefans(){
var box=document.getElementById("divbox");
box.style.display="none"; 
}
setTimeout("codefans()",2000);//2秒，可以改动
</script>

</html>



