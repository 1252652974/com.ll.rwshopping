package com.teach.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teach.dao.UserInfoDAO;
import com.teach.entity.PageBean;
import com.teach.entity.UserInfo;
import com.teach.service.AdminUsersService;

/**
 * Servlet implementation class AdminUsers
 */
@WebServlet("/AdminUsers")
public class AdminUsersServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取当前是第几页
		int pageNum = 1;
		if(request.getParameter("pageNum")!=null) {
			pageNum = Integer.valueOf(request.getParameter("pageNum"));
		}
				//每一页显示的记录数
				int pageSize = 5;
				AdminUsersService service = new AdminUsersService();
				//获取pb对象，里面包含了所用分页所需的数据
				try {
					PageBean pb = service.findAllUserWithPage(pageNum,pageSize);
					List<UserInfo> list = pb.getList();
					request.setAttribute("list", list);
					//request.setAttribute("pageBean", pb);
					request.getRequestDispatcher("/admin/admin_users.jsp").forward(request, response);
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
		//获取当前是第几页
		int pageNum = Integer.valueOf(request.getParameter("pageNum"));
		//每一页显示的记录数
		int pageSize = 5;
		AdminUsersService service = new AdminUsersService();
		
		//获取pb对象，里面包含了所用分页所需的数据
		try {
			PageBean pb = service.findAllUserWithPage(pageNum,pageSize);
			List<UserInfo> list = pb.getList();
			request.setAttribute("list", list);
			request.setAttribute("pageBean", pb);
			request.getRequestDispatcher("/admin/admin_users.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
