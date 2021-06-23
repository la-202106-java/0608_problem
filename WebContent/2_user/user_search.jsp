<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員検索</title>
</head>
<body>

<h2>会員検索</h2>
<form action = "/0608_problem/UserSearchServlet" method = "post">
email <input type ="email" name = "email">

<input type="submit" value = 検索>
<input type="hidden" name = "action" value = "search">
</form>
</body>
</html>