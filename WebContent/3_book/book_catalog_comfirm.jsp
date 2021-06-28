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
<title>資料登録確認</title>
</head>
<body>
<jsp:include page="/navbar.jsp" />
<div class="m-3 p-3">
<div class="col-9">

<h2>資料登録確認（新規目録）</h2>
<h4>以下の資料を登録してもよろしいでしょうか</h4>
<br>
資料名：${book.title}<br>
ISBN番号：${book.isbn}<br>
入荷年月日：${book.arrivalDate}<br>
<br>
分類コード：${catalog.code}<br>
著者：${catalog.auther}<br>
出版社：${catalog.publisher}<br>
出版日：${catalog.publicationDate}<br>

<br>
<div class="btn-toolbar">
 <div class="pr-3">
<form action="/ShinjukuLibrary/BookRegistServlet?action=bookBack" method="post">
<input type ="hidden" name = "title" value="${book.title}">
<input type ="hidden" name = "isbn" value="${book.isbn}">
<input type ="hidden" name = "arrivalDate" value="${book.arrivalDate}">
<input type ="hidden" name = "code"  value="${catalog.code}">
<input type ="hidden" name = "author" value="${catalog.auther}">
<input type ="hidden" name = "publisher" value="${catalog.publisher}">
<input type ="hidden" name = "publicationDate" value="${catalog.publicationDate}">
<input class="btn btn-secondary" type="submit" value = 戻る>
</form>
</div>
<div class="pr-3">
<form action="/ShinjukuLibrary/BookRegistServlet?action=bookRegist" method="post">
<input type ="hidden" name = "title" value="${book.title}">
<input type ="hidden" name = "isbn" value="${book.isbn}">
<input type ="hidden" name = "arrivalDate" value="${book.arrivalDate}">
<input type ="hidden" name = "code"  value="${catalog.code}">
<input type ="hidden" name = "author" value="${catalog.auther}">
<input type ="hidden" name = "publisher" value="${catalog.publisher}">
<input type ="hidden" name = "publicationDate" value="${catalog.publicationDate}">
<input class="btn btn-primary" type="submit" value = 登録>
</form>
</div>
</div>
</div>
</div>
</body>
</html>