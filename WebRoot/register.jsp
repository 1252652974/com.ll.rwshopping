<%@page import="com.teach.entity.UserInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java"  pageEncoding="UTF-8" errorPage="error.jsp"%>

<!DOCTYPE html>
<html class="no-js">
    <head>
        <title></title>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="js/jquery-3.3.1.min.js"></script>
		<script src="layer/layer.js"></script>
		<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css"/>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="css/accounts.css"/>
    </head>
<body>
	<style type="text/css">
        html {
        	height: 100%;
        }
        
        body {
        	padding: 0;
        	margin: 0;
        	width: 100%;
        	background: url(img/register-bj.jpg) no-repeat;
        	background-size: 100% 100%;
        	position: absolute;
        }
        .login-box {
        	border-radius: 8px;
        	width: 450px;
        	height: 530px;
        	border-top: 1px solid transparent;
        	margin:0 auto;
        	margin-top: 40px;
        	background-color: rgba(128,128,128,0.5);
        }
        h1 {
        	text-align: center;
        	margin-bottom: 15px;
        	margin-top: 25px;
        }
        h4 {
        	margin-left: 5%;
        }
        h1,h4 {
        	color: white;
        }
        .input {
        	width: 90%;
        	height: 42px;
        	margin: 0 auto;
        	margin-top: 10px;
        }
        .passcode {
        	width: 60%;
        	/*margin-top: 4px;*/
        	margin-left: 5%!important;
        }
        .passcode-show {
        	width: 27%;
        	height: 42px;
        	margin-top: 4px;
        	margin-right: 5%;
        }
        .passcodeinput {
        	display: flex;
        	flex-direction: row;
        }
        .login-submit {
        	width: 90%;
        	height: 42px;
        	margin: 0 auto;
        	margin-top: 34px;
        	margin-left: 5%;
        	color: rgb(255,255,255);
        	background-color: green;
        }
        p {
        	text-align: center;
        }
        a {
        	font-size: 17px;
        	text-decoration: none;
        	list-style: none;
        	color: white;
        }
        .tologin {
        	margin-top: 17px;
        }
	</style>	
	<div class="login-box">
			<h1>注册</h1>
		<form action="/rwshopping/register" method="post" onsubmit="return validate();">
			<h4>用户名</h4>
			<input type="text" class="form-control input" id="exampleInputEmail1" name="userName" placeholder="请输入用户名">
			<h4>电话号码</h4>
			<input type="tel" class="form-control input" id="exampleInputEmail1" name="userTel" placeholder="请输入联系方式">
			<h4>密码</h4>
			<input type="password" class="form-control input" id="exampleInputEmail1" name="userPwd" placeholder="请输入密码">
			<h4>再次输入密码</h4>                                                                    
			<div class="">
				<input type="password" class="form-control input" id="exampleInputEmail1" name="reUserPwd" placeholder="请再次输入密码">
			</div>
			<button type="submit" class="btn btn-default login-submit">注册</button>
		</form>
			<div class="tologin">
				<p><a href="login.jsp">已有帐号直接登录</a></p>
			</div>
		</div> 
<script>
 function validate() {
		var userName = document.getElementsByName("userName");
		var userPwd = document.getElementsByName("userPwd");
		var reuserPwd = document.getElementsByName("reUserPwd");
		var userTel = document.getElementsByName("userTel");
		
		if(userName[0].value=="") {
			layer.alert("用户名不能为空");
			userName[0].focus();
			return false;
		}
		if(userTel[0].value=="") {
			layer.alert("联系方式不能为空");
			userTel[0].focus();
			return false;
		}
		if(userPwd[0].value=="") {
			layer.alert("用户密码不能为空");
			userPwd[0].focus();
			return false;
		}
		if(reuserPwd[0].value==""||userPwd[0].value!=reuserPwd[0].value) {
			layer.alert("确认密码错误");
			reuserPwd[0].focus();
			return false;
		}
		return true;
	}
</script>
</body>
</html>