<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
<title>教科書売買サイト</title>
</head>
<body>


	<h1>会員検索結果</h1>
	<p>${result_num}件見つかりました。</p>

      <c:forEach items="${member}" var="person">
      	<table>
      		<tr>
      			<td><small>ID ${person.id}</small><br>
      				名前 ${person.name}</td>
      			<td>${person.email}</td>
      			<td>入会年月日 ${person.joinDate}</td>
      		</tr>
      		<tr>
      			<th></th><td><c:if test="${person.quitDate!=null}">退会</c:if></td>
      		</tr>
      		<tr>
      			<td><form action="/0608_problem/MemberDetailServlet?action=detail" method = "post"><input type="submit" value="詳細"></form></td>
      			<td><form action="/0608_problem/MemberDetailServlet?action=delete" method = "post"><input type="submit" value="削除"></form></td>
      		</tr>
      	</table>
      </c:forEach>

</body>
</html>