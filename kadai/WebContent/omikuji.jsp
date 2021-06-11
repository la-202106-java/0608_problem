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
	int number = random.nextInt(6) + 1;
	String value = result(number);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>おみくじ</title>
</head>
<body>
スクリプトレットをしようしておみくじ<br>
今日の運勢は、、、
「<%=value %>」
です。
</body>
</html>