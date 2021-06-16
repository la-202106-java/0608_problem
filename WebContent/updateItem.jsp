<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<tr><td><b>コード</b></td><td><input type="text" name="code" value=${code }></td></tr>
<tr><td><b>カテゴリコード</b></td><td><input type="text" name="category_code"></td></tr>
<tr><td><b>名前</b></td><td><input type="text" name="name"></td></tr>
<tr><td><b>価格</b></td><td><input type="text" name="price"></td></tr>
</table>
<input type="submit" value="更新">
<input type="hidden" name="action" value="update">
</form>

</body>
</html>