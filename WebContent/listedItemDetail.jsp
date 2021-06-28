<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
<title>教科書売買サイト</title>
</head>
<body>

<c:if test="${user == 'admin'}">
<jsp:include page="/adminMenu.jsp" />
</c:if>
<c:if test="${user != 'admin'}">
<jsp:include page="/menu.jsp" />
</c:if>

<h1>教科書詳細</h1>

<c:if test="${item.id<=0 or item == null}">
	<p>該当する商品がありません。</p>
</c:if>

<c:if test="${item.id>0 and item != null}">

	<img src="${item.image}">

	<table>
	<tr><td>ISBN</td><td>${item.isbn}</td></tr>
	<tr><td>タイトル</td><td>${item.title}</td></tr>
	<tr><td>分類</td><td>${departments.get(item.departmentCode)}</td></tr>
	<tr><td>著者名</td><td>${item.author}</td></tr>
	<tr><td>売値</td><td>${item.price}</td></tr>
	<tr><td>状態</td><td>${item.condition}</td></tr>
	<tr><td>在庫</td>
		<td>
			<c:if test="${item.inStock}">〇</c:if>
			<c:if test="${!item.inStock}">×</c:if>
		</td>
	</tr>

	</table>

	<br>

	<%--出品ユーザと管理者ではない--%>
	<c:if test="${user != 'admin' and !is_my_item}">
		<form action="/0608_problem/ListedItemBuyServlet" method="post">
		<input type="hidden" name="action" value="buy">
		<input type="hidden" name="item_id" value="${item.id}">
		<c:if test="${item.inStock}">
		<input type="submit" value="購入">
		</c:if>
		<%-- 在庫なしなら購入をグレーアウト --%>
		<c:if test="${!item.inStock}">
		<input type="submit" value="購入" disabled="disabled">
		</c:if>
		</form>
	</c:if>

	<%-- 出品者または管理者--%>
	<c:if test="${user == 'admin' or is_my_item}">
		<form action="/0608_problem/ListedItemChangeServlet" method="post">
		<input type="hidden" name="action" value="change">
		<input type="hidden" name="item_id" value="${item.id}">
		<input type="submit" value="変更">
		</form>
		<%-- 削除 DeleteServletに移動 --%>
		<form action="/0608_problem/DeleteServlet" method="post">
		<input type="hidden" name="action" value="delete">
		<input type="hidden" name="item_id" value="${item.id}">
		<input type="submit" value="削除">
		</form>
	</c:if>

</c:if>

</body>
</html>