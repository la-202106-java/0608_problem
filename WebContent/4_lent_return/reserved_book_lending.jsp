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
<title>予約取り置き資料貸出確認</title>
</head>
<body>
  <jsp:include page="/navbar.jsp" />
  <div class="m-3 p-3">
    <div class="col-9">
  <h2>予約取り置き資料貸出確認</h2>
  会員ID：${user.id} 、${user.name}さんは以下の取り置き資料があります
  <br> 現在取り置きしている資料
  <br>
  <table class="table table-striped">
    <tr>
      <th>資料ID</th>
      <th>資料名</th>
      <th>貸出</th>
    </tr>
    <c:forEach items="${books}" var="book">
      <tr>
        <td>${book.id}</td>
        <td>${book.title}</td>
        <td>
          <a class="btn btn-danger" href="/0608_problem/BookLendingServlet?action=bookSearch2&userID=${user.id}&bookID=${book.id}" role="button">貸出</a>
        </td>
      </tr>
    </c:forEach>
  </table>
  <a class="btn btn-secondary" href="/0608_problem/BookLendingServlet" role="button">戻る</a>
  <a class="btn btn-primary" href="/0608_problem/BookLendingServlet?action=userSearch2&userID=${user.id}" role="button">別の資料を貸出</a>
   </div>
  </div>
</body>
</html>