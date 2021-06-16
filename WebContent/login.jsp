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

<c:if test="${not empty error}">
${error}。
<br><br>
</c:if>

<form action="/0608_problem/LoginServlet" method="post">
メールアドレス：
<input type="text" name="email"><br>
パスワード：
<input type="password" name="password"><br>
<input type="submit" value="ログイン">
<input type="hidden" name="action" value="login">
</form>

</body>
</html>