<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>資料登録</title>
</head>
<body>

<h2>資料登録（新規目録）</h2>
<h4>新規資料情報、目録情報を入力してください</h4>

${message}
<c:if test="${empty code}" >
<form action="/0608_problem/BookRegistServlet?action=bookConfirm" method="post">
資料名：<input type ="text" name = "title" value="${title}"><br>
ISBN番号：<input type ="text" name = "isbn" value="${isbn}"><br>
入荷年月日：<input type ="date" name = "arrivalDate" value="${arrivalDate}"><br>
<br>
分類コード：<input type ="number" name = "code"><br>
著者：<input type ="text" name = "author"><br>
出版社：<input type ="text" name = "publisher"><br>
出版日：<input type ="date" name = "publicationDate"><br>
<br>
<input type="submit" value = 登録>
</form>
</c:if>

<c:if test="${!empty code}" >
<form action="/0608_problem/BookRegistServlet?action=bookConfirm" method="post">
資料名：<input type ="text" name = "title" value="${title}"><br>
ISBN番号：<input type ="text" name = "isbn" value="${isbn}"><br>
入荷年月日：<input type ="date" name = "arrivalDate" value="${arrivalDate}"><br>
<br>
分類コード：<input type ="number" name = "code" value="${code}"><br>
著者：<input type ="text" name = "author" value="${author}"><br>
出版社：<input type ="text" name = "publisher" value="${publisher}"><br>
出版日：<input type ="date" name = "publicationDate" value="${publicationDate}"><br>
<br>
<input type="submit" value = 登録>
</form>
</c:if>

</body>
</html>

