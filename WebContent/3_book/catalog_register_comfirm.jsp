<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>目録登録確認</title>
</head>
<body>

<h2>目録登録確認</h2>
<h4>以下の資料を目録に登録してもよろしいでしょうか</h4>
<form action="/0608_problem/CatalogRegistServlet?action=confirm&title=${title}&isbn=${isbn}&code=${code}&author=${author}&publisher=${publisher}&publicationDate=${publicationDate}" method="post">
資料名：${title}<br>
ISBN番号：${isbn}<br>
分類コード：${code}<br>
著者：${author}<br>
出版社：${publisher}<br>
出版日：${publicationDate}<br>
<a href="/0608_problem/CatalogRegistServlet?action=back&title=${title}&isbn=${isbn}&code=${code}&author=${author}&publisher=${publisher}&publicationDate=${publicationDate}">戻る</a>
<input type="submit" value = 登録>
</form>

</body>
</html>