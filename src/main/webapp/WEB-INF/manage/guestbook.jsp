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
	<div id="logo"><img src="/resources/images/logo.gif"  width="120" /></div>
	<div class="help"><a href="/show/index">返回前台页面</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li><a href="/user/manager">首页</a></li>
			<li><a href="/manager/user/list">用户</a></li>
			<li><a href="/manager/productClass/list">商品</a></li>
			<li><a href="/manager/order/list">订单</a></li>
			<li class="current"><a href="/manager/guestbook/list">留言</a></li>
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
		<h2>留言管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>ID</th>
					<th>姓名</th>
					<th>留言内容</th>
					<th>状态</th>
					<th>回复内容</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${guestBooklist.datas}" var="gl">
					<tr>
						<td class="first w4 c">${gl.id}</td>
						<td class="w1 c">${gl.user.nickname}</td>
						<td>${gl.content}</td>
						<td class="w1 c"><c:if test="${gl.reply!=''}">已回复</c:if><c:if test="${gl.reply==''}">未处理</c:if></td>
						<td>${gl.reply}</td>
						<c:if test="${gl.reply!=''}">
							<td class="w1 c"><a href="/manager/guestbook/modify/${gl.id}">修改</a> <a class="manageDel" href="/manager/guestbook/delete/${gl.id}">删除</a></td>
						</c:if>
						<c:if test="${gl.reply==''}">
							<td class="w1 c"><a href="/manager/guestbook/modify/${gl.id}">回复</a> <a class="manageDel" href="/manager/guestbook/delete/${gl.id}">删除</a></td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
			<div class="pager">
				<ul class="clearfix">
					<li><a >首页</a></li>
					<li>...</li>
					<li><a >4</a></li>
					<li class="current">5</li>
                    <li><a >6</a></li>
                    <li>...</li>
					<li><a >尾页</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2013 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
