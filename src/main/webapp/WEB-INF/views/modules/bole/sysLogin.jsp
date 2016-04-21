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
    <title>登录</title>
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
    <!--login登录-->
    <script src="${ctxStatic}/bole/js/jquery.validate.js" type="text/javascript"></script>
    <script src="${ctxStatic}/bole/js/additional-methods.js" type="text/javascript"></script>
    
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/bole/styles/login.css"/>
    <style>
        html {
            height: 100%;
            background: url(${ctxStatic}/bole/images/login-bg.jpg) no-repeat scroll center center;
            background-attachment: fixed;
            background-size: cover;
        }
    </style>
    <style type="text/css">  
    label.error { 
   float:left;
    color: red;  
    font-style: italic;  
	}  
	</style>
</head>

<body>
<div class="login-container">
    <div class="login-wrap">
        <!--title-->
        <h1 class="login-title">
            <img src="${ctxStatic}/bole/images/login-logo.png" width="455" height="70" alt="华师一附中博乐分校(博乐市高级中学)新高一网上报名">
        </h1>

        <!--form-->
        <div class="login-form">
            <h2>
            
                <img src="${ctxStatic}/bole/images/login-font.png" width="80" height="37" alt="登录">
            </h2>

            <div style="clear: both;"></div>

            <form class="form" id="commentForm" action="/login/login2.do"  method="post"> 
                <!--姓名-->
                <div class="form-group">
                    <label class="login-form-label"><img src="${ctxStatic}/bole/images/user.png" width="18" height="18" alt="用户名"></label>
                    <input type="text" name="logname" class="login-form-text" placeholder="请输入姓名">
                </div>

                <!--身份证号码-->
                <div class="form-group">
                    <label class="login-form-label"><img src="${ctxStatic}/bole/images/id.png" width="18" height="18" alt="身份证号码"></label>
                    <input type="text" name="idcard" class="login-form-text" placeholder="请输入身份证号码">
                </div>

                <!--密码-->
                <div class="form-group">
                    <label class="login-form-label"><img src="${ctxStatic}/bole/images/pwd.png" width="18" height="18" alt="密码"></label>
                    <input type="password" name="password" class="login-form-text" placeholder="请输入密码">
                </div>
				<label style="float: left;color: red;">${msg}</label>
                <!--btn-->
                <div class="form-group login-btn-wrap">
                    <input type="submit" value="登录" class="fl btn btn-primary">
                    <input id="reg" type="button" value="注册" class="fr btn btn-danger">
                </div>
            </form>

            <div style="clear: both;"></div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
$(function(){
	//注册按钮
	$("#reg").bind("click",function(){
		window.location.href="/regeist/notice.do?reg="+${student};
	});
})

$.validator.addMethod("pid", function(value,element,params) {  
    if(params.test(value)){
    	//填写正确
    	return true;
    }
    
}, "请正确填写身份证号码");

$().ready(function() {  
    
$("#commentForm").validate({  
            rules: {  
            	logname: {  
                        required:true,  
                        minlength:2  
                },  
                idcard: {  
                        pid:/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/,  
                       
                },  
                password: {  
                    required: true,  
                    minlength: 6  
                }
                
                
            },  
            messages: {  
            	logname: {  
                        required:"用户名必填",  
                        minlength:"至少2个字符"  
                },  
              
                password: {  
                    required: "请输入密码",  
                    minlength: "密码不能少于6个字符"  
                }
               
            }  
    });  
}); 


</script>
</html>
