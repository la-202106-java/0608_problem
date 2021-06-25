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
<c:if test="${not empty error}">
${error}
</c:if>
<h1>ログイン</h1>
<form action="/0608_problem/ShowPlanServlet" method="post">
メールアドレス：<input type="text" name="email"><br>
パスワード：<input type="password" name="password"><br>
<input type="hidden" name="action" value="login">
<input type="submit" value="ログイン">
</form>
<hr>
会員登録されていない方
<form action="/0608_problem/registration.jsp" method="post">
<input type="submit" value="会員登録">
</form>

</body>
</html>