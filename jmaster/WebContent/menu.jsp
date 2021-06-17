<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <a href="/jmaster/ShowItemServlet?action=top">ようこそ</a>|
    <c:forEach items="${categories}" var="category">
    <a href="/jmaster/ShowItemServlet?action=list&code=${category.code }">${category.name }</a>|
    </c:forEach>
    <c:if test="${!empty logined}">
	こんにちは、${logined.name}さん|
	</c:if>
	<c:if test="${!empty logined}">
	<a href="/jmaster/LoginServlet?action=logout">ログアウト</a>|
	</c:if>
	<c:if test="${empty logined}">
	<a href="/jmaster/LoginServlet">ログイン</a>|
	</c:if>


    <a href="/jmaster/CartServlet?action=show">カートを見る</a><br>

    <form action="/jmaster/ShowItemServlet" method="post">
	<input type="search" name="keyword" size="15" value="${setName}">
	<input type="submit" value="検索">
	<input type="hidden" name="action" value="search">
	</form>
