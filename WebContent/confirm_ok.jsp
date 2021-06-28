<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 会員情報の登録</title>
</head>
<body>

<jsp:include page="/menu.jsp" />
<h2>会員の新規登録</h2>
<h3>登録完了しました。</h3>
<br>

<table  class="table table-striped table-hover " style="width:40%">
<tr><td>会員ID</td><td>${members.id}</td></tr>
<tr><td>氏名</td><td>${members.name }</td></tr>
<tr><td>住所</td><td>${members.address }</td></tr>
<tr><td>電話番号</td><td>${members.tel}</td></tr>
<tr><td>E-Mail</td><td>${ members.eMail}</td></tr>
<tr><td>生年月日</td><td>${ members.birth}</td></tr>
<tr><td>入会年月日</td><td>${ members.joinDate}</td></tr>
	</table>

</body>
</html>