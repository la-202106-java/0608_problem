<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="java.text.*" %>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
  crossorigin="anonymous">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<title>会員登録 </title>
</head>
<body>
<jsp:include page="/navbar.jsp" />
<%
  GregorianCalendar cal = new GregorianCalendar();
  SimpleDateFormat format = new SimpleDateFormat("yyyy/M/d");
  String datestr = format.format(cal.getTime());
%>

<div class="m-3 p-3">
    <div class="col-9">
	<h2>会員登録</h2>
	<h4>新規会員情報を入力してください</h4>
	<form action = "/0608_problem/UserRegisterServlet" method = "post">
	<div class="form-group">
		<br>氏名：<input type ="text" name = "name">
	</div>
	<div class="form-group">
		住所：<input type ="text" name = "address">
	</div>
	<div class="form-group">
		電話番号：<input type ="text" name = "tel">
	</div>
	<div class="form-group">
		email：<input type ="email" name = "email">
	</div>
	<div class="form-group">
		生年月日：<input type ="date" name = "birthDate">
	</div>
	<div class="form-group">
		入会年月日：<%out.println(datestr);%>
	</div>
	<button class="btn btn-primary" type="submit">登録</button>
	<input type="hidden" name = "action" value = "register1" >
	</form>
</div>
</body>
</html>

