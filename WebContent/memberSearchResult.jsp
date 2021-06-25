<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
<title>教科書売買サイト</title>
</head>
<body>


	<h1>会員検索結果</h1>
	<p>${result_num}件見つかりました。</p>

	<table>
	<c:forEach items="${member}" var="person">

		<tr>
			<td><small>ID ${person.id}</small><br>
				<b>${person.name}</b></td>
			<td><c:if test="${person.quitDate!=null}">退会済み</c:if></td>
			<td><form action="/0608_problem/MemberDetailServlet" method = "post">
				<input type="hidden" name="member_id" value="${person.id}">
				<input type="submit" value="詳細">
				</form>
			</td>
			<td>
				<form action="/0608_problem/MemberQuitServlet" method = "post">
				<input type="hidden" name="member_id" value="${person.id}">
				<c:if test="${person.quitDate==null}">
					<input type="submit" value="退会">
				</c:if>
				<c:if test="${person.quitDate!=null}">
					<input type="submit" value="退会" disabled="disabled">
				</c:if>
				</form>
			</td>
		</tr>
		<tr>
			<td>${person.email}<br>${person.tel}</td>
			<td><small>入会年月日</small> ${person.joinDate}<br>
				<small>退会年月日</small> ${person.quitDate}
			</td>
		</tr>

	</c:forEach>
	</table>

</body>
</html>