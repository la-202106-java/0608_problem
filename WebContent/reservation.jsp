<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約</title>
</head>
<body>
<h1>予約</h1>
<img src="${plan.imgUrl}" alt="${plan.imgUrl}" width="300" height="200">
<form action="/0608_problem/ReservationServlet" method="post">
	宿名：${plan.inn.name}<br>
	宿の場所：〒${plan.inn.postalCode} ${plan.inn.address}<br>
	プラン名：${plan.content}<br>
	チェックイン：${checkIn} ${plan.inn.inTime}<br>
	チェックアウト：${checkOut} ${plan.inn.inTime}<br>
	申込部屋数：
	<select name="roomNum">
	<c:forEach var="i" begin="1" end="${plan.roomMax}" step="1"> <!-- endに予約できる部屋数の最大数を入れるように変更する -->
		<option value="${i}">${i}</option>
	</c:forEach>
	</select>
	部屋<br>
	一部屋当たりの料金：${plan.fee}円<br>
	<input type="hidden" name="action" value="confirm">
	<input type="submit" value="確定">
</form>
</body>
</html>