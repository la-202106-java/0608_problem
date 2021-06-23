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
<title>資料返却確認</title>
</head>
<body>
  <jsp:include page="/navbar.jsp" />
  <div class="m-3 p-3">
    <div class="col-9">
      <h2>資料返却確認</h2>
      <h4>以下の情報で返却してよろしいですか？</h4>
      <br>
      会員番号：${user.id}<br>
      氏名：${user.name}<br>
      資料ID：${book.id}<br>
      資料名：${book.title}<br>
      貸出日：${lending.lendingDate}<br> <br>
      <a class="btn btn-secondary" href="/0608_problem/BookReturningServlet" role="button">戻る</a>
      <a class="btn btn-danger" href="/0608_problem/BookReturningServlet?action=confirm&id=${book.id}" role="button">確認</a>
    </div>
  </div>
</body>
</html>