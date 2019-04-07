<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>登录</title>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="layer/layer.js"></script>
	<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css"/>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
</head>
	<style type="text/css">
        html {
        	height: 100%;
        }
        
        body {
        	padding: 0;
        	margin: 0;
        	width: 100%;
        	background: url(img/login-bj.jpg) no-repeat;
        	background-size: 100% 100%;
        	position: absolute;
        }
        .login-box {
        	border-radius: 8px;
        	width: 380px;
        	height: 470px;
        	border-top: 1px solid transparent;
        	margin:0 auto;
        	margin-top: 50px;
        	background-color: rgba(128,128,128,0.5);
        }
        h1 {
        	text-align: center;
        	margin-bottom: 22px;
        	margin-top: 22px;
        }
        h4 {
        	margin-left: 5%;
        }
        h1,h4 {
        	color: white;
        }
        .input {
        	width: 90%;
        	height: 36px;
        	margin: 0 auto;
        	margin-top: 11px;
        }
        .pwd {
        	margin-top: 22px;
        }
        .passcode {
        	width: 60%;
        	margin-top: 4px;
        	margin-left: 5%!important;
        }
        .passcode-show {
        	width: 27%;
        	height: 36px;
        	margin-top: 4px;
        	margin-right: 5%;
        }
        .passcodeinput {
        	display: flex;
        	flex-direction: row;
        }
        .login-submit {
        	width: 90%;
        	height: 36px;
        	margin: 0 auto;
        	margin-top: 15px;
        	margin-left: 5%;
        	color: rgb(255,255,255);
        	background-color: green;
        }
        .remember-me {
        	margin-left: 5%;
        	margin-top: 15px;
        	height:15px;
        	display: flex;
        	flex-direction: row;
        }
        .check {
        	width: 15px;
        	height: 15px;
        }
        .remember {
        	font-size:15px;
        	margin-left: 5px;
        }
        .toregister {
         	margin-top:10px;
        	text-align: center;
        }
        a {
        	font-size: 15px;
        	text-decoration: none;
        	list-style: none;
        	color: white;
        }
	</style>
	<%
		String userName = "";
		String userPwd = "";
		int loginStatue = 1;
		int verifyStatue = 1;
		//从客户端获取Cookie
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
			for(Cookie cookie:cookies) {
				if("COOKLE_LOGIN_name".equals(cookie.getName())) {
					//解密存在cookie中的信息
					userName = cookie.getValue();
				}
				if("COOKLE_LOGIN_passWord".equals(cookie.getName())) {
					//解密存在cookie中的信息
					userPwd = cookie.getValue();
				}
			}
		}
		if((String)request.getAttribute("loginStatue")=="N") {
			loginStatue = 0;
		}
		if((String)request.getAttribute("verifyStatue")=="N") {
			verifyStatue = 0;
		}
	 %>
	<body>
		<div class="login-box">
			<h1>登录</h1>
		<form class="am-form" action="/rwshopping/UserLoginServlet" method="post">
			<h4>用户名</h4>
			<input type="text" class="form-control input" name="userName" id="exampleInputEmail1" placeholder="请输入用户名"  value="<%=userName %>">
			<h4 class="pwd">密码</h4>
			<input type="password" class="form-control input" name="passWord" id="exampleInputEmail1" placeholder="请输入密码" value="<%=userPwd %>">
			<h4 class="pwd">验证码</h4>                                                                    
			<div class="passcodeinput">
				<input type="text" class="form-control input passcode" name="verifyCode" id="exampleInputEmail1" placeholder="请输入验证码">
				<div class="passcode-show">
					<img id="img1" onclick="load()" title="看不清" src="/rwshopping/ValidateCodeServlet" width="100%" height="100%">
				</div>
			</div>
			<label class="remember-me">
          		<input type="checkbox" class="check" name="rememberME" value="true" checked="checked"> <p class="remember">记住我</p>
          	</label>
			<button type="submit" class="btn btn-default login-submit">登录</button>
		</form>
			<div class="toregister">
				<p class="toregister"><a href="register.jsp">没有账号？去注册</a></p>
			</div>
		</div> 
	</body>
	<script type="text/javascript">
 		function load() {
 		    var imgs = document.getElementById("img1");
 			imgs.src = "/rwshopping/ValidateCodeServlet?"+new Date().getTime();	
 		}
 		var i = <%=loginStatue %>;
 		var k = <%=verifyStatue%>
 		if(i==0) {
 			alert("用户名或密码错误");
 		}else {
 			if(k==0) {
 			alert("验证码错误");
 		}
 		}
 		
 		
 	</script>
</html>
