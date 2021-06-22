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

<h1>教科書が削除されました</h1>
<form action="/0608_problem/DeleteServlet" method = "post">
<input type="submit" value="TOPに戻る">

<!-- topページに戻る(DeleteServlet) -->
<input type="hidden" name = "action" value="topReturn">
</form>


</body>
</html>