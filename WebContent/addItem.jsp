<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>商品登録</h3>
<form method="post" action="ItemManagement?action=add">
<table border="1">

<tr><td>カテゴリーコード：</td><td><input type="text" name="category" ></td></tr>
<tr><td>名前：</td><td><input type="text" name="name" ></td></tr>
<tr><td>価格：</td><td><input type="text" name="price" ></td></tr>
</table>
<input type="submit" name="登録">
</form>
</body>
</html>