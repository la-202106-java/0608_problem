<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 資料の新規登録</title>
<script>


function validateForm()
{
return confirm('登録してもよろしいでしょうか');
}

</script>
</head>
<body>

<jsp:include page="/menu.jsp" />
<h2>資料の新規登録</h2>
ISBN番号『${isbn}』に該当する資料が見つからなかったため、資料目録に登録します。<br>
下記フォームに資料情報を入力してください。<br>

<c:if test="${isError eq 'FALSE'}">
<h3><font color="red">全フォームに情報を入力してください。</font></h3>
</c:if>
<br>
<form  onsubmit="return validateForm()" action="/0608_problem/Shiryou_tourokuServlet" method="post">
資料名：<input type="text" name="title" value="${title}"><br>
著者：<input type="text" name="author" value="${author}"><br>
出版社：<input type="text" name="publisher" value="${publisher}"><br>
出版日：<input type="date" name="publisher_date"value="${publisher_date}"><br>
カテゴリコード：
<select name="choice">
<c:forEach items="${categories}" var = "category" varStatus="stat">
  <option value="${stat.index}">${category.name}</option>
</c:forEach>
</select>
<br>
<input type ="hidden" name ="action" value ="confirm">
  <input type="submit" value="登録">
</form>
</body>
</html>