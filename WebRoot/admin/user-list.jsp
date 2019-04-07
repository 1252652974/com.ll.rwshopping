<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.teach.entity.UserInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<title>用户管理-后台管理</title>
		<base href="../" />
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/bootstrap-theme.min.css" />
		<link rel="stylesheet" href="css/animate.min.css" />
		<link rel="stylesheet" href="css/font-awesome.min.css" />
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</head>
	

		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
					<a class="navbar-brand" href="javascript:void(0);">后台管理</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						
						<li class="dropdown active">
							<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">系统管理 <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li>
									<a href="javascript:void(0);">用户管理</a>
								</li>
								<li>
									<a href="javascript:void(0);">角色管理</a>
								</li>
							</ul>
						</li>
						
						<li class="dropdown">
							<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">文章中心 <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li>
									<a href="javascript:void(0);">文章管理</a>
								</li>
								<li>
									<a href="javascript:void(0);">文章类别管理</a>
								</li>
							</ul>
						</li>
						
						<li class="dropdown">
							<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">产品中心 <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li>
									<a href="javascript:void(0);">产品管理</a>
								</li>
								<li>
									<a href="javascript:void(0);">产品类别管理</a>
								</li>
							</ul>
						</li>
					</ul>
					
					<ul class="nav navbar-nav navbar-right">

						<li class="dropdown" style="position: relative;">
							<a style="padding-left: 50px;" href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="fa fa-user-circle-o" style="font-size:30px; position: absolute; top:10px; left:10px"></i> 管理员：某某某 <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li>
									<a href="javascript:void(0);">修改个人信息</a>
								</li>
								<li>
									<a href="javascript:void(0);">修改登录密码</a>
								</li>
								<li role="separator" class="divider"></li>
								<li>
									<a href="javascript:void(0);">退出系统</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</nav>

	<div class="container">
		<ol class="breadcrumb">
			<li><a href="javascript:void(0);">后台管理</a></li>
			<li><a href="javascript:void(0);">系统管理</a></li>
			<li class="active">用户管理</li>
		</ol>

		<div class="panel panel-primary">
			<!-- Default panel contents -->
			<div class="panel-heading">用户管理</div>
			<div class="panel-body">
				<form class="form-inline">
					<div class="form-group">
						<input name="key" type="text"
							value=""
							class="form-control" placeholder="按账号和姓名模糊搜索">
					</div>
					<div class="form-group">
						<select name="roleId" class="form-control">
							<option value="">所有角色</option>
							
							<option value="-3"
								>所得税法大赛</option>
							
							<option value="0"
								>普通会员</option>
							
							<option value="1"
								>VIP会员</option>
							
							<option value="2"
								>产品管理员</option>
							
							<option value="3"
								>新闻管理员</option>
							
							<option value="999"
								>超级管理员</option>
							
						</select>
					</div>
					<button type="submit" class="btn btn-primary">
						<i class="fa fa-search"></i>
					</button>
					<button type="button" class="btn btn-danger pull-right"
						data-toggle="modal" data-target="#addModal">
						<i class="fa fa-plus"></i>
					</button>
				</form>
			</div>

			<!-- Table -->
			<table class="table table-bordered table-striped table-hover">
				<thead>
					<tr>
						<th>#</th>
						<th>账号</th>
						<th>真实姓名</th>
						<th>性别</th>
						<th>出生日期</th>
						<th>电话号码</th>
						<th>QQ</th>
						<th class="text-center" style="width: 4em">状态</th>
						<th class="text-center" style="width: 10em">操作</th>
					</tr>
				</thead>
				<tbody>
					<%
					List<UserInfo> list = (List<UserInfo>)request.getAttribute("list");
					int count=1;
					for(UserInfo vo : list){
					%>
					<tr>
						<th scope="row"><%=count++ %></th>
						<td><%=vo.getUserName() %></td>
						<td><%=new Boolean(true).equals(vo.getRoleId())?"男":"女"%></td>
						<td></td>
						<td><%=vo.getUserTel() %></td>
						<td></td>
						<td></td>
						<td class="text-center"></td>
						<td class="text-center"><a
							href="javascript:void(0);"  data-toggle="modal" data-target="#lookModal2">查看</a>
							
										<a href="javascript:void(0);"  data-toggle="modal" data-target="#editModal2">修改</a>
							
							
							<a onclick="return confirm('您确定要将密码设置成[123456]吗？')" href="javascript:void(0);">重置密码</a>
										</td>
					</tr>
					<%} %>
				</tbody>
			</table>
		</div>
		<div class="text-center">

			<nav aria-label="Page navigation">
				<ul class="pagination">
					<li><a
						href="javascript:void(0);"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
					
					<li  class="active" ><a
						href="javascript:void(0);">1</a></li>
					
					<li ><a
						href="javascript:void(0);">2</a></li>
					
					<li ><a
						href="javascript:void(0);">3</a></li>
					
					<li ><a
						href="javascript:void(0);">4</a></li>
					
					<li ><a
						href="javascript:void(0);">5</a></li>
					
					<li ><a
						href="javascript:void(0);">6</a></li>
					
					<li><a
						href="javascript:void(0);"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</ul>
			</nav>
		</div>
	</div>

	<!-- 添加用户 -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<form action="admin/user-add.s" class="form-horizontal"
					method="post">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">添加用户</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label class="col-sm-3 control-label">用户名：</label>
							<div class="col-sm-9">
								<input type="text" required="required"
									pattern="^[a-zA-Z]\w{2,14}$" class="form-control"
									name="userName" placeholder="请输入3-15位的字符" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">密码：</label>
							<div class="col-sm-9">
								<input type="password" required="required" pattern="^.{3,10}$"
									class="form-control" name="userPwd" placeholder="请输入3-10位的密码" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">密码确认：</label>
							<div class="col-sm-9">
								<input type="password" required="required" pattern="^.{3,10}$"
									class="form-control" name="userPwdConfirm"
									placeholder="请输入3-10位的密码" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">角色：</label>
							<div class="col-sm-9">
								<select required="required" name="roleId" class="form-control">
									<option value="">请选择角色</option>
									
									<option value="-3">
										所得税法大赛
									</option>
									
									<option value="0">
										普通会员
									</option>
									
									<option value="1">
										VIP会员
									</option>
									
									<option value="2">
										产品管理员
									</option>
									
									<option value="3">
										新闻管理员
									</option>
									
									<option value="999">
										超级管理员
									</option>
									
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">姓名：</label>
							<div class="col-sm-9">
								<input type="text" required="required"
									pattern="^[\u4e00-\u9fa5]{2,4}$" class="form-control"
									name="userRealName" placeholder="请输入2-4位的中文姓名" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">出生日期：</label>
							<div class="col-sm-9">
								<input type="text" required="required"
									pattern="^\d{4}-\d{1,2}-\d{1,2}$" class="form-control"
									name="userBirthdate" placeholder="请输入yyyy-MM-dd格式的日期" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">出生日期：</label>
							<div class="col-sm-9">
								<label class="checkbox-inline"> <input type="radio"
									value="true" name="userSex" checked="checked" /> 男
								</label> <label class="checkbox-inline"> <input type="radio"
									value="false" name="userSex" /> 女
								</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">电话：</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="userTel"
									placeholder="请输入电话" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">qq：</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="userQq"
									placeholder="请输入qq号码" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">身份号码：</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="userIdcard"
									placeholder="请输入身份号码" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">家庭住址：</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="userAddress"
									placeholder="请输入家庭住址" />
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="submit" class="btn btn-primary">添加用户</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>

</html>