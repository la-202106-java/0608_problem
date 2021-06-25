<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="la.bean.PlanBean" %>
<%@ page import="la.bean.ReservationBean" %>

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
<title>予約完了</title>
</head>
<body>
    <div class="col-lg-8 mx-auto p-3 py-md-5">

        <header class="d-flex align-items-center pb-3 mb-5 border-bottom">
            <div class="flex-grow-1">
<h1>予約が完了しました</h1>
            </div>
        </header>

<h2>予約内容</h2>
宿名：${plan.inn.name}<br>
宿の場所：〒${plan.inn.postalCode} ${plan.inn.address}<br>
プラン名：${plan.content}<br>
チェックイン：${checkIn} ${plan.inn.inTime}<br>
チェックアウト：${checkOut} ${plan.inn.inTime}<br>
申込部屋数：${reservation.room}<br>
<%
PlanBean plan = (PlanBean)session.getAttribute("plan");
ReservationBean reservation = (ReservationBean)request.getAttribute("reservation");
int total = plan.getFee() * reservation.getRoom();
%>
合計：<%= total %>円<br>
<a href="/0608_problem/ShowPlanServlet2?action=complete">Topページへ</a>
</div>
</body>
</html>