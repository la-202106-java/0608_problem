<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
	<h1>ログイン</h1>

	<form action="/0608_problem/LoginServlet" method="post">
		<input type="hidden" name="action" value="login">
		メールアドレス：<input type="text" name="email"><br>
		パスワード：<input type="password" name="password"><br>
		<input type="submit" value="ログイン">
	</form>
</body>
</html>