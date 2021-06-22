	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 資料の登録</title>
</head>
<body>

<jsp:include page="/menu.jsp" />
<h2>資料の新規登録</h2>

<form action="/0608_problem/Shiryou_tourokuServlet" method="post">
<h3>正しい情報が入力されていれば、『登録』ボタンを押してください。</h3>
<input type ="hidden" name ="action" value ="add">
  <input type="submit" value="登録">
  <input type="submit" value="修正">
</form>
<br>
<table border="1">
<tr><td>ISBN番号</td><td>${beans.isbn}</td></tr>
<tr><td>資料名</td><td>${beans.title}</td></tr>
<tr><td>カテゴリコード</td><td>${beans.categoryCode}</td></tr>
<tr><td>出版名	</td><td>${beans.authur}</td></tr>
<tr><td>出版社	</td><td>${beans.publisher}</td></tr>
<tr><td>出版日</td><td>${beans.publicationDate}</td></tr>
</table>

</body>
</html>