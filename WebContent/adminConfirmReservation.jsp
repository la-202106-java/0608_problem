<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>確認画面</title>
</head>
<body>
<jsp:include page="/WEB-INF/adminMenu.jsp" />

<h1>${message}</h1>

<c:if test="${not empty Reservations}">
紐づいている予約

	<table border="1">
		<tr>
			<td>宿名</td>
			<td>プラン内容</td>
			<td>チェックイン日</td>
			<td>チェックアウト日</td>
			<td>会員氏名</td>
			<td>会員郵便番号</td>
			<td>会員住所</td>
			<td>会員電話番号</td>
			<td>会員メールアドレス</td>
			<td>確認</td>
			<td>キャンセル</td>
		</tr>
		<c:forEach items="${Reservations}" var="res" varStatus="stat">
			<tr>
				<td>${res.plan.inn.name}</td>
				<td>${res.plan.content}</td>
				<td>${res.inDate}</td>
				<td>${res.outDate}</td>
				<td>${res.member.name}</td>
				<td>${res.member.postalCode}</td>
				<td>${res.member.address}</td>
				<td>${res.member.tel}</td>
				<td>${res.member.emailAddress}</td>
				<td>
					<form action="/0608_problem/AdminDeleteServlet"
						method="post">
						<input type="hidden" name="rlist" value="${Reservations}">
						<input type="hidden" name="id" value="${res.id}">
						<input type="hidden" name="no" value="${stat.count}">
						<input type="hidden" name="action" value="yoyakuok">
						<input type="submit" value="確認">
					</form>
				</td>
				<td>
					<form action="/0608_problem/AdminDeleteServlet"
						method="post">
						<input type="hidden" name="rlist" value="${Reservations}">
						<input type="hidden" name="id" value="${res.id}">
						<input type="hidden" name="no" value="${stat.count}">
						<input type="hidden" name="action" value="yoyakucancel">
						<input
							type="submit" value="キャンセル">
					</form>
				</td>
			</tr>
		</c:forEach>

	</table>
</c:if>

</body>
</html>