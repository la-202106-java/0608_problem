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
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <title>Topページ</title>
</head>

<body>
    <div class="col-lg-8 mx-auto p-3 py-md-5">
        <header class="d-flex align-items-center pb-3 mb-5 border-bottom">
            <div class="flex-grow-1">
                <h1>新宿トラベル</h1>
            </div>
            <div class="fs-5">
                <c:choose>
                    <c:when test="${isLogin eq 'true'}">
                        <a href="/0608_problem/ShowPlanServlet2?action=logout" class="p-3">ログアウト</a>
                        <a href="/0608_problem/membersShow.jsp">会員メニュー</a> <!-- jsp適当なので要変更 -->
                    </c:when>
                    <c:otherwise><a href="/0608_problem/login.jsp">ログイン</a></c:otherwise>
                </c:choose>
            </div>
        </header>

        <main>
            <div class="pb-3">
                <form class="row g-3" action="/0608_problem/ShowPlanServlet2" method="post">
                    <div class="col-md-3">
                        <label for="inputEmail4" class="form-label">チェックイン</label>
                        <input type="date" name="checkIn" class="form-control" value="${checkIn}">
                    </div>
                    <div class="col-md-3">
                        <label for="inputPassword4" class="form-label">チェックアウト</label>
                        <input type="date" name="checkOut" class="form-control" value="${checkOut}">
                    </div>
                    <div class="col-3 position-relative">
                        <input type="hidden" name="action" value="plan">
                        <button type="submit" class="btn btn-outline-dark position-absolute bottom-0">検索</button>
                    </div>
                </form>
            </div>

            <c:if test="${not empty error}">
                ${error}
            </c:if>

            <hr class="">

            <div class="pt-3">
                <form action="/0608_problem/ShowPlanServlet2" method="post">
                    <div class="row mb-3">
                        <label class="col-sm-2 col-form-label">宿名で絞り込む</label>
                        <div class="col-sm-5">
                            <input type="text" name="innName" class="form-control" value="${innName}">
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label class="col-sm-2 col-form-label">宿の場所で絞り込む</label>
                        <div class="col-sm-5">
                            <input type="text" name="place" class="form-control" value="${place}">
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label class="col-sm-2 col-form-label">金額で絞り込む</label>
                        <div class="col-sm-2">
                            <input type="number" name="lower" class="form-control" value="${lower}">
                        </div>
                        <div class="col-sm-1">
                            <p class="text-center">～</p>
                        </div>
                        <div class="col-sm-2">
                            <input type="number" name="upper" class="form-control" value="${upper}">
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-sm-2">
                            <button type="submit" class="btn btn-outline-dark">検索</button>
                            <input type="hidden" name="action" value="narrow">
                        </div>
                    </div>
                </form>


                <div class="row row-cols-1 row-cols-md-3 g-4">
                    <c:forEach items="${plans}" var="plan" varStatus="stat">
                        <form action="/0608_problem/ReservationServlet" method="post">
                            <input type="hidden" name="action" value="reservation">
                            <input type="hidden" name="planCount" value="${stat.count}">
                            <div class="col">
                                <div class="card h-100">
                                    <img src="${plan.imgUrl}" class="card-img-top" alt="plan.content"
                                        onerror="this.src='https://dummyimage.com/600x400/fff/000&text=%E7%94%BB%E5%83%8F%E3%81%AA%E3%81%97'">
                                    <div class="card-body">
                                        <h5 class="card-title">${plan.inn.name}</h5>
                                        <h6 class="card-subtitle mb-2 text-muted">${plan.content}</h6>
                                        <p class="card-text">〒${plan.inn.postalCode} ${plan.inn.address}</p>
                                        <p class="card-text">一部屋当たり　${plan.fee}円</p>
                                        <input type="submit" class="btn btn-primary" value="予約">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </c:forEach>
                </div>
            </div>
        </main>
    </div>
</body>
</html>