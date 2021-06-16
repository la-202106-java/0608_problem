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

<p>${login_message }</p>

<table>

<form  action="/0608_problem/LoginServlet" method="post">
<tr><td>メールアドレス：<input type="text" name="email"></td></tr>
<tr><td>パスワード：<input type="password" name="password"></td></tr>
<tr><td><input type="submit" value="ログイン"><input type="hidden" name="action" value="login"></td></tr>
</form>

</table>

</body>
</html>