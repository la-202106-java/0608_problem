<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
<title>Insert title here</title>
</head>
<body>

<h1>教科書検索結果</h1>

<table border="1">
<tr><td>ID</td><td>ISBN</td><td>タイトル</td>
    <td>分類</td><td>著者名</td><td>売値</td><td>在庫</td></tr>


<tr>
	<td align="center">1</td>
	<td align="center">12345678</td>
	<td align="center">ドラえもん</td>
	<td align="right">文学部系</td>
	<td align="center">藤子不二雄</td>
	<td align="right">1000円</td>
	<td align="center">有</td>


<td>
	<input type="hidden" name="item_code" value="${item.value.code}">
	<input type="submit" value="詳細">
</td>
</tr>

	<tr>
	<td align="center">2</td>
	<td align="center">12345679</td>
	<td align="center">アンパンマン</td>
	<td align="right">理学部系</td>
	<td align="center">藤子不二雄A</td>
	<td align="right">1500円</td>
	<td align="center">無</td>

<td>
	<input type="hidden" name="item_code" value="${item.value.code}">
	<input type="submit" value="詳細">
</td>
</tr>
</table>

</body>
</html>