<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "la.bean.FortuneBean" %>

<%
FortuneBean fb = (FortuneBean)request.getAttribute("fortune");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fortune</title>
</head>
<body>
<h1>占いたい月を入力してね</h1>
<br>
<form action="/lesson/FortuneServlet" method="post">
<select name="month">
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
<option value="7">7</option>
<option value="8">8</option>
<option value="9">9</option>
<option value="10">10</option>
<option value="11">11</option>
<option value="12">12</option>
</select>
月
<input type="submit" value="占ってみる">
</form>
<%if(fb!=null){ %>
${fortune.month}月の運勢は、、<br>
・ラッキーカラー: ${fortune.color }<br>
・ラッキーアイテム: ${fortune.item }<br>
・順位は: ${fortune.rank }位<br>
<%} %>
</body>
</html>