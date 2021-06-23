<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>資料登録</title>
</head>
<body>

<h2>資料登録</h2>
<h4>新規資料情報を入力してください</h4>

<form action="/0608_problem/BookRegistServlet?action=regist" method="post">
資料名：<input type ="text" name = "title"><br>
ISBN番号：<input type ="text" name = "isbn"><br>
入荷年月日：<input type ="date" name = "arrival_date"><br>
<br>
<input type="submit" value = 登録>
</form>

</body>
</html>

