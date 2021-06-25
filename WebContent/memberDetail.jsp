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
	<br>

	<h3>${logined.name}</h3>
	<table>
		<tr><td>住所</td><td>${member_info.address}</td></tr>
		<tr><td>電話番号</td><td>${member_info.tel}</td></tr>
		<tr><td>メールアドレス</td><td>${member_info.email}</td></tr>
		<tr><td>生年月日</td><td>${member_info.birthday}</td></tr>
		<tr><td>入会年月日</td><td>${member_info.joinDate}</td></tr>
		<c:if test="${!empty member_info.quitDate}">
			<tr><td>退会年月日</td><td>${member_info.quitDate}</td></tr>
		</c:if>
		<tr><td>売上金額</td><td>${member_info.sales}</td></tr>
	</table>

	<form action="/0608_problem/LeaveServlet" method="post">
		<input type="submit" value="退会">
	</form>

	<form action="/0608_problem/MemberRegistChangeServlet" method="post">
		<input type="hidden" name="action" value="change">
		<input type="submit" value="会員情報変更">
	</form>

</body>
</html>