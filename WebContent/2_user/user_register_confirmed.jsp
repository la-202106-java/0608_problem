<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "la.bean.NowUserBean" %>
<% NowUserBean r_bean = (NowUserBean)session.getAttribute("r_bean");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
  crossorigin="anonymous">
<title>会員登録完了</title>
</head>
<body>
<jsp:include page="/navbar.jsp" />
 <div class="m-3 p-3">
    <div class="col-9">
    <div class="row">
<h2>会員登録完了</h2>
</div>
    <div class="row">
<h4>以下の情報で会員登録が完了しました</h4>
</div>
<div class="row">
会員ID：${r_bean.id}<br>
氏名：${r_bean.name}<br>
住所：${r_bean.address}<br>
電話番号：${r_bean.tel}<br>
email：${r_bean.email}<br>
生年月日：${r_bean.birthDate}<br>
入会年月日：${r_bean.joinDate}
</div>
<div class="row">
<form action = "/0608_problem/UserRegisterServlet" method = "get" style="display:inline;">
<input type="submit" value = 登録を続行>
<input type="hidden" name = "action" value = "return_register" >
</form>

<form action = "/0608_problem/TopServlet" method = "get" style="display:inline;">
<input type="submit" value = 終了>
</div>
</form>
</div>
</div>
</div>
</body>
</html>

