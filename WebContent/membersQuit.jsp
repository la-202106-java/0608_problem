<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員退会ページ</title>
</head>
<body>

	<h1>会員退会受付</h1>
	<form action="/0608_problem/MembersServlet" method="get">
		会員ID：<input type="text" name="id"><br>
		<input type="submit" value="退会">
		<input type="hidden" name="action" value="quit">
	</form>

</body>
</html>