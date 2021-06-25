<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test=""></c:if>
出品中の商品
<table border="1">
<tr><td>ISBN</td><td>分類</td><td>タイトル</td><td>著者</td><td>値段</td></tr>
<c:forEach items="${itemlist}" var="item" >
<tr><td>${item.code }</td><td>${item.category_code}</td><td>${item.name }</td><td>${item.price }</td></tr>
</c:forEach>
</table>

</body>
</html>