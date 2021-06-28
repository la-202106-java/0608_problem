
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
<h2>会員の退会確認</h2>

<h3>正しい情報が入力されていれば、『退会』ボタンを押してください。</h3>



<table  class="table table-striped table-hover " style="width:40%">
<tr><td>ID</td><td>${ imember.id}</td></tr>
<tr><td>氏名</td><td>${imember.name}</td></tr>
<tr><td>住所</td><td>${imember.address}</td></tr>
<tr><td>電話番号</td><td>${imember.tel}</td></tr>
<tr><td>E-Mail</td><td>${imember.eMail}</td></tr>
<tr><td>生年月日</td><td>${imember.birth}</td></tr>
<tr><td>入会年月日</td><td>${imember.joinDate}</td></tr>
</table>
<br>
<table >
	<form action="/0608_problem/MemberServlet" method="post">
  <button type="submit" class="btn btn-primary">退会</button>
  <input type ="hidden" name ="action" value ="delete">
  </form>
  <form action="/0608_problem/MemberServlet" method="post">
  <button type="submit" class="btn btn-primary">キャンセル</button>
  <input type ="hidden" name ="action" value ="canceltaikai">
</form>
</table>
</body>
</html>
