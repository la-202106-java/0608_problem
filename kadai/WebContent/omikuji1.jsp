<%@page import="java.util.concurrent.ThreadLocalRandom"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%
     int randomNum = ThreadLocalRandom.current().nextInt(1, 7);

     String result="";
    switch(randomNum){
    case 1:
    	result="大吉";
    	break;
    case 2:
    	result="小吉";
    	break;
    case 3:
    	result="凶";
    	break;
    default:
    	result="吉";
    	break;

    }
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
スクリプトレットを使用しておみくじ<br>
今日の運勢は、、、「<%=result %>」です。

</body>
</html>