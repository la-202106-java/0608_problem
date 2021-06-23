<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップメニュー(管理者)</title>
</head>
<body>

<jsp:include page="/WEB-INF/adminMenu.html" />

<form action="/0608_problem/admin/login" method="post">
<input type="submit" value="ログアウト">
<input type="hidden" name="action" value="logout">
</form>
</body>
</html>