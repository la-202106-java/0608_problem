<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
</head>
<body>
	<a href="/0608_problem/myPageServlet?action=top">TOP</a> |
	<a href="/0608_problem/myPageServlet?action=serch">教科書検索</a> |
	<a href="/0608_problem/myPageServlet?action=regist">教科書登録</a> |
	<c:if test="${!empty logined}">
	こんにちは、${logined.name}さん|
	</c:if>

		<!-- サーブレットまだ -->
	<c:if test="${!empty logined}">
	<a href="/0608_problem/LogoutServlet?action=logout">ログアウト</a>|
	<a href="/0608_problem/MypageServlet?action=mypage">マイページ</a> |
	</c:if>
	<c:if test="${empty logined}">
	<a href="/0608_problem/LoginServlet?action=login">ログイン</a>|
	<a href="/0608_problem/MemberRegistServlet">会員登録</a> |
	</c:if>




</body>
</html>