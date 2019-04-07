<%@page import="com.teach.dao.UserInfoDAO"%>
<%@ page language="java"  pageEncoding="UTF-8"%>
<%@page import="com.teach.entity.GoodsInfo"%>
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
		<script src="js/demo.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="css/shopingcart.css"/>
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
	</head>
	<body>
		<%
			List<GoodsInfo> lists = (List<GoodsInfo>)request.getAttribute("list");
		 %>
        <div class="container">
        	<jsp:include page="topnav.jsp"></jsp:include>
        	<div class="navbar">
		    	<ul style="margin-top:10px;">
		    		<li><a href="solds_list.s" style="color:grey;">我的客户订单</a></li>
		    	</ul>
		    </div>
		
		<table id="cartTable">
					    <thead>
					        <tr>
					            <th><label>&nbsp;编号</label></th>
					            <th>商品</th>
					            <th>单价(元)</th>
					            <th>商品种类</th>
					            <th>商品描述</th>
					            <th>上传日期</th>
					            <th>操作</th>
					        </tr>
					    </thead>
					    <tbody>
					        <%
					        String selectGoodsId = null;
					        int index = 0;
            				if(lists!=null&&lists.size()>0) {
            					for(GoodsInfo good:lists) {
            						index++;
            					    String imgurl = good.getGoodsImgs();
            					    int path_idx=0;
            					    String fname2=null;
            					    if(imgurl!=null){
            					      path_idx=imgurl.lastIndexOf("\\")+1;
            						  fname2=imgurl.substring(path_idx,imgurl.length());
            					    }
            						out.println("<tr class=\"goodsindex\">");
            						out.println("<td class=\"checkbox\"><p>"+index+"</p></td>");
            				    	out.println("<td class=\"goods\"><img src=\"/goodsimg\\"+fname2+"\"/><span>"+good.getGoodsName()+"</span></td>");
            				    	out.println("<td class=\"price\">"+good.getGoodsPrice()+"</td>");
            				    	out.println("<td class=\"price\">"+good.getGoodsClass()+"</td>");
            				    	out.println("<td class=\"price\">"+good.getGoodsDesc()+"</td>");
            				    	out.println("<td class=\"price\">"+good.getGoodsPubdate()+"</td>");
            				    	out.println("<td class=\"count\"><a onclick=\"return confirm('您确定要将此商品删除吗？')\" href=\"DeleteGoods?id="+good.getGoodsId()+"\">删除此出售</a></td>");
            				    	out.println("</tr>");
            					}
            				}else {
            					out.println("<h1>没有任何商品</h1>");
            				}
            			   %>
					    </tbody>
                    </table>
                     <div class="foot" id="foot" style="cursor:pointer;">
				    	<p style="width: 100%;height: 45px;display: block;line-height: 45px;text-align: center;">到底了</p>
					 </div>
</body>
</html>