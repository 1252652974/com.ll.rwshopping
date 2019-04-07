<%@ page language="java"  pageEncoding="UTF-8"%>
<%@page import="com.teach.entity.UserInfo"%>
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
		<title>人文二手易购</title>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="js/jquery-3.3.1.min.js"></script>
		<!--<script type="text/javascript" src="js/demo.js"></script>-->
		<script src="js/bootstrap.min.js"></script>
		<script src="layer/layer.js"></script>
		<link rel="stylesheet" href="css/style.css"/>
		<script src="js/jquery-3.3.1.min.js"></script>
		<!--<link rel="stylesheet" href="css/bootstrap.min.css"/>-->
		<link rel="stylesheet" type="text/css" href="css/index.css"/>
		<link rel="stylesheet" href="css/style.css"/>
	</head>
	<body>
		<%
		    int loginStatue = 0;
			UserInfo user = (UserInfo) request.getSession().getAttribute("SESSION_LOGIN_USER");
			int roleId = 0;
			if(user!=null) {
				loginStatue = 1;
				roleId = user.getRoleId();
			}
			
			List<GoodsInfo> lists = (List<GoodsInfo>)request.getAttribute("list");
			GoodsInfoDAO dao = new GoodsInfoDAO();
			int count = (int) dao.countAll();
			
			String status = "index";
			if(request.getAttribute("status")!=null) {
				status = request.getAttribute("status").toString();
			}			
		 %>
 <style type="text/css">
	#targetDiv  
    {  
        width: 100px;  
        height:100px;  
        text-align:center;  
        padding-top:30px;  
        background: #8a8a8a;  
        color: #fff;  
          
        position:fixed;  
        opacity: 0;  
        top:50px;  
        left:-100px; 
        -webkit-transition: all .5s ease-in; 
        -moz-transition: all .5s ease-in;  
        transition: all .5s ease-in;
    }  
    #targetDiv.move  
    {  
        left: 0px;  
        opacity: 1;  
    } 
	</style>
        <div class="container" style="position: relative;">
        	<div class="top-nav">
				<ul class="top-nav-left">
					<li>
						<span class="welcome">喵！欢迎人文二手易购</span><!--文字可以使用span标签放置-->
					</li>
					<li>
						<%
							if(loginStatue == 0) {
								out.print("<a href=\"login.jsp\">请登录</a>");
							}else {
								out.print("<a>欢迎您："+user.getUserName()+"</a>");
							}
						 %>
					</li>
					<li>
						<a href="register.jsp">免费注册</a>
					</li>
					<%
						if(roleId==1) {
							out.print("<li><a href=\"/rwshopping/admin/admin_users.s\">系统管理</a></li>");
						}
					 %>
				</ul>
				<ul class="top-nav-right">				   
				    <li>
				    	<span class="bjpic gwcimg"></span>
				    	<%
							if(loginStatue != 0) {
								out.print("<a href=\"shoppingcart.jsp\">购物车</a>");
							}else {
							    out.print("<a onclick=\"return layer.alert('请先登录')\">购物车</a>");
							}
						 %>
				    </li>
				    <li>
				    	<%
							if(loginStatue != 0) {
								out.print("<a href=\"orders_list.s\">我的订单</a>");
							}else {
							    out.print("<a onclick=\"return layer.alert('请先登录')\">我的订单</a>");
							}
						 %>
				    	<span class="bjpic xsjimg"></span>
				    </li>
				    <li>
				    	<%
							if(loginStatue != 0) {
								out.print("<a href=\"SellGoodsServlet\">我的出售</a>");
							}else {
							    out.print("<a onclick=\"return layer.alert('请先登录')\">我的出售</a>");
							}
						 %>
				    </li>
				    <li><span class="fenggexian">|</span></li>				 
				    <li class="sale">
				        <%
							if(loginStatue != 0) {
								out.print("<a href=\"uploadgoods.jsp\">我要出售</a>");
							}else {
							    out.print("<a onclick=\"return layer.alert('请先登录')\">我要出售</a>");
							}
						 %>
				    	<span class="bjpic xsjimg"></span>
				    </li>
				    <li>
				    	<span class="bjpic dhimg"></span>
				    	<%
				    	    if(loginStatue != 0) {
								out.print("<a href=\"ExitLoginServlet\" onclick=\"return confirm('您确定退出登录吗？')\">安全退出</a>");
							}else {
							    out.print("<a onclick=\"return layer.alert('请先登录')\">安全退出</a>");
							}
						 %>
				    	<span class="bjpic xsjimg"></span>
				    </li>
				</ul>               					
			</div>
			
			<div class="header">
				<div class="h-wrap">
					<div class="logimg-l"></div>
					<div class="seach">
					<form action="SearchGoodsServlet" method="get" onsubmit="return validate();">
					    <div class="seach-test">
					    	 <input type="text" class="input" placeholder="请输入关键字" name="goodsClass" id="searchgoods"/>
					    </div>
					    <div class="seach-btn">
					         <input type="submit" class="button" value="搜索" />
					    </div>
					</form>
					    <div class="seach-wrap">
					    	<ul class="seach-wrap-list">
					    		<li><a href="${pageContext.request.contextPath}/SearchGoodsServlet?goodsClass=衣服">衣服</a></li>
					    		<li><span class="fenggexian">|</span></li>
					    		<li><a href="${pageContext.request.contextPath}/SearchGoodsServlet?goodsClass=电子设备" class="change-color">电子产品</a></li>
					    		<li><span class="fenggexian">|</span></li>
					    		<li><a href="${pageContext.request.contextPath}/SearchGoodsServlet?goodsClass=日常用品">日常用品</a></li>
					    		<li><span class="fenggexian">|</span></li>
					    		<li><a href="${pageContext.request.contextPath}/SearchGoodsServlet?goodsClass=茶叶" class="change-color">茶叶</a></li>
					    		<li><span class="fenggexian">|</span></li>
					    		<li><a href="${pageContext.request.contextPath}/SearchGoodsServlet?goodsClass=学习资料">学习资料</a></li>
					    		<li><span class="fenggexian">|</span></li>
					    		<li><a href="${pageContext.request.contextPath}/SearchGoodsServlet?goodsClass=玩具" class="change-color">玩具</a></li>
					    		<li><span class="fenggexian">|</span></li>
					    		<li><a href="#">手机</a></li>
					    		<li><span class="fenggexian">|</span></li>
					    		<li><a href="${pageContext.request.contextPath}/index.s" class="change-color">全部宝贝</a></li>
					    	</ul>
					    </div>
					</div>
					<div class="logimg-r"></div>	
				</div>
			</div>
            <div class="">
					<table id="cartTable">
					    <thead>
					        <tr>
					            <th><label>&nbsp;编号</label></th>
					            <th>商品</th>
					            <th>单价(元)</th>
					            <th>查看详细信息</th>
					            <th>收藏</th>
					        </tr>
					    </thead>
					    <tbody>
					        
					        <%--<tr>
					            <td class="checkbox"><input class="check-one check" type="checkbox"/></td>
					            <td class="goods"><img src="images/1.jpg" alt=""/><span>Casio/卡西欧 EX-TR350</span></td>
					            <td class="price">5999.88</td>
					            <td class="subtotal">查看详细信息</td>
					            <td class="operation"><span class="delete">收藏</span></td>
					        </tr>--%>
					        <%
					        String selectGoodsId = null;
					        int index = 0;
            				if(lists!=null&&lists.size()>0) {
            					for(GoodsInfo user1:lists) {
            						index++;
            					    String imgurl = user1.getGoodsImgs();
            					    int path_idx=0;
            					    String fname2=null;
            					    if(imgurl!=null){
            					      path_idx=imgurl.lastIndexOf("\\")+1;
            						  fname2=imgurl.substring(path_idx,imgurl.length());
            					    }
            						out.println("<tr class=\"goodsindex\" style=\"display:none;\">");
            						out.println("<td class=\"checkbox\"><p>"+index+"</p></td>");
            				    	out.println("<td class=\"goods\"><img src=\"/goodsimg\\"+fname2+"\"/><span>"+user1.getGoodsName()+"</span></td>");
            				    	out.println("<td class=\"price\">"+user1.getGoodsPrice()+"</td>");
            				    	out.println("<td class=\"count\"><a onClick=\"layer.open({type: 2,area:['500px','400px'],content:'goodsdetail.jsp?id="+user1.getGoodsId()+"'});\">查看详细信息</a></td>");
            				    	if(loginStatue != 0) {
								      	out.println("<td class=\"operation\"><span class=\"delete\"><a class=\"name="+user1.getGoodsName()+"&imgsrc="+fname2+"&price="+user1.getGoodsPrice()+"&goodsId="+user1.getGoodsId()+"\" onclick=\"ajaxProcess(this)\">收藏</a></span></td>");
									}else {
							    		out.print("<td class=\"operation\"><span class=\"delete\"><a onclick=\"return layer.alert('请先登录')\">收藏</a></span></td>");
									}
            				    	out.println("</tr>");
            					}
            				}else {
            					out.println("<h1>没有找到商品</h1>");
            				}
            			   %>
					       
					    </tbody>
                    </table>
				<div class="foot" id="foot" style="cursor:pointer;">
				    		<p style="width: 100%;height: 45px;display: block;line-height: 45px;text-align: center;" id="btn-text">查看更多</p>
				    		<!-- <p style=\"width: 100%;height: 45px;display: block;line-height: 45px;text-align: center;\"><a href=\"index.s?index="+index+"\">查看更多</a></p> -->
				</div>
				<div class="" id="targetDiv">
					<p style="text-align:center; color:red;">系统提示：</p>
					<p style="text-align:center;">收藏成功</p>
				</div>
				
        </div>
        
        <script type="text/javascript">
        function ajaxProcess(obj) {
				var goodscount = obj.value;
				$.ajax(
				  {
				  	url:"addShoppingCar",
				  	data:obj.className.toString(),
				  	success:function() {
				  		 document.querySelector('#targetDiv').className = 'move';
        				 var timeout = setTimeout("document.querySelector('#targetDiv').className = ''",2000);
				  	},
				  	error:function() {alert("收藏失败");}
				  }
				) 
			}	
         function validate() {
         	var searchgoods = document.getElementById("searchgoods");
         	if(searchgoods.value=="") {
         	    layer.alert("请输入搜索关键字");
         	    searchgoods.focus();
         		return false;
         	}
         	return true;
         }
         
        var goodsindexs = document.getElementsByClassName("goodsindex");
        var countGoods = 5;
        var countAll = <%=lists.size() %>;
        if(countGoods>countAll) {
        	countGoods=countAll;
        }
        var btnAdd = document.getElementById("foot");
        if(countAll<countGoods){
        	$('#btn-text').html('到底了(｡•ˇ‸ˇ•｡)');
        	$('#btn-text').css("cursor","default");
        }
        btnAdd.onclick = function() {
        		countGoods+=5;
        		if(countGoods>countAll) {
        			countGoods=countAll;
        		}
        		showGoods(countGoods);
        		if(countAll<=countGoods){
        			$('#btn-text').html('到底了(｡•ˇ‸ˇ•｡)');
        			$('#btn-text').css("cursor","default");
        		}
        }
        function showGoods(countGoods) {
        	for(var index=0;index<countGoods;index++){
        		goodsindexs[index].style.display = "";
        	}
        }
        window.onload=function (){showGoods(countGoods);};
        </script>
        <jsp:include page="footmsg.jsp"></jsp:include>
	</body>
</html>
