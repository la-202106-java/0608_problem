<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書管理システム | メインメニュー</title>

	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<style type="text/css">
	@media ( min-width: 768px ) {
		#banner {
			min-height: 300px;
			border-bottom: none;
		}
		.bs-docs-section {
			margin-top: 8em;
		}
		.bs-component {
			position: relative;
		}
		.bs-component .modal {
			position: relative;
			top: auto;
			right: auto;
			left: auto;
			bottom: auto;
			z-index: 1;
			display: block;
		}
		.bs-component .modal-dialog {
			width: 90%;
		}
		.bs-component .popover {
			position: relative;
			display: inline-block;
			width: 220px;
			margin: 20px;
		}
		.nav-tabs {
			margin-bottom: 15px;
		}
		.progress {
			margin-bottom: 10px;
		}
	}
	</style>

</head>
<body>
					<nav class="navbar navbar-inverse">
						<div class="container-fluid">
							<div class="navbar-header">
								<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-2">
									<span class="sr-only">Toggle navigation</span>
									<span class="icon-bar"></span>
									<span class="icon-bar"></span>
									<span class="icon-bar"></span>
								</button>
								<a class="navbar-brand" href="/0608_problem/index.jsp">TOP</a>
							</div>

							<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
								<ul class="nav navbar-nav">
									<li class="active"><a href="/0608_problem/kaiin_menu.jsp">会員管理 <span class="sr-only">(current)</span></a></li>
									<li><a href="/0608_problem/siryou_kanri.jsp">資料管理</a></li>
									<li><a href="/0608_problem/siryou_kasidasi.jsp">貸出管理</a>	</li>
								</ul>
							</div>
						</div>
					</nav>
<!--

<h3>
<a href="/0608_problem/index.jsp">TOP</a>|
<a href="/0608_problem/kaiin_menu.jsp">会員管理</a>|

<a href="/0608_problem/siryou_kanri.jsp">資料管理</a>|

<a href="/0608_problem/siryou_kasidasi.jsp">資料の貸出</a>|


<a href="/0608_problem/siryou_henkyaku.jsp">資料の返却</a><br>
</h3>
 -->
</body>
</html>