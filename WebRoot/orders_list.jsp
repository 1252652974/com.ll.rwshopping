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
		<title>我的订单</title>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="js/jquery-3.3.1.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/demo.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="css/shopingcart.css"/>
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
	</head>
	<body>
		<%
			List<OrderInfo> lists = (List<OrderInfo>)request.getAttribute("list");
		 %>
        <div class="container">
        	<div class="navbar" style="margin-top:0px;">
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
            				    	out.println("</tr>");
            					}
            				}else {
            					out.println("<h1>没有商品</h1>");
            				}
            			   %>
					        <%--<tr>
					            <td class="checkbox"><input class="check-one check" type="checkbox"/></td>
					            <td class="goods"><img src="images/2.jpg" alt=""/><span>Canon/佳能 PowerShot SX50 HS</span></td>
					            <td class="price">3888.50</td>				
					            <td class="subtotal">查看详细信息</td>
					            <td class="operation"><span class="delete">收藏</span></td>
					        </tr>
					        <tr>
					            <td class="checkbox"><input class="check-one check" type="checkbox"/></td>
					            <td class="goods"><img src="images/3.jpg" alt=""/><span>Sony/索尼 DSC-WX300</span></td>
					            <td class="price">1428.50</td>
					            <td class="subtotal">查看详细信息</td>
					            <td class="operation"><span class="delete">收藏</span></td>
					        </tr>
					        <tr>
					            <td class="checkbox"><input class="check-one check" type="checkbox"/></td>
					            <td class="goods"><img src="images/4.jpg" alt=""/><span>Fujifilm/富士 instax mini 25</span></td>
					            <td class="price">640.60</td>
					            <td class="subtotal">查看详细信息</td>
					            <td class="operation"><span class="delete">收藏</span></td>
					        </tr>--%>
					    </tbody>
                    </table>
				<div class="foot" id="foot">
				   <p style="width: 100%;height: 45px;display: block;line-height: 45px;text-align: center;">到底了(｡•ˇ‸ˇ•｡)</p>
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
        </script>
	</body>
</html>
