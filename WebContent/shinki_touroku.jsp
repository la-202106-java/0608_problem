<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 会員の新規登録</title>
</head>
<body>

<jsp:include page="/menu.jsp" />

<form>
<form action="/0608_problem/LoginServlet" method="post">
	 <input type ="hidden" name ="action" value ="login">
  氏名：<input type="text" name="address"><br>
  住所：<input type="text" name="password"><br>
  電話番号：<input type="text" name="password"><br>
  E-Mail：<input type="text" name="password"><br>
  生年月日：<input type="text" name="password"><br>
  入会年月日：<input type="text" name="password"><br>
  <input type="submit" value="貸出登録">
</form>
</body>
</html>