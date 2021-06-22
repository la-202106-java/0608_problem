<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 資料の新規登録</title>
</head>
<body>

<jsp:include page="/menu.jsp" />
<h2>資料の新規登録</h2>
ISBN番号『${isbn}』に該当する資料が見つからなかったため、資料目録に登録します。<br>
下記フォームに資料情報を入力してください。<br>

<form action="/0608_problem/Shiryou_tourokuServlet" method="post">
資料名：<input type="text" name="title"><br>
著者：<input type="text" name="author"><br>
出版社：<input type="text" name="publisher"><br>
出版日：<input type="text" name="publisher_date"><br>
カテゴリコード：
<select name="choice">
<c: forEach items="${categories}" var = "category">
  <option value="first">First Value</option>
</c:forEach>
</select>
<br>
<input type ="hidden" name ="action" value ="regist2">
  <input type="submit" value="登録">
</form>
</body>
</html>