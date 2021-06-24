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

<c:if test="${user == 'admin'}">
<jsp:include page="/adminMenu.jsp" />
</c:if>
<c:if test="${user != 'admin'}">
<jsp:include page="/menu.jsp" />
</c:if>

	<h1>会員情報確認</h1>
	<h3>こちらでお間違えないですか？</h3>

		<form action="/0608_problem/MemberRegistChangeServlet?action=add" method="post">
		<table>
		<tr><td>名前</td><td>${member.name}</td></tr>
		<tr><td>住所</td><td>${member.address}</td></tr>
		<tr><td>電話番号</td><td>${member.tel}</td></tr>
		<tr><td>メールアドレス</td><td>${member.email}</td></tr>
		<tr><td>生年月日</td><td>${member.birthday}</td></tr>
		</table><br>
		<input type="submit" value="変更">
	</form>
</body>
</html>