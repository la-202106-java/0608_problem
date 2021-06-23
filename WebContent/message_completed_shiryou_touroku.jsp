<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム</title>
</head>
<body>

<jsp:include page="/menu.jsp" />

<h2>下記情報の登録が完了しました</h2>

<br>
<table border="1">
<tr><td>資料ID</td><td>${bbean.id}</td></tr>
<tr><td>資料名</td><td>${beans.title}</td></tr>
<tr><td>ISBN</td><td>${bbean.isbn}</td></tr>
<tr><td>入荷年月日</td><td>${bbean.stockDate}</td></tr>
</table>
</body>
</html>