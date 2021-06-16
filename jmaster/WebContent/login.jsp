<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

<h3>ログイン</h3>

<c:if test="${!empty message}">
${message}<br><br>
</c:if>

<form action="/jmaster/LoginServlet" method="post">
メールアドレス：<input type="email" name="email" value="${setNameAdd}"><br>
パスワード：<input type="password" name="password" value="${setPriceAdd}"><br>
<input type="hidden" name="action" value="login">
<input type="submit" value="ログイン">
</form>

</body>
</html>