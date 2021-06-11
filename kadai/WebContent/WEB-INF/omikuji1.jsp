<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ page import="java.util.Random" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>omikuji</title>
</head>
<body>

<%
Random r= Random();
int dice = r.nextInt(6)+1;

String message;

switch (dice) {
case 1:
message = "大吉です";
break;
case 2:
message = "小吉です";
break;
case 3:
message = "凶です";
break;
default:
message = "吉です";
break;
}

%>

</body>
</html>