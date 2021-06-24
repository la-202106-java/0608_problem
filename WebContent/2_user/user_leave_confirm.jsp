<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "la.bean.NowUserBean" %>
<% NowUserBean bean = (NowUserBean)session.getAttribute("bean");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員退会確認</title>
</head>
<body>

<h2>会員退会確認</h2>
<h4>以下の会員を退会処理してよろしいですか？</h4>
会員ID：${bean.id}<br>
氏名：${bean.name}<br>
住所：${bean.address}<br>
電話番号：${bean.tel}<br>
email：${bean.email}<br>
生年月日：${bean.birthDate}<br>
入会年月日：${bean.joinDate}<br>
<br>


<form action = "/0608_problem/UserSearchServlet" method = "post">
<input type="submit" value = 退会>
<input type="hidden" name ="action" value = "delete2">
<input type="hidden" name ="id" value = "${bean.id}">
</form>

</body>
</html>