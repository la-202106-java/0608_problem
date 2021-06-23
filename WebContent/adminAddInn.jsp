<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>宿情報追加</title>
</head>
<body>
<jsp:include page="/WEB-INF/adminMenu.html" />

<h1>宿の追加</h1>
<form action="/0608_problem/admin/inn" method="get">
宿名：<input type="text" name="name"><br>
分類コード：<input type="number" name="class_code"><br>
郵便番号：〒<input type="text" name="postal_code"><br>
住所：<input type="text" name="address"><br>
チェックイン時間：<input type="time"name ="inTime"><br>
チェックアウト時間：<input type="time" name="outTime"><br>
<input type="submit" value="追加">
<input type="hidden" name="action" value="add">
</form>
<hr>
</body>
</html>