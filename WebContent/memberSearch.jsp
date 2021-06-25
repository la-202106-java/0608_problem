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
    	<p>IDが入力された場合はIDのみで検索します</p><br>
        <p>会員ID:<br>
        <input type = "text" name = "id" value="${search_member_id}"></p>
        <p>会員名:<br>
        <input type = "text" name = "name" value="${search_member.name}"></p>
        <p>住所:<br>
            <input type = "text" name = "address" value="${search_member.address}"></p>
        <p>電話番号:<br>
            <input type = "tel" name = "tel" value="${search_member.tel}"></p>
        <p>E-mail:<br>
            <input type = "text" name = "email" value="${search_member.email}"></p>
        <p>生年月日:<br>
            <input type = "date" name = "date" value="${search_member.birthday}"></p>
        <p>
        <input type="hidden" name = "action" value = "search">
        <input type = "submit" value = "検索"></p>
    </form>

	<hr>

    <jsp:include page="/memberSearchResult.jsp" />

    </body>
    </html>