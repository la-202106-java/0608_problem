<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "la.bean.NowUserBean" %>
<% NowUserBean bean = (NowUserBean)session.getAttribute("bean");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
  crossorigin="anonymous">
<title>会員更新確認</title>
</head>
<body>
<jsp:include page="/navbar.jsp" />
<div class="m-3 p-3">
	<div class="col-9">
		<h2>会員更新確認</h2>
		<form action = "/0608_problem/UserSearchServlet" method = "post">
		<h4>以下の情報で会員情報を更新してよろしいですか？</h4>
		<br>会員ID：${bean.id}<br>
		氏名：${bean.name}<br>
		住所：${bean.address}<br>
		電話番号：${bean.tel}<br>
		email：${bean.email}<br>
		生年月日：${bean.birthDate}<br>
		入会年月日：${bean.joinDate}<br>


		<button class="btn btn-primary" type="submit">更新</button>
		<input type="hidden" name ="action" value = "renew3">
		<input type="hidden" name ="id" value = "${bean.id}">
		<input type="hidden" name ="name" value = "${bean.name}">
		<input type="hidden" name ="address" value = "${bean.address}">
		<input type="hidden" name ="tel" value = "${bean.tel}">
		<input type="hidden" name ="email" value = "${bean.email}">
		<input type="hidden" name ="birthDate" value = "${bean.birthDate}">
		<input type="hidden" name ="joinDate" value = "${bean.joinDate}">
		</form>

</body>
</html>

