<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>資料貸出確認</title>
</head>
<body>
<h2>資料貸出確認</h2>
以下の情報で貸し出します<br>
よろしいですか？<br>
会員番号：${user.id}<br>
氏名：${user.name}<br>
資料ID:${book.id}<br>
資料名：${book.title}<br>

<form method="get" action="/0608_problem/BookLendingServlet">
<input type="hidden" name = "bookID" value="${book.id}">
<input type="hidden" name = "userID" value="${user.id}">
<input type="hidden" name="action" value="finish">
<input type="submit" value = 貸出完了>
</form>

<form method="get" action="/0608_problem/BookLendingServlet">
<input type="hidden" name = "bookID" value="${book.id}">
<input type="hidden" name = "userID" value="${user.id}">
<input type="hidden" name="action" value="continue">
<input type="submit" value = 貸出続行>
</form>
</body>
</html>