<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>資料登録確認</title>
</head>
<body>

<h2>資料登録確認</h2>
<h4>以下の資料は目録に存在しません</h4>
<form action="/ShinjukuLibrary/BookRegistServlet?action=catalog_regist&title=${title}&isbn=${isbn}" method="post">
資料名：${title}<br>
ISBN番号：${isbn}<br>
<br>
目録に登録しますか？<br>
<a href="/ShinjukuLibrary/3_book/book_register.jsp">いいえ</a>
<input type="submit" value = はい>
</form>

</body>
</html>