<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム</title>
</head>
<body>
<h2>会員の退会</h2>

<jsp:include page="/menu.jsp" />

<h2>この会員は退会済みです。</h2>
<br>
<table border="1">
<tr><td>ID</td><td>${ notmember.id}</td></tr>
<tr><td>氏名</td><td>${notmember.name}</td></tr>
<tr><td>退会年月日</td><td>${notmember.withdrawalDate}</td></tr>
</table>
</body>
</html>