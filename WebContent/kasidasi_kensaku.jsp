<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 貸出情報の検索</title>
</head>
<body>

<jsp:include page="/menu.jsp" />
<h2>資料の検索</h2>
<form action="/0608_problem/KasidasiServlet" method="post">
	 <input type ="hidden" name ="action" value ="login">
  会員ID：<input type="text" name="mid" value="${mid }">&nbsp;<br>
  会員名：<input type="text" name="mname" value="${mname }">&nbsp;<br>
<input type="submit" value="検索">

  <hr>

  	<h2>検索結果</h2>



<table border="1">


<tr align="center">
<td>会員ID</td><td>会員名</td><td>資料名</td><td>貸出日</td><td>返却期日</td><td>返却日</td>
</tr>
<c:forEach items="${list }" var="ll">
<tr>
<td>${ll.member.id }</td>
<td>${ll.member.name }</td>
<td>${ll.materialCatalog.title }</td>
<td>${ll.checkoutDate }</td>
<td>${ll.returnDeadline }</td>
<td>${ll.returnDate }</td>
<tr>
</c:forEach>

</table>

</form>
</body>
</html>