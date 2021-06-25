<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<title>資料検索</title>
</head>
<body>
  <jsp:include page="/navbar.jsp" />
  <div class="m-3 p-3">
    <div class="row">
      <h2>資料検索</h2>
    </div>
    <div class="row">
      <form class="form-inline" action="/0608_problem/BookSearchServlet" method="get">
        <div class="form-group">
          資料ID&nbsp;
          <input type="text" name="bookID" class="form-control" id="InputID" placeholder="123">
          <input type="hidden" name="action" value="search_id">
          <button type="submit" class="btn btn-primary">検索</button>
        </div>
      </form>
      <form class="form-inline" action="/0608_problem/BookSearchServlet" method="get">
        <div class="form-group">
          &nbsp;ISBN番号&nbsp;
          <input type="text" name="isbn" class="form-control" id="InputISBN" placeholder="9784111111111">
          <input type="hidden" name="action" value="search_isbn">
          <button type="submit" class="btn btn-primary">検索</button>
        </div>
      </form>
      <form class="form-inline" action="/0608_problem/BookSearchServlet" method="get">
        <div class="form-group">
          &nbsp;資料名&nbsp;
          <input type="text" name="title" class="form-control" id="InputTitle" placeholder="よい子の">
          <input type="hidden" name="action" value="search_title">
          <button type="submit" class="btn btn-primary">検索</button>
        </div>
      </form>
    </div>
  </div>
  <div class="m-3 p-3">
    <div class="row">
      <c:if test="${!empty message}">
        <h3>${message}</h3>
      </c:if>
    </div>
  </div>
  <div class="col-9 px-3">
    <table class="table table-striped">
      <thead>
        <tr>
          <td>ID</td>
          <td>ISBN番号</td>
          <td>資料名</td>
          <td>入荷日</td>
          <td>状態</td>
          <td>備考</td>
          <td>削除</td>
        </tr>
      </thead>

      <tbody>
        <c:forEach items="${books}" var="book">
          <tr>
            <td>${book.id}</td>
            <td>${book.isbn}</td>
            <td>${book.title}</td>
            <td>${book.arrivalDate}</td>
            <td>

            <c:if test="${book.status=='在庫'}">
            <span class="badge badge-pill badge-info">在庫</span>
            </c:if>
            <c:if test="${book.status=='貸出'}">
            <span class="badge badge-pill badge-danger">貸出</span>
            </c:if>
            <c:if test="${book.status=='取置'}">
            <span class="badge badge-pill badge-secondary">取置</span>
            </c:if>

            </td>
            <td>${book.note}</td>
            <td>
              <a class="btn btn-danger" href="/0608_problem/BookDeleteServlet?&id=${book.id}" role="button">削除</a>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</body>
</html>