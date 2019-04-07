<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.teach.entity.UserInfo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css"/>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="css/accounts.css"/>
<title>商品结算</title>
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
        	background: url(img/accounts-bj.jpg) no-repeat;
        	background-size: 100% 100%;
        	position: absolute;
        }
        .login-box {
        	border-radius: 10px;
        	width: 656px;
        	height: 530px;
        	border-top: 1px solid transparent;
        	margin:0 auto;
        	margin-top: 50px;
        	background-color: rgba(128,128,128,0.5);
        }
        h1 {
        	text-align: center;
        	margin-bottom: 25px;
        	margin-top: 30px;
        }
        .login-submit {
        	width: 80%;
        	height: 50px;
        	margin: 0 auto;
        	margin-top: 40px;
        	margin-left: 10%;
        	color: rgb(255,255,255);
        	background-color: cadetblue;
        }
        a {
        	font-size: 20px;
        	text-decoration: none;
        	list-style: none;
        	color: white;
        }
        .tologin {
        	margin-top: 30px;
        }
	</style>
	<% 
		String userName = "";
		UserInfo user = (UserInfo) request.getSession().getAttribute("SESSION_LOGIN_USER");
		if(user!=null) {
				userName = user.getUserName();
			}
		String ids = request.getParameter("ids");
		String nums = request.getParameter("nums");
	%>
	<body>
		<div class="login-box">
			<h1>商品结算</h1>
			<p class="tackle">注意：请不要恶意或非法提交订单以避免不必要的麻烦！</p>
			<p class="orderid">订单编号：<%=System.currentTimeMillis() %></p>
			<p class="username">用户名：<%=userName %></p>
			<form action="AddOrderServlet" method="post" onsubmit="return validate();">
				<input type="hidden" name="ids" value="<%=ids.toString() %>" />
				<input type="hidden" name="nums" value="<%=nums.toString() %>" />
				<div class="address">
					<p>详细地址：</p><input  name="orderadress" type="text"/>
				</div>
				<div class="address">
					<p>联系电话：</p><input  name="ordertel" type="text"/>
				</div>
				<div class="address">
					<p>付款方式：</p><select name="">
										<option>请选择</option>
										<option>普通邮寄</option>
										<option>特快邮寄</option>
										<option>EMS专递方式</option>
				 	      			</select>
				</div>
				<div class="address remark">
					<p>备注信息：</p><input  name="orderdesc" type="text" value="未填写"/>
				</div>
				<button type="submit" class="btn btn-default login-submit">提交订单</button>
			</form>
		</div> 
		<script>
 function validate() {
		var orderadress = document.getElementsByName("orderadress");
		var ordertel = document.getElementsByName("ordertel");
		
		if(orderadress[0].value=="") {
			alert("地址不能为空");
			orderadress[0].focus();
			return false;
		}
		if(ordertel[0].value=="") {
			alert("联系方式不能为空");
			ordertel[0].focus();
			return false;
		}

		return true;
	}
</script>
</body>
</html>