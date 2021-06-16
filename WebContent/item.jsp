<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome shopping!</title>
</head>
<body>

<jsp:include page="/menu.jsp" />

<h1>商品詳細</h1>
商品番号：<b>${item.code}</b><br>
商品名：<b>${item.name}</b><br>
価格（税込）：<b>${item.price}</b><br>

</body>
</html>