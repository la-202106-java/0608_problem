<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<jsp:include page="/menu.jsp" />
<br>

&nbsp;&nbsp;ISBN&nbsp;&nbsp;：<input type="text" name="isbn" size="15"><br><br>
タイトル：<input type="text" name="title" size="15"><br><br>
&nbsp;&nbsp;分類&nbsp;&nbsp;：<select name="select1">
	  <option>----------</option>
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
&nbsp;&nbsp;&nbsp;売値&nbsp;&nbsp;：<input type="text" name="payment" size="15">円以上|<input type="text" name="payment" size="15">円以下<br><br>
&nbsp;&nbsp;状態&nbsp;&nbsp;：<select name="select1">
	  <option>----</option>
      <option>新品</option>
      <option>中古(未使用)</option>
      <option>中古</option>
      </select><br><br>
<input type="checkbox" name="stock" value="stock">在庫有のみ表示<br>
<input type="checkbox" name="stock" value="stock">自分が出品した商品のみ表示<br>
<input type="submit" value="検索"><br><br>

<table border="1">
<tr><td>ID</td><td>ISBN</td><td>タイトル</td>
    <td>分類</td><td>著者名</td><td>売値</td></tr>


<tr>
	<td align="center">1</td>
	<td align="center">12345678</td>
	<td align="center">ドラえもん</td>
	<td align="right">文学部系</td>
	<td align="center">藤子不二雄</td>
	<td align="right">1000円</td>


<td>
	<input type="hidden" name="item_code" value="${item.value.code}">
	<input type="submit" value="詳細">
</td>
</tr>

	<tr>
	<td align="center">2</td>
	<td align="center">12345679</td>
	<td align="center">アンパンマン</td>
	<td align="right">理学部系</td>
	<td align="center">藤子不二雄A</td>
	<td align="right">1500円</td>

<td>
	<input type="hidden" name="item_code" value="${item.value.code}">
	<input type="submit" value="詳細">
</td>
</tr>
</table>
</body>
</html>