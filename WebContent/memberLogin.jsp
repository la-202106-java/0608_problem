<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
<title>教科書販売サイト</title>
</head>
<body>

    <h1>ログイン</h1>
    <form action =/0608_problem/LoginServlet method ="post">
        <p>メールアドレス:<br>
        <input type = "text" name = "email"></p>
        <p>パスワード:<br>
            <input type = "password" name = "pass"></p>
            <input type="hidden" name = "action" value = "login">
            <p><input type = "submit" value = "ログイン"></p>
    </form>
    </body>
    </html>