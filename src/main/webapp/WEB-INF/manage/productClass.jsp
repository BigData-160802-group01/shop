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
	<style type="text/css" rel="stylesheet">
		.nextcolor1:hover{
			background-color: rgba(247,244,235,0.7);
		}
		.nextcolor:hover{
			background-color: rgba(217,214,205,0.5);
		}
	</style>
</head>

<body>
<div id="header" class="wrap">
	<div id="logo"><img src="/resources/images/logo.gif" /></div>
	<div class="help"><a href="/page/index.jsp">返回前台页面</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li><a href="/page/index.jsp">首页</a></li>
			<li><a href="user.jsp">用户</a></li>
			<li class="current"><a href="product.jsp">商品</a></li>
			<li><a href="order.jsp">订单</a></li>
			<li><a href="guestbook.jsp">留言</a></li>
			<li><a href="news.jsp">新闻</a></li>
		</ul>
	</div>
</div>
<div id="childNav">
	<div class="welcome wrap">
		管理员${LoginUser.nickname}您好，今天是<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>，欢迎回到管理后台。
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
				<dd><em><a href="productClass-add.jsp">新增</a></em><a href="productClass.jsp">分类管理</a></dd>
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
		<h2>分类管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>编号</th>
					<th>分类名称</th>
					<th>操作</th>
				</tr>
				<c:if test="${typelist.datas!=null}">
					<c:forEach var="tl" items="${typelist.datas}" varStatus="stat1">
						<tr class="nextcolor">
							<td class="first w4 c">${stat1.index+1}</td>
							<td>${tl.totalListName}</td>
							<td class="w1 c">
								<a href="/manager/productClass/modify/${tl.totalListName}">修改</a>
								<a class="manageDel" href="/manager/productClass/delete/${tl.id}">删除</a>
							</td>
						</tr>
						<c:forEach var="type" items="${tl.type}" varStatus="stat">
							<tr class="nextcolor1">
								<td class="first w4 c" style="font-size: 10px">&nbsp;&nbsp;&nbsp;(${stat.index+1})</td>
								<td class="childClass">${type.typeName}</td>
								<td class="w1 c"><a href="productClass-modify.jsp">修改</a> <a class="manageDel" href="javascript:void(0)">删除</a></td>
							</tr>
						</c:forEach>
					</c:forEach>
				</c:if>
			</table>
		</div>
	</div>
	<div class="clear"></div>
    <div class="pager">
		<ul class="clearfix">
			<li><a href="/manager/productClass/list?pageIndex=1">首页</a></li>
			<c:forEach begin="${typelist.pageIndex}" end="${typelist.pageIndex+8}" step="1" var="ab">
				<c:if test="${ab<=typelist.totalPage}">
					<li class="current"><a href="/manager/productClass/list?pageIndex=${ab}">${ab}</a></li>
				</c:if>
			</c:forEach>
			<li><a href="/manager/productClass/list?pageIndex=1">尾页</a></li>
		</ul>
	</div>
</div>
<div id="footer">
	Copyright &copy; 2013 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
