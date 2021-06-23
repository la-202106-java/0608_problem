<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
<title>Insert title here</title>
</head>
<body>

	<c:if test="${user == 'admin'}">
	<jsp:include page="/adminMenu.jsp" />
	</c:if>
	<c:if test="${user != 'admin'}">
	<jsp:include page="/menu.jsp" />
	</c:if>

    <h1>会員検索結果</h1>
    <form>

        <table border="1">
        <tr><th>会員ID</th><th>氏名</th><th>住所</th><th>電話番号</th>
        <th>E-mail</th><th>生年月日</th><th>入会年月日</th><th>退会年月日</th><th></th><th></th>

        <tr><th>1</th><th>新宿太郎</th><th>新宿区●●</th><th>03-0000-0000</th>
            <th>a@example.com</th><th>1982.01.01</th><th>2010.02.01</th>
            <th>2010.02.01</th>
            <th><form action ="update">
                <input type="submit" value="詳細"></form></th>
            <th><form action ="delete">
                    <input type="submit" value="削除"></form></th>
                </tr>

        <tr><th>2</th><th>渋谷地下</th><th>新宿区●●</th><th>03-0000-1111</th>
            <th>b@example.com</th><th>1999.01.01</th><th>2010.12.01</th>
            <th></th>
            <th><form action ="update">
                <input type="submit" value="詳細"></form></th>
            <th><form action ="delete">
                    <input type="submit" value="削除"></form></th>
                </tr>

        <tr><th>3</th><th>山田花子</th><th>新宿区●●</th><th>03-0000-2222</th>
            <th>c@example.com</th><th>1984.01.01</th><th>2010.02.01</th>
            <th></th>
            <th><form action ="update">
                <input type="submit" value="詳細"></form></th>
            <th><form action ="delete">
                    <input type="submit" value="削除"></form></th>
                </tr>

        </table>
    </form>
    </body>
    </html>