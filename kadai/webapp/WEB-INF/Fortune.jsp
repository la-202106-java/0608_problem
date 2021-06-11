<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.*" %>
<%
	List<String> result = (List<String>)request.getAttribute("result");
%>

<html>
<head>
<meta charset="UTF-8">
<title>おみくじ</title>
</head>
<body>

<%  for (int i = 0; i < result.size(); i++) { %>
		<%= i+1 %> 月の運勢は、、、「<%= result.get(i)%>」です。
		<br >
<%
	}
%>
</body>
</html>