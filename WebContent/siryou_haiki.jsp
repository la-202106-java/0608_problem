<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 資料の廃棄</title>
</head>
<body>

<jsp:include page="/menu.jsp" />

<form>
<form action="/0608_problem/LoginServlet" method="post">
	 <input type ="hidden" name ="action" value ="login">
資料ID：<input type="text" name="address"><br>
資料名：<input type="text" name="address"><br>
  <input type="submit" value="廃棄">
</form>



</body>
</html>