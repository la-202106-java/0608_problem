<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
         <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 会員情報の変更</title>
</head>

<script>
function validateForm()
{
var namae = document.forms["form1"]["name"].value;
if (namae == "")
{
alert("氏名を入力してください。");
return false;
}
var ie = document.forms["form1"]["address"].value;
if (ie == "")
{
alert("住所を入力してください。");
return false;
}
var telnum = document.forms["form1"]["tel"].value;
if (telnum == "")
{
alert("電話番号を入力してください。");
return false;
}
var emeirus = document.forms["form1"]["email"].value;
if (emeirus == "")
{
alert("E-Mailを入力してください。");
return false;
}
return true;
}

</script>
<body>

<jsp:include page="/menu.jsp" />
<h2>会員情報の変更</h2>

会員ID: ${imember.id }

<form name="form1" onsubmit="return validateForm()"action="/0608_problem/MemberServlet" method="post">
  氏名：<input type="text" name="name" value = "${imember.name }"><br>
  住所：<input type="text" name="address"value = "${imember.address }"><br>
  電話番号：<input type="text" name="tel"value = "${imember.tel }"><br>
  E-Mail：<input type="text" name="email"value = "${imember.eMail }"><br>

  <input type="submit" value="変更">
     <input type ="hidden" name ="action" value ="update">

</form>
</body>
</html>