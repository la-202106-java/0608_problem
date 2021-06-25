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
		<tr><td>名前</td><td>${setName}</td></tr>
		<tr><td>住所</td><td>${setAddress}</td></tr>
		<tr><td>電話番号</td><td>${setTel}</td></tr>
		<tr><td>メールアドレス</td><td>${setEmail}</td></tr>
		<tr><td>生年月日</td><td>${setBirthday}</td></tr>
		</table><br>
		<input type="submit" value="登録">
	</form>
	<form action="/0608_problem/MemberRegistServlet?action=cancel" method="post">
	<input type="submit" value="キャンセル">
	</form>
</body>
</html>