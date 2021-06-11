<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import ="la.bean.Omikujibean" %>

<%
	Omikujibean bean = (Omikujibean)request.getAttribute("omikuji");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>おみくじ</title>
</head>
<body>

<h1>STEP1</h1>
スクリプトレットを使用しておみくじ
<br>
今日の運勢は...「今日の運勢は...「<%=bean.getResult() %>」です。」です。

</body>
</html>