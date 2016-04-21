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
</head>

<body>
<!--header-->
<div class="register-header">
    <div class="register-container">
        <img src="${ctxStatic}/bole/images/register-logo.png" class="register-logo" width="622" height="23"
             alt="华师一附中博乐分校(博乐市高级中学)新高一网上报名">
        <a href="javascript:history.go(-1)" class="register-header-a">
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
        <form class="form">
            <div class="register-form-title">学生基本信息</div>

            <div style="clear: both;"></div>
            <!--form-top开始-->
            <div class="fl register-form-top">
                <div class="fl register-form-left">
                    <div class="form-group">
                        <label class="register-form-labelLeft">姓名：</label>
                        <input type="text" class="" placeholder="请输入姓名">
                        <div style="color:#f00;margin-left: 250px;">表单验证</div>
                    </div>

                    <div class="form-group">
                        <label class="register-form-labelLeft">身份证号码：</label>
                        <input type="text" class="" placeholder="请输入姓名">
                    </div>
                    <div class="form-group">
                        <label class="register-form-labelLeft">密码：</label>
                        <input type="password" class="" placeholder="请输入姓名">
                    </div>
                    <div class="form-group">
                        <label class="register-form-labelLeft">性别：</label>
                        <input type="radio" name="Xb" value="nv" checked>&nbsp;女&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="Xb" value="nan">&nbsp;男
                    </div>
                    <div class="form-group">
                        <label class="register-form-labelLeft">族别：</label>
                        <input type="text" class="" placeholder="请输入姓名">
                    </div>
                </div>
                <div class="fl register-form-right">
                    <label class="register-form-upload">附件上传：</label>
                    <a href="javascript:void(0);">
                        <img src="${ctxStatic}/bole/images/register-photo.jpg" width="120" height="160" alt="登记照">
                    </a>
                </div>
            </div>
            <!--form-top结束-->
            <div style="clear: both;"></div>

            <!--户籍所在地 -->
            <div class="form-group">
                <label class="register-form-labelLeft">户籍所在地 ：</label>
                <input type="text" class="register-form-textW100" placeholder="请输入户籍所在地">
            </div>

            <!--父亲联系电话，母亲联系电话 -->
            <div class="register-form-inline">
                <!--父亲联系电话-->
                <div class="fl form-group">
                    父亲联系电话:
                    <input type="text" class="register-form-textW40" placeholder="请输入父亲联系电话">
                </div>
                <!--母亲联系电话-->
                <div class="fr form-group">
                    母亲联系电话：
                    <input type="text" class="register-form-textW40" placeholder="请输入母亲联系电话">
                </div>
            </div>

            <!--父亲姓名，父亲工作单位 -->
            <div class="register-form-inline">
                <!--父亲姓名-->
                <div class="fl form-group">
                    父亲姓名：
                    <input type="text" class="register-form-textW40" placeholder="请输入父亲姓名">

                    <div style="color:#f00; margin-left: 90px;text-align: left;">表单验证</div>

                </div>
                <!--父亲工作单位-->
                <div class="fr form-group">
                    父亲工作单位：
                    <input type="text" class="register-form-textW40" placeholder="请输入父亲工作单位">
                </div>
            </div>

            <!--母亲姓名，母亲工作单位 -->
            <div class="register-form-inline">
                <!--母亲姓名-->
                <div class="fl form-group">
                    母亲姓名：
                    <input type="text" class="register-form-textW40" placeholder="请输入母亲姓名">
                </div>

                <!--父亲工作单位-->
                <div class="fr form-group">
                    母亲工作单位：
                    <input type="text" class="register-form-textW40" placeholder="请输入母亲工作单位">
                </div>
            </div>

            <div style="clear: both;"></div>

            <!--毕业学校 -->
            <div class="form-group">
                <label class="register-form-labelLeft">毕业学校：</label>
                <input type="text" class="register-form-textW100" placeholder="请输入毕业学校">
            </div>

            <!--家庭住址 -->
            <div class="form-group">
                <label class="register-form-labelLeft">家庭住址：</label>
                <input type="text" class="register-form-textW100" placeholder="请输入家庭住址">
                <div style="color:#f00;margin-left: 249px;">表单验证</div>
            </div>


            <!--特长类别 -->
            <c:if test="${student==2}">
            <div class="form-group">
                <label class="register-form-labelLeft">特长类别：</label>
                <select class="register-form-textW100">
                    <option>请选择类型</option>
                    <option>音乐特长</option>
                    <option>美术特长</option>
                    <option>篮球特长</option>
                    <option>田径特长</option>
                    <option>主持人特长</option>
                </select>
            </div>
			</c:if>
            <!--btn-->
            <div class="form-group" style="text-align: center;margin-top: 100px;padding-bottom: 100px;">
                <input type="submit" class="btn btn-primary" value="提交" style="margin-right: 50px;">
                <input type="reset" class="btn btn-danger" value="取消">
            </div>
        </form>
        <!--form结束-->

    </div>
    <!--form wrap结束-->
</div>
</body>
</html>
