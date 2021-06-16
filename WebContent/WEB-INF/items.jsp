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
			<td>コード</td>
			<td>カテゴリコード</td>
			<td>商品名</td>
			<td>値段</td>
			<td>更新</td>
		</tr>
		<c:forEach items="${adminItems}" var="item">
			<tr>
				<td>${item.code}</td>
				<td>${item.categoryCode}</td>
				<td>${item.name}</td>
				<td>${item.price }</td>
				<td><form action="/0608_problem/AdminItemServlet">
				<input type="submit" value="更新">
				<input type="hidden" name="code" value="${item.code}">
				<input type="hidden" name="categoryCode" value="${item.categoryCode}">
				<input type="hidden" name="name" value="${item.name}">
				<input type="hidden" name="price" value="${item.price}">
				<input type="hidden" name="action" value="edit">
				</form>
				</td>
			</tr>
		</c:forEach>

	</table>
<a href="/0608_problem/AdminItemServlet?action=regist">新規登録</a>
</body>
</html>