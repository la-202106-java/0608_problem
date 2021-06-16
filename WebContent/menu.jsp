<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<a href="/0608_problem/ShowItemServlet?action=top">ようこそ</a>|
<c:forEach items="${categories}" var="category">
	<a href="/0608_problem/ShowItemServlet?action=list&code=${category.code}">${category.name}</a>|
</c:forEach>

<a href="/0608_problem/CartServlet?action=show">カートを見る</a>
<br>
	<form action="/0608_problem/ShowItemServret" method="get">
	 <input type ="hidden" name ="action" value ="search">
  <input type="search" name="keyword">
  <input type="submit" value="検索">
</form>