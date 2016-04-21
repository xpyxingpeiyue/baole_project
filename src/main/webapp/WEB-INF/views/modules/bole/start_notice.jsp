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
    <title>个人中心-开始须知</title>
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

    <!--personalCenter个人中心-->
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/bole/styles/personalCenter.css"/>
    <style>
        html {
            height: 100%;
            background: url(images/personalCenter-bg.jpg) no-repeat scroll center center;
            background-attachment: fixed;
            background-size: cover;
        }
        
          p{
        display: inline;
        }
    </style>
    <style type="text/css">
   tr,td {
	border:1px solid #ccc;
	padding:5px 10px;
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
        <span class="fr" style="padding-right: 30px;">您好，${user.logname }</span>
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
        <div class="personalCenter-down2"><img src="${ctxStatic}/bole/images/personalCenter-down2.png" width="21" height="11" alt="选中图标">
        </div>

        <!--开始须知BeganNotice开始-->
        <div class="personalCenterMain personalCenterBeganNotice">
        注册须知：
        </br>
        <c:forEach items="${notice }" var="n" varStatus="i">
        	
            ${i.index+1}、${n.content }</br>
            
        </c:forEach>
        </br>
         考试须知：
         </br>
        <c:forEach items="${notice1 }" var="no" varStatus="t">
           ${t.index+1}、${no.content }</br></br>
        </c:forEach>   
        </div>
        <!--开始须知BeganNotice结束-->
    </div>
    <!--main结束-->

</div>
<!--container结束-->

<div style="height: 136px;"></div>

</body>
</html>



