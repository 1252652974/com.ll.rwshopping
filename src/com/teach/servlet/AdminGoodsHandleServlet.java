package com.teach.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teach.dao.GoodsInfoDAO;
import com.teach.entity.GoodsInfo;

/**
 * Servlet implementation class AdminGoodsHanderServlet
 */
@WebServlet("/AdminGoodsHandleServlet")
public class AdminGoodsHandleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminGoodsHandleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		GoodsInfo user = new GoodsInfo();
		GoodsInfoDAO dao = new GoodsInfoDAO();
		
		String hander = request.getParameter("hander");
		String goodsId = request.getParameter("id");
		String pageNum = request.getParameter("pageNum");
		int id = Integer.parseInt(goodsId);
		
		if("del".equals(hander)) { 
			try {
				dao.deleteById(goodsId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		response.sendRedirect("/rwshopping/admin/admin_goods.s?pageNum="+pageNum);
  	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
