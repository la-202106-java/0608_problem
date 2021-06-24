<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>プラン情報追加</title>
</head>
<body>
<jsp:include page="/WEB-INF/adminMenu.jsp" />

<h1>プランの追加</h1>
<form action="/0608_problem/admin/plan" method="get">
宿ID：<input type="number" name="id"><br>
プラン内容：<input type="text" name="contents"><br>
金額：<input type="number" name="fee">円<br>
部屋数：<input type="number" name="room"><br>
画像：<input type="text"name ="picture"><br>
<input type="submit" value="追加">
<input type="hidden" name="action" value="add">
</form>

</body>
</html>