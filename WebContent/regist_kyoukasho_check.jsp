<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<jsp:include page="/menu.jsp" />


<h1>この情報で登録しますか？</h1>

<table border="1">
<tr><td>ID</td><td>1</td></tr>
<tr><td>ISBN</td><td>12345678</td></tr>
<tr><td>タイトル</td><td>ドラえもん</td></tr>
<tr><td>分類</td><td>文学部系</td></tr>
<tr><td>著者名</td><td>藤子不二雄</td></tr>
<tr><td>売値</td><td>1000円</td></tr>


</table>
<br>

<input type="hidden" name="item_code" value="${item.value.code}">
	<input type="submit" value="キャンセル">
<input type="hidden" name="item_code" value="${item.value.code}">
	<input type="submit" value="登録する">

</body>
</html>