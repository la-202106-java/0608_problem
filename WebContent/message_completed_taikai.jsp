<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 会員の退会</title>
</head>
<body>

<jsp:include page="/menu.jsp" />
<h2>会員の新規登録</h2>
<h3>退会完了しました。</h3>
<br>

<table border="1">
<tr><td>会員ID</td><td>${imembers.id}</td></tr>
<tr><td>氏名</td><td>${imembers.name }</td></tr>
<tr><td>住所</td><td>${imembers.address }</td></tr>
<tr><td>電話番号</td><td>${imembers.tel}</td></tr>
<tr><td>E-Mail</td><td>${ imembers.eMail}</td></tr>
<tr><td>生年月日</td><td>${ imembers.birth}</td></tr>
<tr><td>入会年月日</td><td>${ imembers.joinDate}</td></tr>
<tr><td>退会年月日</td><td>${ imembers.withdrawalDate}</td></tr>
	</table>

</body>
</html>