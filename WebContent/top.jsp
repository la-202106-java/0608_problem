<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Topページ</title>
</head>
<body>
<h1>新宿トラベル</h1>

<c:choose>
	<c:when test = "${isLogin eq 'true'}">
		<a href="/0608_problem/ShowPlan?action=logout">ログアウト</a>
		<a href="/0608_problem/menu.jsp">会員メニュー</a> <!-- jsp適当なので要変更 -->
	</c:when>
	<c:otherwise><a href="/0608_problem/login.jsp">ログイン</a></c:otherwise>
</c:choose>
<!--  |<a href="">会員メニュー</a> -->

<form action="" method="post">
チェックイン<input type="date"  name="checkIn">
チェックアウト<input type="date" name="checkOut">
<input type="submit" value="検索">
<hr>
<br>
宿名で絞り込む：<input type="text" name="name">
<input type="submit" value="検索">
<br>
宿の場所で絞り込む：<input type="text" name="place">
<input type="submit" value="検索">
<br>
金額で絞り込む：
下限<input type="number" name="lower">
上限<input type="number" name="upper">
<input type="submit" value="検索">
</form>

</body>
</html>