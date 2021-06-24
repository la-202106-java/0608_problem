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

<title>資料貸出</title>
</head>
<body>
  <jsp:include page="/navbar.jsp" />
  <div class="m-3 p-3">
    <div class="row">
      <h2>資料貸出</h2>
    </div>
    <div class="row">貸出をする資料IDを入力してください</div>
    <div class="row">
      <form class="form-inline" method="get" action="/0608_problem/BookLendingServlet">
        <div class="form-group">
          資料ID&nbsp;
          <input type="text" name="bookID">
          <input type="hidden" name="userID" value="${userID}">
          <input type="hidden" name="action" value="bookSearch">
          <button type="submit" class="btn btn-primary">貸出</button>
        </div>
      </form>
    </div>
    <div class="row">
      <c:if test="${!empty message}">
        <h5>
          <font color="red">${message}</font>
        </h5>
      </c:if>
    </div>

    <div class="row">
      <br> 現在貸し出している資料<br>
    </div>
    <div class="col-9 px-3">

<table class="table table-striped">
      <thead>
        <tr>
          <th>会員ID</th>
          <th>資料ID</th>
          <th>資料名</th>
          <th>返却期限</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${lendings}" var="lending">
          <tr>
            <td>${lending.userId}</td>
            <td>${lending.bookId}</td>
            <td>${lending.bookTitle}</td>
            <td>${lending.deadline}</td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</body>
</html>