<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Random" %>

<%!
String result(int number) {
	String value;
	switch (number) {
	case 1:
		value = "大吉";
		break;
	case 2:
		value = "小吉";
		break;
	case 3:
		value = "凶";
		break;
	default:
		value = "吉";
		break;
	}
	return value;
}
%>

<%
	Random random = new Random();
	int[] numbers = new int[12];
	for(int i = 0;i<12;i++){
		numbers[i] = random.nextInt(6) + 1;
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>おみくじ</title>
</head>
<body>
スクリプトレットの繰り返し処理を利用しておみくじ<br>
<% for (int i = 1 ; i<=12 ; i++) { %>
<%=i %>月の運勢は、、、
「<%=result(numbers[i-1]) %>」
です。<br>
<% } %>
</body>
</html>