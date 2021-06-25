<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Topページ</title>
</head>
<body>
<h1>新宿トラベル</h1>

<c:choose>
	<c:when test = "${isLogin eq 'true'}">
		<a href="/0608_problem/ShowPlanServlet?action=logout">ログアウト</a>
		<a href="/0608_problem/membersShow.jsp">会員メニュー</a> <!-- jsp適当なので要変更 -->
	</c:when>
	<c:otherwise><a href="/0608_problem/login.jsp">ログイン</a></c:otherwise>
</c:choose>
<!--  |<a href="">会員メニュー</a> -->

<form action="/0608_problem/ShowPlanServlet" method="post">
チェックイン<input type="date"  name="checkIn" value="${checkIn}">
チェックアウト<input type="date" name="checkOut" value="${checkOut}">
<input type="hidden" name="action" value="plan">
<input type="submit" value="検索">
</form>
<hr>

<form action="/0608_problem/ShowPlanServlet" method="post">
宿名で絞り込む：<input type="text" name="innName" value="${innName}">
<input type="hidden" name="action" value="innName">
<input type="submit" value="検索">
</form>

<form action="/0608_problem/ShowPlanServlet" method="post">
宿の場所で絞り込む：<input type="text" name="place" value="${place}">
<input type="hidden" name="action" value="place">
<input type="submit" value="検索">
</form>

<form action="/0608_problem/ShowPlanServlet" method="post">
金額で絞り込む：
下限<input type="number" name="lower" value="${lower}">
上限<input type="number" name="upper" value="${upper}">
<input type="hidden" name="action" value="fee">
<input type="submit" value="検索">
</form>

<c:forEach items="${plans}" var="plan" varStatus="stat">
	<form action="/0608_problem/ReservationServlet" method="post">
	<input type="hidden" name="action" value="reservation">
	<input type="hidden" name="planCount" value="${stat.count}">
	<hr>
	<table>
	<tr>
	<td><img src="${plan.imgUrl}" alt="${plan.content}" width="300" height="200"></td>
	<td>${plan.inn.name} ${plan.content}<br>
		〒${plan.inn.postalCode} ${plan.inn.address}<br>
		一部屋当たり　${plan.fee}円<br>
		<input type="submit" value="予約"></td>
	</tr>
	</table>
	</form>
</c:forEach>

</body>
</html>