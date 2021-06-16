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
<form action="/0608_problem/AdminItemServlet?action=add" method="post">
<table border="1">
  <tr>
    <th>カテゴリコード</th><td><input type="text" name="category"></td>
  </tr>
  <tr>
    <th>名前</th><td><input type="text" name="name"></td>
  </tr>
  <tr>
    <th>価格</th><td><input type="text" name="price"></td>
  </tr>
</table>
<input type="submit" value="登録">
</form>
</body>
</html>