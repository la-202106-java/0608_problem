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
		<input type="submit" value="退会する">
		<input type="hidden" name="action" value="quit">
	</form>

<br>
	<form action="/0608_problem/membersShow.jsp" method="get">
		<input type="submit" value="退会しない">
	</form>

</body>
</html>