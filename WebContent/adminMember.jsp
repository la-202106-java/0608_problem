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
<title>会員情報一覧</title>
</head>
<body>
    <div class="col-lg-8 mx-auto p-3 py-md-5">
<jsp:include page="/WEB-INF/adminMenu.jsp" />


        <header class="d-flex align-items-center pb-3 mb-5 border-bottom">
            <div class="flex-grow-1">
<h1>会員情報の検索</h1>
            </div>
        </header>

<form action="/0608_problem/admin/member" method="post">
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
						<input type="hidden" name="tel" value="${member.tel}">
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
</div>
</body>
</html>