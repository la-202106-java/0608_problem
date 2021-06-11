<%@page import="java.util.concurrent.ThreadLocalRandom"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%!
String getLuck(){
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
        return result;
}
%>
     <%


    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
スクリプトレットを使用しておみくじ<br>
<%
    for (int i = 1; i < 13; i++) {
%>
     <%=i %>月の運勢は、、、「<%=getLuck()%>」です。<br>
<%
    }
%>

</body>
</html>