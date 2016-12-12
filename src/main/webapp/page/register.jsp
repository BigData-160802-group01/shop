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
<div id="Top">
    <div class="help">
        <div class="help2">
            <ul class="h_t_l">
                <li class="top_gray">您好，欢迎光临易买网！</li>
                <c:if test="${!empty LoginUser}">
                    <li class="top_red">${LoginUser.name}</li>
                    <li class="top_red"><a class="button" id="logout" href="/user/logout">注销</a></li>
                </c:if>
                <c:if test="${empty LoginUser}">
                    <li class="top_red"><a href="#" onclick="change()">登录</a></li>
                    <li class="top_red"><a href="/user/register">注册</a></li>
                </c:if>
            </ul>
        </div>
        <div class="help1">
            <a href="/show/shopping" id="shoppingBag" class="shopping">购物车X件</a>
            <a href="##">我的订单</a>
            <a href="##">帮助中心</a>
            <a href="guestbook.html">留言</a>
            <a href="/user/manager">后台管理</a>
        </div>
    </div>
</div>
<div id="header" class="wrap" style="margin-bottom: 20px">
    <div id="logo"><img src="/resources/images/logo.gif" width="160" /></div>
    <div id="search" style="font-size: 0;float: left;margin-left: 50px;margin-top: 20px">

        <input type="text" name="" class="search_1" readonly="readonly" value="  站内">
        <input type="text" name="" class="search_2" placeholder="   请输入商品名称">
        <input type="button" name="" class="search_3" value="搜索">
    </div>
    <div class="top1_m" style="float: left;margin-left: 40px;margin-top: 5px">
        <img src="/resources/images/top_ser.gif" alt="" width="300px" height="50">
    </div>
</div>
<div id="childNav">
    <div class="wrap">
        <ul class="clearfix">
            <c:forEach items="${protypelist}" var="pty">
                <c:forEach items="${pty.type}" var="ty">
                    <li><a href="/show/showtypePro/${ty.typeName}">${ty.typeName}</a></li>
                </c:forEach>
            </c:forEach>
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
