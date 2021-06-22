<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 貸出情報の登録</title>
</head>
<body>

<jsp:include page="/menu.jsp" />
<h2>貸出情報の登録</h2>

<form action="/0608_problem/KasidasiServlet?action=add" method="post">
	 <input type ="hidden" name ="action" value ="login">

  会員ID：<input type="text" name="mid"><br>
  資料ID：<input type="text" name="sid"><br>
  <input type="submit" value="登録">


</form>
</body>
</html>