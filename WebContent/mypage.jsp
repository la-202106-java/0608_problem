<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/menu.jsp" /><br>

<table border="1">
<tr><td>お名前</td><td>${loginedplusa.name }</td></tr>
<tr><td>住所</td><td>${loginedplusa.address }</td></tr>
<tr><td>電話番号</td><td>${loginedplusa.tel }</td></tr>
<tr><td>メールアドレス</td><td>${loginedplusa.email }</td></tr>
<tr><td>生年月日</td><td>${loginedplusa.birthday }</td></tr>
<tr><td>売上金額</td><td>${loginedplusa.sales }</td></tr>
</table>

<form action="/0608_problem/LeaveServlet" method="post">
<input type="submit" value="退会">
</form>

<form action="/0608_problem/MemberRegistChangeServlet" method="post">
<input type="hidden" name="action" value="change">
<input type="submit" value="会員情報変更">
</form>




</body>
</html>