<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
  crossorigin="anonymous">
<!DOCTYPE html>
<html>
<head>
<title>会員検索</title>
</head>
<body>
<jsp:include page="/navbar.jsp" />
<div class="m-3 p-3">
    <div class="col-9">
		<h2>会員検索</h2>
		<form class="form-inline" action = "/0608_problem/UserSearchServlet" method = "post">
		email：
		<input type ="email" name = "email">
		<button class="btn btn-primary" type="submit">検索</button>
		<input type="hidden" name = "action" value = "search">
		</form>
	</div>
</div>
</body>
</html>