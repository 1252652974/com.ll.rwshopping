<%@page import="com.teach.entity.UserInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java"  pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html class="no-js">
    <head>
        <title></title>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="js/jquery-3.3.1.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/amazeui.min.css">
  		<link rel="stylesheet" href="css/app.css">
  		<link rel="stylesheet" type="text/css" href="css/login.css"/>
    </head>
<body>
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
	<style>
        input::-webkit-input-placeholder{
            color: #F1F1F1!important;
        }
        input::-moz-placeholder{   /* Mozilla Firefox 19+ */
            color: #F1F1F1!important;
        }
        input:-moz-placeholder{    /* Mozilla Firefox 4 to 18 */
            color: #F1F1F1!important;
        }
        input:-ms-input-placeholder{  /* Internet Explorer 10-11 */ 
            color: #F1F1F1!important;
        }
    </style>
<div class="am-g myapp-login">
	<div class="myapp-login-bg">
		 <div class="myapp-login-logo">
		 	<i class="">人文二手易购</i>
		 </div>
		 <div class="am-u-sm-10 myapp-login-form">
		 	<form class="am-form" action="/rwshopping/UserLoginServlet" method="post">
			  <fieldset>
		
			    <div class="am-form-group">

			      <input type="Text" name="userName" class="" id="" value="<%=userName %>" placeholder="请输入用户名">
			    </div>

			    <div class="am-form-group">
			     
			      <input type="password" name="passWord" class="" id="doc-ipt-pwd-1" value="<%=userPwd %>"  placeholder="请输入密码">
			    </div>
			    <div class="am-form-group">
			     
			      <input type="text" class="Vaildate" id="doc-ipt-pwd-1" name="verifyCode" value=""  placeholder="请输入验证码">
			      <p style="text-align: center;"><a><img id="img1" onclick="load()" title="看不清" src="/rwshopping/ValidateCodeServlet"></a></p>
			    </div>
			    <div class="checkbox">
        		    <label>
          				<input type="checkbox" name="rememberME" value="true" checked="checked">记住密码
        			</label>
     		 	</div>
			    <p><button type="submit" class="am-btn am-btn-default">登录</button></p>
			    <a href="register.jsp">
			    	<div class="login-text">
			    		还没有账号?去注册
			    	</div>
			    </a>
			  </fieldset>
			</form>
		 </div>
	</div>
</div>
<script type="text/javascript">
 		function load() {
 		    var imgs = document.getElementById("img1");
 			imgs.src = "/rwshopping/ValidateCodeServlet?"+new Date().getTime();	
 		}
 		var i = <%=loginStatue %>;
 		var k = <%=verifyStatue%>
 		if(i==0) {
 			alert("用户名或密码错误");
 		}
 		if(k==0) {
 			alert("验证码错误");
 		}
 		
 	</script>
<script src="js/amazeui.min.js"></script>
<script src="js/app.js"></script>
</body>
</html>