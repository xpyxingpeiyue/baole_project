<%@ page contentType="text/html;charset=UTF-8" %>
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
    <title>注册</title>
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
  
   <script src="${ctxStatic}/bole/js/jquery.validate.js" type="text/javascript"></script>
    <script src="${ctxStatic}/bole/js/additional-methods.js" type="text/javascript"></script>
    <!--register注册-->
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/bole/styles/register.css"/>
    <style>
        html {
            height: 100%;
            background: url(${ctxStatic}/bole/images/register-bg.jpg) no-repeat scroll center center;
            background-attachment: fixed;
            background-size: cover;
        }
    </style>
    
			    <style type="text/css">  
			    label.error {  
			    color: red;  
			    font-size: 12px;
			    display: block;
    			text-align: right;
				}  
				</style>
	<style>
	#validate_hidden { height:0; width:0.1; border:0;padding: 0 }
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
    </div>
</div>

<div class="register-container">
    <!--form wrap开始-->
    <div class="register-form">
        <h2 class="tc">
            <img src="${ctxStatic}/bole/images/register-title.png" width="137" height="31" alt="登录">
        </h2>

        <div style="clear: both;"></div>

        <!--form开始-->
        <form class="form" id="commentForm" action="/regeist/regcenter.do" method="POST" enctype="multipart/form-data">
            <div class="register-form-title">学生基本信息</div>

            <div style="clear: both;"></div>
            <!--form-top开始-->
            <div class="fl register-form-top">
                <div class="fl register-form-left">
                    <div class="form-group">
                        <label class="register-form-labelLeft">姓名：</label>
                        <input type="text" id="logname" name="logname"  class="" placeholder="请输入姓名" ">
                      
                    </div>

                    <div class="form-group">
                        <label class="register-form-labelLeft">身份证号码：</label>
                        <input type="text" name="idcard" id="idCard" class="" placeholder="请输入身份证号码">
                        <label id="card" ></label>
                       
                    </div>
                    <div class="form-group">
                        <label class="register-form-labelLeft">密码：</label>
                        <input type="password" name="password" class="" placeholder="请输入密码">
                    </div>
                    <div class="form-group">
                        <label class="register-form-labelLeft">性别：</label>
                        <input type="radio" name="sex" value="1" checked>&nbsp;女&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="sex" value="2">&nbsp;男
                    </div>
                    <div class="form-group">
                        <label class="register-form-labelLeft">族别：</label>
                        <input type="text" name="national" class="" placeholder="请输入族别">
                    </div>
                </div>
                <div class="fl register-form-right">
                    <label class="register-form-upload">图像上传：</label>
                    <a href="javascript:void(0);">
                    <input  type="file" name="pic"  id="doc" style="display: none" onchange="javascript:setImagePreview();">
                      
                        <img id="preview" src="${ctxStatic}/bole/images/register-photo.jpg" width="120" height="160" alt="登记照" >
                    </a>
                    <div style="margin-top: 119px;width: 100px;height: 10px;float: right;margin-left: -94px">
                    <input type="text" id="validate_hidden"  name="hid" />
                    
                    </div>
                    
                    
                </div>
            </div>
            <!--form-top结束-->
            <div style="clear: both;"></div>

            <!--户籍所在地 -->
            <div class="form-group" style="padding-right: 196px;">
                <label class="register-form-labelLeft">户籍所在地 ：</label>
                <input type="text" name="address" class="register-form-textW100" placeholder="请输入户籍所在地">
            </div>

            <!--父亲联系电话，母亲联系电话 -->
            <div class="register-form-inline">
                <!--父亲联系电话-->
                <div class="fl form-group">
                    父亲姓名:
                    <input type="text" name="fatherName" class="register-form-textW40" placeholder="请输入 父亲姓名">
                    <div id="father"  style="color:#f00; margin-left: 90px;text-align: left;display: none"></div>
                </div>
                <!--母亲联系电话-->
                <div class="fr form-group">
                    母亲姓名：
                    <input type="text" name="motherName" class="register-form-textW40" placeholder="请输入母亲姓名">
                </div>
            </div>

            <!--父亲姓名，父亲工作单位 -->
            <div class="register-form-inline">
                <!--父亲姓名-->
                <div class="fl form-group">
                    父亲工作单位：
                    <input type="text" class="register-form-textW40" name="fatherWork" placeholder="请输入父亲工作单位">

                </div>
                <!--父亲工作单位-->
                <div class="fr form-group">
                    母亲工作单位：
                    <input type="text" name="motherWork" class="register-form-textW40" placeholder="请输入母亲工作单位">
                </div>
            </div>

            <!--母亲姓名，母亲工作单位 -->
            <div class="register-form-inline">
                <!--母亲姓名-->
                <div class="fl form-group">
                    父亲联系电话：
                    <input type="text" name="fatherPhone" class="register-form-textW40" placeholder="请输入 父亲联系电话">
                </div>

                <!--父亲工作单位-->
                <div class="fr form-group">
                    母亲联系电话：
                    <input type="text" name="motherPhone" class="register-form-textW40" placeholder="请输入母亲联系电话">
                </div>
            </div>

            <div style="clear: both;"></div>

            <!--毕业学校 -->
            <div class="form-group" style="padding-right: 196px;">
                <label class="register-form-labelLeft">毕业学校：</label>
                <input type="text" name="graduate" class="register-form-textW100" placeholder="请输入毕业学校">
            </div>

            <!--家庭住址 -->
            <div class="form-group" style="padding-right: 196px;">
                <label class="register-form-labelLeft">家庭住址：</label>
                <input type="text" name="home" class="register-form-textW100" placeholder="请输入家庭住址">
                <!--  <div style="color:#f00;margin-left: 249px;">表单验证</div>-->
            </div>


            <!--特长类别 -->
            <c:if test="${student==2}">
            <div class="form-group" style="padding-right: 196px;">
                <label class="register-form-labelLeft">特长类别：</label>
                <select name="specialClass" class="register-form-textW100">
                    <option value="">请选择类型</option>
                    <option value="1">音乐特长</option>
                    <option value="2">美术特长</option>
                    <option value="3">篮球特长</option>
                    <option value="4">田径特长</option>
                    <option value="5">主持人特长</option>
                </select>
            </div>
			</c:if>
			 <!-- 验证file的假的隐藏域 -->
			
            <!--btn-->
            <div class="form-group" style="text-align: center;margin-top: 100px;padding-bottom: 100px;">
                <input type="submit" class="btn btn-primary" value="提交" style="margin-right: 50px;">
                <input type="button" class="btn btn-danger" value="返回" onclick="history.go(-1)">
            </div>
        </form>
        <!--form结束-->

    </div>
    <!--form wrap结束-->
</div>
</body>
<script>
$(function(){
	$("#preview").bind("click",function(){
		return  $("#doc").click();
	})
})
function setImagePreview() {
        var docObj=document.getElementById("doc");
        var filetype=docObj.value;
        if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(filetype))
        {
          alert("图片类型必须是.gif,jpeg,jpg,png中的一种")
          return false;
        }
        $("#validate_hidden").val("成功");
        var imgObjPreview=document.getElementById("preview");
                if(docObj.files &&    docObj.files[0]){
                	
      				imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
                }else{
                        //IE下
                         docObj.select();
                        var imgSrc = document.selection.createRange().text;
                        var localImagId = document.getElementById("localImag");
                        //必须设置初始大小
                       // localImagId.style.width = "300px";
                       // localImagId.style.height = "120px";
                        //图片异常的捕捉，防止用户修改后缀来伪造图片
						try{
                                localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                                localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
                        }catch(e){
                                alert("您上传的图片格式不正确，请重744新选择!");
                                return false;
                        }
                        imgObjPreview.style.display = 'none';
                        document.selection.empty();
                }
                return true;
        }
</script>
<script type="text/javascript">
//自定义身份证验证格式
$.validator.addMethod("pid", function(value,element) {  
	var flag=false;
	var reg1=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
	var reg2=/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/;
	var date=new Date();
	if(reg1.test(value)||reg2.test(value)){
		var subStr=value.substring(6,10);
		if(subStr<=date.getFullYear()){
			 $.ajax({
			        type: "POST",
			        async:false,
			        url:'/regeist/regcard.do',
			        data:{idcard:value},
			        dataType:'JSON',
			        success: function(data) {5
			        	debugger;
			        	if(data==1){
			        		flag=true;
			        	}
			        },
			        error: function(data) {
			        }
				})
		}
		
    }
	return flag;
	
},"身份证格式不正确或用户已存在 ");

$.validator.addMethod("zb", function(value,element,params) {  
    if(params.test(value)){
    	//填写正确
    	return true;
    }
    
}, "族别必须为汉字");

$.validator.addMethod("add", function(value,element,params) {  
	
    if(params.test(value)){
    	//填写正确
    	return true;
    }
    
}, "汉字数字字母");

$.validator.addMethod("phone", function(value,element) {  
	var mobileRule=/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
	var telphoneRule=/^\d{3,4}-?\d{7,9}$/;
    if(mobileRule.test(value)||telphoneRule.test(value)){
    	//填写正确
    	$("#father").html("");
    	return true;
    }
    
},"手机号码格式不正确");

 $.validator.addMethod("cname", function(value,element,params) {  
		
	    if(params.test(value)){
	    	//填写正确
	    	return true;
	    }
	    
	}, "姓名为2-4位");
 $.validator.addMethod("ad", function(value,element,params) {  
		
	    if(params.test(value)){
	    	//填写正确
	    	return true;
	    }
	    
	}, "必须为汉字");
 
 $.validator.addMethod("hden", function(value,element,params) {  
	
	    if(params.test(value)){
	    	//填写正确
	    	return true;
	    }
	    
	}, "未上传照片");

$().ready(function() {  
    
$("#commentForm").validate({  
            rules: {  
            	logname: {  
                        required:true,  
                        minlength:2  
                },  
                idcard: {  
                        pid:true,  
                       
                },  
                password: {  
                    required: true,  
                    minlength: 6  
                }, 
                national:{
                	zb:/^[\u4E00-\u9FA5]+$/,
                },
                home:{
                	add:/^[\u4E00-\u9FA5A-Za-z0-9]+$/,
                },
                fatherPhone:{
                	phone:/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/ || /^(\(\d{3,4}-)|\d{3.4}-)?\d{7,8}$/,
                },
                motherPhone:{
                	phone:/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/,
                },
                fatherName:{
                	required:true,  
                    minlength:2  
                },
                motherName:{
                	required:true,  
                    minlength:2  
                },
                fatherWork:{
                	add:/^[\u4E00-\u9FA5A-Za-z0-9]+$/,
                },
                hid:{
                	hden:/^[\u4E00-\u9FA5]{2,4}$/,
                },
                
                motherWork:{
                	add:/^[\u4E00-\u9FA5A-Za-z0-9]+$/,
                },
                graduate:{
                	ad:/^[\u4E00-\u9FA5]+$/,
                },
                address:{
                	ad:/^[\u4E00-\u9FA5]+$/,
                },
                specialClass:{
            	   required:true
               }
                
                
            },  
            messages: {  
            	logname: {  
                        required:"请输入用户名",  
                        minlength:"至少2个字符"
                },  
               
                password: {  
                    required: "请输入密码",  
                    minlength: "密码不能少于6个字符"  
                },  
                fatherName:{
                	required:"请输入父亲姓名",  
                    minlength:"至少1个字符"
                },
                motherName:{
                	required:"请输入母亲姓名",  
                    minlength:"至少1个字符" 
                },
                specialClass:{
             	   required:"请至少选择一个"
                }
            }  
    });  
}); 


</script>


</html>
