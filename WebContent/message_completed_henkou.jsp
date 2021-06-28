<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 会員情報の変更</title>
</head>
<body>

<jsp:include page="/menu.jsp" />
<h2>会員情報の変更</h2>
<h3>変更完了しました。</h3>
<br>

<table  class="table table-striped table-hover " style="width:40%">
<tr><td>会員ID</td><td>${upmember.id}</td></tr>
<tr><td>氏名</td><td>${upmember.name }</td></tr>
<tr><td>住所</td><td>${upmember.address }</td></tr>
<tr><td>電話番号</td><td>${upmember.tel}</td></tr>
<tr><td>E-Mail</td><td>${ upmember.eMail}</td></tr>
	</table>

</body>
</html>