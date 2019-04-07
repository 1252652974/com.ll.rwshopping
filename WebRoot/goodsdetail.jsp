<%@page import="com.teach.service.CountGoodsTotalService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.teach.entity.GoodsInfo"%>
<%@page import="com.teach.dao.GoodsInfoDAO"%>
<%@page import="com.teach.entity.UserInfo"%>
<%@page import="com.teach.dao.UserInfoDAO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品详情</title>
</head>
<body>
	<%		
		String id=request.getParameter("id");
		GoodsInfo goods = new GoodsInfo();
		GoodsInfoDAO dao = new GoodsInfoDAO();
		goods = dao.selectById(id); 
		
		String imgurl = goods.getGoodsImgs();
        int path_idx=0;
        String fname2=null;
        if(imgurl!=null){
        	path_idx=imgurl.lastIndexOf("\\")+1;
        	fname2=imgurl.substring(path_idx,imgurl.length());
        }   
        
        int sellerId = goods.getGoodsSellerId();	
        UserInfo user = new UserInfo();
		UserInfoDAO dao1 = new UserInfoDAO();	
		user = dao1.selectByUserId(sellerId);
		
		//计算已售及剩余
		CountGoodsTotalService service = new CountGoodsTotalService();
		int goodsplus = service.countGoodsTotal(goods.getGoodsId());
		int soldNum = goods.getGoodsTotal()-goodsplus;			    
	%>
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
		width:150px;
		height:100px;
		
		}
    </style>
	<div class="goods-detail">
					<h2><%=goods.getGoodsName() %></h2>
					<%
						out.println("<img src=\"/goodsimg\\"+fname2+"\"/>");
					 %>
					<h3>商品价格：</h3>
					<h4><%=goods.getGoodsPrice() %></h4>
					<h3>商品描述：</h3>
					<h4><%=goods.getGoodsDesc() %></h4>
					<h3>商品类型：</h3>
					<h4><%=goods.getGoodsClass() %></h4>
					<h3>商品卖家：</h3>
					<h4><%=user.getUserName() %></h4>
					<h3>商品上传日期：</h3>
					<h4><%=goods.getGoodsPubdate() %></h4>
					<h3>商品已售：</h3>
					<h4><%=soldNum %>件</h4>
					<h3>商品库存：</h3>
					<h4><%=goodsplus %>件</h4>
	</div>
</body>
</html>