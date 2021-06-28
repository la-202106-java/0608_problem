<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約一覧</title>
</head>
<body>
<jsp:include page="/adminConfirmReservation.jsp" />

<header class="d-flex align-items-center pb-3 mb-5 border-bottom">
            <div class="flex-grow-1">
		<h1>予約情報の検索</h1>
            </div>
        </header>

	<form action="/0608_problem/AdminDeleteServlet" method="post">
		会員名:<input type="text" name="name" value="${name}"> <input
			type="submit" value="検索"> <input type="hidden" name="action"
			value="searchReservation"> <br>
	</form>
	<hr>
<c:if test="${not empty ReservationsByName}">

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
			<td>キャンセル</td>
		</tr>
		<c:forEach items="${ReservationsByName}" var="res">
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
						<input type="hidden" name="id" value="${res.id}">
						<input type="hidden" name="action" value="reservationCancel">
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