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
    <form action =/0608_problem/MemberRegistServlet method ="post">
        <p>名前:<br>
        <input type = "text" name = "name" value="${setName}"></p>
        <p>住所:<br>
            <input type = "text" name = "address" value="${setAddress}"></p>
        <p>電話番号:<br>
            <input type = "text" name = "tel" value="${setTel}"></p>
        <p>生年月日:<br>
            <input type = "date" name = "date" value="${setBirthday}"></p>
        <p>E-mail:<br>
            <input type = "text" name = "email" value="${setEmail}"></p>
        <p>パスワード:<br>
            <input type = "password" name = "pass" value="${setPass}"></p>
            <input type="hidden" name = "action" value = "create">
            <p><input type = "submit" value = "登録"></p>
    </form>
    </body>
    </html>