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
<title>貸出履歴</title>
</head>
<body>
  <jsp:include page="/navbar.jsp" />
  <div class="m-3 p-3">
    <div class="col-9">
      <h2>貸出履歴</h2>
      <div class="form-group">
      <div style="display:inline-flex">
      <div class="pr-3">
        <form class="form-inline" action="/ShinjukuLibrary/LendingSearchServlet?action=book" method="post">
          資料ID&nbsp;
          <input type="text" name="book_id">
          <input type="submit" value=検索>
        </form>
        </div>
        <form class="form-inline" action="/ShinjukuLibrary/LendingSearchServlet?action=overdue" method="post">
          <button type="submit" class="btn btn-danger">延滞資料一覧</button>
        </form>
      </div>
      </div>

      <div class="form-group">
        <form class="form-inline" action="/ShinjukuLibrary/LendingSearchServlet?action=user" method="post">

          会員ID&nbsp;
          <input type="text" name="user_id">
          <input type="submit" value=検索>
          &nbsp;
          <input type="checkbox" name="check">
          返却済みも含む
        </form>
      </div>




    </div>

    <div class="col-9 px-3">
      <table class="table table-striped">
        <thead>
          <tr>
            <th>貸出ID</th>
            <th>会員ID</th>
            <th>資料ID</th>
            <th>貸出年月日</th>
            <th>返却期限（年月日）</th>
            <th>返却年月日</th>
            <th>備考</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${books}" var="book">
            <tr>
              <td>${book.id}</td>
              <td>${book.userId}</td>
              <td>${book.bookId}</td>
              <td>${book.lendingDate}</td>
              <td>${book.deadline}</td>
              <td>${book.returnDate}</td>
              <td>${book.note}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</body>
</html>