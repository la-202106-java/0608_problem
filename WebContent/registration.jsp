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
<title>会員登録</title>
</head>
<body>
    <div class="col-lg-8 mx-auto p-3 py-md-5">

        <header class="d-flex align-items-center pb-3 mb-5 border-bottom">
            <div class="flex-grow-1">
<h1>会員登録</h1>
            </div>
        </header>

<form action="/0608_problem/ShowPlanServlet2" method="post">
氏名：<input type="text" name="name"><br>
郵便番号：<input type="text" name="postalCode"><br>
住所：<input type="text" name="address"><br>
電話番号：<input type="text" name="tel"><br>
E-Mail：<input type="text" name="email"><br>
生年月日：<input type="date" name="birthday"><br>
パスワード：<input type="password" name="password"><br>

<input type="hidden" name="action" value="registration">
<input type="submit" value="登録">
</form>
</div>
</body>
</html>