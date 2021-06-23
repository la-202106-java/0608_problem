<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
<h1>ログイン</h1>

${errMessage}

<form action="/0608_problem/LoginServlet" method="post">
メールアドレス：<input type="text" name="email" size="20"><br>
パスワード：<input type="text" name="password" size="20"><br>
<input type="submit" value="ログイン">
<input type="hidden" name="action" value="login">
</form>
<br>
</body>
</html>