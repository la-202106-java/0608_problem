<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <title>ログイン</title>
</head>

<body>
    <div class="col-lg-8 mx-auto p-3 py-md-5">
        <c:if test="${not empty error}">
            ${error}
        </c:if>

        <header class="d-flex align-items-center pb-3 mb-5 border-bottom">
            <div class="flex-grow-1">
              <h1>ログイン</h1>
            </div>
        </header>

        <form action="/0608_problem/ShowPlanServlet2" method="post">
            メールアドレス：<input type="text" name="email" value="diw@dgs.com"><br>
            パスワード：<input type="password" name="password" value="fjasofha"><br>
            <input type="hidden" name="action" value="login">
            <input type="submit" value="ログイン">
        </form>
        <hr>
        会員登録されていない方
        <form action="/0608_problem/registration.jsp" method="post">
            <input type="submit" value="会員登録">
        </form>
    </div>
</body>

</html>