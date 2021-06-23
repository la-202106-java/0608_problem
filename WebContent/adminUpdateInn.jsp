<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>宿情報更新</title>
</head>
<body>
<h1>宿の更新</h1>
<form action="/0608_problem/admin/inn" method="get">
宿ID：${innTarget.id}
宿名：<input type="text" name="name" value="${innTarget.name}"><br>
分類コード：<input type="number" name="class_code" value="${innTarget.classCode}"><br>
郵便番号：〒<input type="text" name="postal_code" value="${innTarget.postalCode}"><br>
住所：<input type="text" name="address" value="${innTarget.address}"><br>
チェックイン時間：<input type="time"name ="inTime" value="${innTarget.inTime}"><br>
チェックアウト時間：<input type="time" name="outTime" value="${innTarget.outTime}"><br>
<input type="submit" value="更新">
<input type="hidden" name="action" value="update">
<input type="hidden" name="id" value="${innTarget.id}">
</form>
</body>
</html>