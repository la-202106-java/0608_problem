<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.Random" %>

<%!
String omiku(int dice){
	switch (dice) {
	case 1:
		return "大吉";
	case 2:
		return "中吉";
	case 3:
		return "小吉";
	default :
		return "吉";
	}
}
%>
<%
Random rand = new Random();
int dice = rand.nextInt(6)+1;
String omikuji = omiku(dice);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>おみくじ</title>
</head>
<body>

<%= omikuji %>

 </body>
</html>