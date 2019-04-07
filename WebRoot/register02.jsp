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
		 	<form class="am-form" action="/rwshopping/register" method="post" onsubmit="return validate();">
			  <fieldset>
		
			    <div class="am-form-group">

			      <input type="text" class="" id="doc-ipt-email-1" value="" name="userName" placeholder="请输入用户名">
			    </div>
					
				<div class="am-form-group">

			      <input type="tel" class="" id="doc-ipt-email-1" value="" name="userTel" placeholder="请输入联系电话">
			    </div>
			    
			    <div class="am-form-group">
			     
			      <input type="password" class="" id="doc-ipt-pwd-1" value="" name="userPwd"  placeholder="请输入密码">
			    </div>
			    
			    <div class="am-form-group">
			     
			      <input type="password" class="" id="doc-ipt-pwd-1" value="" name="reUserPwd" placeholder="请再次输入密码">
			    </div>
			    <p><button type="submit" class="am-btn am-btn-default">注册</button></p>
			    <a href="index.jsp">
			    	<div class="login-text">
			    		返回易购首页
			    	</div>
			    </a>
			  </fieldset>
			</form>
		 </div>
	</div>
</div>
<script>
 function validate() {
		var userName = document.getElementsByName("userName");
		var userPwd = document.getElementsByName("userPwd");
		var reuserPwd = document.getElementsByName("reUserPwd");
		var userTel = document.getElementsByName("userTel");
		
		if(userName[0].value=="") {
			alert("用户名不能为空");
			userName[0].focus();
			return false;
		}
		if(userTel[0].value=="") {
			alert("联系方式不能为空");
			userTel[0].focus();
			return false;
		}
		if(userPwd[0].value=="") {
			alert("用户密码不能为空");
			userPwd[0].focus();
			return false;
		}
		if(reuserPwd[0].value==""||userPwd[0].value!=reuserPwd[0].value) {
			alert("确认密码错误");
			reuserPwd[0].focus();
			return false;
		}
		return true;
	}
</script>
<script src="js/amazeui.min.js"></script>
<script src="js/app.js"></script>
</body>
</html>