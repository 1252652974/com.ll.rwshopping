package com.teach.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teach.dao.GoodsInfoDAO;
import com.teach.dao.OrderInfoDAO;
import com.teach.entity.GoodsInfo;
import com.teach.entity.OrderInfo;
import com.teach.entity.UserInfo;
import com.teach.service.OrderSortService;

/**
 * Servlet implementation class SoldsListServlst
 */
@WebServlet("/solds_list.s")
public class SoldsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SoldsListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		OrderInfo order = new OrderInfo();
		OrderInfoDAO orderDao = new OrderInfoDAO();
		//GoodsInfo goods = new GoodsInfo();
		GoodsInfoDAO goodsDao = new GoodsInfoDAO();
		
		UserInfo user = (UserInfo) request.getSession().getAttribute("SESSION_LOGIN_USER");
		int userId = user.getUserId();
		List<OrderInfo> list = new ArrayList<OrderInfo>();
		
		try {
			List<GoodsInfo> goodsList = goodsDao.selectBySellerId(userId);
			for(GoodsInfo goods:goodsList) {
				list.addAll(orderDao.selectByGoodsId(goods.getGoodsId()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OrderSortService sercice = new OrderSortService();
		sercice.orderSort(list);
		request.setAttribute("list", list);//保存数据到请求属性中
		request.getRequestDispatcher("/solds_list.jsp").forward(request, response);//请求转发
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
