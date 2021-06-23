<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>目録検索</title>
</head>
<body>

<h2>目録検索</h2>

資料名 <input type ="text" name = "titles"><input type="submit" value = 検索><br>
件の資料が検索されました<br>
<table border="1">
    <tr>
      <th>貸出名</th>
      <th>ISBN</th>
      <th>状態</th>
      <th>予約/取置</th>
    </tr>
    <tr>
      <td></td>
      <td></td>
      <td></td>
      <td><input type="submit" value = 予約></td>
    </tr>
    <tr>
      <td></td>
      <td></td>
      <td></td>
      <td><input type="submit" value = 取置></td>
    </tr>
  </table>

</body>
</html>