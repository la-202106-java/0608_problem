<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%request.setAttribute("basyo", 3); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>図書管理システム | 資料貸出</title>

<script>


function validateForm()
{
var testStr=/^[0-9]+$/;

var  mid4add= document.forms["form1"]["mid4add"].value;
if (mid4add == "")
{
alert("会員IDを入力してください。");
return false;
}

if(mid4add.match(testStr)==null){
	alert("会員IDに数値を入力してください。");
	return false;

}

var sid4add = document.forms["form1"]["sid4add"].value;
if (sid4add == "")
{
alert("資料IDを入力してください。");
return false;
}


if(sid4add.match(testStr)==null){
	alert("資料IDに数値を入力してください。");
	return false;

}
return true;
}

</script>
</head>
<body>

<jsp:include page="/menu.jsp" />



<div style="width:40%;text-align:center;">
        <div class="well bs-component">
          <form class="form-horizontal" onsubmit="return validateForm()" name="form1" action="/0608_problem/KasidasiServlet?action=add" method="post">
            <fieldset>
              <legend>貸出登録</legend>
              <div class="form-group">
                <label for="inputEmail" class="col-lg-3 control-label">会員ID：</label>
                <div class="col-lg-9">
                  <input type="text"  class="form-control" name="mid4add" value="${mid }" maxlength="9">
                </div>
              </div>
              <div class="form-group">
                <label for="inputPassword" class="col-lg-3 control-label">資料ID：</label>
                <div class="col-lg-9">
                  <input type="text"  class="form-control" name="sid4add" value="${mname }"  maxlength="20">
                  </div>
              </div>
              <div class="form-group" >
                <div class="col-lg-10 col-lg-offset-2">
                  <button type="submit" class="btn btn-primary">登録</button>
                </div>
              </div>
            </fieldset>
          </form>
        </div>
</div>

<!--


<h2>資料貸出</h2>

<form name="form1" onsubmit="return validateForm()" action="/0608_problem/KasidasiServlet?action=add" method="post">


会員ID：<input type="text" name="mid4add"><br>
資料ID：<input type="text" name="sid4add"><br>
  <input type="submit" value="登録">


</form>
 -->
</body>
</html>