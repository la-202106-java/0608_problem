<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報更新ページ</title>
</head>
<body>

	<h1>会員情報更新</h1>
	<form action="/0608_problem/MembersServlet" method="get">
		氏名：<input type="text" name="name" value ="${member.name}"><br>
		郵便番号：<input type="text" name="postal_code" value ="${member.postalCode}"><br>
		住所：<input type="text" name="address" value ="${member.address}"><br>
		電話番号：<input type="text" name="tel" value ="${member.tel}"><br>
		e-Mailアドレス：<input type="text" name="email_address" value ="${member.emailAddress}"><br>
		<input type="submit" value="更新">
		<input type="hidden" name="action" value="update">
	</form>

</body>
</html>