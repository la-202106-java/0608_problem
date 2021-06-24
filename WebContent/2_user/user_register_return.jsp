<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="java.text.*" %>
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

<%
  GregorianCalendar cal = new GregorianCalendar();
  SimpleDateFormat format = new SimpleDateFormat("yyyy/M/d");
  String datestr = format.format(cal.getTime());
%>


<h2>会員登録</h2>
<h4>新規会員情報を入力してください</h4>
<form action = "/0608_problem/UserRegisterServlet" method = "post">
氏名：<input type ="text" name = "name" value="${bean.name}"><br>
住所：<input type ="text" name = "address" value="${bean.address}"><br>
電話番号：<input type ="text" name = "tel" value="${bean.tel}"><br>
email <input type ="email" name = "email" value="${bean.email}"><br>
生年月日：<input type ="date" name = "birthDate" value="${bean.birthDate}"><br>
入会年月日：<%out.println(datestr);%>
<br>
<input type="submit" value = 登録>
<input type="hidden" name = "action" value = "register1" >
</form>


</body>
</html>

