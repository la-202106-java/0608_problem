<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品管理</title>
</head>
<body>
  <h1>商品更新</h1>
  <form action="/0608_problem/ItemServlet2" method="post">
    <table border="1">
      <tr>
        <td>コード</td>
        <td>
          ${code}
          <input type="hidden" name="code" value="${code}">
        </td>
      </tr>
      <tr>
        <td>カテゴリコード</td>
        <td>
          <input type="text" name="categoryCode" size="10">
        </td>
      </tr>
      <tr>
        <td>名前</td>
        <td>
          <input type="text" name="name" size="10">
        </td>
      <tr>
        <td>価格</td>
        <td>
          <input type="text" name="price" size="10">
        </td>
      </tr>
    </table>
    <input type="submit" value="更新">
    <input type="hidden" name="action" value="update">

  </form>
</body>
</html>