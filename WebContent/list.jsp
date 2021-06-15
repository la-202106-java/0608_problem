<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to shopping!</title>
</head>
<body>
<jsp:include page="/menu.jsp"></jsp:include>

<h3>商品一覧</h3>
<c:forEach items="${items }" var="item">
<form action="/shopping/CartServlet?action=add" method="post">
<input type="hidden" name="item_code" value="${item.code }">
商品番号：<b>${item.code }</b><br>
商品名：<b>${item.name}</b><br>
価格（税込）：<b>${item.price}</b>円<br>
個数：
<select name="quantity">
<option value="1">1
<option value="2">2
<option value="3">3
<option value="4">4
<option value="5">5
</select>
個<br>
<input type="submit" value="カートに追加">
</form>
<tr><td>${item.code }</td><td>${item.name }</td><td>${item.price }</td></tr>


</c:forEach>
</body>
</html>