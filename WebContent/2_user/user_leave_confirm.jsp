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
<title>会員退会確認</title>
</head>
<body>
<jsp:include page="/navbar.jsp" />
<div class="m-3 p-3">
	<div class="col-9">
		<h2>会員退会確認</h2>
		<h4>以下の会員を退会処理してよろしいですか？</h4>
		<br>会員ID：${bean.id}<br>
		氏名：${bean.name}<br>
		住所：${bean.address}<br>
		電話番号：${bean.tel}<br>
		email：${bean.email}<br>
		生年月日：${bean.birthDate}<br>
		入会年月日：${bean.joinDate}<br>
		<form action = "/ShinjukuLibrary/UserSearchServlet" method = "post">
		<button class="btn btn-danger" type="submit">退会</button>
		<input type="hidden" name ="action" value = "delete2">
		<input type="hidden" name ="id" value = "${bean.id}">
		</form>
	</div>
</div>

</body>
</html>