<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
  crossorigin="anonymous">

<!-- FontAwesome CSS -->
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />
<title>目録登録確認</title>
</head>
<body>
<jsp:include page="/navbar.jsp" />
<div class="m-3 p-3">
<div class="col-9">

<h2>目録登録確認</h2>
<h4>以下の資料を目録に登録してもよろしいでしょうか</h4>
<br>
<form action="/0608_problem/CatalogRegistServlet?action=confirm&title=${title}&isbn=${isbn}&code=${code}&author=${author}&publisher=${publisher}&publicationDate=${publicationDate}" method="post">
資料名：${title}<br>
ISBN番号：${isbn}<br>
分類コード：${code}<br>
著者：${author}<br>
出版社：${publisher}<br>
出版日：${publicationDate}<br>
<br>
<div class="btn-toolbar">
 <div class="pr-3">
<a class="btn btn-secondary" href="/0608_problem/CatalogRegistServlet?action=back&title=${title}&isbn=${isbn}&code=${code}&author=${author}&publisher=${publisher}&publicationDate=${publicationDate}">戻る</a>
</div>
<div class="pr-3">
<input class="btn btn-primary" type="submit" value = 登録>
</div>
</div>
</form>
</div>
</div>
</body>
</html>