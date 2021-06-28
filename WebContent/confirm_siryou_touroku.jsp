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

<c:if test ="${Inputcheck eq 'True' }">
<h3><font color="red">入力された資料の情報が既に目録に登録されていました。</font></h3>
</c:if>

<form action="/0608_problem/Shiryou_tourokuServlet" method="post">
<h3>正しい情報が入力されていれば、『登録』ボタンを押してください。</h3>
<input type ="hidden" name ="action" value ="add">

 <button type="submit" class="btn btn-primary">登録</button>
</form>
<br>
<form action="/0608_problem/Shiryou_tourokuServlet" method="post">
<input type ="hidden" name ="action" value ="mod">
  <button type="submit" class="btn btn-primary">修正</button>
</form>
<br>

<table  class="table table-striped table-hover " style="width:40%">
<tr><td>ISBN番号</td><td>${beans.isbn}</td></tr>
<tr><td>資料名</td><td>${beans.title}</td></tr>
<tr><td>カテゴリコード</td><td>${beans1.name}</td></tr>
<tr><td>出版名	</td><td>${beans.authur}</td></tr>
<tr><td>出版社	</td><td>${beans.publisher}</td></tr>
<tr><td>出版日</td><td>${beans.publicationDate}</td></tr>
</table>

</body>
</html>