<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 会員の検索</title>
</head>
<body>

<jsp:include page="/menu.jsp" />
<h2>会員の検索</h2>

<p>
E-Mailもしくは会員ID、どちらか検索できます。<br>
ボタン選択して、検索値を入力してください。
</p>

<form action="/0608_problem/MemberServlet" method="post">
 	 <input type ="hidden" name ="action" value ="search">
 	 	 <input type="radio" name="radio" value="email" checked>
	E-Mail：<input type="text" name="email" min = "0" max="255" value=${eMail }><br>
<br>
	 <input type="radio" name="radio" value="id" >
会員ID：<input type="text" name="id" value = ${id }><br>
  <input type="submit" value="検索">
</form>

<h3>該当する会員の情報は以下の通りです。</h3>
<br>
<table border="1">

<tr><td>ID</td><td>${ imember.id}</td></tr>
<tr><td>氏名</td><td>${imember.name}</td></tr>
<tr><td>住所</td><td>${imember.address}</td></tr>
<tr><td>電話番号</td><td>${imember.tel}</td></tr>
<tr><td>E-Mail</td><td>${imember.eMail}</td></tr>
<tr><td>生年月日</td><td>${imember.birth}</td></tr>
<tr><td>入会年月日</td><td>${imember.joinDate}</td></tr>
</table>
<c:if test = "${imember ne null}" >
<form action="/0608_problem/MemberServlet" method="post">
  <input type="submit" value="変更">
    	 <input type ="hidden" name ="action" value ="confirmhenkou">
    	 </form>
    	 <form action="/0608_problem/MemberServlet" method="post">
  <input type="submit" value="退会">
    	 <input type ="hidden" name ="action" value ="confirmtaikai">
</form>
</c:if>
</body>
</html>