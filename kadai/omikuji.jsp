<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import= "java.util.Random" %>
<%!
String luck(int no){
	String message;
	switch(no){
	case 1:
		message = "大吉";
		break;
	case 2:
		message = "小吉";
		break;
	case 3:
		message = "凶";
		break;
	default :
		message = "吉";
		break;
	}
	return message;
}
%>

<%
	Random rand = new Random();
	int dice = rand.nextInt(6) + 1;
	String message = luck(dice);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>omikuji</title>
</head>
<body>
<h1>STEP1</h1>

<h2>スクリプトレットを使用しておみくじ</h2><br>

今日の運勢は...<%=message %>です。


</body>
</html>