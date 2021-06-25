<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%session.invalidate();

    %>
    <%request.setAttribute("basyo", 1); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 会員管理</title>
</head>
<body>

<jsp:include page="/menu.jsp" />

<h2>
<ul>
<li><a href="/0608_problem/kaiin_touroku.jsp">会員の新規登録</a></li>
<li><a href="/0608_problem/kaiin_kensaku.jsp">会員の検索</a></li>
</ul>
</h2>
</body>
</html>