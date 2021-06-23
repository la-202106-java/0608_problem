<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
<title>教科書売買サイト</title>
</head>
<body>

	<jsp:include page="/adminMenu.jsp" />

    <h1>会員検索</h1>
    <form action="/0608_problem/MemberSearchServlet" method ="post">
        <p>会員ID:<br>
        <input type = "text" name = "id"></p>
        <p>会員名:<br>
        <input type = "text" name = "name"></p>
        <p>住所:<br>
            <input type = "text" name = "address"></p>
        <p>電話番号:<br>
            <input type = "tel" name = "tel"></p>
        <p>E-mail:<br>
            <input type = "text" name = "email"></p>
        <p>生年月日:<br>
            <input type = "date" name = "date"></p>
        <p>
        <input type="hidden" name = "action" value = "search">
        <input type = "submit" value = "検索"></p>
    </form>

	<hr>

    <jsp:include page="/memberSearchResult.jsp" />

    </body>
    </html>