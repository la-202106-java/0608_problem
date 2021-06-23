<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
<title>Insert title here</title>
</head>
<body>

<table border="1">
<tr><td>ID</td><td>1</td></tr>
<tr><td>ISBN</td><td>12345678</td></tr>
<tr><td>タイトル</td><td>ドラえもん</td></tr>
<tr><td>分類</td><td>文学部系</td></tr>
<tr><td>著者名</td><td>藤子不二雄</td></tr>
<tr><td>売値</td><td>1000円</td></tr>

</table>

<!-- Servlet先まだ未記入　アクション操作未記入 -->
<form action="/0608_problem/Servlet?action=" method="post">
<input type="hidden" name="item_code" value="${item.value.code}">
<input type="submit" value="購入">
</form>


<!-- Servlet先まだ未記入　アクション操作未記入 -->
<form action="/0608_problem/Servlet?action=" method="post">
<input type="hidden" name="item_code" value="${item.value.code}">
<input type="submit" value="変更">
</form>

<!--DeleteServletに移動 -->
<form action="/0608_problem/DeleteServlet?action=deleteCheck" method="post">
<input type="hidden" name="item_code" value="${item.value.code}">
<input type="submit" value="削除">
</form>


</body>
</html>