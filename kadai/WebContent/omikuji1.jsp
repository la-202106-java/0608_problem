<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Random" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>おみくじ</title>
</head>
<body>
<%
Random rand = new Random();
int dice = rand.nextInt(6)+1;
String fortune;

switch(dice){
case 1:
	fortune = "大吉";
	break;
case 2:
	fortune = "中吉";
	break;
case 3:
	fortune = "凶";
	break;
default:
	fortune = "吉";
	break;
}
%>

<h1>STEP1</h1>
<br>
スクリプトレットを使用しておみくじ<br><br>
今日の運勢は、、、「
<%=fortune %>
」です。

</body>
</html>