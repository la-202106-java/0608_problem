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
<h2>資料の廃棄完了</h2>

<p>廃棄年月日が登録されました。</p>

<table border="1">
<tr><td>資料ID</td><td>ISBN番号</td><td>入荷年月日</td><td>廃棄年月日</td></tr>
<tr><td>${bean.id }</td><td>${bean.isbn }</td><td>${bean.stockDate }</td><td><font color="red">${bean.disposalDate }</font></td></tr>
</table>

</body>
</html>