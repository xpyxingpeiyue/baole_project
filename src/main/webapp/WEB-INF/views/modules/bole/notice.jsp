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
    <title>考生须知</title>
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
    <!--examineeNotice须知-->
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/bole/styles/examineeNotice.css"/>
    <style>
        html {
            height: 100%;
            background: url(${ctxStatic}/bole/images/examineeNotice-bg.jpg) no-repeat scroll center center;
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
<div class="examineeNotice-container">
    <div class="examineeNotice-wrap">
        <!--title-->
        <h1>
         华师一附中博乐分校(博乐市高级中学)新高一网上报名须知
        <c:if test="${student=='1'}">
       （华师班考生）
        </c:if>
       
       <c:if test="${student=='2'}">
           （特长班考生）
        </c:if>
           
        </h1>

        <!--p-->
        <div class="examineeNotice-p">
                                内容：</br>
          
            <c:forEach items="${notice}" var="n" varStatus="i">
            
             ${i.index+1}、${n.content}</br></br>
           
            
            </c:forEach>
            
        </div>

        <!--height:276px;-->
        <div style="height:276px;"></div>

        <!--btn-->
        <div class="examineeNotice-tn-wrap">
            <form action="/regeist/regeist.do" method="post">
                <div class="form-group">
                    <input id="notice" type="checkbox">&nbsp;&nbsp;我已经阅读须知并完全理解
                </div>
                <div class="from-group">
                    <input type="submit" value="注册" disabled="true" class="btn btn-gray" style="margin-top: 20px;">
                </div>
            </form>
        </div>

        <div style="height:96px;"></div>
    </div>
</div>
</body>
<script type="text/javascript">
$(function(){
	//判断是够点击考生须知
	
	$("#notice").bind("click",function(){
		var rule=$("input[type='checkbox']").is(':checked');
		if(rule){
			
			$(".btn").attr("disabled",false).css("background-color","#48D1CC");
		}else{
			$(".btn").attr("disabled",true).css("background-color","#7f7f7f");
		}
		
	});
	
})

</script>
</html>


