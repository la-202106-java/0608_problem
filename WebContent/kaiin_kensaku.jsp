<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 会員の検索</title>
</head>
<script>
function validateForm()
{
var emeiru = document.forms["form1"]["email"].value;
var kaiinid = document.forms["form1"]["id"].value;
if (emeiru == ""&& kaiinid=="")
{
alert("E-Mailか会員IDを入力してください。");
return false;
}
return true;
}

</script>


<body>

<jsp:include page="/menu.jsp" />
<h2>会員の検索</h2>

<p>
E-Mailもしくは会員ID、どちらか検索できます。<br>
ボタン選択して、検索値を入力してください。
</p>
<table>
<form name="form1" onsubmit="return validateForm()" action="/0608_problem/MemberServlet" method="post">
 	 <input type ="hidden" name ="action" value ="search">
 	 	 <input type="radio" name="radio" value="email" checked>
	E-Mail：<input type="text" name="email" min = "0" max="255" value=${eMail }><br>
<br>
	 <input type="radio" name="radio" value="id" >
会員ID：<input type="text" name="id" value = ${id }><br>
  <input type="submit" value="検索">
</form>


<form action="/0608_problem/MemberServlet" method="post">
 <input type="submit" value="会員一覧">
  	 <input type ="hidden" name ="action" value ="memberlist">
</form>

</table>
<hr>
<c:if test = "${menlist ne null }">
<h3>該当する会員の情報は以下の通りです。</h3>
<table border="1">
<tr><td>ID</td><td>氏名</td><td>住所</td><td>電話番号</td><td>E-Mail</td><td>生年月日</td><td>入会年月日</td><td>退会年月日</td></tr>
<c:forEach items="${menlist }" var="item">


<tr><td>${item.id}</td>
<td>${item.name}</td>
<td>${item.address}</td>
<td>${item.tel}</td>
<td>${item.eMail}</td>
<td>${item.birth}</td>
<td>${item.joinDate}</td>
<td>${item.withdrawalDate}</td></tr>
</c:forEach>
</c:if>



<c:if test = "${imember ne null}" >
<c:if test = "${Ismenberlist ne 'true'}" >

<h3>該当する会員の情報は以下の通りです。</h3>
<table border="1">

<tr><td>ID</td><td>氏名</td><td>住所</td><td>電話番号</td><td>E-Mail</td><td>生年月日</td><td>入会年月日</td><td>退会年月日</td><td>変更</td><td>退会</td></tr>
<tr><form action="/0608_problem/MemberServlet" method="post">

<td>${imember.id}</td>
<td>${imember.name}</td>
<td>${imember.address}</td>
<td>${imember.tel}</td>
<td>${imember.eMail}</td>
<td>${imember.birth}</td>
<td>${imember.joinDate}</td>
<td>${imember.withdrawalDate}</td>

<td><input type="submit" value="変更">
    	 <input type ="hidden" name ="action" value ="confirmhenkou"></td>
    	 </form>
<form action="/0608_problem/MemberServlet" method="post">
<c:if test = "${imember.withdrawalDate eq null }">
  <td><input type="submit" value="退会">
    	 <input type ="hidden" name ="action" value ="confirmtaikai"></td></tr>
    	 </c:if>
<c:if test = "${imember.withdrawalDate ne null }">
<td>退会済み</td></tr>
</c:if>
</form>
</table>
</c:if>
</c:if>
</body>
</html>