<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易买网 - 首页</title>
<link type="text/css" rel="stylesheet" href="/resources/css/style.css" />
<script type="text/javascript" src="/resources/scripts/jquery-1.8.3.min.js"></script>
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
<div id="position0" class="wrap">
	您现在的位置：<a href="index.jsp">易买网</a> &gt; 结账
</div>
<div id="main" class="wrap">
	<div class="lefter">
		<div class="box">
			<h2>商品分类</h2>
			<dl>
				<c:forEach items="${protypelist}" var="ptl">
					<dt>${ptl.totalListName}</dt>
					<c:forEach items="${ptl.type}" var="pt">
						<dd><a href="/show/showtypePro/${pt.typeName}">${pt.typeName}</a></dd>
					</c:forEach>
				</c:forEach>
			</dl>
		</div>
	</div>
</div>
<div id="news" class="right-main">
		<div class="content">
			<form action="/show/addAddress" method="post">
				<div class="addAddress">
					<p>新增收货地址: <span style="color: black;font-size: 13px">&nbsp;&nbsp;以下均为必填项</span></p>
					<table>
						<tr>
							<td>联系人：</td>
							<td><input type="text" class="addinput" name="consignee" placeholder="长度不能超过10位"></td>
						</tr>
						<tr>
							<td>电话号码：</td>
							<td><input type="text" class="addinput" name="telphone" placeholder="长度不能超过12位"></td>
						</tr>
						<tr>
							<td>详细地址：</td>
							<td><input type="text" class="addinput" name="adress" placeholder="建议如实填写详细地址"></td>
						</tr>
						<tr>
							<td colspan="2"><input type="radio" name="chose" value="1">设置为默认收货地址</td>
						</tr>
						<tr>
							<td colspan="2"><button type="submit">确认添加地址</button></td>
						</tr>
					</table>
				</div>
			</form>
		</div>
		<div class="showaddress">
			<p>以保存地址</p>
			<table>
				<tr class="title">
					<td width="10%">收货人</td>
					<td width="40%">收货地址</td>
					<td width="20%">联系电话</td>
					<td width="15%">操作</td>
					<td width="15">设为默认地址</td>
				</tr>
				<c:if test="${!empty addresslist}">
					<c:forEach items="${addresslist}" var="addr">
						<tr class="addressbody" >
							<td>${addr.consignee}</td>
							<td>${addr.adress}</td>
							<td>${addr.telphone}</td>
							<td>
								<a href="#">修改</a>
								<a href="#">删除</a>
							</td>
							<td>
								<input type="radio" name="chose" value="1">
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</div>
	</div>
<div class="clear"></div>
<div id="position1" class="wrap"></div>
<div class="wrap">
	<div id="shopping"></div>
</div>
<div id="footer">
	Copyright &copy; 2010 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>

</html>
