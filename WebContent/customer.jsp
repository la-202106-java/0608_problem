<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>お客様情報</title>
</head>
<body>
	<h3>お客様情報を入力してください</h3>

	<br>
	${ message }
	<br>

<form action="/0608_problem/LoginServlet?action=add" method="post">
	<table border="1">
		<tr>
		<td>お名前</td><td><input type="text" name="name"></td>
		</tr>
		<tr>
		<td>住所</td><td><input type="text" name="address"></td>
		</tr>
		<tr>
		<td>電話番号</td><td><input type="text" name="tel"></td>
		</tr>
		<tr>
		<td>e-mail</td><td><input type="text" name="email"></td>
		</tr>
		<tr>
		<td>パスワード</td><td><input type="password" name="password"></td>
		</tr>
	</table>
<input type="submit" value="登録">
</form>
</body>
</html>