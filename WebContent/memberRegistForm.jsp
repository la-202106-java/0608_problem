<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
<title>Insert title here</title>
</head>
<body>

    <h1>会員登録</h1>
    <form action =/0608_problem/MemberFormServlet method ="post">
        <p>名前:<br>
        <input type = "text" name = "name"></p>
        <p>住所:<br>
            <input type = "text" name = "address"></p>
        <p>電話番号:<br>
            <input type = "text" name = "tel"></p>
        <p>生年月日:<br>
            <input type = "date" name = "date"></p>
        <p>E-mail:<br>
            <input type = "text" name = "email"></p>
        <p>パスワード:<br>
            <input type = "password" name = "pass"></p>
            <input type="hidden" name = "action" value = "create">
            <p><input type = "submit" value = "登録"></p>
    </form>
    </body>
    </html>