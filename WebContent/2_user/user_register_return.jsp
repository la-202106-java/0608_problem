<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="java.text.*" %>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
  crossorigin="anonymous">
<%@ page import = "la.bean.NowUserBean" %>
<% NowUserBean bean = (NowUserBean)session.getAttribute("bean");%>

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
    <div class="row">
<h2>会員登録</h2>
    </div>
    <div class="row">
<h4>新規会員情報を入力してください</h4>
    </div>
<div class="row">
<form action = "/0608_problem/UserRegisterServlet" method = "post">
<div class="form-group">
氏名：<input type ="text" name = "name" value="${bean.name}">
</div>
<div class="form-group">
住所：<input type ="text" name = "address" value="${bean.address}">
</div>
<div class="form-group">
電話番号：<input type ="text" name = "tel" value="${bean.tel}">
</div>
<div class="form-group">
email <input type ="email" name = "email" value="${bean.email}">
</div>
<div class="form-group">
生年月日：<input type ="date" name = "birthDate" value="${bean.birthDate}">
</div>
<div class="form-group">
入会年月日：<%out.println(datestr);%>
</div>
<input type="submit" value = 登録>
<input type="hidden" name = "action" value = "register1" >
</div>
</form>
</div>
</div>
</div>

</body>
</html>
