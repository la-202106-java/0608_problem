<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
 <a href="/shopping/ShowItemServlet?action=top">ようこそ</a>

 <c:forEach items="${categories }" var="category">
<a href="ShowItemServlet?action=list&code=${category.code }&page=0">${category.name}</a>

 </c:forEach>

 <a href="/shopping/CartServlet?action=show">カートを見る</a>
<br>
<form action="ShowItemServlet" method="get" >
<input type="hidden" value="search" name="action">
<input type="hidden" value="0" name="page">
<input type="text" name="name" ><input type="submit" value="検索" >
</form>