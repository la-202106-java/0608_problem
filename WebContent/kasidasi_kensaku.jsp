<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setAttribute("basyo", 3); %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script>


function returnM(id,name,title,chkDate){
	if(confirm(name+"様が"+chkDate+"に貸出されました\n'"+title+"'の返却を行いますか？")){
		window.location.href="KasidasiServlet?action=return&lid="+id;
	}


}

function validateForm()
{
var testStr=/^[0-9]+$/;
var  mid4search= document.forms["form1"]["mid"].value;
if(mid4search.length>0 && mid4search.match(testStr)==null){
	alert("会員IDに数値を入力してください。");
	return false;

}
return true;

}

</script>
<meta charset="UTF-8">
<title>図書管理システム | 貸出情報の検索</title>
</head>
<body>

<jsp:include page="/menu.jsp" />



<div style="width:40%">
        <div class="well bs-component">
          <form class="form-horizontal" onsubmit="return validateForm()" name="form1" action="/0608_problem/KasidasiServlet" method="post">
            <fieldset>
              <legend>貸出情報の検索</legend>
              <div class="form-group">
                <label for="inputEmail" class="col-lg-2 control-label">会員ID</label>
                <div class="col-lg-10">
                  <input type="text"  class="form-control" name="mid" value="${mid }" maxlength="13">
                </div>
              </div>
              <div class="form-group">
                <label for="inputPassword" class="col-lg-2 control-label">会員名</label>
                <div class="col-lg-10">
                  <input type="text"  class="form-control" name="mname" value="${mname }"  maxlength="20">
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



  <hr>

  	<h2>検索結果</h2>


<table class="table table-striped table-hover ">
						<thead>
							<tr>
								<th>#</th>
								<th>会員ID</th>
								<th>会員名</th>
								<th>資料名</th>
								<th>貸出日</th>
								<th>返却期日</th>
								<th>返却日</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>

						<c:forEach items="${list }" var="ll">
<tr

<c:if test="${ll.isOut }"> class="warning"</c:if>
<c:choose>
  <c:when test="${empty ll.returnDate }">
	class="info"
  </c:when>
  <c:otherwise>
	class="success"
  </c:otherwise>
</c:choose>
>>
<td></td>
<td>${ll.member.id }</td>
<td>${ll.member.name }</td>
<td>${ll.materialCatalog.title }</td>
<td>${ll.checkoutDate }</td>
<td>${ll.returnDeadline }</td>
<td>${ll.returnDate }</td>

<c:choose>
  <c:when test="${empty ll.returnDate }">
   <td align="center"><input type="button" value="返却"
onClick="returnM(${ll.id},'${ ll.member.name}','${ ll.materialCatalog.title}','${ ll.checkoutDate}')" >
</td>
  </c:when>
  <c:otherwise>
  <td  align="center" >
  返却済み
  </td>
  </c:otherwise>
</c:choose>
<tr>
</c:forEach>

						</tbody>
					</table>

<script>
<c:if test="${msg != null}">
alert('${msg}');
</c:if>
</script>
<BR><BR>
</body>
</html>