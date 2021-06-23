<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import = "la.bean.NowUserBean" %>
<% NowUserBean bean = (NowUserBean)session.getAttribute("bean");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員検索結果</title>
</head>
<body>

<h2>会員検索結果</h2>
<form action = "/0608_problem/UserSearchServlet" method = "post">
email <input type ="email" name = "email">
<input type="submit" value = 検索>
<input type="hidden" name = "action" value ="search">
</form>
<br>
会員ID：${bean.id}<br>
氏名：${bean.name}<br>
住所：${bean.address}<br>
電話番号：${bean.tel}<br>
email：${bean.email}<br>
生年月日：${bean.birthDate}<br>
入会年月日：${bean.joinDate}<br>


<input type="submit" value = 更新>

<a href="/0608_problem/UserSearchServlet?&id=${bean.id}" role="button">退会</a>

</body>
</html>