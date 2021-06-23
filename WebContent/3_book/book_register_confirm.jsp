<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>資料登録確認</title>
</head>
<body>

<form action="/0608_problem/BookRegistServlet?action=confirm&title=${title}&isbn=${isbn}&arrival_date=${arrivalDate}" method="post">
<h2>資料登録確認</h2>
<h4>以下の資料を登録してもよしいですか？</h4>
資料名：${title}<br>
ISBN番号：${isbn}<br>
入荷年月日：${arrivalDate}<br>
<br>
<a href=""></a>
<input type="submit" value = 登録>
</form>

</body>
</html>