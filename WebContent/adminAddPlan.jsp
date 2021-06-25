<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>プラン情報追加</title>
</head>
<body>
<jsp:include page="/WEB-INF/adminMenu.jsp" />

<h1>プランの追加</h1>
<c:if test="${empty Inns}">
プランを追加できる宿が存在しません
</c:if>

<c:if test="${not empty Inns}">
<form action="/0608_problem/admin/plan" method="get">
宿名：
<select name="id">
<c:forEach items="${Inns}" var="inn">
<option value="${inn.id}">${inn.name}</option>
</c:forEach>
</select><br>
プラン内容：<input type="text" name="contents"><br>
金額：<input type="number" name="fee">円<br>
部屋数：<input type="number" name="room"><br>
画像：<input type="text"name ="picture"><br>
<input type="submit" value="追加">
<input type="hidden" name="action" value="add">
</form>
</c:if>
</body>
</html>