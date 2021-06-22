	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 資料の登録</title>
</head>
<body>

<jsp:include page="/menu.jsp" />
<h2>資料の新規登録</h2>
<form>
<form action="/0608_problem/LoginServlet" method="post">
<h3>正しい情報が入力されていれば、『登録』ボタンを押してください。</h3>
  <input type="submit" value="登録">
  <input type="submit" value="修正">
</form>
<br>
<table border="1">
<tr><td>資料ID</td><td>123</td></tr>
<tr><td>ISBN番号</td><td>4906638015</td></tr>
<tr><td>資料名</td><td>リンゴの皮の向き方百選</td></tr>
<tr><td>入荷年月日</td><td>2021年6月21日</td></tr>
<tr><td>資料目録ID</td><td>123</td></tr>
</table>

</body>
</html>