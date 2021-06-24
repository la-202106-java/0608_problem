<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>警告</title>
</head>
<body>
<jsp:include page="/WEB-INF/adminMenu.html" />
<h1>削除しますか。</h1>
<form action="/0608_problem/AdminDeleteServlet" method="post">
<input type="submit" value="はい">
<input type="hidden" name="action" value="delete"><br>
<input type="hidden" name="id" value="${id}">
<input type="hidden" name="from" value="${from}">
</form>

<br>

<form action="/0608_problem/AdminDeleteServlet" method="post">
<input type="submit" value="いいえ">
<input type="hidden" name="action" value="cancel"><br>
<input type="hidden" name="from" value="${from}">
</form>

</body>
</html>