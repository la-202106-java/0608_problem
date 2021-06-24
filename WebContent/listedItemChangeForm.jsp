<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
<title>Insert title here</title>
</head>
<body>

<c:if test="${user == 'admin'}">
<jsp:include page="/adminMenu.jsp" />
</c:if>
<c:if test="${user != 'admin'}">
<jsp:include page="/menu.jsp" />
</c:if>


<h1>教科書情報変更</h1>

<form action="/0608_problem/ListedItemChangeServlet" method="post">
ISBN：<br>
<input type="text" name="isbn" size="15"><br>
タイトル：<br>
<input type="text" name="title" size="15"><br>
分類：<br>
<select name="department_code">
	<%-- 選択肢は「0:文学部系」のような表示だが、送信されるのはintのコードのみ --%>
	<c:forEach var="i" begin="0" end="${departments_size -1}" step="1">
		<option value="${i}">${i} : ${departments.get(i)}</option>
	</c:forEach>
</select><br><br>
著者名：<br>
<input type="text" name="author" size="15"><br>
売値：<br>
<input type="text" name="payment" size="15"><br>
状態：<br>
<select name="condition">
	<c:forEach items="${conditions}" var="condition">
		<option>${condition}</option>
	</c:forEach>
</select>
<br>
<input type="submit" value="登録">
</form>

</body>
</html>