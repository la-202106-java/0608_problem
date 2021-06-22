<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 貸出情報の登録</title>
</head>
<body>

<jsp:include page="/menu.jsp" />

<form>
<form action="/0608_problem/LoginServlet" method="post">
<h1>貸出登録</h1>
<h3>正しい情報が入力されていれば、『登録』ボタンを押してください。</h3>
  <input type="submit" value="登録">
  <input type="submit" value="修正">
</form>
<br>
<table border="1">
<tr><td>会員ID</td><td>1</td></tr>
<tr><td>会員名</td><td>田中太郎</td></tr>
<tr><td>資料名</td><td>JAVAマスター</td></tr>
<tr><td>貸出年月日</td><td>2021年6月21日</td></tr>
<tr><td>返却期日</td><td>2021年9月21日</td></tr>
</table>

</body>
</html>