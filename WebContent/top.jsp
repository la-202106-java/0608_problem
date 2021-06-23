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

<h3>教科書売買サイトへようこそ</h3>

<h2>新着</h2>

<table>
<tr><td>タイトル</td><td>ドラえもん</td></tr>
<tr><td>著者名</td><td>藤子不二雄</td></tr>
<tr><td>売値</td><td>1000円</td></tr>
<tr><td><form><input type="submit" value="詳細"></form></td></tr>
</table>

<table>
<tr><td>タイトル</td><td>ボッコちゃん</td></tr>
<tr><td>著者名</td><td>星新一</td></tr>
<tr><td>売値</td><td>600円</td></tr>
<tr><td><form><input type="submit" value="詳細"></form></td></tr>
</table>

<table>
<tr><td>タイトル</td><td>わたしの隣の王国</td></tr>
<tr><td>著者名</td><td>七河迦南</td></tr>
<tr><td>売値</td><td>1100円</td></tr>
<tr><td><form><input type="submit" value="詳細"></form></td></tr>
</table>

</body>
</html>