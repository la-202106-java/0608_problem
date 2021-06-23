<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
         <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 会員情報の変更</title>
</head>
<body>

<jsp:include page="/menu.jsp" />
<h2>会員情報の変更</h2>

会員ID: ${imember.id }

<form action="/0608_problem/MemberServlet" method="post">
  氏名：<input type="text" name="name" value = "${imember.name }"><br>
  住所：<input type="text" name="address"value = "${imember.address }"><br>
  電話番号：<input type="text" name="tel"value = "${imember.tel }"><br>
  E-Mail：<input type="text" name="email"value = "${imember.eMail }"><br>

  <input type="submit" value="変更">
     <input type ="hidden" name ="action" value ="update">

</form>
</body>
</html>