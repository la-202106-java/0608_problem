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
<h1><b>商品更新</b></h1>

	<form action = "/0608_problem/AdminItemServlet" method = "post">
	<table border="1">
	<tr><td><b>コード</b></td>
	<td>${code}<input type = "hidden" name = "code" value = "${code}"></td></tr>
	<tr><td><b>カテゴリーコード</b></td>
	<td><input type = "text" name ="category_code"></td></tr>
	<tr><td><b>名前</b></td>
	<td><input type = "text" name ="name"></td></tr>
	<tr><td><b>価格</b></td>
	<td><input type = "text" name ="price"></td></tr>

	</table>

	<input type= "submit" value = 登録>
	<input type = "hidden" name = "action" value = "update">
	</form>

</body>
</html>