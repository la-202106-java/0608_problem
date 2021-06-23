<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "la.bean.NowUserBean" %>
<% NowUserBean r_bean = (NowUserBean)session.getAttribute("r_bean");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員登録完了</title>
</head>
<body>

<h2>会員登録完了</h2>
<h4>以下の情報で会員登録が完了しました</h4>
会員ID：${r_bean.id}<br>
氏名：${r_bean.name}<br>
住所：${r_bean.address}<br>
電話番号：${r_bean.tel}<br>
email：${r_bean.email}<br>
生年月日：${r_bean.birthDate}<br>
入会年月日：${r_bean.joinDate}
<br>


<form action = "/0608_problem/UserRegisterServlet" method = "get">
<input type="submit" value = 登録を続行>
<input type="hidden" name = "action" value = "return_register1" >
</form>

<form action = "/0608_problem/TopServlet" method = "get">
<input type="submit" value = 終了>
</form>

</body>
</html>

