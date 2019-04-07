<%@page import="java.util.List"%>
<%@page import="com.teach.entity.UserInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <style type="text/css">
		.foot-msg {
			margin-top: 5px;
			width: 100%;
			height: 30px;
		}
		.foot-msg * {
			text-decoration: none;
			list-style: none;
			margin: 0 0;
		}
		.foot-nav {
			display: flex;
			justify-content: space-around;
		}
		.foot-nav li {
			line-height: 30px;
		}
	</style>
	<%
		Cookie[] cookies = request.getCookies();
		String lastVisitTime = "";
		if(cookies!=null) {
			for(Cookie cookie:cookies) {
				if("lastVisitTime".equals(cookie.getName())) {
					lastVisitTime = cookie.getValue();
				}
			}
		}
		
		ServletContext context = getServletContext();
		List<String> ips  = (List<String>) context.getAttribute("ipNum");
		
	 %>
	<div class="foot-msg">
	<ul class="foot-nav">
		<li>上次访问时间：<%=lastVisitTime %></li>
		<li>在线人数：<%=request.getSession().getServletContext().getAttribute("num") %>人</li>
		<li>访问人数：<%=ips.size() %>人</li>
	</ul>
	</div>
