<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
</head>
<body>
<h1>新規登録</h1>
<c:if test="${!empty message}">
${message}<br><br>
</c:if>

<form action="/0608_problem/RegistServlet" method="post">
名前:<input type="text" name="name"><br>
住所:<input type="text" name="address"><br>
電話番号:<input type="tel" name="tel"><br>
メールアドレス:<input type="email" name="email"><br>
パスワード:<input type="password" name="password"><br>
<input type="hidden" name="action" value="regist">
<input type="submit" value="新規登録">
</form>

</body>
</html>