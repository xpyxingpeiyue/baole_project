<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>通知管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/oa/oaNotify/course?status=${status}">科目列表</a></li>
		<c:if test="${!oaNotify.self}"><shiro:hasPermission name="oa:oaNotify:edit"><li><a href="${ctx}/oa/oaNotify/form2?status=${status}">科目添加</a></li></shiro:hasPermission></c:if>
	</ul>
	
	<form:form id="searchForm" modelAttribute="course" action="${ctx}/oa/oaNotify/course?status=${status}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
	</form:form>

	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>科目名称</th>
				<th>时间</th>
				<c:if test="${!oaNotify.self}"><shiro:hasPermission name="oa:oaNotify:edit"><th>操作</th></shiro:hasPermission></c:if>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="course">
			<tr>
				<td>
					${course.name}
				</td>
				<td>
					${course.time }
			
				
				<c:if test="${!requestScope.oaNotify.self}">
				<shiro:hasPermission name="oa:oaNotify:edit">
				<td>
    				<a href="${ctx}/oa/oaNotify/form2?id=${course.id}&&status=${status}">修改</a>
					<a href="${ctx}/oa/oaNotify/delete2?id=${course.id}&&status=${status}" onclick="return confirmx('确认要删除该通知吗？', this.href)">删除</a>
				</td>
				</shiro:hasPermission></c:if>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>