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
                  <a class="btn btn-outline-dark btn-lg" href="/ShinjukuLibrary/UserSearchServlet" role="button">
                    会員検索 <i class="fas fa-search" ></i></a>
                </td>
                <td>
                  <a class="btn btn-secondary btn-lg" href="/ShinjukuLibrary/BookSearchServlet" role="button">
                    資料検索 <i class="fas fa-search"></i>
                  </a>
                </td>
                <td>
                  <a class="btn btn-secondary btn-lg" href="/ShinjukuLibrary/BookRegistServlet" role="button">
	                  資料登録 <i class="fas fa-plus-circle"></i>
                  </a>
                </td>
                <td>
                  <a class="btn btn-outline-dark btn-lg" href="/ShinjukuLibrary/CatalogSearchServlet" role="button">
                    目録検索 <i class="fas fa-search"></i>
                  </a>
                </td>
              </tr>
              <tr>
                <td>
                  <a class="btn btn-outline-dark btn-lg" href="/ShinjukuLibrary/UserRegisterServlet" role="button">
                  会員登録 <i class="fas fa-plus-circle" ></i></a>
                </td>
                <td>
                  <a class="btn btn-danger btn-lg"  href="/ShinjukuLibrary/BookLendingServlet" role="button">資料貸出</a>
                </td>
                <td>
                  <a class="btn btn-secondary btn-lg" href="/ShinjukuLibrary/LendingSearchServlet">貸出履歴 <i class="fas fa-search"></i></a>
                </td>
                <td>
                  <a class="btn btn-outline-dark btn-lg" href="/ShinjukuLibrary/CatalogRegistServlet" role="button">
                  	目録登録 <i class="fas fa-plus-circle"></i>
                  </a>
                </td>
              </tr>
              <tr>
                <td></td>
                <td>
                <a class="btn btn-primary btn-lg" href="/ShinjukuLibrary/BookReturningServlet" role="button">資料返却</a>
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
                <td bgcolor="#1995ad"><h3><b>延滞資料一覧</b></h3></td>
              </tr>
              </tbody>
            </table>
          </div>
          <div class="col-9 px-3">
          <table class="table table-striped">
              <thead>
                <tr>
                  <th scope="col">貸出ID</th>
                  <th scope="col">会員ID</th>
                  <th scope="col">資料ID</th>
                  <th scope="col">貸出年月日</th>
                  <th scope="col">返却期限（年月日）</th>
                  <th scope="col">超過日</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${books}" var="book">
                <tr>
                  <td scope="row">${book.id}</td>
                  <td>
                  <a href="/ShinjukuLibrary/UserSearchServlet?action=detail&id=${book.userId}">${book.userId}</a>
                  </td>
                  <td>${book.bookId}</td>
           		  <td>${book.lendingDate}</td>
           		  <td>${book.deadline}</td>
           		  <c:if test="${book.overDays ge 30}">
           		  <td class="text-danger">${book.overDays}</td>
           		  </c:if>
           		  <c:if test="${book.overDays lt 30}">
           		  <td >${book.overDays}</td>
           		  </c:if>
                </tr>
 			  </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
</body>
</html>