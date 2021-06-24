<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約取り置き資料貸出確認</title>
</head>
<body>
<h2>予約取り置き資料貸出確認</h2>
会員ID：${user.id} 、${user.name}さんは以下の取り置き資料があります

現在取り置きしている資料<br>
<table border="1">
    <tr>
      <th>資料ID</th>
      <th>資料名</th>
      <th>貸出</th>
    </tr>
<c:forEach items="${books}" var="book">
          <tr>
            <td>${book.id}</td>
            <td>${book.title}</td>
            <td><input type="submit" value = 貸出></td>
          </tr>
</c:forEach>
</table>
<input type="submit" value = 戻る>
<input type="submit" value = 別資料を貸出>


</body>
</html>