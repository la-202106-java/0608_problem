<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>目録登録</title>
</head>
<body>
<jsp:include page="/navbar.jsp" />
<div class="m-3 p-3">
<div class="col-9">

<h2>目録登録</h2>

<h4>新規資料情報を入力してください</h4>

${message}

<br>
<form action="/0608_problem/CatalogRegistServlet?action=regist" method="post">
<div class="form-group">
資料名：<input type ="text" name = "title" value="${title}"><br>
</div>
<div class="form-group">
ISBN番号：<input type ="text" name = "isbn" value="${isbn}"><br>
</div>
<div class="form-group">
分類コード：
<select name="code">
<option value="0">0:総記</option>
<option value="1">1:哲学</option>
<option value="2">2:歴史</option>
<option value="3">3:社会科学</option>
<option value="4">4:自然科学</option>
<option value="5">5:技術</option>
<option value="6">6:産業</option>
<option value="7">7:芸術</option>
<option value="8">8:言語</option>
<option value="9">9:文学</option>
</select>
</div>
<div class="form-group">
著者：<input type ="text" name = "author" value="${author}"><br>
</div>
<div class="form-group">
出版社：<input type ="text" name = "publisher" value="${publisher}"><br>
</div>
<div class="form-group">
出版日：<input type ="date" name = "publication_date" value="${publicationDate}"><br>
</div>
<br>
<input class="btn btn-primary" type="submit" value = 登録>
</form>

</div>
</div>
</body>
</html>