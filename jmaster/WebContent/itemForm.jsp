<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

ソート：<a href="/jmaster/ItemServlet?action=sort&key=price_asc">値段の低い順</a>
<a href="/jmaster/ItemServlet?action=sort&key=price_desc">値段の高い順</a><br>

<form action="/jmaster/ItemServlet" method="post">
追加：商品名<input type="text" name="name" value="${ setNameAdd}">
価格<input type="text" name="price" size="5" value="${ setPriceAdd}">を<input type="submit" value="追加">
<input type="hidden" name="action" value="add">
</form>

<form action="/jmaster/ItemServlet" method="post">
検索：商品名<input type="text" name="name" size="5" value="${ setName}"> | <input type="text" name="price2" size="5" value="${ setPrice2}">円以上 <input type="text" name="price" size="5" value="${ setPrice}">円以下の商品を
<input type="submit" value="検索">
<input type="hidden" name="action" value="search">
</form>

<form action="/jmaster/ItemServlet" method="post">
削除：商品番号<input type="text" name="code" size="5" value="${setCode}">
番の商品を<input type="submit" value="削除">
<input type="hidden" name="action" value="delete">
</form>
