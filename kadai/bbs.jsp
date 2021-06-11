<%@ page language = "java" contentType ="text/html; charset=UTF-8"
	pageEncoding = "UTF-8" %>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>message</title>
</head>
<body>

<form action ="/lesson/BBSServlet" method="post">
名前:<br>
<input type ="name" name = "name"><br>
メッセージ:<br>
<textarea name ="message" cols ="40" rows="5">
</textarea>
<br><input type="submit" value="書き込み">
</form>




</body>
</html>