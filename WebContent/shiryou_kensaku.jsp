<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setAttribute("basyo", 2); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 資料の検索</title>
</head>
<body>
<%
	request.setAttribute("location", 2);
%>
<jsp:include page="/menu.jsp" />




<div style="width:40%;text-align:center">
        <div class="well bs-component">
          <form class="form-horizontal"  name="form1" onsubmit="return validateForm()" action="/0608_problem/Shiryou_kensakuServlet" method="post">
            <fieldset>
              <legend>資料の検索</legend>
              <legend>
<span style="font-size:16px;">資料IDもしくは資料名、どちらか検索できます。<br>
ボタン選択して、検索値を入力してください。</span></legend>
              <div class="form-group">
                <label for="inputEmail" class="col-lg-3 control-label">
                 <input type="radio" name="radio" value="id" checked>
			資料ID：</label>
                <div class="col-lg-8">
                  <input class="form-control" type="number" name="id" min="0" max="255" value=${id } >
                </div>
              </div>
              <div class="form-group">
                <label for="inputPassword" class="col-lg-3 control-label">
                 <input type="radio" name="radio" value="title" >
			資料名：</label>
                <div class="col-lg-8">
                  <input class="form-control" type="text" name="part_of_title" value=${title } >
                  </div>
              </div>
              <div class="form-group">
                <div class="col-lg-10 col-lg-offset-2">
                  <button type="submit" class="btn btn-primary">検索</button>
                </div>
              </div>
            </fieldset>
	 <input type ="hidden" name ="action" value ="search">
          </form>
        </div>
</div>

<!--


<h2>資料の検索</h2>

<p>
資料IDもしくは資料名、どちらか検索できます。<br>
ボタン選択して、検索値を入力してください。
</p>

<form action="/0608_problem/Shiryou_kensakuServlet" method="post">
	 <input type ="hidden" name ="action" value ="search">
	 <input type="radio" name="radio" value="id" checked>
資料ID：<input type="number" name="id" min="0" max="255" value=${id }><br>
<br>
	 <input type="radio" name="radio" value="title" >
資料名：<input type="text" name="part_of_title" value=${title }><br>
<input type="submit" value="検索">

<br>


 -->
<!--
	 <input type="radio" name="radio" value="stock_date" >
入荷年月日：<input type="date" name="stock_date" value=${stock_date }><br>
  <input type="submit" value="検索">
   </form>
-->


<c:if test="${count >= 0 }">
<p><font color="red">ヒットした件数は${count }件です。</font></p>
</c:if>

<table  class="table table-striped table-hover ">

						<thead>
							<tr>
							<th>資料ID</th>
							<th>ISBN番号</th>
							<th>入荷年月日</th>
							<th>資料名</th>
							<th>廃棄</th></tr>
						</thead>

<c:forEach items="${items }" var="item">
<tr>
<td>${item.material_id }<input type="hidden" name="material_id" value=${item.material_id }></td>
<td>${item.isbn }<input type="hidden" name="isbn" value=${item.isbn }></td>
<td>${item.stockDate }<input type="hidden" name="stockDate" value=${item.stockDate }></td>
<td>${item.title }<input type="hidden" name="title" value=${item.title }></td>

<td>
<c:if test="${item.disposalDate eq null}">

<form action="/0608_problem/Shiryou_haikiServlet" method="post">

<input type="submit" value="廃棄">
<input type ="hidden" name ="action" value ="dispose_confirm">
<input type="hidden" name="material_id" value="${item.material_id }" >

</form>
</c:if>

<c:if test="${item.disposalDate ne null }">
廃棄済み
</c:if>

</td>
</tr>



</c:forEach>
</table>


<!--


<table border="1">
<tr><td>資料ID</td><td>ISBN番号</td><td>入荷年月日</td><td>資料名</td><td>廃棄</td></tr>

<c:forEach items="${items }" var="item">
<tr><form action="/0608_problem/Shiryou_haikiServlet" method="post">
<td>${item.material_id }<input type="hidden" name="material_id" value=${item.material_id }></td>
<td>${item.isbn }<input type="hidden" name="isbn" value=${item.isbn }></td>
<td>${item.stockDate }<input type="hidden" name="stockDate" value=${item.stockDate }></td>
<td>${item.title }<input type="hidden" name="title" value=${item.title }></td>

<c:if test="${item.disposalDate eq null}">
<td><input type="submit" value="廃棄"><input type ="hidden" name ="action" value ="dispose_confirm"></td></tr>
</c:if>

<c:if test="${item.disposalDate ne null }">
<td>廃棄済み</td></tr>
</c:if>

</form>
</c:forEach>
</table>

 -->
 <br><br>
</body>
</html>