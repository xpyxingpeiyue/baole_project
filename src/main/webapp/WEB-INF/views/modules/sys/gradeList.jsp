<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/sys/user/status?status="+${status});
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/sys/user/grade?status="+${status});
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
	<script type="text/javascript">
	function deleteAll(status){
		var r;
		if(status==0){
		  r=window.confirm('你确定要删除所有华师班学生成绩吗？');
		}
		if(status==1){
		r=window.confirm('你确定要删除所有音乐班学生成绩吗？');
		}
		if(status==2){
			r=window.confirm('你确定要删除所有美术班学生成绩吗？');
			}
		if(status==3){
			r=window.confirm('你确定要删除所有篮球班学生成绩吗？');
			}
		if(status==4){
			r=window.confirm('你确定要删除所有田径班学生成绩吗？');
			}
		if(status==5){
			r=window.confirm('你确定要删除所有主持人学生成绩吗？');
			}
		if(r){
			window.location.href="${ctx}/sys/user/deleteAllGrade?status="+status;
		}
		
		
	}
	
	</script>
	
</head>
<body>
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/sys/user/import?status=${status}" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/sys/user/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/user/grade?status=${status}">学生信息列表</a></li>
		<!-- 
		<shiro:hasPermission name="sys:user:edit"><li><a href="${ctx}/sys/user/form">用户添加</a></li></shiro:hasPermission>
		 -->
	</ul>
	<form:form id="searchForm" modelAttribute="regeist" action="${ctx}/sys/user/grade?status=${status}" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<!-- 省份证号来查找 -->
			<li><label>身份证：</label><form:input path="idcard" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			
			<li><label>姓&nbsp;&nbsp;&nbsp;名：</label><form:input path="logname" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			
			
			<li class="btns" style="margin-left: 30px">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<!-- 
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
				  -->
				<input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
				<input id="btnDelete" class="btn btn-primary" type="button" value="删除" onclick="deleteAll(${status})"/>
				 
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>身份证号</th><th>姓名</th><th>考籍号</th><th>性别</th><th>族别</th><th>毕业院校</th>
		
		<c:forEach items="${courses}" var="c">
		<th >${c.name}</th>
		</c:forEach>
		<th>总分</th>
		<shiro:hasPermission name="sys:user:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="regeist">
			<tr>
				<td>${regeist.idcard}</td>
				<td><a href="${ctx}/sys/user/userDetail?idcard=${regeist.idcard}">${regeist.logname}</a></td>
				<td>${regeist.examination}</td>
				<td><c:if test="${regeist.sex==1}">
				女
				</c:if>
				<c:if test="${regeist.sex==2}">
				男
				</c:if>
				</td>
				<td>${regeist.national}</td>
				<td>${regeist.graduate}</td>
				<c:set var="sum" value="0" />
				<c:forEach items="${regeist.grades}" var="rg" varStatus="i">
				<td>${rg.score}</td>
				
				<c:set var="sum" value="${sum+rg.score}" />
                <c:if test="${i.last}">
                <td>${sum }</td>
                </c:if>
				</c:forEach>
				<shiro:hasPermission name="sys:user:edit"><td>
    				<!-- <a href="${ctx}/sys/user/formGrade?id=${grade.id}&&idcard=${grade.reg.idcard}&&status=${status}">修改</a>  --> 
					<a href="${ctx}/sys/user/deleteGrade?idcard=${regeist.idcard}&&status=${status}" onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>