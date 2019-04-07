package com.teach.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teach.dao.UserInfoDAO;
import com.teach.entity.UserInfo;

/**
 * Servlet implementation class AdminUsersHandleServlet
 */
@WebServlet("/AdminUsersHandleServlet")
public class AdminUsersHandleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUsersHandleServlet() {
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
		UserInfo user = new UserInfo();
		UserInfoDAO dao = new UserInfoDAO();
		
		String hander = request.getParameter("hander");
		String userId = request.getParameter("id");
		String pageNum = request.getParameter("pageNum");
		int id = Integer.parseInt(userId);
		
		if("del".equals(hander)) { 
			try {
				dao.deleteById(userId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("alter".equals(hander)) {
			try {
				user = dao.selectByUserId(id);
				user.setUserPwd("123");
				dao.update(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		response.sendRedirect("/rwshopping/admin/admin_users.s?pageNum="+pageNum);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
