<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約追加確認</title>
</head>
<body>

<h2>予約追加確認</h2>

以下の情報で予約します<br>
よろしいですか？<br>
会員番号：${userBean.id}<br>
氏名：${userBean.name}<br>
ISBN：${isbn}<br>
資料名：${title}<br>

<form method="post">
<input type="submit" value ="戻る" formaction="/0608_problem/CatalogSearchServlet">
<input type="submit" value = 予約 formaction="/0608_problem/CatalogReserveServlet">
<input type="hidden" name="action" value="reserve_confirm_execute">
<input type="hidden" name="isbn" value="${isbn}">
<input type="hidden" name="user_id" value="${userBean.id}">
</form>
</body>
</html>