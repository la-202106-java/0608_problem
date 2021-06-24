<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報一覧</title>
</head>
<body>
<jsp:include page="/WEB-INF/adminMenu.jsp" />

<form action="/0608_problem/admin/member" method="post">
<h1>会員情報の検索</h1>
氏名:<input type="text" name="name" value="${name}">
e-mail:<input type="text" name="email" value="${email}">
<input type="submit" value="検索">
<input type="hidden" name="action" value="search"><br>
</form>
<hr>

<c:if test="${not empty Members}">
	<table border="1">
		<tr>
			<td>会員番号</td>
			<td>会員氏名</td>
			<td>パスワード</td>
			<td>郵便番号</td>
			<td>住所</td>
			<td>電話番号</td>
			<td>メールアドレス</td>
			<td>生年月日</td>
			<td>入会年月日</td>
			<td>退会年月日</td>
			<td>変更</td>
			<td>削除</td>
		</tr>
		<c:forEach items="${Members}" var="member">
			<tr>
				<td>${member.id}</td>
				<td>${member.name}</td>
				<td>${member.password}</td>
				<td>${member.postalCode}</td>
				<td>${member.address}</td>
				<td>${member.tel}</td>
				<td>${member.emailAddress}</td>
				<td>${member.birthDate}</td>
				<td>${member.joinDate}</td>
				<td>${member.quiteDate}</td>
				<td>
					<form action="/0608_problem/admin/member"
						method="post">
						<input type="hidden" name="id" value="${member.id}">
						<input type="hidden" name="name" value="${member.name}">
						<input type="hidden" name="postal_code" value="${member.postalCode}">
						<input type="hidden" name="address" value="${member.address}">
						<input type="hidden" name="email" value="${member.emailAddress}">
						<input type="hidden" name="action" value="edit">
						<input type="submit" value="変更">
					</form>
				</td>
				<td>
					<form action="/0608_problem/AdminDeleteServlet"
						method="post">
						<input type="hidden" name="id" value="${member.id}">
						<input type="hidden" name="from" value="member">
						<input type="hidden" name="action" value="kakunin">
						<input
							type="submit" value="削除">
					</form>
				</td>
			</tr>
		</c:forEach>

	</table>
</c:if>

</body>
</html>