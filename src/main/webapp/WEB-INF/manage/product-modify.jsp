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
	<div id="logo"><img src="/resources/images/logo.gif" width="120" /></div>
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
		<h2>修改商品</h2>
		<div class="manage">
			<form action="/manager/product/modify" method="post" enctype="multipart/form-data">
				<table class="form">
					<tr>
						<td class="field">商品名称(*)：</td>
						<td><input type="text" class="text" name="proname" value="${updateProduct.proname}" /></td>
					</tr>
                    <tr>
						<td class="field">描述：</td>
						<td><input type="text" class="text" name="description" value="${updateProduct.description}" /></td>
					</tr>
					<tr>
						<td class="field">所属分类：</td>
						<td>
							<div style="width:100px; text-align:center; font-size:14px;display: inline-block">一级分类</div>
							<select name="proType.proTypeList.totalListName" id="dis">
								<option value="0" selected="selected">请选择</option>
								<c:forEach items="${protypelist}" var="pr">
									<c:if test="${pr.totalListName==updateProduct.proType.proTypeList.totalListName}">
										<option selected="selected" value="${pr.totalListName}">${pr.totalListName}</option>
									</c:if>
									<c:if test="${pr.totalListName!=updateProduct.proType.proTypeList.totalListName}">
										<option value="${pr.totalListName}">${pr.totalListName}</option>
									</c:if>
								</c:forEach>
							</select>
							<div style="width:100px; text-align:center; font-size:14px;display: inline-block">二级分类</div>
							<select name="proType.typeName" id="stre">
								<option value="0" selected="selected">请选择</option>
								<c:forEach items="${updateProduct.proType.proTypeList.type}" var="tylist">
									<c:if test="${tylist.typeName==updateProduct.proType.typeName}">
										<option selected="selected" value="${tylist.typeName}">${tylist.typeName}</option>
									</c:if>
									<c:if test="${tylist.typeName!=updateProduct.proType.typeName}">
										<option value="${tylist.typeName}">${tylist.typeName}</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
					</tr>					
					<tr>
						<td class="field">商品价格(*)：</td>
						<td><input type="text" class="text tiny" name="price" value="${updateProduct.price}" /></td>
					</tr>
					
					<tr>
						<td class="field">库存(*)：</td>
						<td>
							<input type="text" class="text tiny" name="stock" value="${updateProduct.stock}"/>
							<input type="hidden" name="id" value="${updateProduct.id}">
						</td>
					</tr>
					<tr>
						<td class="field">商品图片：</td>
						<td><input type="file" class="text" name="photo" /></td>
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
