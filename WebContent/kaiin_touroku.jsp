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
<h2>会員の新規登録</h2>

<form action="/0608_problem/MemberServlet?action=confirm"method="post">

  氏名：<input type="text" name="name"><br>
  住所：<input type="text" name="address"><br>
  電話番号：<input type="text" name="tel"><br>
  E-Mail：<input type="text" name="mail"><br>
  生年月日：<input type="text" name="birth"><br>
  <input type="submit" value="登録確認">
  <input type="hidden" name="action" value="confirm">
</form>
</body>
</html>