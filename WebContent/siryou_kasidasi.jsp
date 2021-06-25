<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%request.setAttribute("basyo", 3); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 資料の貸し出し</title>
</head>
<body>

<jsp:include page="/menu.jsp" />
<h2>
<ul>
<li><a href="KasidasiServlet">貸出情報の検索</a></li>
<li><a href="/0608_problem/kasidasi_touroku.jsp">貸出情報の登録</a></li>
</ul>
</h2>
</body>
</html>