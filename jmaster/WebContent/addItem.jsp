<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h3>商品登録</h3>

<form action="/jmaster/ItemServlet2" method="post">
カテゴリコード<input type="text" name="category_code" size="5">
名前<input type="text" name="name" size="5">
価格<input type="text" name="price" size="5">
<input type ="hidden" name ="action" value ="add2">
<input type="submit" name="登録">


</form>


</body>
</html>