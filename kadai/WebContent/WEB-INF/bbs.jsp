<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>掲示板</title>
</head>
<body>
	名前：<br>
	<form action="/lesson/BbsServlet" method="post">
		<input type="text" name="name">

	<br>
	メッセージ：<br>

		<textarea name="message" ></textarea>
		<br>
		<input type="submit" value="書き込み">
	</form>
	<hr>
	<%
		@SuppressWarnings("unchecked")
		ArrayList<String> messages = (ArrayList<String>)request.getAttribute("messages");
		if(messages != null){
			for(String message : messages){
				if(message.length() > 0){
	%>
				<%= message %>
				<hr>
	<%
				}
			}
		}
	%>


</body>
</html>