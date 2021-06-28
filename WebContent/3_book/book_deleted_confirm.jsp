<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
  crossorigin="anonymous">

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>資料削除確認</title>
</head>
<body>
  <jsp:include page="/navbar.jsp" />
  <div class="m-3 p-3">
    <div class="col-9">
      <h2>資料削除確認</h2>
      <h4>以下の資料を削除してよろしいですか？</h4>
      <br>
      資料ID：${book.id}<br> ISBN番号：${book.id}<br> 資料名：${book.title}<br> 入荷日：${book.arrivalDate}<br> <br>
      <a class="btn btn-secondary" href="/ShinjukuLibrary/BookSearchServlet?action=search_id&bookID=${book.id}" role="button">戻る</a>
      <a class="btn btn-danger" href="/ShinjukuLibrary/BookDeleteServlet?action=delete&id=${book.id}" role="button">削除</a>
    </div>
  </div>
</body>
</html>