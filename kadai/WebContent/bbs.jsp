<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Set"%>
<%!
	ArrayList<String> messages = new ArrayList<String>();
	ArrayList<String> names = new ArrayList<String>();
%>
<%
	String text = request.getParameter("text");
	String name = request.getParameter("name");
	messages.add(text);
	names.add(name);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>掲示板</title>
</head>
<body>
<form action='/kadai1/bbs.jsp' method='post'>
名前：<br>
<input type="text" name="name">
<br>
メッセージ：<br>
<textarea name='text' rows='4' cols='40'></textarea><br>
<input type='submit' value='書き込み'>
</form>
<% for(int i =0;i<messages.size();i++) { %>
<hr><%=names.get(i) %>:
<%=messages.get(i) %><br>
<% } %>
</body>
</html>