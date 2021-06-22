<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 資料の検索</title>
</head>
<body>

<jsp:include page="/menu.jsp" />
<h2>資料の検索</h2>
<form>
<form action="/0608_problem/LoginServlet" method="post">
	 <input type ="hidden" name ="action" value ="login">
資料ID：<input type="text" name="address"><br>
資料名：<input type="text" name="address"><br>
  <input type="submit" value="検索">
</form>

<h3>該当する資料の情報は以下の通りです。</h3>
<br>
<table border="1">
<tr><td>資料ID</td><td>5</td></tr>
<tr><td>ISBN番号</td><td>4906638015</td></tr>
<tr><td>資料名</td><td>リンゴの皮の向き方百選</td></tr>
<tr><td>入荷年月日</td><td>2021年6月21日</td></tr>
<tr><td>資料目録ID</td><td>123</td></tr>
</table>
<form>
<form action="/0608_problem/LoginServlet" method="post">
  <input type="submit" value="変更">
  <input type="submit" value="廃棄">
</form>

</body>
</html>