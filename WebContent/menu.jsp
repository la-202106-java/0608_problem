<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import = "la.bean.CustomerBean" %>
<% CustomerBean bean = (CustomerBean)session.getAttribute("bean");%>

<a href="/0608_problem/ShowItemServlet?action=top">ようこそ</a>|
<c:forEach items="${categories}" var="category">
	<a href="/0608_problem/ShowItemServlet?action=list&code=${category.code}">${category.name}</a>|
</c:forEach>

<c:if test="${bean !=null}">
こんにちは、${bean.name}さん｜
</c:if>

<a href="/0608_problem/CartServlet?action=show">カートを見る</a>

<form action = "/0608_problem/ShowItemServlet" method = "post">
<input type="hidden" name="action" value="search">
<input type = "text" name = "keyword">
<input type = "submit" value = "検索">
</form>

