<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>须知管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" charset="utf-8" src="${ctxStatic}/utf8-jsp/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctxStatic}/utf8-jsp/ueditor.all.min.js"> </script>
     <script type="text/javascript" charset="utf-8" src="${ctxStatic}/utf8-jsp/ueditor.parse.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			 var ue = UE.getEditor('editor');
			//$("#name").focus();
			$("#inputForm").validate({
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
	<!-- 华师班注册 -->
		<li><a href="${ctx}/sys/user/grade?status=${grade.reg.specialClass}">成绩列表</a></li>
		<li class="active"><a href="${ctx}/sys/user/gradeForm?id=${grade.id}">成绩修改</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="grade" action="${ctx}/sys/user/saveGrade?status=${grade.reg.specialClass}" method="post" class="form-horizontal">
		<sys:message content="${message}"/>	
		
		<!-- 隐藏域用来存放id -->
		<input type="hidden" name="id" value="${grade.id}">
		
		<div class="control-group">
			<label class="control-label">身份证:</label>
			<div class="controls">
				<form:input path="reg.idcard" disabled="true" htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">姓名:</label>
			<div class="controls">
				<form:input path="reg.logname" disabled="true" htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">课程名:</label>
			<div class="controls">
				<form:input path="name"  htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分数:</label>
			<div class="controls">
				<form:input path="score"  htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		<div class="form-actions">
		    <shiro:hasPermission name="oa:oaNotify:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>