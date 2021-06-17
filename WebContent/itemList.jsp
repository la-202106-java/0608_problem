<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
 <script type="text/javascript">
 function goUpdate(code) {
	 window.location.href = 'ItemManagement?action=goUpdate&code='+code;
 }
 function deleteItem(code) {
	 window.location.href = 'ItemManagement?action=delete&code='+code;

 }
  </script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table border="1">
<tr><td>コード</td><td>カテゴリコード</td><td>商品名</td><td>価格</td><td>更新</td><td>削除</td></tr>
<c:forEach items="${items  }" var="item">
<tr><td>${item.code }</td><td>${item.category }</td><td>${item.name }</td><td>${item.price }</td>
<td><input type="button" onclick="goUpdate(${item.code})" value="更新"></td>
<td><input type="button" onclick="deleteItem(${item.code})" value="削除"></td>

<td></td>
</tr>

</c:forEach>
</table>
<a href="ItemManagement?action=regist" >新規登録</a>


</body>
</html>