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
    <title>网上报名入口</title>
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
	<script src="${ctxStatic}/jquery/jquery-1.9.1.js" type="text/javascript"></script>
    <!--entrance网上报名入口-->
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/bole/styles/entrance.css"/>
    <style>
        html {
            height: 100%;
            background: url(${ctxStatic}/bole/images/entrance-bg.jpg) no-repeat scroll center center;
            background-size: cover;
        }
    </style>
</head>

<body>

<div class="entrance-container">
    <div class="entrance-wrap">
        <!--title-->
        <h1>
            <img src="${ctxStatic}/bole/images/entrance-logo.png" width="562" height="89" alt="华师一附中博乐分校(博乐市高级中学)新高一网上报名">
        </h1>

        <!--form-->
        <div class="entrance-form">
                <div class="form-group">
                    选择项：
                    <input type="radio" name="Radios" value="1" checked="checked">华师班报名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="Radios" value="2">特长班报名
                </div>
                <div class="form-group entrance-btn-wrap">
                    <input id="sub" type="button" value="登录" class="btn btn-primary">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input id="reg" type="button" value="注册" class="btn btn-warning">
                </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
$(function(){
	//登陆时的验证
	$("#sub").bind("click",function(){
		var regValue=$('input:radio:checked').val();
		window.location.href="login/login.do?reg="+regValue;
	});
	//注册按钮
	$("#reg").bind("click",function(){
		var regValue=$('input:radio:checked').val();
		window.location.href="regeist/notice.do?reg="+regValue;
	});
})
</script>
</html>

