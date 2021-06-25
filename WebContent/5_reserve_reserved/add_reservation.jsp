<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約追加</title>
</head>
<body>

<h2>予約追加</h2>
以下の資料を予約します<br>
会員IDを入力してください<br>
ISBN：${isbn}<br>
資料名：${title}<br>

<form method="post">

会員ID <input type ="text" name = "user_id"><br>

<input type="submit" value ="戻る" formaction="/0608_problem/CatalogSearchServlet">
<input type="submit" value = 予約 formaction="/0608_problem/CatalogReserveServlet">
<input type="hidden" name="action" value="reserve_confirm">
<input type="hidden" name="isbn" value="${isbn}">
<input type="hidden" name="title" value="${title}">
</form>

</body>
</html>