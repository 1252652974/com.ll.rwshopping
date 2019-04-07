<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.teach.entity.GoodsInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<title>商品管理</title>
		<meta charset="utf-8" />
		<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
		<script src="../js/jquery-3.3.1.min.js"></script>
		<script src="../js/bootstrap.min.js"></script>
		<script src="../layer/layer.js"></script>
		<link rel="stylesheet" href="../css/bootstrap.min.css" />
		<link rel="stylesheet" href="../css/admin_users.css" />
	</head>
	<body>
		<div class="container">
			<div class="top-nav">
				<nav class="navbar navbar-inverse">
					<div class="container-fluid">
						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-9"
							 aria-expanded="false">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
							<a class="navbar-brand" href="/rwshopping/index.s">首页</a>
						</div>

						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-9">
							<ul class="nav navbar-nav">
								<li class="active"><a>商品管理</a></li>
								<li><a href="admin_users.s">用户管理</a></li>
								<li><a href="admin_orders.s">订单管理</a></li>
								<li><a href="../ExitLoginServlet" onclick="return confirm('您确定退出登录吗？')">安全退出</a></li>
							</ul>
						</div><!-- /.navbar-collapse -->
					</div><!-- /.container-fluid -->
				</nav>
			</div>

			<div class="user-list">
				<table class="table table-striped">
					<thead>
						<tr>
						    <th>#</th>
							<th>商品ID</th>
							<th>商品名</th>
							<th>商品图片</th>
							<th>价格</th>
							<th>卖家ID</th>
							<th>商品描述</th>
							<th>商品种类</th>
							<th class="text-center" style="width: 10em">操作</th>
						</tr>
					</thead>
					<tbody>
					<%
					List<GoodsInfo> list = (List<GoodsInfo>)request.getAttribute("list");
					int count=1;
					for(GoodsInfo vo : list){
					
					String imgurl = vo.getGoodsImgs();
            		int path_idx=0;
            		String fname2=null;
            		if(imgurl!=null){
            			path_idx=imgurl.lastIndexOf("\\")+1;
            			fname2=imgurl.substring(path_idx,imgurl.length());
            		}
					%>
					<tr>
						<td scope="row"><%=count++ %></td>
						<td><%=vo.getGoodsId() %></td>
						<td><%=vo.getGoodsName() %></td>
						<td><%=fname2 %></td>
						<td><%=vo.getGoodsPrice() %></td>
						<td><%=vo.getGoodsSellerId() %></td>
						<td><%=vo.getGoodsDesc() %></td>
						<td><%=vo.getGoodsClass() %></td>
						<td class="text-center">
							
										<!-- <a href="javascript:void(0);"  data-toggle="modal" data-target="#editModal2">修改</a> -->
							
							
							<a onclick="return confirm('您确定要将此商品删除吗？')"  href="../AdminGoodsHandleServlet?hander=del&id=<%=vo.getGoodsId() %>&pageNum=${requestScope.pageBean.pageNum}">删除商品</a>
										</td>
					</tr>	
					<%} %>
				</tbody>
				</table>
				
				<div class="operation">
				
				</div>
			</div>
            
            <div class="foot-nav">
<%-- 构建分页导航 --%>
            <p class="text-info" style="font-size: 20px; text-align: center;">共有${requestScope.pageBean.totalRecord}个商品，共${requestScope.pageBean.totalPage }页，当前为${requestScope.pageBean.pageNum}页</p>
	    <nav aria-label="...">
				<ul class="pagination pagination-lg center-block" style="width: 450px;">
	<li>
            <a href="${pageContext.request.contextPath}/admin/admin_goods.s?pageNum=1" aria-label="Previous"><span aria-hidden="true">首页</span></a>
	</li>
            <%--如果当前页为第一页时，就没有上一页这个超链接显示 --%>
            <c:if test="${requestScope.pageBean.pageNum ==1}">
                <c:forEach begin="${requestScope.pageBean.start}" end="${requestScope.pageBean.end}" step="1" var="i">
                    <c:if test="${requestScope.pageBean.pageNum == i}">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/admin_goods.s?pageNum=${i}">${i}</a>   
			</li>   
                    </c:if>                
                    <c:if test="${requestScope.pageBean.pageNum != i}">
			<li>
                            <a href="${pageContext.request.contextPath}/admin/admin_goods.s?pageNum=${i}">${i}</a>   
			</li>                                     
                    </c:if>                        
                </c:forEach>
	<li>
              <a href="${pageContext.request.contextPath}/admin/admin_goods.s?pageNum=${requestScope.pageBean.pageNum+1}">下一页</a>  
	</li>                   
            </c:if>
            
            <%--如果当前页不是第一页也不是最后一页，则有上一页和下一页这个超链接显示 --%>
            <c:if test="${requestScope.pageBean.pageNum > 1 && requestScope.pageBean.pageNum < requestScope.pageBean.totalPage}">
	<li>
               <a href="${pageContext.request.contextPath}/admin/admin_goods.s?pageNum=${requestScope.pageBean.pageNum-1}">上一页</a>
	</li>
                <c:forEach begin="${requestScope.pageBean.start}" end="${requestScope.pageBean.end}" step="1" var="i">    
                    <c:if test="${requestScope.pageBean.pageNum == i}">
                       <li>
                            <a href="${pageContext.request.contextPath}/admin/admin_goods.s?pageNum=${i}">${i}</a>   
			</li> 
                    </c:if>            
                    <c:if test="${requestScope.pageBean.pageNum != i}">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/admin_goods.s?pageNum=${i}">${i}</a>   
			</li>                                          
                    </c:if>                        
                </c:forEach>
        <li>
              <a href="${pageContext.request.contextPath}/admin/admin_goods.s?pageNum=${requestScope.pageBean.pageNum+1}">下一页</a>  
	</li>     
            </c:if>
            
            <%-- 如果当前页是最后一页，则只有上一页这个超链接显示，下一页没有 --%>
            <c:if test="${requestScope.pageBean.pageNum == requestScope.pageBean.totalPage}">
	<li>
               <a href="${pageContext.request.contextPath}/admin/admin_goods.s?pageNum=${requestScope.pageBean.pageNum-1}">上一页</a>
	</li>
                <c:forEach begin="${requestScope.pageBean.start}" end="${requestScope.pageBean.end}" step="1" var="i">
                    <c:if test="${requestScope.pageBean.pageNum == i}">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/admin_goods.s?pageNum=${i}">${i}</a>   
			</li>
                    </c:if>
                    <c:if test="${requestScope.pageBean.pageNum != i}">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/admin_goods.s?pageNum=${i}">${i}</a>   
			</li>                                          
                    </c:if>                
                </c:forEach>
            </c:if>
            <%--尾页 --%>
	<li>
            <a href="${pageContext.request.contextPath}/admin/admin_goods.s?pageNum=${requestScope.pageBean.totalPage}" aria-label="Next"><span aria-hidden="true">尾页</span></a>
	</li>
		</ul>
	</nav>
</div>
		</div>
	</body>
</html>