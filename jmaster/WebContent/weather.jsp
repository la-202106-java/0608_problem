<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% String weather;
int random = (int)(Math.random()*4);
switch(random){
case 0:
weather = "晴れ";
break;
case 1:
weather = "曇り";
break;
case 2:
weather = "雨";
break;
default:
weather = "雪";
}
%>
<h2>天気予報</h2>
明日の天気は、<%= weather %>でしょう。<br>