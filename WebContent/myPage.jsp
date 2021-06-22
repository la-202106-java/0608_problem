<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
<title>教科書売買サイト</title>
</head>
<body>

<!-- 管理者と会員のログイン判定 -->
<c:if test="${user == 'admin'}">
<jsp:include page="/adminMenu.jsp" />
</c:if>
<c:if test="${user != 'admin'}">
<jsp:include page="/menu.jsp" />
</c:if>

	<h1>会員情報</h1>
	<a href="">会員情報変更</a>
	<br>

	<h3>山田花子</h3>
	住所：東京都新宿区1-1-1<br>
	電話番号：090-999-9999<br>
	E-Mail：sample@aaa.com<br>
	生年月日：2000/01/01<br>

	<hr>
	<a href="/0608_problem/LeaveServlet?action=leave">退会</a>
</body>
</html>