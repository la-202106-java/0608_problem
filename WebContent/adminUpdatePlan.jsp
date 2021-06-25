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
<title>プラン情報更新</title>
</head>
<body>
    <div class="col-lg-8 mx-auto p-3 py-md-5">
<jsp:include page="/WEB-INF/adminMenu.jsp" />

        <header class="d-flex align-items-center pb-3 mb-5 border-bottom">
            <div class="flex-grow-1">
	<h1>プランの更新</h1>
            </div>
        </header>

	<form action="/0608_problem/admin/plan" method="get">
		プランID：${planTarget.planId}<br> 宿ID：<input type="number" name="id"
			value="${planTarget.innId}"><br> プラン内容：<input
			type="text" name="contents" value="${planTarget.content}"><br>
		金額：<input type="number" name="fee" value="${planTarget.fee}">円<br>
		部屋数：<input type="number" name="room" value="${planTarget.roomMax}"><br>
		画像：<input type="text" name="picture" value="${planTarget.imgUrl}"><br>
		<input type="submit" value="更新"> <input type="hidden"
			name="action" value="update"> <input type="hidden"
			name="planid" value="${planTarget.planId}">
	</form>
</div>
</body>
</html>