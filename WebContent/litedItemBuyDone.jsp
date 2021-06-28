<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
<title>教科書売買サイト</title>
</head>
<body>
<c:if test="${user == 'admin'}">
<jsp:include page="/adminMenu.jsp" />
</c:if>
<c:if test="${user != 'admin'}">
<jsp:include page="/menu.jsp" />
</c:if>

<h1>購入が確定しました</h1>

<table>
<tr><td>ID</td><td>${item.id}</td></tr>
<tr><td>ISBN</td><td>${item.isbn}</td></tr>
<tr><td>タイトル</td><td>${item.title}</td></tr>
<tr><td>分類</td><td>${departments.get(item.departmentCode)}</td></tr>
<tr><td>著者名</td><td>${item.author}</td></tr>
<tr><td>売値</td><td>${item.price}</td></tr>
</table>
<br><br>
<h3>カウンターまでお越しください</h3>
<br>

<a href="/0608_problem/top">TOPに戻る</a>

</body>
</html>