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
<br>

<h1>教科書登録</h1>

<form action="/0608_problem/ListedItemRegistServlet" method="post">
ISBN：<br>
<input type="text" name="isbn" value="${regist_item.isbn}"><br>
タイトル：<br>
<input type="text" name="title" value="${regist_item.title}"><br>
分類：<br>
<select name="department_code">
	<%-- 選択肢は「0:文学部系」のような表示だが、送信されるのはintのコードのみ --%>
	<c:forEach var="i" begin="0" end="${departments_size -1}" step="1">
		<c:if test="${regist_item.departmentCode==i}">
			<option value="${i}" selected="selected">${i} : ${departments.get(i)}</option>
		</c:if>
		<c:if test="${empty regist_item or regist_item.departmentCode!=i}">
			<option value="${i}">${i} : ${departments.get(i)}</option>
		</c:if>
	</c:forEach>
</select><br><br>
著者名：<br>
<input type="text" name="author" value="${regist_item.author}"><br>
売値：<br>
<input type="text" name="price" value="${regist_item.price}"><br>
状態：<br>
<select name="condition">
	<c:forEach items="${conditions}" var="condition">
		<c:if test="${condition==regist_item.condition}">
			<option selected="selected">${condition}</option>
		</c:if>
		<c:if test="${empty regist_item.condition or condition!=regist_item.condition}">
			<option>${condition}</option>
		</c:if>
	</c:forEach>
</select>
<br>
画像URL：<br>
<input type="text" name="image" value="${regist_item.image}">
<br>
<input type="hidden" name="action" value="regist_check">
<input type="submit" value="登録">
</form>

</body>
</html>