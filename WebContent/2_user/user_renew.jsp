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
<title>会員更新</title>
</head>
<body>
<jsp:include page="/navbar.jsp" />
<div class="m-3 p-3">
    <div class="col-9">
		<h2>会員更新</h2>
		<form action = "/0608_problem/UserSearchServlet" method = "post">
		<div class="form-group">
			<br>会員ID：${bean.id}
		</div>
		<div class="form-group">
			氏名：<input type ="text" name = "name" value="${bean.name}"><br>
		</div>
		<div class="form-group">
			住所：<input type ="text" name = "address" value="${bean.address}"><br>
		</div>
		<div class="form-group">
			電話番号：<input type ="tel" name = "tel" value="${bean.tel}"><br>
		</div>
		<div class="form-group">
			email <input type ="email" name = "email" value="${bean.email}"><br>
		</div>
		<div class="form-group">
			生年月日：${bean.birthDate}<br>
		</div>
		<div class="form-group">
			入会年月日：${bean.joinDate}
		</div>


		<button class="btn btn-primary" type="submit">更新</button>
		<input type="hidden" name ="action" value = "renew2">
		<input type="hidden" name ="id" value = "${bean.id}">
		<input type="hidden" name ="birthDate" value = "${bean.birthDate}">
		<input type="hidden" name ="joinDate" value = "${bean.joinDate}">
		</form>
	</div>
</div>

</body>
</html>