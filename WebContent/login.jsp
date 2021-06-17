<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome shopping!</title>
</head>
<body>
<h1>ログイン</h1>

<c:if test="${isLogin eq false}">
メールアドレスとパスワードが一致しませんでした。
</c:if>
<br>
	<form action="/0608_problem/LoginServlet" method="post">
	 <input type ="hidden" name ="action" value ="login">
  メールアドレス：<input type="text" name="address"><br>
  パスワード：<input type="text" name="password"><br>
  <input type="submit" value="ログイン">
</form>
</body>
</html>