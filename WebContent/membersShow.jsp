<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報検索</title>
</head>
<body>

	<table border="1">
		<tr>
			<td>会員ID</td>
			<td>パスワード</td>
			<td>氏名</td>
			<td>郵便番号</td>
			<td>住所</td>
			<td>電話番号</td>
			<td>E-Mail</td>
			<td>生年月日</td>
			<td>入会年月日</td>
			<td>退会年月日</td>
		</tr>

		<c:forEach items="${members}" var="member">
			<tr>
				<td>${member.id}</td>
				<td>${member.password}</td>
				<td>${member.name}</td>
				<td>${member.postalCode}</td>
				<td>${member.address}</td>
				<td>${member.tel}</td>
				<td>${member.emailAddress}</td>
				<td>${member.birthDate}</td>
				<td>${member.joinDate}</td>
				<td>${member.quitDate}</td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>