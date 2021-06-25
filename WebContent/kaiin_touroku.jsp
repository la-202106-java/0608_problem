<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setAttribute("basyo", 1);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 会員の新規登録</title>
</head>
<script>
	function validateForm() {
		var simei = document.forms["form1"]["name"].value;
		if (simei == "") {
			alert("氏名を入力してください。");
			return false;
		}
		var jusho = document.forms["form1"]["address"].value;
		if (jusho == "") {
			alert("住所を入力してください。");
			return false;
		}
		var denwa = document.forms["form1"]["tel"].value;
		if (denwa == "") {
			alert("電話番号を入力してください。");
			return false;
		}
		var meiru = document.forms["form1"]["mail"].value;
		if (meiru == "") {
			alert("E-Mailを入力してください。");
			return false;
		}
		var tanjoubi = document.forms["form1"]["birth"].value;
		if (tanjoubi == "") {
			alert("生年月日を入力してください。");
			return false;
		}
		return true;
	}
</script>

<body>

	<jsp:include page="/menu.jsp" />


	<div style="width: 50%; text-align: center">
		<div class="well bs-component">
			<form class="form-horizontal" onsubmit="return validateForm()"
				name="form1" action="/0608_problem/MemberServlet" method="post">
				<fieldset>
					<legend>会員の新規登録</legend>
					<div class="form-group">
						<label for="inputEmail" class="col-lg-2 control-label">
							氏名：</label>
						<div class="col-lg-10">
							<input type="text" class="form-control" name="name"
								value="${member.name }">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail" class="col-lg-2 control-label">
							住所：</label>
						<div class="col-lg-10">
							<input type="text" class="form-control" name="address"
								value="${member.address }">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail" class="col-lg-2 control-label">
							電話番号：</label>
						<div class="col-lg-10">
							<input type="text" class="form-control" name="tel"
								value="${member.tel }">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail" class="col-lg-2 control-label">
							E-Mail：</label>
						<div class="col-lg-10">
							<input type="email" class="form-control" name="mail"
								value="${member.eMail }">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword" class="col-lg-2 control-label">生年月日：</label>
						<div class="col-lg-10">
							<input type="date" class="form-control" name="birth"
								value="${member.birth }">
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-10 col-lg-offset-2">
							<button type="submit" class="btn btn-primary">登録確認</button>
						</div>
					</div>
				</fieldset>
				<input type="hidden" name="action" value="confirm">
			</form>
		</div>
	</div>

	<!--
<form name="form1" onsubmit="return validateForm()" action="/0608_problem/MemberServlet"method="post">
<input type="hidden" name="action" value="confirm">

  氏名：<input type="text" name="name"value = "${member.name }"><br>
  住所：<input type="text" name="address"value = "${member.address }"><br>
  電話番号：<input type="text" name="tel"value = "${member.tel }"><br>
  E-Mail：<input type="text" name="mail"value = "${member.eMail }"><br>
  生年月日：<input type="date"  name="birth"value = "${member.birth }"><br>
  <input type="submit" value="登録確認">
</form>
 -->
</body>
</html>