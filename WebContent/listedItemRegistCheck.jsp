<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
<title>教科書売買サイト</title>
</head>
<body>

<c:if test="${user == 'admin'}">
<jsp:include page="/adminMenu.jsp" />
</c:if>
<c:if test="${user != 'admin'}">
<jsp:include page="/menu.jsp" />
</c:if>


<h1>教科書登録</h1>
<h3>この情報で登録しますか？</h3>

<table>
<tr><td>ISBN</td><td>${regist_item.isbn}</td></tr>
<tr><td>タイトル</td><td>${regist_item.title}</td></tr>
<tr><td>分類</td><td>${departments.get(regist_item.departmentCode)}</td></tr>
<tr><td>著者名</td><td>${regist_item.author}</td></tr>
<tr><td>売値</td><td>${regist_item.price}</td></tr>
<tr><td>状態</td><td>${regist_item.condition}</td></tr>


</table>
<br>

<form action="/0608_problem/ListedItemRegistServlet" method="post">

	<input type="hidden" name="isbn" value="${regist_item.isbn}">
	<input type="hidden" name="title" value="${regist_item.title}">
	<input type="hidden" name="department_code" value="${regist_item.departmentCode}">
	<input type="hidden" name="author" value="${regist_item.author}">
	<input type="hidden" name="price" value="${regist_item.price}">
	<input type="hidden" name="condition" value="${regist_item.condition}">

	<input type="hidden" name="action" value="regist">

	<input type="submit" name="cancel" value="キャンセル">
	<input type="submit" name="regist" value="登録する">
</form>
</body>
</html>