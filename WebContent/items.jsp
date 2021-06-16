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
<h1><b>商品一覧</b></h1>

	<table border="1">
	<tr><td><b>コード</b></td><td><b>カテゴリーコード</b></td><td><b>商品名</b></td><td><b>価格</b></td><td><b>更新</b></td></tr>

	<c:forEach items="${items}" var="item">
		<tr><td>${item.code}</td><td>${item.category_code}</td><td>${item.name}</td><td>${item.price}</td>
		<td><form action = "/0608_problem/AdminItemServlet" method = "post">
		<input type="hidden" name="code" value="${item.code}">
		<input type= "submit" value = 更新>
		<input type = "hidden" name = "action" value = "edit"></form>
		</td>
	</tr>
	</c:forEach>

	</table>

<a href = "/0608_problem/AdminItemServlet?action=regist">新規登録</a>

</body>
</html>