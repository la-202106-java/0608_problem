<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="menu.jsp"></jsp:include>

<h1>本当に退会しますか？</h1>

<div style="display:inline-flex">
<form action="/0608_problem/LeaveServlet" method= "post">
<input type="submit" value="キャンセル">
<input type="hidden" name = "action" value="cancel">
</form>

<form action="/0608_problem/LeaveServlet" method= "post">
<input type="submit" value="退会する">
<input type="hidden" name = "action" value="doleave">
</form>
</div>

</body>
</html>