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
<input type="text" name="isbn"><br>
タイトル：<br>
<input type="text" name="title"><br>
分類：<br>
<select name="department_code">
	  <option value="-1">----------</option>
      <option value="0">0:文学部系</option>
      <option value="1">1:教育学部系</option>
      <option value="2">2:法学部系</option>
      <option value="3">3:社会学部系</option>
      <option value="4">4:経済学部系</option>
      <option value="5">5:理学部系</option>
      <option value="6">6:医学部系</option>
      <option value="7">7:歯学部系</option>
      <option value="8">8:薬学部系</option>
      <option value="9">9:工学部系</option>
      <option value="10">10:農学部系</option>
      </select><br><br>
著者名：<br>
<input type="text" name="author"><br><br>
売値：<br>
<input type="text" name="price_min" size="15">円以上 |
<input type="text" name="price_max" size="15">円以下<br>
状態：<br>
<select name="condition">
	<option value="">----</option>
	<option>新品</option>
	<option>未使用</option>
	<option>中古</option>
</select>
<br><br>
<input type="checkbox" name="stock" value="true">在庫有のみ表示<br>
<input type="checkbox" name="my_item" value="true">自分が出品した商品のみ表示<br>
<input type="hidden" name="action" value="search">
<input type="submit" value="検索">
</form>

<hr>

<jsp:include page="/listedItemSearchResult.jsp" />

</body>
</html>