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
<title>予約</title>
</head>
<body>
    <div class="col-lg-8 mx-auto p-3 py-md-5">

        <header class="d-flex align-items-center pb-3 mb-5 border-bottom">
            <div class="flex-grow-1">
<h1>予約</h1>
            </div>
        </header>

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
</div>
</body>
</html>