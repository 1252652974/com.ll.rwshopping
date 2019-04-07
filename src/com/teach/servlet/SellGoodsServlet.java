package com.teach.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teach.dao.GoodsInfoDAO;
import com.teach.entity.GoodsInfo;
import com.teach.entity.UserInfo;

/**
 * Servlet implementation class SellGoodsServlet
 */
@WebServlet("/SellGoodsServlet")
public class SellGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		GoodsInfo goods = new GoodsInfo();
        GoodsInfoDAO dao = new GoodsInfoDAO();
        UserInfo user = (UserInfo) request.getSession().getAttribute("SESSION_LOGIN_USER");
		int sellerId = 0;
		if(user!=null) {
			sellerId = user.getUserId();
		}
        int len=5;
        try {
			len = (int) dao.countAll();
			List<GoodsInfo> list = dao.selectBySellerId(sellerId);
			
			request.setAttribute("list", list);//保存数据到请求属性中
			request.getRequestDispatcher("/sellGoods.jsp").forward(request, response);//请求转发
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
