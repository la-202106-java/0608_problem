<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Random" %>

<%!
String uranai(int dice){
	String fortune;
	switch(dice){
	case 1:
		fortune = "大吉";
		return fortune;
	case 2:
		fortune = "中吉";
		return fortune;
	case 3:
		fortune = "凶";
		return fortune;
	default:
		fortune = "吉";
		return fortune;
	}
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>おみくじ</title>
</head>
<body>
<h1>STEP3</h1><br>
リクエストパラメータを取得しておみくじ<br><br>
<%
request.setCharacterEncoding("UTF-8");
String name = request.getParameter("name");
if(name == null || name.length() == 0){
	name = "名無し";
}
Random rand = new Random();
int dice = rand.nextInt(6)+1;
String fortune = uranai(dice);
out.println(name + "さんの運勢は、、、「"+ fortune + "」です。<br>");
%>
</body>
</html>