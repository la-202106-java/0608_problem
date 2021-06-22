<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 会員情報の変更</title>
</head>
<body>

<jsp:include page="/menu.jsp" />
<h2>会員の新規登録</h2>
<h3>登録完了しました。</h3>
<br>

<table border="1">
<tr><td>会員ID</td><td>${ member.id}</td></tr>
<tr><td>氏名</td><td>${member.name }</td></tr>
<tr><td>住所</td><td>${member.address }</td></tr>
<tr><td>電話番号</td><td>${member. tel}</td></tr>
<tr><td>E-Mail</td><td>${ member.eMail}</td></tr>
<tr><td>生年月日</td><td>${ member.birth}</td></tr>
<tr><td>入会年月日</td><td>${ member.joinDate}</td></tr>
	</table>

</body>
</html>