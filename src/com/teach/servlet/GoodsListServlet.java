package com.teach.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teach.entity.PageBean;
import com.teach.entity.GoodsInfo;
import com.teach.service.AdminGoodsService;

/**
 * Servlet implementation class GoodsListServlet
 */
@WebServlet("/admin/admin_goods.s")
public class GoodsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			//获取当前是第几页
			int pageNum = 1;
			if(request.getParameter("pageNum")!=null) {
				pageNum = Integer.valueOf(request.getParameter("pageNum"));
			}
			//每一页显示的记录数
			int pageSize = 5;
			AdminGoodsService service = new AdminGoodsService();
			
			PageBean pb = service.findAllUserWithPage(pageNum,pageSize);
			List<GoodsInfo> list = pb.getList();
			request.setAttribute("pageBean", pb);
			
			//3.处理结果并跳转到对应的视图
			request.setAttribute("list", list);//保存数据到请求属性中
//			response.sendRedirect("admin_users.jsp");//响应重定向
			request.getRequestDispatcher("/admin/admin_goods.jsp").forward(request, response);//请求转发
			
		}catch(Exception ex){
			throw new ServletException(ex);
		}response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
