<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
  crossorigin="anonymous">
<title>取置確認</title>
</head>
<body>
<jsp:include page="/navbar.jsp" />
<div class="m-3 p-3">
    <div class="col-9">
		<h2>取置確認</h2>

		以下の資料を取り置きしてもよろしいですか？<br>
		<br>会員番号：${userBean.id}<br>
		氏名：${userBean.name}<br>
		資料ID：${book_id}<br>
		資料名：${title}<br>

		<form method="post">
		<button type="button" class="btn btn-secondary" onclick="history.back()">戻る</button>
			<button type="submit" class="btn btn-primary" formaction="/ShinjukuLibrary/CatalogReservedServlet">取置</button>
			<input type="hidden" name="action" value="reserved_confirm_execute">
			<input type="hidden" name="book_id" value="${book_id}">
			<input type="hidden" name="user_id" value="${userBean.id}">
		</form>
	</div>
</div>
</body>
</html>

