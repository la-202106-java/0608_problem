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
<h1>STEP2</h1><br>
スクリプトレットで繰り返し処理を使用しておみくじ<br><br>
<%
Random rand = new Random();
for(int i=1;i<=12;i++){
	int dice = rand.nextInt(6)+1;
	String monthFortune = uranai(dice);
	out.println(i + "月の運勢は、、、「"+ monthFortune + "」です。<br>");
}
%>

</body>
</html>