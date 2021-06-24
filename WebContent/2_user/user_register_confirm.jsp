<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "la.bean.NowUserBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
  crossorigin="anonymous">
<title>会員登録確認</title>
</head>
<body>
<jsp:include page="/navbar.jsp" />
<% NowUserBean bean = (NowUserBean)request.getAttribute("bean");%>

 <div class="m-3 p-3">
    <div class="col-9">
    <div class="row">
<h2>会員登録確認</h2>
</div>
    <div class="row">
<h4>以下の情報で会員登録をしてよろしいですか？</h4>
</div>
<div class="row">
氏名：${bean.name}<br>
住所：${bean.address}<br>
電話番号：${bean.tel}<br>
email：${bean.email}<br>
生年月日：${bean.birthDate}<br>
入会年月日：${bean.joinDate}
</div>
<div class="row">
<form action = "/0608_problem/UserRegisterServlet" method = "get" style="display:inline;">
<input type="submit" value = 戻る>
<input type="hidden" name = "action" value = "return_register1" >
</form>
<form action = "/0608_problem/UserRegisterServlet" method = "get" style="display:inline;">
<input type="submit" value = 登録>
<input type="hidden" name = "action" value = "register2" >
</div>
</form>
</div>
</div>
</div>
</body>
</html>

