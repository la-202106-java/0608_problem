<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップメニュー</title>
</head>
<body>
<h1>管理者メニュー</h1>
<a href="/0608_problem/admin/top?link=member">会員情報の検索</a>|
<a href="/0608_problem/admin/top?link=addInn">宿情報の追加</a>|
<a href="/0608_problem/admin/top?link=searchInn">宿情報の検索</a>|
<a href="/0608_problem/admin/top?link=addPlan">プラン情報の追加</a>|
<a href="/0608_problem/admin/top?link=searchPlan">プラン情報の検索</a>|
<a href="/0608_problem/admin/top?link=reservation">予約の検索</a>|
<c:if test="${not empty isAdminLogin}">
<form action="/0608_problem/admin/login" method="post" style="display:inline">
<input type="submit" value="ログアウト">
<input type="hidden" name="action" value="logout">
</form>
</c:if>
<c:if test="${empty isAdminLogin}">
<form action="/0608_problem/admin/login" method="post" style="display:inline">
<input type="submit" value="ログイン">
<input type="hidden" name="action" value="login">
</form>
</c:if>
<hr>
</body>
</html>