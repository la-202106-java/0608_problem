<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%request.setAttribute("basyo", 2); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 資料の新規登録</title>
</head>
<body>

<jsp:include page="/menu.jsp" />
<c:if test="${isError eq 'FALSE'}">
<h3>数字のみの13桁からなるISBN番号を入力してください。</h3>
</c:if>




<div style="width:40%">
        <div class="well bs-component">
          <form class="form-horizontal" action="/0608_problem/Shiryou_tourokuServlet" method="post">
            <fieldset>
              <legend>資料の新規登録</legend>
              <div class="form-group">
                <label for="inputEmail" class="col-lg-3 control-label">ISBN番号：</label>
                <div class="col-lg-7">
                  <input type="text" name="isbn" class="form-control"  maxlength="13">
                  <br>
  <input type="submit"  class="btn btn-primary" value="登録">
                </div>
              </div>
              <div class="form-group">
                <div class="col-lg-10 col-lg-offset-2">
                </div>
              </div>
            </fieldset>
 <input type ="hidden" name ="action" value ="regist">
          </form>
        </div>
</div>

<!--
<form action="/0608_problem/Shiryou_tourokuServlet" method="post">
ISBN番号：<input type="text" name="isbn" maxlength="13">
 <input type ="hidden" name ="action" value ="regist">
  <input type="submit" value="登録">
</form>
 -->
</body>
</html>