<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.teach.entity.UserInfo"%>
<%@page import="com.teach.entity.GoodsInfo"%>
<%@page import="com.teach.dao.GoodsInfoDAO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="java.lang.reflect.Array"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="java.util.Iterator"%>
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<script src="js/jquery-3.3.1.min.js"></script>
<script src="layer/layer.js"></script>
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
	<div class="top-nav">
				<ul class="top-nav-left">
					<li>
						<span class="welcome"><a href="index.s" style="color:red;">喵！欢迎人文二手易购</a></span><!--文字可以使用span标签放置-->
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