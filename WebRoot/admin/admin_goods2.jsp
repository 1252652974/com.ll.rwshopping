<%@ page language="java"  pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<title>商品管理</title>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
		<script src="../js/jquery-3.3.1.min.js"></script>
		<script src="../js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="../css/bootstrap.min.css" />
		<link rel="stylesheet" href="../css/admin_users.css" />
	</head>
	<body>
		<div class="container">
			<div class="top-nav">
				<nav class="navbar navbar-inverse">
					<div class="container-fluid">
						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-9"
							 aria-expanded="false">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
							<a class="navbar-brand" href="../index.jsp">首页</a>
						</div>

						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-9">
							<ul class="nav navbar-nav">
								<li class="active"><a href="admin_users.jsp">用户管理</a></li>
								<li><a href="#">用户收藏</a></li>
								<li><a href="#">退出登录</a></li>
							</ul>
						</div><!-- /.navbar-collapse -->
					</div><!-- /.container-fluid -->
				</nav>
			</div>

			<div class="user-list">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>商品ID</th>
							<th>商品名</th>
							<th>价格</th>
							<th>卖家</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>001</td>
							<td>Rammohan </td>
							<td>Reddy</td>
							<td>A+</td>
						</tr>
						<tr>
							<td>002</td>
							<td>Smita</td>
							<td>Pallod</td>
							<td>A</td>
						</tr>
						<tr>
							<td>003</td>
							<td>Rabindranath</td>
							<td>Sen</td>
							<td>A+</td>
						</tr>
					</tbody>
				</table>
				
				<div class="operation">
				
				</div>
			</div>

			<div class="paging">
				<nav aria-label="...">
					<ul class="pagination">
						<li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
						<li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#" aria-label="Next"><span aria-hidden="true">»</span></a></li>
					</ul>
				</nav>
			</div>
		</div>
	</body>
</html>