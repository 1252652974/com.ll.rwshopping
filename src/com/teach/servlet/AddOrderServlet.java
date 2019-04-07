package com.teach.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teach.dao.OrderInfoDAO;
import com.teach.entity.OrderInfo;
import com.teach.entity.UserInfo;

/**
 * Servlet implementation class AddOrderServlet
 */
@WebServlet("/AddOrderServlet")
public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String ids = request.getParameter("ids");
		String nums = request.getParameter("nums");
		String orderAdress = request.getParameter("orderadress");
		String orderTel = request.getParameter("ordertel");
		String orderDesc = request.getParameter("orderdesc");
		
		UserInfo user = (UserInfo) request.getSession().getAttribute("SESSION_LOGIN_USER");
		int userId = user.getUserId();
		int orderId = (int)System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		String orderTime = sdf.format(new Date());
		
		String[] goodsIds = ids.split("#");
		String[] goodsnums = nums.split("#");
		/*System.out.println(java.util.Arrays.toString(goodsnums));*/
		OrderInfoDAO dao = new OrderInfoDAO();
		
		int index = 0;
		for(String goodsId:goodsIds) {
			/*System.out.println("goodsId:"+goodsId);*/
			OrderInfo order = new OrderInfo();
			order.setGoodsId(Integer.parseInt(goodsId));
			order.setUserId(userId);
			order.setOrderAddress(orderAdress);
			order.setOrderId(orderId);
			order.setOrderRemark(orderDesc);
			order.setOrderTel(orderTel);
			order.setGoodsNum(Integer.parseInt(goodsnums[index]));
			order.setOrderTime(orderTime);
			try {
				dao.insert(order);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			index++;
		}
		response.sendRedirect("orders_list.s");
		
	}

}
