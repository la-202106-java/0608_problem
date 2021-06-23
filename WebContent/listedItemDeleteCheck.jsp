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

<h1>この教科書を削除しますか？</h1>

<table border="1">
<tr><td>id</td><td>ISBN</td><td>タイトル</td><td>カテゴリ</td><td>著者</td><td>売値</td></tr>

<c:forEach items="${id_item}" var="item" >
<tr><td>${item.id}</td><td>${item.isbn}</td><td>${item.title}</td><td>${item.department_code}</td><td>${item.author}</td><td>${item.price}</td></tr>
</c:forEach>

</table>

<!-- キャンセルするボタン(DeleteServlet)-->
<div style="display:inline-flex">
<form action="/0608_problem/DeleteServlet" method= "post">
<input type="submit" value="キャンセル">
<input type="hidden" name = "action" value="cancel">
</form>

<!-- 削除するボタン(DeleteServlet) -->
<form action="/0608_problem/DeleteServlet" method= "post">
<input type="submit" value="削除する">
<input type="hidden" name = "action" value="delete">
</form>
</div>




</body>
</html>