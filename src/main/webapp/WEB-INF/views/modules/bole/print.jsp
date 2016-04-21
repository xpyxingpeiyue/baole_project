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
    <title>个人中心-准考证打印</title>
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
    <!--personalCenter个人中心-->
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/bole/styles/personalCenter.css"/>
    <style>
        html {
            height: 100%;
            background: url(${ctxStatic}/bole/images/personalCenter-bg.jpg) no-repeat scroll center center;
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
        <div class="personalCenter-down3"><img src="${ctxStatic}/bole/images/personalCenter-down3.png" width="21" height="11" alt="选中图标">
        </div>

        <!--print开始-->
        <div class="personalCenterMain personalCenterPrint" >
            <!--table开始-->
            <table class="personalCenterPrint-table" border=1 cellSpacing=0 cellPadding=1 width="100%" style="border-collapse:collapse" bordercolor="#333333">
                <thead>
                <tr>
                    <td colspan="3" align="center">
                    <c:if test="${student==1 }">
        				<span style="font-size: 20px">华师一附中博乐分校（博乐市高级中学）新高一华师班考试</span>
				    </c:if>
				    <c:if test="${student==2}">
        				<span>华师一附中博乐分校（博乐市高级中学）新高一特长班考试</span>
				    </c:if>
                      
                        </br>
                        <span>准考证</span>
                    </td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td colspan="3" align="center">${user.logname }</td>
                </tr>
                <tr>
                    <td width="308" style="padding-left:7px;">身份证号</td>
                    <td width="336" style="padding-left:7px;">${user.idcard }</td>
                    <td width="126" rowspan="6" align="center"><img src="${user.picture }" width="120" height="160" alt="登记照" style="margin-top: 7px;"></td>
                </tr>
                <tr>
                    <td width="308" style="padding-left:7px;">考籍号</td>
                    <td width="336" style="padding-left:7px;">${user.examination}</td>
                </tr>
                <tr>
                    <td width="308" style="padding-left:7px;">性别</td>
                    <td width="336" style="padding-left:7px;">
                    <c:if test="${user.sex==1 }">女</c:if>
                     <c:if test="${user.sex==2 }">男</c:if>
                    </td>
                </tr>
                <tr>
                    <td width="308" style="padding-left:7px;">民族</td>
                    <td width="336" style="padding-left:7px;">${user.national }</td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                    <c:choose>
                    <c:when test="${student==1 }">教学楼
                    </c:when>
                 	 <c:when test="${user.special==1 }">教学楼
                    </c:when>
                    <c:when test="${user.special==2 }">教学楼
                    </c:when>
                    <c:otherwise>
                    		体育运动场
                    </c:otherwise>
                    </c:choose>
                  </td>
                </tr>
                <tr>
                    <td colspan="2" align="center" style="padding:10px;">华师一附中博乐分校</td>
                </tr>
                <tr>
                    <td align="center">科目</td>
                    <td align="center">时间（北京）</td>
                    <td align="center">考场/座位</td>
                </tr>
                <c:forEach items="${course}" var="c">
                <tr>
                
					<td style="padding-left:7px;">${c.name }</td>
                    <td align="center">${c.time }</td>
                    <td align="center">${user.number}-${user.zw}</td>
                    
                </tr>
                </c:forEach>
                
                </tbody>
            </table>
            <!--table结束-->

            <!--message开始-->
            <div class="personalCenterPrint-message">
                              考试内容须知：
                              </br>
            <c:forEach items="${exam}" var="v" varStatus="i">
            ${i.index+1}、${v.content }</br>
            </c:forEach>
                
            </div>
            <!--message结束-->

            <!--btn-->
            <div id="print" style="margin-top: 20px;">
                <button type="button" class="btn btn-primary" onclick="printit()">打印</button>
            </div>


        </div>
        <!--print结束-->
    </div>
    <!--main结束-->

</div>
<!--container结束-->

<div style="height: 136px;"></div>

</body>
<script type="text/javascript">
function printit()  
{  
if (confirm('确定打印吗？'))  
{  
bdhtml=window.document.body.innerHTML;
sprnstr="table";//设置打印开始区域  
eprnstr="print";//设置打印结束区域  
prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); //从开始代码向后取html  

prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr)-9);//从结束代码向前取html 

window.document.body.innerHTML=prnhtml; 

window.print();  
window.document.body.innerHTML=bdhtml;  
}  
}  
</script>
</html>



