<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ page import="la.bean.PlusBean"%>

    <%
    PlusBean bean = (PlusBean)request.getAttribute("plus");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

今日の運勢は<%=bean.getAnswer2()%>

</body>
</html>