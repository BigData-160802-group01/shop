<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理 - 易买网</title>
<link type="text/css" rel="stylesheet" href="/resources/css/style.css" />
<script type="text/javascript" src="/resources/scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="/resources/scripts/function.js"></script>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="/resources/images/logo.gif" width="120"/></div>
	<div class="help"><a href="/show/index">返回前台页面</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li class="current"><a href="/user/manager">首页</a></li>
			<li><a href="/manager/user/list">用户</a></li>
			<li><a href="/manager/productClass/list">商品</a></li>
			<li><a href="/manager/order/list">订单</a></li>
			<li><a href="/manager/guestbook/list">留言</a></li>
			<li><a href="/manager/news/list">新闻</a></li>
		</ul>
	</div>
</div>
<div id="childNav">
	<div class="welcome wrap">
		管理员 [${LoginUser.nickname}] 您好，今天是<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>，欢迎回到管理后台。
	</div>
</div>
<div id="position" class="wrap">
	您现在的位置：<a href="/show/index">易买网</a> &gt; 管理后台
</div>
<div id="main" class="wrap">
	<div id="menu-mng" class="lefter">
		<div class="box">
			<dl>
				<dt>用户管理</dt>
				<dd><a href="/manager/user/list">用户管理</a></dd>
				<dt>商品信息</dt>
				<dd><em><a href="/manager/productClass/addtype">新增</a></em><a href="/manager/productClass/list">分类管理</a></dd>
				<dd><em><a href="/manager/product/addproduct">新增</a></em><a href="/manager/product/list">商品管理</a></dd>
				<dt>订单管理</dt>
				<dd><a href="/manager/order/list">订单管理</a></dd>
				<dt>留言管理</dt>
				<dd><a href="/manager/guestbook/list">留言管理</a></dd>
				<dt>新闻管理</dt>
				<dd><em><a href="/manager/news/addNews">新增</a></em><a href="/manager/news/list">新闻管理</a></dd>
			</dl>
		</div>
	</div>
	<div class="main">
		<h2>回复留言</h2>
		<div class="manage">
			<form action="/manager/guestbook/modify" method="post">
				<table class="form">
					<tr>
						<td class="field">留言编号：</td>
						<td>${updateGuestBook.id}</td>
					</tr>
					<tr>
						<td class="field">留言姓名：</td>
						<td>${updateGuestBook.user.nickname}</td>
					</tr>
					<tr>
						<td class="field">留言内容：</td>
						<td>${updateGuestBook.content}</td>
					</tr>
					<tr>
						<td class="field">回复内容：</td>
						<td><textarea name="reply">${updateGuestBook.reply}</textarea></td>
						<input type="hidden" name="user.id" value="${updateGuestBook.user.id}">
						<input type="hidden" name="id" value="${updateGuestBook.id}">
						<input type="hidden" name="content" value="${updateGuestBook.content}">
						<input type="hidden" name="createtime" value="${updateGuestBook.createtime}">
						<input type="hidden" name="replytime" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>">
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-blue"><input type="submit" name="submit" value="更新" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2013 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
