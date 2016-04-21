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
		<li><a href="${ctx}/cms/comment/exam?status=1">须知列表</a></li>
		<li class="active"><a href="${ctx}/cms/comment/formExam1?id=${notice.id}">注册须知<shiro:hasPermission name="oa:oaNotify:edit">${not empty notice.id ? '修改' : '添加'}</shiro:hasPermission><shiro:lacksPermission name="oa:oaNotify:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="notice" action="${ctx}/cms/comment/saveExam1" method="post" class="form-horizontal">
		<sys:message content="${message}"/>	
		
		<!-- 隐藏域用来存放id -->
		<input type="hidden" name="id" value="${notice.id}">
		<!-- 用来存放类别 -->
		<input type="hidden" name="classid" value="${notice.classid }"/>
		
		<div class="control-group">
			<label class="control-label">内容：</label>
			<div class="controls">
			<%-- 	<form:textarea path="content" htmlEscape="false" rows="6" maxlength="2000" class="input-xxlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span> --%>
				 <textarea id="editor" type="text/plain"  style="width:750px;height:300px;" name="content">${notice.content}</textarea>
			</div>
		</div>
		<div class="form-actions">
		    <shiro:hasPermission name="oa:oaNotify:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>