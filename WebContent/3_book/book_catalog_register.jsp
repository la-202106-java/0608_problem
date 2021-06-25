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
<title>資料登録</title>
</head>
<body>
<jsp:include page="/navbar.jsp" />
<div class="m-3 p-3">
<div class="col-9">

<h2>資料登録（新規目録）</h2>
<h4>新規資料情報、目録情報を入力してください</h4>

${message}
<c:if test="${empty code}" >
<form action="/0608_problem/BookRegistServlet?action=bookConfirm" method="post">
<div class="form-group">
資料名：<input type ="text" name = "title" value="${title}"><br>
</div>
<div class="form-group">
ISBN番号：<input type ="text" name = "isbn" value="${isbn}"><br>
</div>
<div class="form-group">
入荷年月日：<input type ="date" name = "arrivalDate" value="${arrivalDate}"><br>
</div>
<br>
<div class="form-group">
分類コード：<input type ="number" name = "code"><br>
</div>
<div class="form-group">
著者：<input type ="text" name = "author"><br>
</div>
<div class="form-group">
出版社：<input type ="text" name = "publisher"><br>
</div>
<div class="form-group">
出版日：<input type ="date" name = "publicationDate"><br>
</div>
<br>
<input class="btn btn-primary" type="submit" value = 登録>
</form>
</c:if>

<c:if test="${!empty code}" >
<form action="/0608_problem/BookRegistServlet?action=bookConfirm" method="post">
<div class="form-group">
資料名：<input type ="text" name = "title" value="${title}"><br>
</div>
<div class="form-group">
ISBN番号：<input type ="text" name = "isbn" value="${isbn}"><br>
</div>
<div class="form-group">
入荷年月日：<input type ="date" name = "arrivalDate" value="${arrivalDate}"><br>
</div>
<br>
<div class="form-group">
分類コード：<input type ="number" name = "code" value="${code}"><br>
</div>
<div class="form-group">
著者：<input type ="text" name = "author" value="${author}"><br>
</div>
<div class="form-group">
出版社：<input type ="text" name = "publisher" value="${publisher}"><br>
</div>
<div class="form-group">
出版日：<input type ="date" name = "publicationDate" value="${publicationDate}"><br>
</div>
<br>
<input class="btn btn-primary" type="submit" value = 登録>
</form>
</c:if>

</div>
</div>
</body>
</html>

