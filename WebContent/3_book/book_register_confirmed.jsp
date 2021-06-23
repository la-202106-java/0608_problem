<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>資料登録完了</title>
</head>
<body>

<h2>資料登録完了</h2>
<h4>以下の資料登録が完了しました</h4>
資料ID：<br>
資料名：${title}<br>
ISBN番号：${isbn}<br>
入荷年月日：${arrivalDate}<br>
<br>
<a href="/0608_problem/3_book/book_register.jsp">登録続行</a>
<a href="/0608_problem/TopServlet">終了</a>

</body>
</html>


