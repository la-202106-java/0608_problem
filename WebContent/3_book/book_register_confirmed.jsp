<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
  crossorigin="anonymous">

<!-- FontAwesome CSS -->
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />
<title>資料登録完了</title>
</head>
<body>
<jsp:include page="/navbar.jsp" />
<div class="m-3 p-3">
<div class="col-9">

<div class="row">
<h2>資料登録完了</h2>
</div>
<div class="row">
<h4>以下の資料登録が完了しました</h4>
</div>
<br>
資料ID：${id}<br>
資料名：${title}<br>
ISBN番号：${isbn}<br>
入荷年月日：${arrivalDate}<br>
<br>
<div class="btn-toolbar">
<div class="pr-3">
<a href="/0608_problem/3_book/book_register.jsp">登録続行</a>
</div>
<a href="/0608_problem/TopServlet">終了</a>
</div>
</div>
</div>
</body>
</html>


