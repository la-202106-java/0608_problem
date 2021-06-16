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
			<tr><td>カテゴリコード</td><td><input type="text" name="category_code"></td></tr>
			<tr><td>商品名</td><td><input type="text" name="name"></td></tr>
			<tr><td>価格</td><td><input type="text" name="price"></td></tr>
		</table>
		<input type="submit" value="登録">
		<input type="hidden" name="action" value="add">
	</form>
</body>
</html>
