<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>





<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>商品登録</h1>


<form action="/0608_problem/itemServlet" method="post" name = "serch">

<table border="1">
<tr><td>カテゴリコード</td><td><input type="text" name="cotegory" ></td></tr>
<tr><td>名前</td><td><input type="text" name="name" ></td></tr>
<tr><td>価格</td><td><input type="text" name="price" ></td></tr>

</table>

<input type="submit" value="登録">
<input type="hidden" name = "action" value="add">
</form>



</body>
</html>