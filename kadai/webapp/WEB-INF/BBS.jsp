<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>掲示板</title>
</head>
<body>

<%
	List<String> messages = (List<String>)request.getAttribute("messages");
	if (messages == null) {
		messages = new ArrayList<String>();
	}
%>

<form action="/lesson/bbs" method="post">
	<p>メッセージ：</p>
	<div>
		<textarea rows="3" cols="40" name="message"></textarea>
	</div>
	<input type="submit" value="書き込み">
	<%	for (String message : messages) { %>
	<hr>
	<%= message %>
	<% }%>
</form>
</body>
</html>