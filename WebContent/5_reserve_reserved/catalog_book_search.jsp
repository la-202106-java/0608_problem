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

<title>資料検索</title>
</head>
<body>
<jsp:include page="/navbar.jsp" />
<div class="m-3 p-3">
    <div class="col-9">
		<h2>資料検索</h2>
		<c:if test="${not empty message}">
		<p class="">${message}</p>
		</c:if>

		<div class="container pt-3">
			<div class="row">
				<p>取り置きできる${bookList.size()}件の資料が見つかりました<br>
				手元にある資料IDを確認し、取り置く資料を選択してください</p>
			</div>
		</div>
	</div>
</div>
<div class="m-3 p-3">
	<div class="col-9 px-3">
	   <table class="table table-striped">
	      <thead>
		    <tr>
		      <th>貸出ID</th>
		      <th>資料名</th>
		      <th>取置</th>
		    </tr>
		    </thead>
		    <tbody>
		    <c:forEach items="${bookList}" var="book">
			    <tr>
			      <td>${book.id}</td>
			      <td>${book.title}</td>
			      <td>
			        <form action="/ShinjukuLibrary/CatalogReservedServlet" method="post">
			          <button type="submit" class="btn btn-secondary">取置</button>
			          <input type="hidden" name="book_id" value="${book.id}">
			          <input type="hidden" name="title" value="${book.title}">
			          <input type="hidden" name="action" value="confirm">
			        </form>
			      </td>
			    </tr>
			</c:forEach>
			</tbody>
	  </table>
	</div>
</div>
</body>
</html>