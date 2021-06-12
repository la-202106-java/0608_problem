<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ page import="java.util.ArrayList"  %>
    <% @SuppressWarnings("unchecked") ArrayList<String> messa = (ArrayList<String>) session.getAttribute("products"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メッセージフォーム</title>
</head>
<body>
<h2>あなたは現在、「${name}」というユーザ名でログイン中です</h2><br>
<form action="/lesson/AddMessageServlet" method="get">
メッセージ：
<br>
<textarea name = "value1" rows="4" cols="40">

 </textarea>
 <br>
<input type = "submit" value = "書き込み">
<a href="/lesson/LoginServlet3?action=logout">ログアウト</a>
	<br>
</form>
<hr>
<%
if(messa!=null){
for (int i = 0; i < messa.size(); i++) {
%>
			${name}：<%=messa.get(i)%>  <br>
<%
		}
}
%>

</body>
</html>