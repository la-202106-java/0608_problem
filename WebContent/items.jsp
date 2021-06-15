<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品管理</title>
</head>
<body>
	<h1>商品一覧</h1>
	<table border="1">
	<tr>
	<td>NO</td><td>カテゴリコード</td><td>商品名</td><td>値段</td>
	</tr>
	<c:forEach items="${items}" var="item">
		<tr><td>${item.code}</td><td>${item.category}</td><td>${item.name}</td><td>${item.price}</td></tr>
	</c:forEach>
	</table>
</body>
</html>