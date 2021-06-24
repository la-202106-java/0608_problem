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
<c:if test="${isError eq 'FALSE'}">
<h3>数字のみの13桁からなるISBN番号を入力してください。</h3>
</c:if>


<form action="/0608_problem/Shiryou_tourokuServlet" method="post">
ISBN番号：<input type="text" name="isbn" maxlength="13">
 <input type ="hidden" name ="action" value ="regist">
  <input type="submit" value="登録">
</form>
</body>
</html>