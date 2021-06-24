<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "la.bean.NowUserBean" %>
<% NowUserBean bean = (NowUserBean)request.getAttribute("bean");%>
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
<div class="m-3 p-3">
	<div class="col-9">
	<h2>会員登録確認</h2>
	<h4>以下の情報で会員登録をしてよろしいですか？</h4>
	<br>氏名：${bean.name}<br>
	住所：${bean.address}<br>
	電話番号：${bean.tel}<br>
	email：${bean.email}<br>
	生年月日：${bean.birthDate}<br>
	入会年月日：${bean.joinDate}
	<div class="btn-toolbar">
		<div class="pr-3">
			<div class="form-group">
				<form action = "/0608_problem/UserRegisterServlet" method = "get" style="display:inline;">
				<button class="btn btn-secondary" type="submit">戻る</button>
				<input type="hidden" name = "action" value = "return_register1" >
				</form>
	 		</div>
		</div>
		<div class="pr-3">
			<div class="form-group">
				<form action = "/0608_problem/UserRegisterServlet" method = "get" style="display:inline;">
				<button class="btn btn-primary" type="submit">登録</button>
				<input type="hidden" name = "action" value = "register2" >
				</form>
			</div>
		</div>
	</div>
</div>

</body>
</html>

