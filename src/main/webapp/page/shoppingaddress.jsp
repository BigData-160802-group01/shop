<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>易买网 - 首页</title>
    <link type="text/css" rel="stylesheet" href="/resources/css/style.css"/>
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
        <form action="/show/payforShopping" method="post">
            <div style="float: left;">
                <h3 style="margin-bottom: 20px;margin-left: 20px">选择收货地址</h3>
                <c:forEach items="${LoginUser.userAdresses}" var="addr" begin="0" end="1">
                <c:if test="${addr.chose==1}">
                <div class="userAdress" style="border: 2px solid rgb(250,126,49)">
                    </c:if>
                    <c:if test="${addr.chose==0}">
                    <div class="userAdress">
                        </c:if>
                        <ul>
                            <li style="height: 30px;border-bottom: 1px solid rgb(242,242,242);">${addr.consignee}</li>
                            <li>收货地址：${addr.adress}</li>
                            <li>联系电话：${addr.telphone}</li>
                        </ul>
                    </div>
                    </c:forEach>
                    <div class="userAdress" style="vertical-align: top" onclick="location.href='/show/createAdress'">
                        <ul>
                            <li style="height: 30px;border-bottom: 1px solid rgb(242,242,242);">管理收货地址</li>
                        </ul>
                    </div>
                    <div id="shopping">
                        <h3 style="margin: 20px 0px">确认订单信息</h3>
                        <table>
                            <tr>
                                <th>商品名称</th>
                                <th>商品价格</th>
                                <th>购买数量</th>
                                <th>操作</th>
                            </tr>
                            <c:forEach items="${needPay}" var="payPro">
                                <tr id="product_id_0">
                                    <td class="thumb"><img src="${payPro.product.pitch}" width="100" height="80"/><a
                                            href="product-view.html">${payPro.product.proname}</a></td>
                                    <td class="price" id="price_id_0">
                                        <span>￥${payPro.product.price}</span>
                                        <input type="hidden" value="${payPro.product.price}"/>
                                    </td>
                                    <td class="number">
                                        <span name="del">-</span>
                                        <input id="number_id_0" type="text" name="number" value="1"/>
                                        <span name="add">+</span>
                                    </td>
                                    <td class="delete"><a href="javascript:void(0)">删除</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                        <div class="total"><span id="total">总计：￥0</span></div>
                        <div class="button"><input type="submit" value=""/></div>
                    </div>
        </form>
    </div>
</div>
<div class="clear"></div>
<div id="footer">
    Copyright &copy; 2010 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>

</html>
