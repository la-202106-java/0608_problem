<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<% String subject;
int random = (int)(Math.random()*4);
switch(random){
case 0:
subject = "プログラミング入門";
break;
case 1:
subject = "オープンソースDB";
break;
case 2:
subject = "Linux入門";
break;
default:
subject = "Android開発講座";
}
%>
<h2>今月のお奨め講座</h2>
<h1><%= subject %></h1>