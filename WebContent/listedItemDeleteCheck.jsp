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

<table>
<tr><td>ISBN</td><td>タイトル</td><td>カテゴリ</td><td>著者</td><td>売値</td></tr>


<tr><td>${setIsbn}</td><td>${setTtitle}</td><td>${setDepartment_code}</td><td>${setAuthor}</td><td>${setPrice}</td></tr>


</table>


<div style="display:inline-flex">
<!-- 削除するボタン(DeleteServlet) -->
<form action="/0608_problem/DeleteServlet" method= "post">
<input type="submit" value="削除する">
<input type="hidden" name = "action" value="dodelete">
</form>

<!-- キャンセルするボタン(DeleteServlet)-->
<form action="/0608_problem/DeleteServlet" method= "post">
<input type="submit" value="キャンセル">
<input type="hidden" name = "action" value="cancel">
</form>


</div>




</body>
</html>