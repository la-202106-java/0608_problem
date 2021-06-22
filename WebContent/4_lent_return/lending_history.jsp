<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>貸出履歴</title>
</head>
<body>
<h2>資料貸出</h2>
資料ID <input type ="text" name = "book_id">
<input type="submit" value = 延滞資料><br>
会員ID <input type ="text" name = "user_id">
<input type="checkbox">貸出中のみ<br>
<input type="submit" value = 検索>

<table border="1">
    <tr>
      <th>貸出ID</th>
      <th>会員ID</th>
      <th>資料ID</th>
      <th>貸出年月日</th>
      <th>返却期限（年月日）</th>
      <th>返却年月日</th>
      <th>備考</th>
    </tr>
    <tr>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
    </tr>
  </table>

</body>
</html>