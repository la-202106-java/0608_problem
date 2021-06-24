<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>資料貸出</title>
</head>
<body>
<h2>資料貸出</h2>
貸出をする資料IDを入力してください<br>
<form method="get" action="/0608_problem/BookLendingServlet">
資料ID <input type ="text" name = "bookID">
<input type ="hidden" name = "userID" value="${userID}">
<input type="hidden" name="action" value="bookSearch">
<input type="submit" value = 貸出>
</form>

<c:if test="${!empty message}">
     <h5><font color="red">${message}</font></h5>
</c:if>

<br>
現在貸し出している資料<br>
<table border="1">
    <tr>
      <th>会員ID</th>
      <th>資料ID</th>
      <th>資料名</th>
      <th>返却期限</th>
    </tr>
<c:forEach items="${lendings}" var="lending">
          <tr>
            <td>${lending.userId}</td>
            <td>${lending.bookId}</td>
            <td>${lending.bookTitle}</td>
            <td>${lending.deadline}</td>
          </tr>
</c:forEach>
  </table>
</body>
</html>