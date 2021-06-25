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
<title>プラン情報一覧</title>
</head>
<body>
    <div class="col-lg-8 mx-auto p-3 py-md-5">
	<jsp:include page="/WEB-INF/adminMenu.jsp" />


        <header class="d-flex align-items-center pb-3 mb-5 border-bottom">
            <div class="flex-grow-1">
		<h1>プラン情報の検索</h1>
            </div>
        </header>

	<form action="/0608_problem/admin/plan" method="post">
		宿名:<input type="text" name="name" value="${name}"><br>
		削除された項目を含める：<input type="checkbox" name="ck01" value= checked> <input
			type="submit" value="検索"> <input type="hidden" name="action"
			value="search"> <br>
	</form>
	<hr>

<c:if test="${not empty Plans}">
	<table border="1">
		<tr>
			<td>プランID</td>
			<td>宿名</td>
			<td>プラン内容</td>
			<td>金額</td>
			<td>部屋数</td>
			<td>画像URL</td>
			<td>削除年月日</td>
			<td>変更</td>
			<td>削除</td>
		</tr>
		<c:forEach items="${Plans}" var="plan">
			<tr>
				<td>${plan.planId}</td>
				<td>${plan.inn.name}</td>
				<td>${plan.content}</td>
				<td>${plan.fee}</td>
				<td>${plan.roomMax}</td>
				<td>${plan.imgUrl}</td>
				<td>${plan.deleteDate}</td>
				<td>
					<form action="/0608_problem/admin/plan"
						method="post">
						<input type="hidden" name="planid" value="${plan.planId}">
						<input type="hidden" name="id" value="${plan.inn.id}">
						<input type="hidden" name="contents" value="${plan.content}">
						<input type="hidden" name="fee" value="${plan.fee}">
						<input type="hidden" name="room" value="${plan.roomMax}">
						<input type="hidden" name="picture" value="${plan.imgUrl}">
						<input type="hidden" name="action" value="edit">
						<input type="submit" value="変更">
					</form>
				</td>
				<td>
					<form action="/0608_problem/AdminDeleteServlet"
						method="post">
						<input type="hidden" name="id" value="${plan.planId}">
						<input type="hidden" name="from" value="plan">
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