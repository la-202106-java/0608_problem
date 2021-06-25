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
<title>取置</title>
</head>
<body>
<jsp:include page="/navbar.jsp" />
<div class="m-3 p-3">
    <div class="col-9">
		<h2>取置</h2>
		<c:if test="${not empty error}">
			<div class="alert alert-danger" role="alert">
			  ${error}
			</div>
		</c:if>
		以下の資料を取り置きします<br>
		会員IDを入力してください<br>
		<br>資料ID：${book_id}<br>
		資料名：${title}<br>

		<form method="post">

		会員ID <input type ="text" name = "user_id"><br>

		<button type="button" class="btn btn-secondary" onclick="history.back()">戻る</button>
		<button type="submit" class="btn btn-primary" formaction="/0608_problem/CatalogReservedServlet">取置</button>
		<input type="hidden" name="action" value="reserved_confirm">
		<input type="hidden" name="book_id" value="${book_id}">
		<input type="hidden" name="title" value="${title}">
		</form>
	</div>
</div>

</body>
</html>