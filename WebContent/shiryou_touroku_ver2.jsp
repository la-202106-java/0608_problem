<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%request.setAttribute("basyo", 1); %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 資料の新規登録</title>
<script>


function validateForm()
{
return confirm('登録してもよろしいでしょうか');
}

</script>
</head>
<body>

<jsp:include page="/menu.jsp" />
<h2>資料の新規登録</h2>
ISBN番号『${isbn}』に該当する資料が見つからなかったため、資料目録に登録します。<br>
下記フォームに資料情報を入力してください。<br>

<c:if test="${isError eq 'FALSE'}">
<h3><font color="red">全フォームに情報を入力してください。</font></h3>
</c:if>
<br>
<!--



<form  onsubmit="return validateForm()" action="/0608_problem/Shiryou_tourokuServlet" method="post">
資料名：<input type="text" name="title" value="${title}"><br>
著者：<input type="text" name="author" value="${author}"><br>
出版社：<input type="text" name="publisher" value="${publisher}"><br>
出版日：<input type="date" name="publisher_date" value="${publisher_date}"><br>
カテゴリコード：
<select name="choice">
<c:forEach items="${categories}" var = "category" varStatus="stat">
  <option value="${stat.index}">${category.name}</option>
</c:forEach>
</select>
<br>
<input type ="hidden" name ="action" value ="confirm">
  <input type="submit" value="登録">
</form>

 -->

<div style="width:60%">
        <div class="well bs-component">
          <form class="form-horizontal" onsubmit="return validateForm()" name="form1"  action="/0608_problem/Shiryou_tourokuServlet" method="post">
            <fieldset>
              <legend>資料の新規登録</legend>
              <div class="form-group">
                <label for="inputEmail" class="col-lg-3 control-label">資料名：</label>
                <div class="col-lg-8">
                  <input type="text" name="title" value="${title}"  class="form-control">
                </div>
              </div>
                <div class="form-group">
                <label for="inputEmail" class="col-lg-3 control-label">著者：</label>
                <div class="col-lg-8">
                  <input type="text" name="author" value="${author}" class="form-control">
                </div>
              </div>
                <div class="form-group">
                <label for="inputEmail" class="col-lg-3 control-label">出版社：</label>
                <div class="col-lg-8">
                  <input type="text" name="publisher" value="${publisher}" class="form-control">
                </div>
                </div>
                 <div class="form-group">
                <label for="inputEmail" class="col-lg-3 control-label">出版日：</label>
                <div class="col-lg-8">
                 <input type="date" name="publisher_date" class="form-control" value="${publisher_date}"   >
                </div>
              </div>
                <div class="form-group">
                <label for="inputEmail" class="col-lg-3 control-label">カテゴリコード：</label>
                <div class="col-lg-5">
                 <select name="choice" class="form-control">
				<c:forEach items="${categories}" var = "category" varStatus="stat">
				  <option value="${stat.index}">${category.name}</option>
				</c:forEach>
				</select>
                </div>
              </div>


              <div class="form-group">
                <div class="col-lg-10 col-lg-offset-2">
                  <button type="submit" class="btn btn-primary">登録</button>
                </div>
              </div>
            </fieldset>
<input type ="hidden" name ="action" value ="confirm">
          </form>
        </div>
</div>
</body>
</html>




