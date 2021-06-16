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
<form action="/0608_problem/AdminItemServlet?action=update&code=${code}" method="post">
<table border="1">
  <tr>
    <th><b>コード</b></th><td>${code}</td>
  </tr>
  <tr>
    <th><b>カテゴリコード</b></th><td><input type="text" name="category"></td>
  </tr>
  <tr>
    <th><b>名前</b></th><td><input type="text" name="name"></td>
  </tr>
  <tr>
    <th><b>価格</b></th><td><input type="text" name="price"></td>
  </tr>
</table>
<input type="submit" value="更新">
</form>
</body>
</html>