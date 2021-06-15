<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
 <script type="text/javascript">
 function f1() {
     document.forms['searchForm'].elements['sort'].value=0;
     document.forms['searchForm'].submit();

 }
 function f2() {
     document.forms['searchForm'].elements['sort'].value=1;
     document.forms['searchForm'].submit();

 }
  </script>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
ソート：<a href="javascript:void(0);" onclick="f1();">値段の低い順</a>、
<a href="javascript:void(0);" onclick="f2();">値段の高い順</a><br>

<form action="ItemServlet"method="post"  name="searchForm" >
検索：商品名<input type="text" name="name" value="${name }">｜価格<input type="text" name="lower"  value="${lower}">円以上
<input type="text" name="upper"  value="${uppera}">円以下の商品を<input type="submit" value="検索">
<input type="hidden" name="action"  value="serach">
<input type="hidden" name="sort" id="sort" value="0">
</form>


<form action="ItemServlet"method="post">
追加：商品名<input type="text" name="name">
価格<input type="text" name="price">を<input type="submit" value="追加">
<input type="hidden" name="action" value="add">
</form>

<form action="ItemServlet"method="post">
修正：商品番号<input type="text" name="code">番の値段を
<input type="text" name="price">に<input type="submit" value="変更">
<input type="hidden" name="action" value="update">
</form>
<table border="1">

<tr><td>NO</td><td>商品名</td><td>値段</td></tr>
<c:forEach items="${items }" var="item">
<tr><td>${item.code }</td><td>${item.name }</td><td>${item.price }</td></tr>

</c:forEach>
</table>
</body>
</html>