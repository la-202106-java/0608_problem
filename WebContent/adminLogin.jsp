<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>教科書売買サイト</title>
</head>
<body>
	<h1>管理者ログイン</h1>

	<form action="/0608_problem/AdminLoginServlet" method ="post">
		メールアドレス<br>
		<input type="text" name = "mail"><br>
		パスワード<br>
		<input type="password" name = password><br>
		<input type="hidden" name = "action" value = "login">
		<input type="submit" value="ログイン">
	</form>
</body>
</html>