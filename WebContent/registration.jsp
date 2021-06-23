<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員登録</title>
</head>
<body>
<h1>会員登録</h1>
<form action="/0608_problem/ShowPlanServlet" method="post">
氏名：<input type="text" name="name"><br>
郵便番号：<input type="text" name="postalCode"><br>
住所：<input type="text" name="address"><br>
電話番号：<input type="text" name="tel"><br>
E-Mail：<input type="text" name="email"><br>
生年月日：<input type="date" name="birthday"><br>
パスワード：<input type="password" name="password"><br>

<input type="hidden" name="action" value="registration">
<input type="submit" value="登録">
</form>
</body>
</html>