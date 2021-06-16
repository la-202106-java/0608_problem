<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<a href="/0608_problem/ShowItemServlet?action=top">ようこそ</a>|
<c:forEach items="${categories}" var="category">
	<a href="/0608_problem/ShowItemServlet?action=list&code=${category.code}">${category.name}</a>|
</c:forEach>
<c:if test="${customer.name eq null}">
<a href="/0608_problem/login.jsp">ログイン</a>|
</c:if>
<c:if test="${customer.name ne null}">
こんにちは、${customer.name}さん|
<a href="/0608_problem/LoginServlet?action=logout">ログアウト</a>|
</c:if>
<a href="/0608_problem/CartServlet?action=show">カートを見る</a>

<form action="/0608_problem/ShowItemServlet?action=search" method="post">
<input type="text" name="keyword" size="20">
<input type="submit" value="検索">
</form>