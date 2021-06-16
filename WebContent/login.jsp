<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<h1>ログイン</h1>
	<form action="/0608_problem/LoginServlet" method="get">
	 <input type ="hidden" name ="action" value ="login">
  メールアドレス：<input type="text" name="address"><br>
  パスワード：<input type="text" name="password"><br>
  <input type="submit" value="ログイン">
</form>