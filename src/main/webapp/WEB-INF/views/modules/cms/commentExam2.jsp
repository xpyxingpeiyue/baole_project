<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考试须知</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
   tr,td {
	border:1px solid #ccc;
	padding:5px 10px;
	}
    </style>
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
	<!-- 华师班考试须知 -->
		<li class="active"><a href="${ctx}/cms/comment/exam?status=2">考试须知列表</a></li>
		<c:if test="${!oaNotify.self}"><shiro:hasPermission name="oa:oaNotify:edit"><li><a href="${ctx}/cms/comment/formExam2">考试须知添加</a></li></shiro:hasPermission></c:if>
	</ul>
	
	<form:form id="searchForm" modelAttribute="notice" action="${ctx}/cms/comment/saveExam2" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
	</form:form>

	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>考试须知id</th>
				<th>考试须知内容</th>
				<c:if test="${!oaNotify.self}"><shiro:hasPermission name="oa:oaNotify:edit"><th>操作</th></shiro:hasPermission></c:if>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="notice" varStatus="p">
			<tr>
				<td>
					${p.index+1}
				</td>
				<td>
					${notice.content}
			
				
				<c:if test="${!requestScope.oaNotify.self}">
				<shiro:hasPermission name="oa:oaNotify:edit">
				<td>
    				<a href="${ctx}/cms/comment/formExam2?id=${notice.id}">修改</a>
					<a href="${ctx}/cms/comment/deleteExam2?id=${notice.id}" onclick="return confirmx('确认要删除该通知吗？', this.href)">删除</a>
				</td>
				</shiro:hasPermission></c:if>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>