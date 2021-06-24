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

<title>貸出履歴</title>
</head>
<body>
<jsp:include page="/navbar.jsp" />
<div class="m-3 p-3">
<div class="row">
   <h2>貸出履歴</h2>
</div>

<form class="form-inline" action="/0608_problem/LendingSearchServlet?action=book" method="post">
<div class="form-group">
資料ID&nbsp; <input type ="text" name = "book_id">
<input type="submit" value = 検索>
</div>
</form>

<br>
<form class="form-inline" action="/0608_problem/LendingSearchServlet?action=user" method="post">
<div class="form-group">
会員ID&nbsp;<input type ="text" name = "user_id">
<input type="submit" value = 検索>
<input type="checkbox" name="check">返却済みも含む
</div>
</form>

<br>
<form action="/0608_problem/LendingSearchServlet?action=overdue" method="post">
<input type="submit" value = 延滞資料一覧>
</form>


</div>

<table border="1">
    <tr>
      <th>貸出ID</th>
      <th>会員ID</th>
      <th>資料ID</th>
      <th>貸出年月日</th>
      <th>返却期限（年月日）</th>
      <th>返却年月日</th>
      <th>備考</th>
    </tr>
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
  </table>

</body>
</html>