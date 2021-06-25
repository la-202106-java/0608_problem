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
<title>宿情報更新</title>
</head>
<body>
    <div class="col-lg-8 mx-auto p-3 py-md-5">
<jsp:include page="/WEB-INF/adminMenu.jsp" />

        <header class="d-flex align-items-center pb-3 mb-5 border-bottom">
            <div class="flex-grow-1">
<h1>宿の更新</h1>
            </div>
        </header>

<form action="/0608_problem/admin/inn" method="get">
宿ID：${innTarget.id}<br>
宿名：<input type="text" name="name" value="${innTarget.name}"><br>
分類コード：<input type="number" name="class_code" value="${innTarget.classCode}"><br>
郵便番号：〒<input type="text" name="postal_code" value="${innTarget.postalCode}"><br>
住所：<input type="text" name="address" value="${innTarget.address}"><br>
チェックイン時間：<input type="time"name ="inTime" value="${innTarget.inTime}"><br>
チェックアウト時間：<input type="time" name="outTime" value="${innTarget.outTime}"><br>
<input type="submit" value="更新">
<input type="hidden" name="action" value="update">
<input type="hidden" name="id" value="${innTarget.id}">
</form>
</div>
</body>
</html>