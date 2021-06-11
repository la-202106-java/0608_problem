<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@ page import="la.servlet.BbsServlet" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>掲示板</title>
</head>
<body>
メッセージ：
<br>

<form action="/lesson/BbsServlet" method="post">
<textarea name="message" cols="40" rows="5"></textarea>
<br>
<input type="submit" value="書き込み">
</form>

<%
ArrayList<String> messages = new ArrayList<String>();
messages = (ArrayList<String>) request.getAttribute("messages");
%>
<hr>
<%
for(int i=0; i<messages.size();i++ ){
messages[i]
}%>

</body>
</html>