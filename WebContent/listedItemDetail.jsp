<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
<title>Insert title here</title>
</head>
<body>

<table border="1">
<tr><td>ID</td><td>${item.id}</td></tr>
<tr><td>ISBN</td><td>${item.isbn}</td></tr>
<tr><td>タイトル</td><td>${item.title}</td></tr>
<tr><td>分類</td><td>${item.department_code}</td></tr>
<tr><td>著者名</td><td>${item.author}</td></tr>
<tr><td>売値</td><td>${item.price}</td></tr>
<tr><td>在庫</td><td>${item.condition}</td></tr>


</table>



<%--出品ユーザと管理者ではない--%>
<c:if test="${user != 'admin'} and ${user != 'is_my_item'}">
<!-- アクション操作未記入 -->
<form action="/0608_problem/ListedItemBuyServlet?action=buy" method="post">
<input type="hidden" name="item_code" value="${item.code}">
<input type="submit" value="購入">
</form>
</c:if>

<%-- 出品者または管理者--%>
<c:if test="${user == 'admin'} or ${user == 'is_my_item'} ">
<%-- アクション操作未記入 --%>
<form action="/0608_problem/ListedItemChengeServlet?action=change" method="post">
<input type="hidden" name="item_code" value="${item.code}">
<input type="submit" value="変更">
</form>
<%--DeleteServletに移動 --%>
<form action="/0608_problem/DeleteServlet?action=deleteCheck" method="post">
<input type="hidden" name="item_code" value="${item.code}">
<input type="submit" value="削除">
</form>
</c:if>

</body>
</html>