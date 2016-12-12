<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易买网 - 首页</title>
<link type="text/css" rel="stylesheet" href="/resources/css/style.css" />
<style type="text/css">
		#mydiv{
			display: none;
			background-color: rgba(174,174,174,0.5);
			filter:alpha(opacity=50);/*IE浏览器的半透明样式*/
			width:100%;
			height:100%;
			position:absolute;/*层为浮动层*/
			left:1px;
			top:1px;
		}
	</style>
<script type="text/javascript" src="/resources/scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="/resources/scripts/function.js"></script>
<script type="text/javascript">
		function init(){
			mydiv.style.display="none";
		}
		function change(){
			mydiv.style.display="block";
		}
        $(function () {
            $("#logsub").click(function () {
                var a=userId.value;
                var b=password.value;
                var c=code.value;
                $.ajax({
                    url:"/user/login1",
                    type:"post",
                    data:{username:a,password:b,code:c},
                    dataType:"text",
                    success: function (dddd) {
                        if (dddd=="success"){
                            $("#mydiv").css("display","none");
                            location.reload();
                        }else {
                            $("#showerror td span").append(dddd);
                        }

                    }});
            })
        })

	</script>
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
	  </div>
	</div>
	<div class="main">
		<div class="price-off">
            <div class="slideBox">
                <ul id="slideBox">
                    <li><img src="/resources/images/product/banner_1.jpg"/></li>
                    <li><img src="/resources/images/product/banner_2.jpg"/></li>
                    <li><img src="/resources/images/product/banner_3.jpg"/></li>
                    <li><img src="/resources/images/product/banner_4.jpg"/></li>
                </ul>
            </div>
			<ul class="product clearfix">
				<c:forEach items="${protypelist}" var="ptl">
					<c:forEach items="${ptl.type}" var="pt">
						<h2>${pt.typeName}</h2>
						<c:forEach items="${pt.products}" var="pr" begin="1" end="8">
							<li>
								<dl>
									<dt><a href="/show/productC/${pr.id}" target="_self"><img src="${pr.pitch}" style="width: 100px;height: 100px" /></a></dt>
									<dd class="title"><a href="/show/productC/${pr.id}" target="_self">${pr.proname}</a></dd>
									<dd class="price">￥${pr.price}</dd>
								</dl>
							</li>
						</c:forEach>
					</c:forEach>
				</c:forEach>
			</ul>
		</div>
		<div class="side">			
			<div class="spacer"></div>
			<div class="news-list">
				<h4>新闻动态</h4>
				<ul>
					<c:forEach items="${newslist}" var="nl">
					<li><a href="news-view.jsp" target="_self">${nl.title}</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="spacer clear"></div>
    </div>
</div>
<div id="footer">
	Copyright &copy; 2013 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>

<div id="mydiv">
<div id="register" class="wrap">
	<div class="shadow">
		<em class="corner lb"></em>
		<em class="corner rt"></em>
		<div class="box">
			<h1>欢迎回到易买网</h1>
			<form id="loginForm" method="post" action="/user/login" onsubmit="return false" >
				<table>

					<tr>
						<td class="field">用户名：</td>
						<td>
							<input class="text" type="text" id="userId" name="username" />
							<span></span>
						</td>
					</tr>
					<tr>
						<td class="field">登录密码：</td>
						<td>
							<input class="text" type="password" id="password" name="password" />
							<span></span>
						</td>
					</tr>
					<tr>
						<td class="field">验证码：</td>
						<td>
							<img src="/user/validLogin" id="safeCode"/><a id="changeCode" href="#">看不清，换一张</a><br>
							<input type="text" name="code" id="code">
						</td>
					</tr>
                    </tr>
                    <tr id="showerror">
                        <td class="field" colspan="2" style="text-align: left;padding-left: 100px">
                            <span style="color:red;font-size: 15px;display: block">${errorMessage}</span>
                        </td>
                    </tr>
                    <tr>
					<tr>
						<td></td>
						<td>
							<label class="ui-green"><input type="submit" id="logsub" name="submit" value="立即登录" /></label>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<label class="ui-green"><input type="button" name="goback" onclick="init()" value="返回主页" /></label>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
</div>
</body>
</html>
