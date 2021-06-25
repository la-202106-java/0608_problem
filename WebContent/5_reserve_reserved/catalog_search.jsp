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

<title>目録検索</title>
</head>
<body>
<jsp:include page="/navbar.jsp" />
<div class="m-3 p-3">
    <div class="col-9">
		<h2>目録検索</h2>
		<c:if test="${not empty message}">
		<p class="">${message}</p>
		</c:if>
		<form action="/0608_problem/CatalogSearchServlet" method="post">
		資料名 <input type ="text" name = "titles" value="${titles}"><input type="submit" value = 検索>
		</form>

		<c:if test="${result.size() > 0}">

		${result.size()}件の資料が検索されました<br>
	</div>
</div>
<div class="m-3 p-3">
	<div class="col-9 px-3">
	   <table class="table table-striped">
	      <thead>
		    <tr>
		      <th>貸出名</th>
		      <th>ISBN</th>
		      <th>状態</th>
		      <th>予約/取置</th>
		    </tr>
		    </thead>
		    <tbody>
		    <c:forEach items="${result}" var="item">
			    <tr>
			      <td>${item.title}</td>
			      <td>${item.isbn}</td>
			      <td>在庫${item.stockCount}</td>
			      <c:if test="${item.stockCount eq 0}">
				      <td>
				        <form action="/0608_problem/CatalogReserveServlet" method="post">
				          <button type="submit" class="btn btn-primary">予約</button>
				          <input type="hidden" name="isbn" value="${item.isbn}">
				          <input type="hidden" name="title" value="${item.title}">
				          <input type="hidden" name="action" value="reserve">
				        </form>
				      </td>
			      </c:if>
			      <c:if test="${item.stockCount ne 0}">
				      <td><input type="submit" value ="取置"></td>
			      </c:if>
			    </tr>
			</c:forEach>
			</tbody>
		  </table>
		</c:if>
	</div>
</div>
</body>
</html>