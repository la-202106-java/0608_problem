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

<input type="submit" value = 戻る>
<input type="submit" value = 予約>

</body>
</html>