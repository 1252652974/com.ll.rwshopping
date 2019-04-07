<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.teach.entity.UserInfo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/shopingcart.css"/>
<link rel="stylesheet" type="text/css" href="css/goods_account.css"/>
<title>商品结算</title>
</head>
<body>
	<% 
		String userName = "";
		UserInfo user = (UserInfo) request.getSession().getAttribute("SESSION_LOGIN_USER");
		if(user!=null) {
				userName = user.getUserName();
			}
		String ids = request.getParameter("ids");
	%>
	<div class="container">
			<p class="title">商品结算</p>
			<p class="tackle">注意：请不要恶意或非法提交订单以避免不必要的麻烦！</p>
			<p class="orderid">订单编号：<%=System.currentTimeMillis() %></p>
			<p class="username">用户名：<%=userName %></p>
			<form action="AddOrderServlet" method="post">
				<input type="hidden" name="ids" value="<%=ids.toString() %>" />
				<input type="hidden" name="nums" value="" />
				<div>
					详细地址：<input  name="orderadress" type="text"/>
				</div>
				<div>
					联系电话：<input  name="ordertel" type="tel"/>
				</div>
				付款方式：<select name="">
							<option>请选择</option>
							<option>普通邮寄</option>
							<option>特快邮寄</option>
							<option>EMS专递方式</option>
				 	      </select>
				<div>
					备注信息：<textarea rows="2" name="orderdesc"></textarea>
				</div>
				<div style="width: 100px; margin: 0 auto;">
					<input type="submit" value="提交订单"/>
				</div>
			</form>
		</div>
</body>
</html>