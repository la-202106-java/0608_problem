<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<a href="/0608_problem/ShowItemServlet?action=top">ようこそ</a>|
<c:forEach items="${categories}" var="category">
	<a href="/0608_problem/ShowItemServlet?action=list&code=${category.code}">${category.name}</a>|
</c:forEach>
<c:if test="${!empty customer}">
こんにちは、${customer.name}さん|
<a href="/0608_problem/LoginServlet?action=logout">ログアウト</a>|
</c:if>
<c:if test="${empty customer}">
<a href="/0608_problem/LoginServlet">ログイン</a>|
<a href="/0608_problem/RegistServlet">新規登録</a>|
</c:if>
<a href="/0608_problem/CartServlet?action=show">カートを見る</a>
<br>
<form action="/0608_problem/ShowItemServlet" method="get">
<input type="hidden" name="action" value="search">
<input type="text" name="keyword">
<input type="submit" value="検索">
</form>