<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import = "java.util.* "%>
<%!
 List<String> responseList = new ArrayList<String>();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>掲示板</title>
</head>
<body>

<%
request.setCharacterEncoding("UTF-8");
String res = request.getParameter("res");
String name = request.getParameter("name");
if(name == null || name.length() == 0){
	name = "名無し";
}
if (res != null && res.length() != 0) {
	responseList.add(name+":"+res);
}
%>

<form action="/lesson/BBS.jsp" method="post">
名前：<br>
<input type="text" name="name"><br>
メッセージ：<br>
<textarea rows="10" cols="60" name="res"></textarea><br>
<input type="submit" value="書き込み">
</form>
<hr>
<%
for(int i =0;i<responseList.size();i++){
	out.println(responseList.get(i));
	out.println("<br><hr>");
}
%>

</body>
</html>