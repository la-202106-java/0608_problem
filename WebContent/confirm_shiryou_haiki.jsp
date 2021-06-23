<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 資料の廃棄</title>
</head>
<body>

<jsp:include page="/menu.jsp" />
<h2>資料の廃棄確認</h2>

<form action="/0608_problem/Shiryou_haikiServlet" method="post">

<table border="1">
<tr><td>資料ID</td><td>ISBN番号</td><td>入荷年月日</td><td>資料名</td></tr>
<tr><td><input type="hidden" name="material_id" value=${material_id }>${material_id }</td><td>${isbn }</td><td>${stockDate }</td><td>${title }</td></tr>
</table>

<br>

<p>正しい情報が入力されていれば、『廃棄』ボタンを押してください。</p>
<table>
<tr><td>
<input type="submit" value="廃棄"><input type ="hidden" name ="action" value ="dispose_ok">
</form>
</td>
<td>
<form action="/0608_problem/Shiryou_haikiServlet" method="post">
<input type="submit" value="キャンセル"><input type ="hidden" name ="action" value ="dispose_cancel">
</form>
</td>
</tr>
</table>

</body>
</html>