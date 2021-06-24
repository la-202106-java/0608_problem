<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script>


function returnM(id,name,title,chkDate){
	if(confirm(name+"様が"+chkDate+"に貸出されました\n'"+title+"'の返却を行いますか？")){
		window.location.href="KasidasiServlet?action=return&lid="+id;
	}


}

function validateForm()
{
var testStr=/^[0-9]+$/;
var  mid4search= document.forms["form1"]["mid"].value;
if(mid4search.length>0 && mid4search.match(testStr)==null){
	alert("会員IDに数値を入力してください。");
	return false;

}
return true;

}

</script>
<meta charset="UTF-8">
<title>図書管理システム | 貸出情報の検索</title>
</head>
<body>

<jsp:include page="/menu.jsp" />
<h2>貸出情報の検索</h2>
<form onsubmit="return validateForm()" name="form1" action="/0608_problem/KasidasiServlet" method="post">
	 <input type ="hidden" name ="action" value ="search">
  会員ID：<input type="text" name="mid" value="${mid }">&nbsp;<br>
  会員名：<input type="text" name="mname" value="${mname }">&nbsp;<br>
<input type="submit" value="検索">

  <hr>

  	<h2>検索結果</h2>



<table border="1">


<tr align="center">
<td>会員ID</td>
<td>会員名</td>
<td>資料名</td>
<td>貸出日</td>
<td>返却期日</td>
<td>返却日</td>
<td>操作</td>
</tr>
<c:forEach items="${list }" var="ll">
<tr>
<td>${ll.member.id }</td>
<td>${ll.member.name }</td>
<td>${ll.materialCatalog.title }</td>
<td>${ll.checkoutDate }</td>
<td>${ll.returnDeadline }</td>
<td>${ll.returnDate }</td>

<c:choose>
  <c:when test="${empty ll.returnDate }">
   <td align="center"><input type="button" value="返却"
onClick="returnM(${ll.id},'${ ll.member.name}','${ ll.materialCatalog.title}','${ ll.checkoutDate}')" >
</td>
  </c:when>
  <c:otherwise>
  <td>
  返却済み
  </td>
  </c:otherwise>
</c:choose>
<tr>
</c:forEach>

</table>

</form>
<script>
<c:if test="${msg != null}">
alert('${msg}');
</c:if>
</script>
</body>
</html>