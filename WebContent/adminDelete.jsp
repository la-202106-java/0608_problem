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
<title>警告</title>
</head>
<body>
    <div class="col-lg-8 mx-auto p-3 py-md-5">
<jsp:include page="/WEB-INF/adminMenu.jsp" />

        <header class="d-flex align-items-center pb-3 mb-5 border-bottom">
            <div class="flex-grow-1">
<h1>削除しますか。</h1>
            </div>
        </header>

<form action="/0608_problem/AdminDeleteServlet" method="post">
<input type="submit" value="はい">
<input type="hidden" name="action" value="delete"><br>
<input type="hidden" name="id" value="${id}">
<input type="hidden" name="from" value="${from}">
</form>

<br>

<form action="/0608_problem/AdminDeleteServlet" method="post">
<input type="submit" value="いいえ">
<input type="hidden" name="action" value="cancel"><br>
<input type="hidden" name="from" value="${from}">
</form>
</div>
</body>
</html>