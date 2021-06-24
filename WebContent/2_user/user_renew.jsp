<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "la.bean.NowUserBean" %>
<% NowUserBean bean = (NowUserBean)session.getAttribute("bean");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員更新</title>
</head>
<body>
<form action = "/0608_problem/UserSearchServlet" method = "post">
<h2>会員更新</h2>

会員ID：${bean.id}<br>
氏名：<input type ="text" name = "name" value="${bean.name}"><br>
住所：<input type ="text" name = "address" value="${bean.address}"><br>
電話番号：<input type ="tel" name = "tel" value="${bean.tel}"><br>
email <input type ="email" name = "email" value="${bean.email}"><br>
生年月日：${bean.birthDate}<br>
入会年月日：${bean.joinDate}
<br>


<input type="submit" value = 更新>
<input type="hidden" name ="action" value = "renew2">
<input type="hidden" name ="id" value = "${bean.id}">
<input type="hidden" name ="birthDate" value = "${bean.birthDate}">
<input type="hidden" name ="joinDate" value = "${bean.joinDate}">

</form>
</body>
</html>