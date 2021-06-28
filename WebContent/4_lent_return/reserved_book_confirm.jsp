<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
  crossorigin="anonymous">

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>資料取置確認</title>
</head>
<body>
  <jsp:include page="/navbar.jsp" />
  <div class="m-3 p-3">
    <div class="col-9">

      <h2>資料取置確認</h2>
      <h5>
      以下の予約があります。<br> 取り置いたら完了ボタンを押してください<br>
      </h5>

      会員番号：${top.id}<br>
      氏名：${top.name}<br>
      資料ID:${book.id}<br>
      資料名：${book.title}<br>
      <a class="btn btn-danger" href="/0608_problem/BookReturningServlet?action=reserve&id=${book.id}&userID=${top.id}" role="button">取置完了</a>
    </div>
  </div>
</body>
</html>