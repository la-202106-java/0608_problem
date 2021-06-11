<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="la.servlet.OmikujiServlet" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Omikuji</title>
</head>
<body>
<%
String result = (String)request.getAttribute("result");
%>
<%= result %>
<br><hr>
<%
OmikujiServlet om = new OmikujiServlet();
for (int i=1;i<=12;i++) {
%>
<%= i %>
月の運勢は、、、「
<%=  om.omikuji() %>
」です。
<br>
<% }%>
</body>
</html>