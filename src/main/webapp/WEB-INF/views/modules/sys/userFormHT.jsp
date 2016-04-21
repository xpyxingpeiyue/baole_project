<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				rules: {
					loginname: ""
				},
				messages: {
					loginname: {remote: "用户登录名已存在"},
					confirmNewPassword: {equalTo: "输入与上面相同的密码"}
				},
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/user/hs?status=${status}">学生信息列表</a></li>
		 
		
		<li class="active"><a href="${ctx}/sys/user/updateHS?id=${regeist.id}">用户<shiro:hasPermission name="sys:user:edit">${not empty regeist.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:user:edit">查看</shiro:lacksPermission></a></li>

		 <!--
		 <li class="active"><a href="${ctx}/sys/user/hsDetail?id=${user.id}">用户查看</a></li>
		  -->
	</ul><br/>
	<form:form id="inputForm" modelAttribute="regeist" action="${ctx}/sys/user/saveAll?status=${status}" method="POST" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">头像:</label>
			<div class="controls">
			<img id="preview" src="${regeist.picture}" width="120" height="160" alt="登记照" >
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">身份证号:</label>
			<div class="controls">
				<form:input path="idcard"  disabled="true"  htmlEscape="false" maxlength="200" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">姓名:</label>
			<div class="controls">
				<form:input path="logname"  htmlEscape="false" maxlength="200" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">密码:</label>
			<div class="controls">
				<form:input path="password"  htmlEscape="false" maxlength="200" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">性别:</label>
			<div class="controls">
				<c:if test="${regeist.sex==2}">
				<input type="text" disabled="true" value="男">
				</c:if>
				<c:if test="${regeist.sex==1}">
				<input type="text" disabled="true" value="男">
				</c:if>
				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地址:</label>
			<div class="controls">
				<form:input path="address"  htmlEscape="false" maxlength="200" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">民族:</label>
			<div class="controls">
				<form:input path="national"  htmlEscape="false" maxlength="200" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">父亲姓名:</label>
			<div class="controls">
				<form:input path="fatherName"  htmlEscape="false" maxlength="200" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">母亲姓名:</label>
			<div class="controls">
				<form:input path="motherName"  htmlEscape="false" maxlength="200" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">父亲工作:</label>
			<div class="controls">
				<form:input path="fatherWork"  htmlEscape="false" maxlength="200" class="input-xlarge required"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">母亲工作:</label>
			<div class="controls">
				<form:input path="motherWork"  htmlEscape="false" maxlength="200" class="input-xlarge required"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">父亲电话:</label>
			<div class="controls">
				<form:input path="fatherPhone"  htmlEscape="false" maxlength="200" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">母亲电话:</label>
			<div class="controls">
				<form:input path="motherPhone"  htmlEscape="false" maxlength="200" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">毕业院校:</label>
			<div class="controls">
				<form:input path="graduate"  htmlEscape="false" maxlength="200" class="input-xlarge required"/>
			</div>
		</div>
		<!--  -->
		<div class="control-group">
			<label class="control-label">家庭住址:</label>
			<div class="controls">
				<form:input path="home" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
			</div>
		</div>
		
		<div class="form-actions">
		  
			<shiro:hasPermission name="sys:user:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
		
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>