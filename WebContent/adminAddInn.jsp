<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>宿情報追加</title>
</head>
<body>
<jsp:include page="/WEB-INF/adminMenu.jsp" />

<h1>宿の追加</h1>
<form action="/0608_problem/admin/inn" method="get">
宿名：<input type="text" name="name"><br>
宿の分類：
<select name="class_code">
<option value="0">シティホテル</option>
<option value="1">リゾートホテル</option>
<option value="2">ビジネスホテル</option>
<option value="3">旅館</option>
<option value="4">民宿</option>
<option value="5">ペンション</option>
</select><br>
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