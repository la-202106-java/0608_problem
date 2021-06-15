<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	ソート：<a href="/0608_problem/ItemServlet2?action=sort&key=price_asc">値段の低い順</a>、
	<a href="/0608_problem/ItemServlet2?action=sort&key=price_desc">値段の高い順</a>

	<form action="/0608_problem/ItemServlet2" method="post">
	追加：商品名<input type="text" name="name">
	価格<input type="text" name="price" size="5">
	を<input type="submit" value="追加">
	<input type="hidden" name="action" value="add">
	</form>

	<form action="/0608_problem/ItemServlet2" method="post">
	検索：
	商品名<input type="text" name="name" size="5" value="${search_name}"> |
	価格<input type="text" name="min_price" size="5" value="${search_min}">円以上
	<input type="text" name="max_price" size="5" value="${search_max}">円以下の商品を
	<input type="submit" value="検索">
	<input type="hidden" name="action" value="search">
	</form>

	<form action="/0608_problem/ItemServlet2" method="post">
	削除：商品番号<input type="text" name="code" size="5">
	番の商品を<input type="submit" value="削除">
	<input type="hidden" name="action" value="delete">
	</form>
</body>
</html>