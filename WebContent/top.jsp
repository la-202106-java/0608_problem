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

<!-- FontAwesome CSS -->
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />

<link rel="stylesheet" href="style.css" type="text/css">
<title>新宿図書館管理システム</title>
</head>
<body>
 <jsp:include page="/navbar.jsp" />

        <div class="pb-1 row">
          <div class="col px-0">
            <img src="images/shinjyukulogo.jpg" class="img-fluid" alt="会員管理">
          </div>
        </div>
        <div class="pb-3 px-2 row">
          <table class="table table-borderless">
            <tbody>
              <tr>
                <td bgcolor="#a1d6e2"><h3><b>会員管理 <i class="fas fa-user"></i></b></h3></td>
                <td colspan="2" bgcolor="#1995ad"><h3><b>資料管理 <i class="fas fa-book"></i></b></h3></td>
                <td bgcolor="#a1d6e2"><h3><b>目録管理</b></h3></td>
              </tr>
              <tr>
                <td>
                  <button type="submit" class="btn btn-outline-dark btn-lg">
                    会員検索 <i class="fas fa-search"></i>
                  </button>
                </td>
                <td>
                  <a class="btn btn-secondary btn-lg" href="/0608_problem/BookSearchServlet" role="button">
                    資料検索 <i class="fas fa-search"></i>
                  </a>
                </td>
                <td>
                  <button type="submit" class="btn btn-secondary btn-lg">資料登録 <i class="fas fa-plus-circle"></i></button>
                </td>
                <td>
                  <button type="submit" class="btn btn-outline-dark btn-lg">
                    目録検索 <i class="fas fa-search"></i>
                  </button>
                </td>
              </tr>
              <tr>
                <td>
                  <a class="btn btn-outline-dark btn-lg" href="/0608_problem/UserRegisterServlet" role="button">
                  会員登録 <i class="fas fa-plus-circle" ></i></a>
                </td>
                <td>
                  <button type="submit" class="btn btn-danger btn-lg">資料貸出</button>
                </td>
                <td>
                  <button type="submit" class="btn btn-secondary btn-lg">貸出履歴 <i class="fas fa-search"></i></button>
                </td>
                <td>
                  <button type="submit" class="btn btn-outline-dark btn-lg">目録登録 <i class="fas fa-plus-circle"></i></button>
                </td>
              </tr>
              <tr>
                <td></td>
                <td>
                <a class="btn btn-primary btn-lg" href="/0608_problem/BookReturningServlet" role="button">資料返却</a>
                </td>
                <td></td>
                <td></td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="row">
          <div class="col-12 px-2">
            <table class="table table-borderless">
              <tbody>
              <tr>
                <td bgcolor="#1995ad"><h3><b>延滞者一覧</b></h3></td>
              </tr>
              </tbody>
            </table>
          </div>
          <div class="col-9 px-3">
          <table class="table table-striped">
              <thead>
                <tr>
                  <th scope="col"></th>
                  <th scope="col">会員番号</th>
                  <th scope="col">返却日</th>
                  <th scope="col">超過日数</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <th scope="row">1</th>
                  <td>12345</td>
                  <td>2021/06/02</td>
                  <td><font color="red">20</font></td>
                </tr>
                <tr>
                  <th scope="row">2</th>
                  <td>12444</td>
                  <td>2021/06/15</td>
                  <td>7</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
</body>
</html>