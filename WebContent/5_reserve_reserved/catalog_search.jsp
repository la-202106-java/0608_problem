<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>目録検索</title>
</head>
<body>

<h2>目録検索</h2>
<c:if test="${not empty error}">
<p class="">${error}</p>
</c:if>
<form action="/0608_problem/CatalogSearchServlet" method="post">
資料名 <input type ="text" name = "titles" value="${titles}"><input type="submit" value = 検索>
</form>

<c:if test="${result.size() > 0}">

${result.size()}件の資料が検索されました<br>

<table border="1">
    <tr>
      <th>貸出名</th>
      <th>ISBN</th>
      <th>状態</th>
      <th>予約/取置</th>
    </tr>
    <c:forEach items="${result}" var="item">
	    <tr>
	      <td>${item.title}</td>
	      <td>${item.isbn}</td>
	      <td>${item.stockCount}</td>
	      <td><input type="submit" value = 予約></td>
	    </tr>
	</c:forEach>
  </table>
</c:if>

</body>
</html>