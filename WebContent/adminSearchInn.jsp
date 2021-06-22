<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>宿情報一覧</title>
</head>
<body>
<jsp:include page="/WEB-INF/adminMenu.html" />

<form action="/0608_problem/AdminInnServlet" method="post">
宿情報の検索<br>
宿名:<input type="text" name="name">
<input type="submit" value="検索">
<input type="hidden" name="action" value="search">
<br>
</form>
<hr>
</body>
</html>