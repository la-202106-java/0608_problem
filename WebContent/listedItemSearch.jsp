<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<br>

<h1>教科書検索</h1>

<form action="/0608_problem/ListedItemSearchServlet" method="post">
ISBN：<br>
<input type="text" name="isbn"value="${search_isbn}"><br>
タイトル：<br>
<input type="text" name="title"value="${search_title}"><br>
分類：<br>
<select name="department_code">
	<option value="-1">----------</option>
	<c:forEach var="i" begin="0" end="${departments_size -1}" step="1">
		<c:if test="${search_department_code==i}">
			<option value="${i}" selected="selected">${i} : ${departments.get(i)}</option>
		</c:if>
		<c:if test="${empty search_department or search_department_code!=i}">
			<option value="${i}">${i} : ${departments.get(i)}</option>
		</c:if>
	</c:forEach>
      </select><br><br>
著者名：<br>
<input type="text" name="author" value="${search_author}"><br><br>
売値：<br>
<input type="text" name="price_min" size="15" value="${search_price_min}">円以上 |
<input type="text" name="price_max" size="15" value="${search_price_max}">円以下<br>
状態：<br>
<select name="condition">
	<option value="">----</option>
	<c:forEach items="${conditions}" var="condition">
		<c:if test="${condition==search_condition}">
			<option selected="selected">${condition}</option>
		</c:if>
		<c:if test="${empty search_condition or condition!=search_condition}">
			<option>${condition}</option>
		</c:if>
	</c:forEach>
</select>
<br><br>
<c:if test="${search_stock == 'true'}">
	<input type="checkbox" name="stock" value="true" checked="checked">在庫有のみ表示<br>
</c:if>
<c:if test="${empty search_stock}">
	<input type="checkbox" name="stock" value="true">在庫有のみ表示<br>
</c:if>
<c:if test="${search_my_item == 'true'}">
	<input type="checkbox" name="my_item" value="true" checked="checked">自分が出品した商品のみ表示<br>
</c:if>
<c:if test="${empty search_my_item}">
	<input type="checkbox" name="my_item" value="true">自分が出品した商品のみ表示<br>
</c:if>

<input type="hidden" name="action" value="search">
<input type="submit" value="検索">
</form>

<hr>
<c:if test="${!empty result_num}">
	<h1>教科書検索結果</h1>
	<p>${result_num}件見つかりました。</p>
</c:if>

<jsp:include page="/listedItemSearchResult.jsp" />

</body>
</html>