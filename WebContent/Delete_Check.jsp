<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="menu.jsp"></jsp:include>

<h1>教科書を削除しますか？</h1>
<form action="">
<input type="submit" value="キャンセル">
<input type="hidden" name = "action" value="serch">
<input type="submit" value="削除する">
<input type="hidden" name = "action" value="serch">
</form>

</body>
</html>