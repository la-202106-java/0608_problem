<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

ソート：<a href="/0608_problem/ItemServlet2?action=sort&key=price_asc">値段の低い順</a> ,
<a href="/0608_problem/ItemServlet2?action=sort&key=price_desc">値段の高い順</a><br>

<form action="/0608_problem/ItemServlet2" method="post">
追加：商品名<input type="text" name="name">
価格<input type="text" name="price" size="5">を<input type="submit" value="追加">
<input type="hidden" name="action" value="add">
</form>

<form action="/0608_problem/ItemServlet2" method="post">
検索：商品名<input type="text" name="name"> | 価格<input type="text" name = "minprice" size ="5">円以上<input type="text" name="maxprice" size="5">円以下の商品を
<input type="submit" value="検索">
<input type="hidden" name="action" value="search">
</form>

<form action="/0608_problem/ItemServlet2" method="post">
削除：商品番号<input type="text" name="code" size="5">
番の商品を<input type="submit" value="削除">
<input type="hidden" name="action" value="delete">
</form>