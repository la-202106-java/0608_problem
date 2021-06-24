<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
<title>教科書売買サイト</title>
</head>
<body>

<h1>教科書検索結果</h1>
<p><c:if test="${!empty result_num}">
	${result_num}件見つかりました。
</c:if></p>

<c:forEach items="${listed_items}" var="item">
	<table>
      		<tr>
      			<td><b>${item.title}</b><br>
      				<small>${departments.get(item.departmentCode)}</small></td>
      			<td>${item.price}円</td>
      			<td>在庫　
      				<c:if test="${item.inStock}">〇</c:if>
      				<c:if test="${!item.inStock}">×</c:if>
      			</td>
      			<td>
      				<form>
      					<input type="hidden" value="${item.id}">
      					<input type="submit" value="詳細">
      				</form>
      			</td>
    		</tr>
    	</table>
</c:forEach>

</body>
</html>