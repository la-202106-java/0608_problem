<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>

<form action="/0608_problem/LoginDiaryServlet" method="post">
email：
<input type="text" name="email" placeholder="info@example.com"><br>
パスワード：
<input type="password" name="password" placeholder="パスワードを入力"><br>
<input type="submit" value="ログイン">
<input type="hidden" name="action" value="login">
</form>
</body>
</html>