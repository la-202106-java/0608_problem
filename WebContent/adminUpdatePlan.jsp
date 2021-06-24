<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>プラン情報更新</title>
</head>
<body>
<jsp:include page="/WEB-INF/adminMenu.jsp" />
	<h1>プランの更新</h1>
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

</body>
</html>