<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>照片管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/gen/genTable?status=${status}">照片对照表</a></li>
		<!--
		<shiro:hasPermission name="gen:genTable:edit"><li><a href="${ctx}/gen/genTable/form">业务表添加</a></li></shiro:hasPermission>
		  -->
	</ul>
	<form:form id="searchForm" modelAttribute="regeist" action="${ctx}/gen/genTable?status=${status}" method="post" >
		  
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		
	</form:form>
	<div style="position: relative;left: 15%;">
	<table id="lbContent">
		
		<tr >
		<c:forEach items="${page.list}" var="regeist" varStatus="status">
			<c:if test="${status.index>=0&&status.index<6 }">
			<td   width="100px" style="height:80px ;text-align: center;padding: 35px 16px 35px 15px;" ><span style="display: inline-block;float:left;margin-top: -2px;">${regeist.zw}</span> <a href="${ctx}/gen/genTable/genDetail?idcard=${regeist.idcard}"><img style="width：100px;height:80px;" src="${regeist.picture }" width="80px" height="70px" alt="登记照" style="margin-top: 0px; margin-left: 14px;" onerror='this.src="${ctxStatic}/bole/images/user.png"'></a></br><span style="margin-left: 11px;display: inline-block;float:left;">${regeist.logname}</span></br><span style="float: left; margin-left: 9px;">${regeist.examination}</span><span style="float: left; margin-left: 9px;">${regeist.number}</span></td>
			</c:if>
		</c:forEach>
		</tr>
		<tr >
		<c:forEach items="${page.list}" var="regeist" varStatus="status">
			<c:if test="${status.index>=6&&status.index<12 }">
			<td   width="100px" style="height:80px ;text-align: center;padding: 35px 16px 35px 15px;" ><span style="display: inline-block;float:left;margin-top: -2px;">${regeist.zw}</span> <a href="${ctx}/gen/genTable/genDetail?idcard=${regeist.idcard}"><img style="width：100px;height:80px;" src="${regeist.picture }" width="80px" height="70px" alt="登记照" style="margin-top: 0px; margin-left: 14px;" onerror='this.src="${ctxStatic}/bole/images/user.png"'></a></br><span style="margin-left: 11px;display: inline-block;float:left;">${regeist.logname}</span></br><span style="float: left; margin-left: 9px;">${regeist.examination}</span><span style="float: left; margin-left: 9px;">${regeist.number}</span></td>
			</c:if>
		</c:forEach>
		</tr>
		<tr>
		<c:forEach items="${page.list}" var="regeist" varStatus="status">
			<c:if test="${status.index>=12&&status.index<18}">
			<td   width="100px" style="height:80px ;text-align: center;padding: 35px 16px 35px 15px;" ><span style="display: inline-block;float:left;margin-top: -2px;">${regeist.zw}</span> <a href="${ctx}/gen/genTable/genDetail?idcard=${regeist.idcard}"><img style="width：100px;height:80px;" src="${regeist.picture }" width="80px" height="70px" alt="登记照" style="margin-top: 0px; margin-left: 14px;" onerror='this.src="${ctxStatic}/bole/images/user.png"'></a></br><span style="margin-left: 11px;display: inline-block;float:left;">${regeist.logname}</span></br><span style="float: left; margin-left: 9px;">${regeist.examination}</span><span style="float: left; margin-left: 9px;">${regeist.number}</span></td>
			</c:if>
		</c:forEach>
		</tr>
		<tr>
		<c:forEach items="${page.list}" var="regeist" varStatus="status">
			<c:if test="${status.index>=18&&status.index<24 }">
			<td   width="100px" style="height:80px ;text-align: center;padding: 35px 16px 35px 15px;" ><span style="display: inline-block;float:left;margin-top: -2px;">${regeist.zw}</span> <a href="${ctx}/gen/genTable/genDetail?idcard=${regeist.idcard}"><img style="width：100px;height:80px;" src="${regeist.picture }" width="80px" height="70px" alt="登记照" style="margin-top: 0px; margin-left: 14px;" onerror='this.src="${ctxStatic}/bole/images/user.png"'></a></br><span style="margin-left: 11px;display: inline-block;float:left;">${regeist.logname}</span></br><span style="float: left; margin-left: 9px;">${regeist.examination}</span><span style="float: left; margin-left: 9px;">${regeist.number}</span></td>
			</c:if>
		</c:forEach>
		</tr>
		<tr>
		<c:forEach items="${page.list}" var="regeist" varStatus="status">
			<c:if test="${status.index>=24&&status.index<30 }">
			<td   width="100px" style="height:80px ;text-align: center;padding: 35px 16px 35px 15px;" ><span style="display: inline-block;float:left;margin-top: -2px;">${regeist.zw}</span> <a href="${ctx}/gen/genTable/genDetail?idcard=${regeist.idcard}"><img style="width：100px;height:80px;" src="${regeist.picture }" width="80px" height="70px" alt="登记照" style="margin-top: 0px; margin-left: 14px;" onerror='this.src="${ctxStatic}/bole/images/user.png"'></a></br><span style="margin-left: 11px;display: inline-block;float:left;">${regeist.logname}</span></br><span style="float: left; margin-left: 9px;">${regeist.examination}</span><span style="float: left; margin-left: 9px;">${regeist.number}</span></td>
			</c:if>
		</c:forEach>
		</tr>
	</table>
	</div>
	<div style="margin-left: 182px" class="pagination">${page}</div>
	<div style="margin-left: 182px">
	<input id="btnSubmit" class="btn btn-primary" type="submit" value="打印" onclick="printit()"/>
	</div>
	
		<script type="text/javascript">
			function printit() 
			//去掉所有的超链接
			
			{  
			if (confirm('确定打印吗？'))  
			{  
				
				$("#lbContent a").each(function() {  
	                $(this).removeAttr("href");  
	            });	
				
			bdhtml=window.document.body.innerHTML;
			sprnstr="table";//设置打印开始区域  
			eprnstr="</table>";//设置打印结束区域  
			prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)-1); //从开始代码向后取html  
			
			prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr)+8);//从结束代码向前取html  
			window.document.body.innerHTML=prnhtml; 
			
			window.print();  
			window.document.body.innerHTML=bdhtml; 
			<c:forEach items="${page.list}" var="reg" >
			$("#lbContent a").each(function() {  
                $(this).attr("href","${ctx}/gen/genTable/genDetail?idcard=${reg.idcard}");  
            });
			</c:forEach>
			} 
			}  
			</script>
	
</body>
</html>
