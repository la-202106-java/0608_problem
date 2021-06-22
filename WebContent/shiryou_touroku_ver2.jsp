<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 資料の新規登録</title>
</head>
<body>

<jsp:include page="/menu.jsp" />
<h2>資料の新規登録</h2>
ISBN番号『1212121212123』に該当する資料が見つからなかったため、資料目録に登録します。<br>
下記フォームに資料情報を入力してください。<br>
<form>
<form action="/0608_problem/LoginServlet" method="post">
	 <input type ="hidden" name ="action" value ="login">
資料名：<input type="text" name="address"><br>
  <input type="submit" value="登録">
</form>
</body>
</html>