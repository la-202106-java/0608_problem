<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setAttribute("basyo", 1); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | 会員の検索</title>
</head>
<script>
function validateForm()
{
var emeiru = document.forms["form1"]["email"].value;
var kaiinid = document.forms["form1"]["id"].value;
if (emeiru == ""&& kaiinid=="")
{
alert("E-Mailか会員IDを入力してください。");
return false;
}
return true;
}

</script>


<body>

<jsp:include page="/menu.jsp" />

<div style="width:50%;text-align:center">
        <div class="well bs-component">
          <form class="form-horizontal"  name="form1" onsubmit="return validateForm()" action="/0608_problem/MemberServlet" method="post">
            <fieldset>
              <legend>会員の検索</legend>
              <legend>
<span style="font-size:16px;">E-Mailもしくは会員ID、どちらか検索できます。<br>
ボタン選択して、検索値を入力してください。</span></legend>
              <div class="form-group">
                <label for="inputEmail" class="col-lg-3 control-label"> <input type="radio" name="radio" value="email" checked>
                E-Mail：</label>
                <div class="col-lg-8">
                  <input type="text" name="email" min = "0" max="255"  class="form-control" value=${eMail }>
                </div>
              </div>
              <div class="form-group">
                <label for="inputPassword" class="col-lg-3 control-label"> <input type="radio" name="radio" value="id" >
会員ID：</label>
                <div class="col-lg-8">
                  <input type="text" name="id" class="form-control" value = ${id }>
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

<form action="/0608_problem/MemberServlet" method="post">

 <button type="submit" class="btn btn-primary">会員一覧</button>
  	 <input type ="hidden" name ="action" value ="memberlist">
</form>


<c:if test = "${menlist ne null }">

<h3>該当する会員の情報は以下の通りです。</h3>
<table class="table table-striped table-hover ">
						<thead>
							<tr>
							<th>ID</th>
							<th>氏名</th>
							<th>住所</th>
							<th>電話番号</th>
							<th>E-Mail</th>
							<th>生年月日</th>
							<th>入会年月日</th>
							<th>退会年月日</th></tr>
						</thead>
						<tbody>

<c:forEach items="${menlist }" var="item">


<tr><td>${item.id}</td>
<td>${item.name}</td>
<td>${item.address}</td>
<td>${item.tel}</td>
<td>${item.eMail}</td>
<td>${item.birth}</td>
<td>${item.joinDate}</td>
<td>${item.withdrawalDate}</td></tr>
</c:forEach>

						</tbody>
					</table>


</c:if>



<c:if test = "${imember ne null}" >
<c:if test = "${Ismenberlist ne 'true'}" >

<h3>該当する会員の情報は以下の通りです。</h3>
<table class="table table-striped table-hover ">
						<thead>
							<tr>
							<th>ID</th>
							<th>氏名</th>
							<th>住所</th>
							<th>電話番号</th>
							<th>E-Mail</th>
							<th>生年月日</th>
							<th>入会年月日</th>
							<th>退会年月日</th>
							<th>変更</th><th>退会</th>
							</tr>
						</thead>
						<tbody>


<tr>

<td>${imember.id}</td>
<td>${imember.name}</td>
<td>${imember.address}</td>
<td>${imember.tel}</td>
<td>${imember.eMail}</td>
<td>${imember.birth}</td>
<td>${imember.joinDate}</td>
<td>${imember.withdrawalDate}</td>

<td><form action="/0608_problem/MemberServlet" method="post"><input type="submit" value="変更">
    	 <input type ="hidden" name ="action" value ="confirmhenkou"></form></td>

 <td>
<c:if test = "${imember.withdrawalDate eq null }">
 <form action="/0608_problem/MemberServlet" method="post"><input type="submit" value="退会">
    	 <input type ="hidden" name ="action" value ="confirmtaikai"></form>
 </c:if>
<c:if test = "${imember.withdrawalDate ne null }">
退会済み
</c:if>
</td>
</tr>
</table>
</c:if>
</c:if>
<br><br>
</body>
</html>