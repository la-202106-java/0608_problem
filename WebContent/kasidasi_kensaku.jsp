<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 貸出情報の検索</title>
</head>
<body>

<jsp:include page="/menu.jsp" />
<h2>資料の検索</h2>
<form action="/0608_problem/LoginServlet" method="post">
	 <input type ="hidden" name ="action" value ="login">
  会員ID：<input type="text" name="id"><br>
  会員名：<input type="text" name="name"><br>

  <input type="submit" value="検索">
  <hr>

  	<h2>検索結果</h2>



<table border="1">


<tr align="center">
<td>会員ID</td><td>会員名</td><td>資料名</td><td>貸出日</td><td>返却期日</td><td>返却日</td>
</tr>

<tr>
<td>1</td>
<td>田中太郎</td>
<td>JAVAマスター</td>
<td>2021年4月26日</td>
<td>2021年5月06日</td>
<td align="center">-</td>
</tr>

<tr>
<td>1</td>
<td>田中太郎</td>
<td>JAVAベーシック</td>
<td>2021年4月11日</td>
<td>2021年4月26日</td>
<td>2021年4月20日</td>
</tr>


</table>

</form>
</body>
</html>