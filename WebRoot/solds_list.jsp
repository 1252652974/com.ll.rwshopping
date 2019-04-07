<%@page import="com.teach.dao.UserInfoDAO"%>
<%@ page language="java"  pageEncoding="UTF-8"%>
<%@page import="com.teach.entity.OrderInfo"%>
<%@page import="com.teach.entity.GoodsInfo"%>
<%@page import="com.teach.dao.GoodsInfoDAO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="java.lang.reflect.Array"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="java.util.Iterator"%>


<!DOCTYPE html>
<html>
	<head>
		<title>我的出售</title>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="js/jquery-3.3.1.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<!-- <script src="js/demo.js" type="text/javascript" charset="utf-8"></script> -->
		<link rel="stylesheet" type="text/css" href="css/shopingcart.css"/>
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
		<link rel="stylesheet" type="text/css" href="css/common.css"/>
	</head>
	<body>
		<%
			List<OrderInfo> lists = (List<OrderInfo>)request.getAttribute("list");
		 %>
        <div class="container">
        	<div class="navbar">
        		<jsp:include page="topnav.jsp"></jsp:include>
		    </div>
            <div class="">
					<table id="cartTable">
					    <thead>
					        <tr>
					            <th><label>&nbsp;单号</label></th>
					            <th>商品</th>
					            <th>单价(元)</th>
					            <th>数量</th>
					            <th>已付(元)</th>
					            <th>订单状态</th>
					            <th>订单详情</th>
					            <th>操作</th>
					        </tr>
					    </thead>
					    <tbody>
					        <%
					        GoodsInfoDAO dao = new GoodsInfoDAO();
            				if(lists!=null&&lists.size()>0) {
            					for(OrderInfo order:lists) {
            					    String imgurl = dao.selectById(order.getGoodsId()).getGoodsImgs();
            					    int path_idx=0;
            					    String fname2=null;
            					    if(imgurl!=null){
            					      path_idx=imgurl.lastIndexOf("\\")+1;
            						  fname2=imgurl.substring(path_idx,imgurl.length());
            					    }
            					    String status = "未出货";
            					    if(order.getOrdersStatus()==1) {status = "已出货";}
            						out.println("<tr>");
            						out.println("<td class=\"checkbox\"><p>"+order.getOrderId()+"</p></td>");
            				    	out.println("<td class=\"goods\"><img src=\"/goodsimg\\"+fname2+"\"/><span>"+dao.selectById(order.getGoodsId()).getGoodsName()+"</span></td>");
            				    	out.println("<td class=\"price\">"+dao.selectById(order.getGoodsId()).getGoodsPrice()+"</td>");
            				    	out.println("<td class=\"price\">"+order.getGoodsNum()+"</td>");
            				    	out.println("<td class=\"price\">"+order.getGoodsNum()*Integer.parseInt(dao.selectById(order.getGoodsId()).getGoodsPrice())+"</td>");
            				    	out.println("<td class=\"price\">"+status+"</td>");
            				    	out.println("<td class=\"count\"><a onClick=\"layer.open({type: 2,area:['500px','400px'],content:'orderdetail.jsp?id="+order.getId()+"'});\">查看详细信息</a></td>");
            				    	if(order.getOrdersStatus()==1) {
            				    		out.println("<td class=\"count\"><a style=\"color:gray\">出货成功</a></td>");
            				    	}else {
            				    		out.println("<td class=\"count\"><a class=\"id="+order.getId()+"\" onclick=\"ajaxProcess(this)\">马上出货</a></td>");
            				    	}
            				    	out.println("</tr>");
            					}
            				}else {
            					out.println("<h1>没有商品</h1>");
            				}
            			   %>
					       
					    </tbody>
                    </table>
				<div class="foot" id="foot">
				   <p style="width: 100%;height: 45px;display: block;line-height: 45px;text-align: center;">到底了(｡•ˇ‸ˇ•｡)</p>
				</div>
				<div class="" id="targetDiv">
					<p style="text-align:center; color:red;">系统提示：</p>
					<p style="text-align:center;">出货成功</p>
				</div>
        </div>
        <script type="text/javascript">
         function validate() {
         	var searchgoods = document.getElementById("searchgoods");
         	if(searchgoods.value=="") {
         	    alert("请输入搜索关键字");
         	    searchgoods.focus();
         		return false;
         	}
         	return true;
         }
         
         function ajaxProcess(obj) {
				var goodscount = obj.value;
				$.ajax(
				  {
				  	url:"OrderStatueServlet",
				  	data:obj.className.toString(),
				  	success:function() {
				  		document.querySelector('#targetDiv').className = 'move';
        				var timeout = setTimeout("document.querySelector('#targetDiv').className = ''",2000);
        				obj.parentElement.previousElementSibling.previousElementSibling.textContent="已出货";
        				obj.style.cssText="color:gray;";
        				obj.innerHTML="出货成功";
        				obj.removeAttr("onclick");
				  	},
				  	error:function() {alert("出货失败");}
				  }
				) 
			}
        </script>
	</body>
</html>