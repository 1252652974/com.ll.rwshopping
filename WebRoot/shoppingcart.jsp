<%@page import="com.teach.service.CountGoodsTotalService"%>
<%@page import="org.apache.catalina.connector.Request"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.teach.entity.UserInfo"%>
<%@page import="com.teach.entity.GoodsInfo"%>
<%@page import="com.teach.dao.GoodsInfoDAO"%>
<%@page import="java.util.List"%>
<%@page import=" javax.servlet.http.HttpSession"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java"  pageEncoding="UTF-8"%>

<!DOCTYPE html>
	<head>
		<title>shoppingcart</title>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="js/jquery-3.3.1.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/demo.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="css/shopingcart.css"/>
	</head>
	<body>
		<%
			List<GoodsInfo> car = (ArrayList<GoodsInfo>)request.getSession().getAttribute("shoppingCar");
		 %>
		<div class="container">
		   <jsp:include page="topnav.jsp"></jsp:include>
		    
            <div class="cart-list" style="margin-top:15px;">
            	<link rel="stylesheet" type="text/css" href="css/style.css"/>
            	<table id="cartTable">
            		<thead>
            			<tr>
            				<th><label><input class="check-all check" type="checkbox"/>&nbsp;全选</label></th>
            				<th>商品</th>
            				<th>单价</th>
            				<th>数量</th>
            				<th>小计</th>
            				<th>操作</th>
            			</tr>
            		</thead>
            		<tbody>
            			<%
            				StringBuffer ids = new StringBuffer("");
            				if(car!=null&&car.size()>0) {
            					for(GoodsInfo goods:car) {
            						CountGoodsTotalService service = new CountGoodsTotalService();
									int goodsplus = service.countGoodsTotal(goods.getGoodsId());
            						/* 在地址栏传递带#的字符串需要转义（ascll）：%23 */
            						ids.append(goods.getGoodsId()+"%23");
            						out.println("<tr>");
            						out.println("<td class=\"checkbox\"><input class=\"check-one check choose\" name=\""+goods.getGoodsId()+"\" type=\"checkbox\" /></td>");
            				    	out.println("<td class=\"goods\"><img src=\"/goodsimg\\"+goods.getGoodsImgs()+"\"/><span>"+goods.getGoodsName()+"</span></td>");
            				    	out.println("<td class=\"price\">"+goods.getGoodsPrice()+"</td>");
            				    	out.println("<td class=\"count\"><a><span class=\"reduce\"></span></a><input class=\"count-input\" type=\"text\" value=\"1\" id=\"goods-count\" name=\""+goodsplus+"\" oninput=\"return reValue(this)\"/><span class=\"add\" onclick=\"return reNumValue(this)\">+</span></td>");
            				    	out.println("<td class=\"subtotal\">"+goods.getGoodsPrice()+"</td>");
            				    	out.println("<td class=\"operation\"><span class=\"delete\"><a href=\"DeleteFormCartServlet?imgsrc="+goods.getGoodsImgs()+"\">删除</a></span></td>");
            						out.println("</tr>");
            					}
            				}else {
            					out.println("<h1>购物车没有任何商品</h1>");
            				}
            			 %>

            		</tbody>
            	</table>
            <%if(car!=null&&car.size()>0) { %>
            	<div class="foot" id="foot">
            		<label class="fl select-all"><input type="checkbox" class="check-all check"/>&nbsp;全选</label>
            		<a class="fl delete" id="deleteAll" href="javascript:;">删除</a>
            		<div class="fr closing"><a <%-- href="goods_account.jsp?ids=<%=ids.toString() %>" --%> onclick="return countNum()">结 算</a></div>
            		<div class="fr total">合计：￥<span id="priceTotal">0.00</span></div>
            		<div class="fr selected" id="selected">已选商品<span id="selectedTotal">0</span>件<span class="arrow up">︽</span><span class="arrow down">︾</span></div>
            		<div class="selected-view">
            			<div id="selectedViewList" class="clearfix">
            				<div><img src="images/1.jpg"><span>取消选择</span></div>
            			</div>
            			<span class="arrow">◆<span>◆</span></span>
            		</div>
            	</div>
             <%	} %>
            </div>
		</div>
		<script type="text/javascript">
			var ids = '';
			var nums = '';
			function countNum(){
				var goods = document.getElementsByClassName("choose");
				for(var i=0;i<goods.length;i++) {
					if(goods[i].checked==true) {
						ids = ids.concat(goods[i].name.toString())+"%23";
						var numObj = goods[i].parentElement.nextElementSibling.nextElementSibling.nextElementSibling.childNodes[1];
						nums = nums.concat(numObj.value.toString())+"%23";
					}
				}
				window.location.href="goods_account.jsp?ids="+ids+"&"+"nums="+nums;
			}
			function reValue(input) {
				if(parseInt(input.value)>parseInt(input.name)) {
					input.value=input.name;
				}	
			}
			function reNumValue(add) {
			 	var input = add.previousElementSibling;
				if(parseInt(input.value)>=parseInt(input.name)) {
					input.value=parseInt(input.name)-1;
				}	
			}
			/* function ajaxProcess(obj) {
				var goodscount = obj.value;
				$.ajax(
				  {
				  	url:"GoodsCountServlet",
				  	data:{goodsCount:goodscount},
				  	success:function() {},
				  	error:function() {alert("失败");}
				  }
				)
			} */				
		</script>
	</body>
</html>