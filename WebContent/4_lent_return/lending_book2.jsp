<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>資料貸出</title>
</head>
<body>
<h2>資料貸出</h2>
貸出をする資料IDを入力してください<br>
資料ID <input type ="number" name = "book_id">
<input type="submit" value = 貸出>
<br>
現在貸し出している資料
<table border="1">
    <tr>
      <th>資料ID</th>
      <th>資料名</th>
      <th>返却予定日</th>
    </tr>
    <tr>
      <td></td>
      <td></td>
      <td></td>
    </tr>
  </table>
</body>
</html>