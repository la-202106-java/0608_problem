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
<title>Plus</title>
</head>
<body>

${plus.value1}+${plus.value2}=${plus.answer}
#<%=bean.getValue1()%>+<%=bean.getValue2()%>=<%=bean.getAnswer()%>

</body>
</html>