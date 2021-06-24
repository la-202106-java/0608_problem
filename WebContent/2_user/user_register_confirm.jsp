<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "la.bean.NowUserBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員登録確認</title>
</head>
<body>
<% NowUserBean bean = (NowUserBean)request.getAttribute("bean");%>


<h2>会員登録確認</h2>
<h4>以下の情報で会員登録をしてよろしいですか？</h4>
氏名：${bean.name}<br>
住所：${bean.address}<br>
電話番号：${bean.tel}<br>
email：${bean.email}<br>
生年月日：${bean.birthDate}<br>
入会年月日：${bean.joinDate}
<br>
<form action = "/0608_problem/UserRegisterServlet" method = "get" style="display:inline;">

<input type="submit" value = 戻る>
<input type="hidden" name = "action" value = "return_register1" >
</form>
<form action = "/0608_problem/UserRegisterServlet" method = "get" style="display:inline;">
<input type="submit" value = 登録>
<input type="hidden" name = "action" value = "register2" >
</form>
</body>
</html>

