<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 会員の検索</title>
</head>
<body>

<jsp:include page="/menu.jsp" />

<form>
<form action="/0608_problem/LoginServlet" method="post">
	 <input type ="hidden" name ="action" value ="login">
	E-Mail：<input type="text" name="address"><br>
会員ID：<input type="text" name="address"><br>
  <input type="submit" value="検索">
</form>

<h3>該当する会員の情報は以下の通りです。</h3>
<br>
<table border="1">
<tr><td>ID</td><td>4</td></tr>
<tr><td>氏名</td><td>田中太郎</td></tr>
<tr><td>住所</td><td>東京都新宿区新宿2-1-11</td></tr>
<tr><td>電話番号</td><td>03-0000-1111</td></tr>
<tr><td>E-Mail</td><td>aaa@bbb.com</td></tr>
<tr><td>生年月日</td><td>1997年5月11日</td></tr>
<tr><td>入会年月日</td><td>2021年6月21日</td></tr>
	</table>
</body>
</html>