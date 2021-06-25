<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報更新</title>
</head>
<body>
<jsp:include page="/WEB-INF/adminMenu.jsp" />
<h1>会員の更新</h1>
<form action="/0608_problem/admin/member" method="get">

会員ID：${memberTarget.id}<br>
会員氏名：<input type="text" name="name" value="${memberTarget.name}"><br>
郵便番号：〒<input type="text" name="postal_code" value="${memberTarget.postalCode}"><br>
住所：<input type="text" name="address" value="${memberTarget.address}"><br>
e-mail：<input type="text"name ="email" value="${memberTarget.emailAddress}"><br>
電話番号：<input type="text"name ="tel" value="${memberTarget.tel}"><br>
<input type="submit" value="更新">
<input type="hidden" name="action" value="update">
<input type="hidden" name="id" value="${memberTarget.id}">
</form>
</body>
</html>