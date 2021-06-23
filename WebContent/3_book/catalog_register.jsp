<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>目録登録</title>
</head>
<body>

<h2>目録登録</h2>

<h4>新規資料情報を入力してください</h4>

${message}

<form action="/0608_problem/CatalogRegistServlet?action=regist" method="post">
資料名：<input type ="text" name = "title" value="${title}"><br>
ISBN番号：<input type ="text" name = "isbn" value="${isbn}"><br>
分類コード：<input type ="number" name = "code" value="${code}"><br>
著者：<input type ="text" name = "author" value="${author}"><br>
出版社：<input type ="text" name = "publisher" value="${publisher}"><br>
出版日：<input type ="date" name = "publication_date" value="${publicationDate}"><br>
<br>
<input type="submit" value = 登録>
</form>


</body>
</html>