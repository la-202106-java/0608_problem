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

    <h1>会員情報変更</h1>
    <form action =/0608_problem/MemberRegistChangeServlet method ="post">
        <p>名前:<br>
        <input type = "text" name = "name" value="${member.name}"></p>
        <p>住所:<br>
            <input type = "text" name = "address" value="${member.address}"></p>
        <p>電話番号:<br>
            <input type = "text" name = "tel" value="${member.tel}"></p>
        <p>生年月日:<br>
            <input type = "date" name = "date" value="${member.birthday}" ></p>
        <p>E-mail:<br>
            <input type = "text" name = "email" value="${member.email}"></p>
        <p>パスワード:<br>
            <input type = "password" name = "pass" value="${member.pass}"></p>
            <input type="hidden" name = "action" value = "check">
            <p><input type = "submit" value = "変更"></p>
    </form>
    </body>
    </html>