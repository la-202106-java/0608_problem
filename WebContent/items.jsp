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
	<td><b>NO</b></td><td><b>カテゴリコード</b></td><td><b>商品名</b></td><td><b>値段</b></td><td><b>更新</b></td><td><b>削除</b></td>
	</tr>
	<c:forEach items="${items}" var="item">

		<tr>
		<td>${item.code}</td>
		<td>${item.category}</td>
		<td>${item.name}</td>
		<td>${item.price}</td>
		<form action="/0608_problem/AdminItemServlet?action=updateForm&code=${item.code}" method="post">
		<td><input type="submit" value="更新"></td>
		</form>
		<form action="/0608_problem/AdminItemServlet?action=delete&code=${item.code}" method="post">
		<td><input type="submit" value="削除"></td>
		</form>
		</tr>

	</c:forEach>
	</table>
	<a href="/0608_problem/AdminItemServlet?action=regist">新規登録</a>
</body>
</html>