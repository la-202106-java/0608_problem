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

<h1>教科書を削除しますか？</h1>

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