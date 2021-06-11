<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BBS</title>
</head>
<body>
<form action="/lesson/BBSServlet" method="post">
名前:<br>
<input type="text" name="name">
<br>
メッセージ:<br>
<textarea name="message" rows="5" cols="40">
</textarea>
<br>
<input type="submit" value="書き込み">
</form>
<hr>

<%
List<String> messages = (List<String>)request.getAttribute("messages");
for (String m : messages) {
%>
<%= m %>
<br><hr>
<% }%>

</body>
</html>