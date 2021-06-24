<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>資料登録確認</title>
</head>
<body>

<h2>資料登録確認（新規目録）</h2>
<h4>以下の資料を登録してもよろしいでしょうか</h4>
資料名：${book.title}<br>
ISBN番号：${book.isbn}<br>
入荷年月日：${book.arrivalDate}<br>
<br>
分類コード：${catalog.code}<br>
著者：${catalog.auther}<br>
出版社：${catalog.publisher}<br>
出版日：${catalog.publicationDate}<br>

<form action="/0608_problem/BookRegistServlet?action=bookBack" method="post">
<input type ="hidden" name = "title" value="${book.title}">
<input type ="hidden" name = "isbn" value="${book.isbn}">
<input type ="hidden" name = "arrivalDate" value="${book.arrivalDate}">
<input type ="hidden" name = "code"  value="${catalog.code}">
<input type ="hidden" name = "author" value="${catalog.auther}">
<input type ="hidden" name = "publisher" value="${catalog.publisher}">
<input type ="hidden" name = "publicationDate" value="${catalog.publicationDate}">
<input type="submit" value = 戻る>
</form>

<form action="/0608_problem/BookRegistServlet?action=bookRegist" method="post">
<input type ="hidden" name = "title" value="${book.title}">
<input type ="hidden" name = "isbn" value="${book.isbn}">
<input type ="hidden" name = "arrivalDate" value="${book.arrivalDate}">
<input type ="hidden" name = "code"  value="${catalog.code}">
<input type ="hidden" name = "author" value="${catalog.auther}">
<input type ="hidden" name = "publisher" value="${catalog.publisher}">
<input type ="hidden" name = "publicationDate" value="${catalog.publicationDate}">
<input type="submit" value = 登録>
</form>

</body>
</html>