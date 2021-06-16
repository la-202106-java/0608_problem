<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <%
   request.setAttribute("age" , "35");
   %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Sample</title>
</head>
<body>

<c:if test="${age ge 20}">
成人です
</c:if>

<c:if test="${age lt 20}">
未成人です
</c:if>


</body>
</html>