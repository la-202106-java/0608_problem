<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 資料の廃棄</title>
</head>
<body>

<jsp:include page="/menu.jsp" />
<h2>資料の廃棄確認</h2>
<!--  -->
<form action="/0608_problem/Shiryou_haikiServlet" method="post">

<table  class="table table-striped table-hover " style="width:60%">
<tr><td>資料ID</td><td>ISBN番号</td><td>入荷年月日</td><td>資料名</td></tr>
<c:forEach items="${items }" var="item">
<tr><td><input type="hidden" name="material_id" value=${item.material_id }>${item.material_id }</td><td>${item.isbn }</td><td>${item.stockDate }</td><td>${item.title }</td></tr>
</c:forEach>
</table>

<br>

<p>正しい情報が入力されていれば、『廃棄』ボタンを押してください。</p>
<table>
<tr><td>

 <button type="submit" class="btn btn-primary">廃棄</button><input type ="hidden" name ="action" value ="dispose_ok">
</form>
</td>
<td>
<form action="/0608_problem/Shiryou_haikiServlet" method="post">

 <button type="submit" class="btn btn-primary">キャンセル</button><input type ="hidden" name ="action" value ="dispose_cancel">
</form>
</td>
</tr>
</table>

</body>
</html>