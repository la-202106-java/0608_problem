<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 資料の返却</title>
</head>
<body>

<jsp:include page="/menu.jsp" />

<form>
<form action="/0608_problem/LoginServlet" method="post">
<h3>正しい情報が入力されていれば、『返却』ボタンを押してください。</h3>
  <input type="submit" value="返却">
  <input type="submit" value="修正">
</form>
<br>
<table border="1">
<tr><td>会員ID</td><td>5</td></tr>
<tr><td>資料ID</td><td>65489</td></tr>
<tr><td>貸出年月日</td><td>2021年6月21日</td></tr>
<tr><td>返却期日</td><td>2021年6月31日</td></tr>
</table>

</body>
</html>