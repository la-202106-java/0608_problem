<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<title>資料登録</title>
</head>
<body>
<jsp:include page="/navbar.jsp" />
<div class="m-3 p-3">
<div class="col-9">

 <div class="row">
  <h2>資料登録</h2>
 </div>
<h4>新規資料情報を入力してください</h4>

${message}
<br>
<form action="/0608_problem/BookRegistServlet?action=regist" method="post">
<div class="form-group">
資料名：<input type ="text" name = "title" value="${title}" placeholder="">
</div>
<div class="form-group">
ISBN番号：<input type ="text" name = "isbn" value="${isbn}" placeholder="">
</div>
<div class="form-group">
入荷年月日：<input type ="date" name = "arrival_date" value="${arrivalDate}" placeholder="123">
</div>
<br>
<input type="submit" class="btn btn-primary" value = 登録>
</form>
</div>
</div>
</body>
</html>

