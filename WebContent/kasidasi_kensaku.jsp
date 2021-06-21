<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 貸出情報の検索</title>
</head>
<body>

<jsp:include page="/menu.jsp" />

<form>
<form action="/0608_problem/LoginServlet" method="post">
	 <input type ="hidden" name ="action" value ="login">
  会員ID：<input type="text" name="search"><br>

  <input type="submit" value="検索">
  <hr>

  	<h2>検索結果</h2>
</form>
</body>
</html>