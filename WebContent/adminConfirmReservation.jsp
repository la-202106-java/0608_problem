<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<title>確認画面</title>
</head>
<body>
    <div class="col-lg-8 mx-auto p-3 py-md-5">
<jsp:include page="/WEB-INF/adminMenu.jsp" />


        <header class="d-flex align-items-center pb-3 mb-5 border-bottom">
            <div class="flex-grow-1">
<h1>${message}</h1>
            </div>
        </header>


<c:if test="${not empty Reservations}">
削除されたプランに紐づいている予約

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
</div>
</body>
</html>