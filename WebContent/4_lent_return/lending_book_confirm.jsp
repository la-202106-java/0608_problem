<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
  crossorigin="anonymous">

<title>資料貸出確認</title>
</head>
<body>
  <jsp:include page="/navbar.jsp" />
  <div class="m-3 p-3">
    <div class="row">
      <h2>資料貸出確認</h2>
      以下の情報で貸し出します<br> よろしいですか？<br>
    </div>
    <div class="row">
      以下の情報で貸し出します。
    </div>
    <div class="row">
      よろしいですか？
    </div>
    会員番号：${user.id}<br> 氏名：${user.name}<br> 資料ID:${book.id}<br> 資料名：${book.title}<br>

    <form method="get" action="/0608_problem/BookLendingServlet">
      <input type="hidden" name="bookID" value="${book.id}">
      <input type="hidden" name="userID" value="${user.id}">
      <input type="hidden" name="action" value="finish">
      <input type="submit" value=貸出完了>
    </form>

    <form method="get" action="/0608_problem/BookLendingServlet">
      <input type="hidden" name="bookID" value="${book.id}">
      <input type="hidden" name="userID" value="${user.id}">
      <input type="hidden" name="action" value="continue">
      <input type="submit" value=貸出続行>
    </form>
    </div>
</body>
</html>