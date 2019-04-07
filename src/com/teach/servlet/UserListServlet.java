package com.teach.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teach.entity.PageBean;
import com.teach.entity.UserInfo;
import com.teach.service.AdminUsersService;
import com.teach.service.UserInfoService;
import com.teach.service.impl.UserInfoServiceImpl;

public class UserListServlet extends HttpServlet {
	
	private UserInfoService service = new UserInfoServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			//获取当前是第几页
			int pageNum = 1;
			if(request.getParameter("pageNum")!=null) {
				pageNum = Integer.valueOf(request.getParameter("pageNum"));
			}
			//每一页显示的记录数
			int pageSize = 5;
			AdminUsersService service = new AdminUsersService();
			
			PageBean pb = service.findAllUserWithPage(pageNum,pageSize);
			List<UserInfo> list = pb.getList();
			request.setAttribute("pageBean", pb);
			
			//3.处理结果并跳转到对应的视图
			request.setAttribute("list", list);//保存数据到请求属性中
//			response.sendRedirect("admin_users.jsp");//响应重定向
			request.getRequestDispatcher("/admin/admin_users.jsp").forward(request, response);//请求转发
			
		}catch(Exception ex){
			throw new ServletException(ex);
		}
	}
}
