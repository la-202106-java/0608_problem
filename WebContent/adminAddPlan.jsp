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
<title>プラン情報追加</title>
</head>
<body>
    <div class="col-lg-8 mx-auto p-3 py-md-5">
<jsp:include page="/WEB-INF/adminMenu.jsp" />


        <header class="d-flex align-items-center pb-3 mb-5 border-bottom">
            <div class="flex-grow-1">
<h1>プランの追加</h1>
            </div>
        </header>

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
</div>
</body>
</html>