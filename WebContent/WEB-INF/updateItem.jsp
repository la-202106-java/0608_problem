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
	<h1>商品更新</h1>
	<form action="/0608_problem/AdminItemServlet" method="post">
		<table border="1">
		<tr>
				<td>コード</td>
				<td>${item.code}</td>
			</tr>
			<tr>
				<td>カテゴリコード</td>
				<td><input type="text" name="categoryCode" value ="${item.categoryCode}"></td>
			</tr>
			<tr>
				<td>名前</td>
				<td><input type="text" name="name" value ="${item.name}"></td>
			</tr>
			<tr>
				<td>価格</td>
				<td><input type="text" name="price" value ="${item.price}"></td>
			</tr>
		</table>
		<input type="submit" value="更新"> <input type="hidden"
			name="action" value="update">
		<input type="hidden" name="code" value="${item.code}">
	</form>

</body>
</html>