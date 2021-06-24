<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップメニュー</title>
</head>
<body>
<h1>管理者メニュー</h1>
<a href="/0608_problem/adminMember.jsp">会員情報の検索</a>|
<a href="/0608_problem/adminAddInn.jsp">宿情報の追加</a>|
<a href="/0608_problem/adminSearchInn.jsp">宿情報の検索</a>|
<a href="/0608_problem/adminAddPlan.jsp">プラン情報の追加</a>|
<a href="/0608_problem/adminSearchPlan.jsp">プラン情報の検索</a>|
<form action="/0608_problem/admin/login" method="post" style="display:inline">
<input type="submit" value="ログアウト">
<input type="hidden" name="action" value="logout">
</form>
<hr>
</body>
</html>