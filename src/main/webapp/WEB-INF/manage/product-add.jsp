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
<script type="text/javascript">
		$(function () {
			$("#dis").change(function () {
				var a=dis.value;
				$.ajax({
					url:"/manager/product/modify/search",
					type:"post",
					data:{name:a},
					success:function (data) {
						$("#stre").empty();
						for (var i=0;i<data.length;i++){
							var b= "<option value="+data[i]+">"+data[i]+"</option>";
							$("#stre").append(b);
						}
					}
				})
			})
		})
</script>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="/resources/images/logo.gif" /></div>
	<div class="help"><a href="/page/index.jsp">返回前台页面</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li><a href="index.jsp">首页</a></li>
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
				<dd><a href="user.jsp">用户管理</a></dd>
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
		<h2>添加商品</h2>
		<div class="manage">
			<form id="productAdd" action="/manager/product/addproduct" method="post" enctype="multipart/form-data">
				<table class="form">
					<tr>
						<td class="field">商品名称(*)：</td>
						<td><input type="text" class="text" name="proname" /><span></span></td>
					</tr>
                    <tr>
						<td class="field">描述：</td>
						<td><input type="text" class="text" name="description" /></td>
					</tr>
					<tr>
						<td class="field">所属分类：</td>
						<td>
							<div style="width:100px; text-align:center; font-size:14px;display: inline-block">一级分类</div>
							<select name="proType.proTypeList.totalListName" id="dis">
								<option value="0" selected="selected">请选择</option>
								<c:forEach items="${protypelist}" var="pr">
										<option value="${pr.totalListName}">${pr.totalListName}</option>
								</c:forEach>
							</select>
							<div style="width:100px; text-align:center; font-size:14px;display: inline-block">二级分类</div>
							<select name="proType.typeName" id="stre">
								<option value="0" selected="selected">请选择</option>
							</select>
						</td>
					</tr>					
					<tr>
						<td class="field">商品价格(*)：</td>
						<td><input type="text" class="text tiny" name="price" /> 元<span></span></td>
					</tr>
					
					<tr>
						<td class="field">库存(*)：</td>
						<td><input type="text" class="text tiny" name="stock" /><span></span></td>
					</tr>
					<tr>
						<td class="field">商品图片(*)：</td>
						<td><input type="file" class="text" name="photo" /><span></span></td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-blue"><input type="submit" name="submit" value="确定" /></label></td>
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
