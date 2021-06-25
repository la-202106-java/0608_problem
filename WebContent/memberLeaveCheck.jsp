<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
<title>Insert title here</title>
</head>
<body>
<c:if test="${user == 'admin'}">
<jsp:include page="/adminMenu.jsp" />
</c:if>
<c:if test="${user != 'admin'}">
<jsp:include page="/menu.jsp" />
</c:if>

<h1>本当に退会しますか？</h1>

<!-- キャンセルするボタン(LeaveServlet)-->
<div style="display:inline-flex">
<form action="/0608_problem/LeaveServlet" method= "post">
<input type="hidden" name="member_id" value="${leavemember.id}">
<input type="submit" value="キャンセル">
<input type="hidden" name = "action" value="cancel">
</form>

<!-- 退会するボタン(LeaveServlet) -->
<form action="/0608_problem/LeaveServlet" method= "post">
<input type="hidden" name="member_id" value="${leavemember.id}">
<input type="submit" value="退会する">
<input type="hidden" name = "action" value="doleave">
</form>
</div>

</body>
</html>