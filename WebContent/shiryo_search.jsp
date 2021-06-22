<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 資料の検索</title>
</head>
<body>

<jsp:include page="/menu.jsp" />
<h2>資料の検索</h2>

<form action="/0608_problem/ShiryoServlet" method="post">
	 <input type ="hidden" name ="action" value ="search">
資料ID：<input type="text" name="id"><br>
または<br>
資料名：<input type="text" name="part_of_title"><br>
  <input type="submit" value="検索">
</form>

<p>ヒットした件数は${count }件です。</p>

<table border="1">
<tr><td>資料ID</td><td>ISBN番号</td><td>入荷年月日</td><td>資料名</td><td>廃棄</td></tr>

<c:forEach items="${items }" var="item">
<tr><td>${item.id }</td><td>${item.isbn }</td><td>${item.stockDate }</td><td>${item.title }</td>
<td><form action="/0608_problem/ShiryoServlet" method="post"><input type="submit" value="廃棄"><input type ="hidden" name ="action" value ="delete"></form></td></tr>
</c:forEach>
</table>

</body>
</html>