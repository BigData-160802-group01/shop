<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
	<div id="logo"><img src="/resources/images/logo.gif" /></div>
	<div class="help"><a href="/page/index.jsp">返回前台页面</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li><a href="index.jsp">首页</a></li>
			<li class="current"><a href="user.jsp">用户</a></li>
			<li><a href="product.jsp">商品</a></li>
			<li><a href="order.jsp">订单</a></li>
			<li><a href="guestbook.jsp">留言</a></li>
			<li><a href="news.jsp">新闻</a></li>
		</ul>
	</div>
</div>
<div id="childNav">
	<div class="welcome wrap">
		管理员 [${LoginUser.nickname}] 您好，今天是<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>，欢迎回到管理后台。
	</div>
</div>
<div id="position" class="wrap">
	您现在的位置：<a href="index.jsp">易买网</a> &gt; 管理后台
</div>
<div id="main" class="wrap">
	<div id="menu-mng" class="lefter">
		<div class="box">
			<dl>
				<dt>用户管理</dt>
				<dd><a href="/manager/user/list">用户管理</a></dd>
			  <dt>商品信息</dt>
				<dd><em><a href="productClass-add.jsp">新增</a></em><a href="/manager/productClass/list">分类管理</a></dd>
				<dd><em><a href="product-add.jsp">新增</a></em><a href="product.jsp">商品管理</a></dd>
				<dt>订单管理</dt>
				<dd><a href="order.jsp">订单管理</a></dd>
				<dt>留言管理</dt>
				<dd><a href="guestbook.jsp">留言管理</a></dd>
				<dt>新闻管理</dt>
				<dd><em><a href="news-add.jsp">新增</a></em><a href="news.jsp">新闻管理</a></dd>
			</dl>
		</div>
	</div>
	<div class="main">
		<h2>用户管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>用户名</th>
					<th>真实姓名</th>
					<th>性别</th>
					<th>Email</th>
					<th>手机</th>
					<th>操作</th>
				</tr>
				<c:if test="${userlist.datas!=null}">
					<c:forEach var="user" items="${userlist.datas}">
						<tr>
							<td class="first w4 c">${user.name}</td>
							<td class="w1 c">${user.nickname}</td>
							<td class="w2 c">${user.sex}</td>
							<td>${user.email}</td>
							<td class="w4 c">${user.phone}</td>
								<td class="w1 c">
									<a href="/manager/user/modify/${user.id}">修改</a>
									<a class="manageDel" href="/manager/user/delete/${user.id}">删除</a></td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</div>
	</div>
	<div class="clear"></div>
     <div class="pager">
				<ul class="clearfix">
					<li><a href="/manager/user/list?pageIndex=1">首页</a></li>
					<c:forEach begin="${userlist.pageIndex}" end="${userlist.pageIndex+8}" step="1" var="ab">
						<c:if test="${ab<=userlist.totalPage}">
							<li class="current"><a href="/manager/user/list?pageIndex=${ab}">${ab}</a></li>
						</c:if>
					</c:forEach>
					<li><a href="/manager/user/list?pageIndex=1">尾页</a></li>
				</ul>
			</div>
</div>
<div id="footer">
	Copyright &copy; 2013 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
