<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="la.bean.PlanBean" %>
<%@ page import="la.bean.ReservationBean" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約完了</title>
</head>
<body>
<h1>予約が完了しました</h1>
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
</body>
</html>