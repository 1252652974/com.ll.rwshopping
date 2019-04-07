<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.teach.dao.OrderInfoDAO"%>
<%@page import="com.teach.entity.OrderInfo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<style>
		h2 {
	    	margin: 2px auto;
	    }
		h3,h4 {
		   margin: 2px auto;
		}
		h3 {
			color: gray;
		}
		img {
		width:300px;
		height:200px;
		
		}
    </style>
    <%
    	String id=request.getParameter("id");
		OrderInfo order = new OrderInfo();
		OrderInfoDAO dao = new OrderInfoDAO();
		order = dao.selectById(id); 
     %>
	<div class="goods-detail">
					<h2>订单详情</h2></br>
					<h3>订单编号：</h3>
					<h4><%=order.getOrderId() %></h4>
					<h3>预留联系方式：</h3>
					<h4><%=order.getOrderTel() %></h4>
					<h3>预留地址：</h3>
					<h4><%=order.getOrderAddress() %></h4>
					<h3>下单时间</h3>
					<h4><%=order.getOrderTime() %></h4>
					<h3>备注信息</h3>
					<h4><%=order.getOrderRemark() %></h4>
	</div>
</body>
</html>