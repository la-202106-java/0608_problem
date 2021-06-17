<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to shopping!</title>
</head>
<body>
<jsp:include page="/menu.jsp"></jsp:include>

<h3>商品一覧</h3>
${totalItem  }件の商品が存在しました
<br><br>
<c:forEach items="${items }" var="item">
<form action="CartServlet?action=add" method="post">
<input type="hidden" name="item_code" value="${item.code }">
商品番号：<b>${item.code }</b><br>
商品名：<b>${item.name}</b><br>
価格（税込）：<b>${item.price}</b>円<br>
個数：
<select name="quantity">
<option value="1">1
<option value="2">2
<option value="3">3
<option value="4">4
<option value="5">5
</select>
個<br>
<a href="ShowItemServlet?action=detail&code=${item.code }">詳細</a><br>
<input type="submit" value="カートに追加">
</form>
<br>

</c:forEach>

<%int totalPage=Integer.parseInt(request.getAttribute("totalPage").toString()); %>

<%

for(int i=1;i<totalPage+1;i++){
	if(request.getParameter("action").equals("search")){
		String name=request.getParameter("name");
%>

<a href="ShowItemServlet?action=search&page=<%=i-1 %>&name=<%=name %>"><%=i %></a> &nbsp;
<%
	}else{
		String code=request.getParameter("code");

%>
<a href="ShowItemServlet?action=list&page=<%=i-1 %>&code=<%=code %>"><%=i %></a> &nbsp;
<%
	}
}
%>
</body>
</html>