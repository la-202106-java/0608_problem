<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>図書管理システム | 資料貸出</title>

<script>


function validateForm()
{
var  mid4add= document.forms["form1"]["mid4add"].value;
if (mid4add == "")
{
alert("会員IDを入力してください。");
return false;
}

var sid4add = document.forms["form1"]["sid4add"].value;
if (sid4add == "")
{
alert("資料IDを入力してください。");
return false;
}
return true;
}

</script>
</head>
<body>

<jsp:include page="/menu.jsp" />
<h2>資料貸出</h2>

<form name="form1" onsubmit="return validateForm()" action="/0608_problem/KasidasiServlet?action=add" method="post">
	 <input type ="hidden" name ="action" value ="login">

会員ID：<input type="text" name="mid4add"><br>
資料ID：<input type="text" name="sid4add"><br>
  <input type="submit" value="登録">


</form>
</body>
</html>