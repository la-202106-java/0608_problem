<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show All Items</title>
</head>
<body>


<h1>商品一覧</h1>


	<table border="1">
	<tr><td>コード</td><td>カテゴリコード</td><td>商品名</td><td>値段</td></tr>

	<c:forEach items="${items}" var="item">
		<tr><td>${item.code}</td><td>${item.category_code}</td><td>${item.name}</td><td>${item.price}</td></tr>
	</c:forEach>

	</table>

<a href="/0608_problem/ItemServlet2?action=regist">新規登録</a><br>

</body>
</html>
