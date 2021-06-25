<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>会員情報管理</title>
</head>
<body>
    <div class="col-lg-8 mx-auto p-3 py-md-5">

        <header class="d-flex align-items-center pb-3 mb-5 border-bottom">
            <div class="flex-grow-1">
<h3>ようこそ、${member.name}さん</h3>
            </div>
        </header>

<a href="/0608_problem/top2.jsp">検索メニューへ</a>
	<jsp:include page="/memberUpdateLink.jsp" />
	<hr>
	<jsp:include page="/memberQuitLink.jsp" />
	<hr>
	<jsp:include page="/memberReservationLog.jsp" />
	<hr>


	<!--
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
				<td>${member.quiteDate}</td>
			</tr>
		</c:forEach>

	</table>
	-->
</div>
</body>
</html>