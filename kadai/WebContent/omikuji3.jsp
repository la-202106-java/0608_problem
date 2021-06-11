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
	String name = request.getParameter("name");
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
リクエストパラメータを取得しておみくじ結果を表示<br>
<%=name %>さんの運勢は、、、
「<%=value %>」
です。
</body>
</html>