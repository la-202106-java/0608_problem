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


<title>資料返却</title>
</head>
<body>
  <jsp:include page="/navbar.jsp" />
 <div class="m-3 p-3">
    <div class="col-9">
    <div class="row">
      <h2>資料返却</h2>
    </div>
    <div class="row">
    <h5>返却をする資料IDを入力してください</h5><br>
    </div>
    <div class="row">
      <form class="form-inline" action="/0608_problem/BookReturningServlet" method="get">
        <div class="form-group">
          資料ID&nbsp;
          <input type="text" name="bookID" class="form-control" id="InputID" placeholder="123">
          <input type="hidden" name="action" value="search">
          <button type="submit" class="btn btn-primary">返却</button>
        </div>
      </form>
    </div>
    <div class="row">
     <c:if test="${!empty message}">
        <h5><font color="red">${message}</font></h5>
      </c:if>
    </div>
  </div>
  </div>

</body>
</html>