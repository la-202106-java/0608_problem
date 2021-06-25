<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<title>会員情報更新ページ</title>
</head>
<body>
    <div class="col-lg-8 mx-auto p-3 py-md-5">

        <header class="d-flex align-items-center pb-3 mb-5 border-bottom">
            <div class="flex-grow-1">
	<h1>会員情報更新</h1>
            </div>
        </header>


	<form action="/0608_problem/MembersServlet" method="get">
		会員ID：<input type="text" name="id"><br>
		氏名：<input type="text" name="name"><br>
		郵便番号：<input type="text" name="postal_code"><br>
		住所：<input type="text" name="address"><br>
		e-Mailアドレス：<input type="text" name="email_address"><br>
		<input type="submit" value="更新">
		<input type="hidden" name="action" value="search">
	</form>
</div>
</body>
</html>