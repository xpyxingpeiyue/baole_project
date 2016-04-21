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
						$("#searchForm").attr("action","${ctx}/sys/user/export?status="+${status});
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
			$("#searchForm").attr("action","${ctx}/sys/user/hs?status="+${status});
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
	<script type="text/javascript">
	function deleteAll(status){
		var r;
		if(status==1){
		  r=window.confirm('你确定要删除所有华师班学生信息吗？');
		}else{
		r=window.confirm('你确定要删除所有特长班学生信息吗？');
		}
		if(r){
			window.location.href="${ctx}/sys/user/deleteAll?status="+status;
		}
		
		
	}
	
	</script>
</head>
<body>
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/sys/user/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/sys/user/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/user/hs?status=${status}">学生信息列表</a></li>
		<!-- 
		<shiro:hasPermission name="sys:user:edit"><li><a href="${ctx}/sys/user/form">用户添加</a></li></shiro:hasPermission>
		 -->
	</ul>
	<form:form id="searchForm" modelAttribute="regeist" action="${ctx}/sys/user/hs?status=${status}" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<!-- 省份证号来查找 -->
			<li><label>身份证：</label><form:input path="idcard" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			
			<li><label>姓&nbsp;&nbsp;&nbsp;名：</label><form:input path="logname" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			
			<!--  -->
			<c:if test="${status==1}">
			<li><label>考&nbsp;&nbsp;&nbsp;场：</label><form:input path="number" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			</c:if>
			<c:if test="${status==2}">
			<li><label>特长类别：</label>
			<form:select path="specialClass" htmlEscape="false" class="input-medium">
                    <option value="">请选择类型</option>
                     <option value="1" <c:if test="${regeist.specialClass==1}">selected</c:if>>音乐特长</option>
                    <option value="2"<c:if test="${regeist.specialClass==2}">selected</c:if>>美术特长</option>
                    <option value="3"<c:if test="${regeist.specialClass==3}">selected</c:if>>篮球特长</option>
                    <option value="4"<c:if test="${regeist.specialClass==4}">selected</c:if>>田径特长</option>
                    <option value="5"<c:if test="${regeist.specialClass==5}">selected</c:if>>主持人特长</option>
                </form:select>
                </li>
			</c:if>
			<li class="btns" style="margin-left: 30px">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
				<!-- 
				<input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
				 -->
				  <input id="btnDelete" class="btn btn-primary" type="button" value="删除" onclick="deleteAll(${status})"/>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>考场号</th><th>座号</th><th>考籍号</th><th>身份证</th><th>姓名</th><th >地址</th><th >民族</th> <shiro:hasPermission name="sys:user:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="regeist">
			<tr>
			     <td>${regeist.number}</td>
			     <td>${regeist.zw}</td>
				<td>${regeist.examination}</td>
				<td>${regeist.idcard}</td>
				<td><a href="${ctx}/sys/user/hsDetail?id=${regeist.id}">${regeist.logname}</a></td>
				<td>${regeist.address}</td>
				<td>${regeist.national}</td>
				
				<shiro:hasPermission name="sys:user:edit"><td>
    				<a href="${ctx}/sys/user/updateHS?id=${regeist.id}&&status=${status}">修改</a> 
					<a href="${ctx}/sys/user/deleteHS?id=${regeist.id}&&status=${status}" onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>