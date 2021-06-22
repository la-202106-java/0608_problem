<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
<title>Insert title here</title>
</head>
<body>

<jsp:include page="/menu.jsp" />
<br>

&nbsp;&nbsp;ISBN&nbsp;&nbsp;：<input type="text" name="isbn" size="15"><br><br>
タイトル：<input type="text" name="title" size="15"><br><br>
&nbsp;&nbsp;分類&nbsp;&nbsp;：<select name="select1">
      <option>0:文学部系</option>
      <option>1:教育学部系</option>
      <option>2:法学部系</option>
      <option>3:社会学部系</option>
      <option>4:経済学部系</option>
      <option>5:理学部系</option>
      <option>6:医学部系</option>
      <option>7:歯学部系</option>
      <option>8:薬学部系</option>
      <option>9:工学部系</option>
      <option>10:農学部系</option>
      </select><br><br>
&nbsp;著者名：<input type="text" name="author" size="15"><br><br>
&nbsp;&nbsp;&nbsp;売値&nbsp;&nbsp;：<input type="text" name="payment" size="15"><br><br>
&nbsp;&nbsp;状態&nbsp;&nbsp;：<select name="select1">
      <option>新品</option>
      <option>中古(未使用)</option>
      <option>中古</option>
      </select><br>
<input type="submit" value="登録">
</body>
</html>