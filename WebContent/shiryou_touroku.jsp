<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 資料の新規登録</title>
</head>
<body>

<jsp:include page="/menu.jsp" />

<form>
<form action="/0608_problem/LoginServlet" method="post">
	 <input type ="hidden" name ="action" value ="login">
ISBN番号：<input type="text" name="address"><br>
  資料名：<input type="text" name="address"><br>
 入荷年月日：<input type="text" name="address"><br>
  <input type="submit" value="登録">
</form>
</body>
</html>