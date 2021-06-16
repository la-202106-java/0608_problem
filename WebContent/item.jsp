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

<h3>商品詳細</h3>
<input type="hidden" name="item_code" value="${bean.code }">
商品番号：<b>${bean.code }</b><br>
商品名：<b>${bean.name}</b><br>
価格（税込）：<b>${bean.price}</b>円<br>


</body>
</html>