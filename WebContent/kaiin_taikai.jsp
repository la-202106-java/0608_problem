<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 会員の退会</title>
</head>
<body>

<jsp:include page="/menu.jsp" />
<h2>会員の退会</h2>
<form action="/0608_problem/Servlet" method="post">

会員ID：<input type="text" name="address"><br>
  <input type="submit" value="退会">
  	 <input type ="hidden" name ="action" value ="delete">
</form>
</body>
</html>