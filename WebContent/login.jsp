<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
  crossorigin="anonymous">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>

  <div class="m-5">
    <div class="container">
      <h2>新宿図書館 管理者ログイン</h2>

      <!-- 入力フォーム -->
      <div class="col-6">
        <form action="/0608_problem/LoginServlet" method="post">
          <div class="form-group">
            <label for="InputEmail">メールアドレス</label>
            <input type="email" name="email" class="form-control" id="InputEmail" aria-describedby="emailHelp" placeholder="xxx@gmail.com">
          </div>
          <div class="form-group">
            <label for="InputPassword">パスワード</label>
            <input type="password" name="password" class="form-control" id="InputPassword" placeholder="英数字8文字以上">
          </div>
          <button type="submit" class="btn btn-primary">ログイン</button>
        </form>
      </div>

      <div class="row">
        <div class="col">
          <c:if test="${!empty message}">
            <br><h5>${message}</h5><br>
          </c:if>
      </div>
      </div>
    </div>
  </div>

</body>
</html>