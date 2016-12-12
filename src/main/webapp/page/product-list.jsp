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
<div id="position" class="wrap">
	您现在的位置：<a href="/show/index">易买网</a> &gt; <a href="/show/showtypelistPro/${ProductList[0].proType.proTypeList.totalListName}">${ProductList[0].proType.proTypeList.totalListName}</a> &gt; ${ProductList[0].proType.typeName}
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
		<div class="spacer"></div>
		<div class="last-view">
			<h2>最近浏览</h2>
			<dl class="clearfix">
				<dt><img src="/resources/images/product/10_small.jpg" /></dt>
				<dd><a href="product-view.jsp" target="_self">利仁2018M福满堂电饼铛 好用实惠</a><a href="product-view.jsp"></a></dd>
				<dt>&nbsp;</dt>
				<dd>&nbsp;</dd>
		  </dl>
			<script type="text/javascript">
				document.write("Cookie中记录的购物车商品ID："+ getCookie("product") + "，可以在动态页面中进行读取");
			</script>
		</div>
	</div>
	<div class="main">
		<div class="product-list">
			<h2>全部商品</h2>			
			<div class="clear"></div>
			<ul class="product clearfix">
			<c:forEach items="${ProductList}" var="pro">
				<li>
					<dl>
						<dt><a href="/show/productC/${pro.id}" target="_self"><img src="${pro.pitch}" /></a></dt>
						<dd class="title"><a href="/show/productC/${pro.id}" target="_self">${pro.proname}</a></dd>
						<dd class="price">￥${pro.price}</dd>
					</dl>
				</li>
			</c:forEach>

			</ul>
			<div class="clear"></div>
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
	Copyright &copy; 2010 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
