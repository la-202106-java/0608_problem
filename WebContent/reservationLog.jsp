<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約履歴</title>
</head>
<body>
<c:if test="${not empty message}">
${message}
</c:if>
<h1>予約一覧</h1>

<c:if test="${empty Reservations}">
<h3>予約はありません</h3>
</c:if>

<c:if test="${not empty Reservations}">
<h2>予約履歴</h2>
<c:forEach items="${Reservations}" var="res" varStatus="stat">
<h3>予約：${stat.count}</h3>
宿名：${res.plan.inn.name}<br>
宿の住所：${res.plan.inn.address}<br>
プラン内容：${res.plan.content}<br>
チェックイン日：${res.inDate} チェックイン時間：${res.plan.inn.inTime}<br>
チェックアウト日：${res.outDate} チェックアウト時間：${res.plan.inn.outTime}<br>
部屋数：${res.room}<br>
料金：${res.plan.fee}円×${res.room}部屋
<form action="/0608_problem/MembersServlet"
						method="post">
						<input type="hidden" name="id" value="${res.id}">
						<input type="hidden" name="action" value="yoyakucancel">
						<input
							type="submit" value="キャンセル">
					</form>
</c:forEach>
</c:if>
<br>
<a href="/0608_problem/membersShow.jsp">会員メニューへ</a>

</body>
</html>