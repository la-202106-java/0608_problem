<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>宿情報一覧</title>
</head>
<body>
	<jsp:include page="/WEB-INF/adminMenu.html" />

	<form action="/0608_problem/admin/inn" method="post">
		<h1>宿情報の検索</h1>
		宿名:<input type="text" name="name" value="${name}"> <input
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
					<form action="/0608_problem/AdminInnServlet?action=delete"
						method="post">
						<input type="hidden" name="name" value="${inn.name}"> <input
							type="submit" value="削除">
					</form>
				</td>
			</tr>
		</c:forEach>

	</table>
</c:if>

</body>
</html>