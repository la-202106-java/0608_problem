<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報一覧</title>
</head>
<body>
<jsp:include page="/WEB-INF/adminMenu.html" />

<form action="/0608_problem/AdminMemberServlet" method="post">
<h1>会員情報の検索</h1>
氏名:<input type="text" name="name">
e-mail:<input type="text" name="email">
<input type="submit" value="検索">
<input type="hidden" name="action" value="search"><br>
</form>
<hr>
</body>
</html>