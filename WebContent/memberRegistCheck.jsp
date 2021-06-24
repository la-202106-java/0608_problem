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

		<form action="/0608_problem/MemberRegistServlet?action=add" method="post">
		<table>
		<tr><td>名前</td><td>${logined.name}</td></tr>
		<tr><td>住所</td><td>${logiend.address}</td></tr>
		<tr><td>電話番号</td><td>${logiend.tel}</td></tr>
		<tr><td>メールアドレス</td><td>${logiend.email}</td></tr>
		<tr><td>生年月日</td><td>${logiend.birthday}</td></tr>
		</table><br>
		<input type="submit" value="登録">
	</form>
</body>
</html>