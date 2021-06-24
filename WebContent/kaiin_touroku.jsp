<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 会員の新規登録</title>
</head>
<script>


function validateForm()
{
var  simei = document.forms["form1"]["name"].value;
if (simei == "")
{
alert("氏名を入力してください。");
return false;
}
var jusho = document.forms["form1"]["address"].value;
if (jusho == "")
{
alert("住所を入力してください。");
return false;
}
var denwa = document.forms["form1"]["tel"].value;
if (denwa == "")
{
alert("電話番号を入力してください。");
return false;
}
var meiru = document.forms["form1"]["mail"].value;
if (meiru == "")
{
alert("E-Mailを入力してください。");
return false;
}
var tanjoubi = document.forms["form1"]["birth"].value;
if (tanjoubi == "")
{
alert("生年月日を入力してください。");
return false;
}
return true;
}


</script>

<body>

<jsp:include page="/menu.jsp" />
<h2>会員の新規登録</h2>

<form name="form1" onsubmit="return validateForm()" action="/0608_problem/MemberServlet"method="post">
<input type="hidden" name="action" value="confirm">

  氏名：<input type="text" name="name"><br>
  住所：<input type="text" name="address"><br>
  電話番号：<input type="text" name="tel"><br>
  E-Mail：<input type="text" name="mail"><br>
  生年月日：<input type="date" value="1990-01-01"  name="birth"><br>
  <input type="submit" value="登録確認">
</form>
</body>
</html>