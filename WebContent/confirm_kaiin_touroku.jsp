<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 会員の新規登録</title>
</head>
<body>

<jsp:include page="/menu.jsp" />
<h2>会員の新規登録確認</h2>
<form action="/0608_problem/MemberServlet"method="post">

<h3>正しい情報が入力されていれば、『登録』ボタンを押してください。</h3>

 <button type="submit" class="btn btn-primary">登録</button>
  <input type="hidden" name="action" value="addmember">
  </form>
	  <form action="/0608_problem/MemberServlet"method="post">
 <button type="submit" class="btn btn-primary">キャンセル</button>
    <input type="hidden" name="action" value="cancel">
</form>
<br>


<table  class="table table-striped table-hover " style="width:40%">

<tr><td>氏名</td><td>${ member.name}</td></tr>
<tr><td>住所</td><td>${ member.address}</td></tr>
<tr><td>電話番号</td><td>${member.tel}</td></tr>
<tr><td>E-Mail</td><td>${ member.eMail}</td></tr>
<tr><td>生年月日</td><td>${member.birth }</td></tr>
	</table>


</body>
</html>