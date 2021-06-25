<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>教科書売買サイト</title>
</head>
<body>

<c:if test="${user == 'admin'}">
<jsp:include page="/adminMenu.jsp" />
</c:if>
<c:if test="${user != 'admin'}">
<jsp:include page="/menu.jsp" />
</c:if>

<h1>教科書情報変更</h1>
<h3>この情報で登録しますか？</h3>

<table>
<tr><td>ISBN</td><td>${textbook.isbn}</td></tr>
<tr><td>タイトル</td><td>${textbook.title}</td></tr>
<tr><td>分類</td><td>${departments.get(textbook.departmentCode)}</td></tr>
<tr><td>著者名</td><td>${textbook.author}</td></tr>
<tr><td>売値</td><td>${textbook.price}</td></tr>
<tr><td>状態</td><td>${textbook.condition}</td></tr>
<tr><td>画像URL</td><td>${textbook.image}</td></tr>

</table>
<br>
<img src="${textbook.image}">
<br>

<form action="/0608_problem/ListedItemChangeServlet" method="post">

	<input type="hidden" name="id" value="${textbook.id}">
	<input type="hidden" name="isbn" value="${textbook.isbn}">
	<input type="hidden" name="title" value="${textbook.title}">
	<input type="hidden" name="department_code" value="${textbook.departmentCode}">
	<input type="hidden" name="author" value="${textbook.author}">
	<input type="hidden" name="price" value="${textbook.price}">
	<input type="hidden" name="condition" value="${textbook.condition}">
	<input type="hidden" name="image" value="${textbook.image}">

	<input type="hidden" name="action" value="regist">

	<input type="submit" name="cancel" value="キャンセル">
	<input type="submit" name="regist" value="登録する">
</form>
</body>
</html>