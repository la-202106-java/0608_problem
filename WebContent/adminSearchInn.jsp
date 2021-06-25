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
<title>宿情報一覧</title>
</head>
<body>
    <div class="col-lg-8 mx-auto p-3 py-md-5">
	<jsp:include page="/WEB-INF/adminMenu.jsp" />


        <header class="d-flex align-items-center pb-3 mb-5 border-bottom">
            <div class="flex-grow-1">
		<h1>宿情報の検索</h1>
            </div>
        </header>

	<form action="/0608_problem/admin/inn" method="post">
		宿名:<input type="text" name="name" value="${name}">
		削除された項目を含める：<input type="checkbox" name="ck01" value= checked> <input
			type="submit" value="検索"> <input type="hidden" name="action"
			value="search"> <br>
	</form>
	<hr>

<c:if test="${not empty Inns}">



	<table border="1">
		<tr>
			<td>宿ID</td>
			<td>宿名</td>
			<td>分類コード</td>
			<td>郵便番号</td>
			<td>住所</td>
			<td>チェックイン時間</td>
			<td>チェックアウト時間</td>
			<td>変更</td>
			<td>削除</td>
		</tr>
		<c:forEach items="${Inns}" var="inn">
			<tr>
				<td>${inn.id}</td>
				<td>${inn.name}</td>
				<td>${inn.classCode}</td>
				<td>${inn.postalCode}</td>
				<td>${inn.address}</td>
				<td>${inn.inTime}</td>
				<td>${inn.outTime}</td>
				<td>
					<form action="/0608_problem/admin/inn"
						method="post">
						<input type="hidden" name="id" value="${inn.id}">
						<input type="hidden" name="name" value="${inn.name}">
						<input type="hidden" name="class_code" value="${inn.classCode}">
						<input type="hidden" name="postal_code" value="${inn.postalCode}">
						<input type="hidden" name="address" value="${inn.address}">
						<input type="hidden" name="inTime" value="${inn.inTime}">
						<input type="hidden" name="outTime" value="${inn.outTime}">
						<input type="hidden" name="action" value="edit">
						<input type="submit" value="変更">
					</form>
				</td>
				<td>
					<form action="/0608_problem/AdminDeleteServlet"
						method="post">
						<input type="hidden" name="id" value="${inn.name}">
						<input type="hidden" name="from" value="inn">
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