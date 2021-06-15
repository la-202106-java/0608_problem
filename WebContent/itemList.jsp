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
<table border="1">
<c:forEach items="${items }" var="item">
<tr><td>${item.code }</td><td>${item.name }</td><td>${item.price }</td></tr>

</c:forEach>
</table>
</body>
</html>