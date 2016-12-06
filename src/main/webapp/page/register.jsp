<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>易买网 - 新用户注册</title>
    <link type="text/css" rel="stylesheet" href="/resources/css/style.css"/>
    <script type="text/javascript" src="/resources/scripts/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="/resources/scripts/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="/resources/scripts/function.js"></script>
</head>
<body>
<div id="header" class="wrap">
    <div id="logo"><img src="/resources/images/logo.gif"/></div>
    <div class="help">
        <a href="shopping.jsp" class="shopping">购物车X件</a>
        <c:if test="${!empty LoginUser}">
            ${LoginUser.name}
            <a class="button" id="logout" href="/user/logout">注销</a>
        </c:if>
        <c:if test="${empty LoginUser}">
            <a href="/page/login.jsp">登录</a>
            <a href="/user/register">注册</a>
        </c:if>
        <a href="guestbook.jsp">留言</a>
        <a href="manage/index.jsp">后台管理</a>
    </div>
    <div class="navbar">
        <ul class="clearfix">
            <li class="current"><a href="#">首页</a></li>
            <li><a href="#">图书</a></li>
            <li><a href="#">百货</a></li>
            <li><a href="#">品牌</a></li>
            <li><a href="#">促销</a></li>
        </ul>
    </div>
</div>
<div id="childNav">
    <div class="wrap">
        <ul class="clearfix">
            <li class="first"><a href="#">音乐</a></li>
            <li><a href="#">影视</a></li>
            <li><a href="#">少儿</a></li>
            <li><a href="#">动漫</a></li>
            <li><a href="#">小说</a></li>
            <li><a href="#">外语</a></li>
            <li><a href="#">数码相机</a></li>
            <li><a href="#">笔记本</a></li>
            <li><a href="#">羽绒服</a></li>
            <li><a href="#">秋冬靴</a></li>
            <li><a href="#">运动鞋</a></li>
            <li><a href="#">美容护肤</a></li>
            <li><a href="#">家纺用品</a></li>
            <li><a href="#">婴幼奶粉</a></li>
            <li><a href="#">饰品</a></li>
            <li class="last"><a href="#">Investor Relations</a></li>
        </ul>
    </div>
</div>
<div id="register" class="wrap">
    <div class="shadow">
        <em class="corner lb"></em>
        <em class="corner rt"></em>
        <div class="box">
            <h1>欢迎注册易买网</h1>
            <ul class="steps clearfix">
                <li class="current"><em></em>填写注册信息</li>
                <li class="last"><em></em>注册成功</li>
            </ul>
            <form id="regForm" method="post" action="/user/register">
                <table>
                    <tr>
                        <td class="field">用户名(*)：</td>
                        <td>
                            <input class="text" type="text" name="name" id="userId" maxlength="10"/>
                            <input type="hidden" id="valiad">
                            <span></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="field">真实姓名(*)：</td>
                        <td><input class="text" type="text" name="nickname"/><span></span></td>
                    </tr>
                    <tr>
                        <td class="field">登录密码(*)：</td>
                        <td><input class="text" type="password" id="password" name="password"/><span></span></td>
                    </tr>
                    <tr>
                        <td class="field">确认密码(*)：</td>
                        <td><input class="text" type="password" name="confirmPassword"/><span></span></td>
                    </tr>
                    <tr>
                        <td class="field">性别(*)：</td>
                        <td>
                            <input class="radio" type="radio" name="sex" value="男" checked="checked">
                            男性</input>
                            <input class="radio" type="radio" name="sex" value="女"
                            >女性</input>
                            <span></span></td>
                    </tr>
                    <tr>
                        <td class="field">出生日期：</td>
                        <td><input id="birthday" class="text" type="text" name="birthday"/><span></span></td>
                    </tr>
                    <tr>
                        <td class="field">身份证：</td>
                        <td><input class="text" type="text" name="identity"/><span></span></td>
                    </tr>
                    <tr>
                        <td class="field">电子邮件：</td>
                        <td><input class="text" type="text" name="email"/><span></span></td>
                    </tr>
                    <tr>
                        <td class="field">手机(*)：</td>
                        <td><input class="text" type="text" name="phone"/><span></span></td>
                    </tr>
                    <tr>
                        <td class="field">收货地址(*)：</td>
                        <td><input class="text" type="text" name="address"/><span></span></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><label class="ui-green"><input type="submit" name="submit" value="提交注册"/></label></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div class="clear"></div>
</div>
<div id="footer">
    Copyright &copy; 2010 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
