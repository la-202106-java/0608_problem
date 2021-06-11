<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BBS_jsp</title>
</head>
<body>

<form action="/lesson/BbsServlet" method="post">

名前：<br>
<input type="text" name="name"><br>
メッセージ：<br>
<textarea name="message" rows="5" cols="50"></textarea><br>
<input type="submit" value="書き込み">

<hr>

<% ArrayList<String> message = (ArrayList<String>)request.getAttribute("message"); %>

<% for(String str : message) { %>
<%= str %>
<hr>
<% } %>

</form>

</body>
</html>