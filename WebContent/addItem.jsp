<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品管理</title>
</head>
<body>

<h1>商品登録</h1>

<form action="/0608_problem/AdminItemServlet" method="post">
<table border="1">
	<tr align="center"><td><b>カテゴリコード</b><td><input type="text" name="categoryCode"></tr>
	<tr align="center"><td><b>名前</b></td><td><input type="text" name="name"></tr>
	<tr align="center"><td><b>価格</b></td><td><input type="text" name="price"></tr>
</table>
<input type="hidden" name="action" value="add">
<input type="submit" value="登録">
</form>

</body>
</html>