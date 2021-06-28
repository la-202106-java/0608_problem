<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
</head>
<body>
	<a href="/0608_problem/top">TOP</a> |

	<c:if test="${!empty logined}">
		こんにちは、${logined.name}さん |
	</c:if>

	<a href="/0608_problem/ListedItemSearchServlet">教科書検索</a> |

		<!-- サーブレットまだ -->
	<c:if test="${!empty logined}">
	<a href="/0608_problem/ListedItemRegistServlet">教科書登録</a> |
	<a href="/0608_problem/LoginServlet?action=logout">ログアウト</a> |
	<a href="/0608_problem/MemberDetailServlet">マイページ</a>
	</c:if>
	<c:if test="${empty logined}">
	<a href="/0608_problem/LoginServlet">ログイン</a> |
	<a href="/0608_problem/MemberRegistServlet">会員登録</a>
	</c:if>
	<br>
	<c:if test="${flag > 0}">
	<a href="/0608_problem/ListedItemBuyedCheck">購入された商品があります</a>
	</c:if>




</body>
</html>