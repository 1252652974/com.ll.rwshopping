package com.teach.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teach.dao.GoodsInfoDAO;
import com.teach.entity.GoodsInfo;

/**
 * Servlet implementation class IndexGoodsListServlet
 */
@WebServlet("/index.s")
public class IndexGoodsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexGoodsListServlet() {
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
		
		int len=5;
	    
		try {
			len = (int) dao.countAll();
			List<GoodsInfo> list = dao.selectAll(0, len);
			
			request.setAttribute("list", list);//保存数据到请求属性中
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//记录上次访问时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd#HH:mm:ss");
		String nowTime = sdf.format(new Date());
		Cookie lastVisitTimeC = new Cookie("lastVisitTime",nowTime);
		lastVisitTimeC.setMaxAge(365*24*60*60);
		response.addCookie(lastVisitTimeC);
		
		//记录访问人数
		ServletContext context = getServletContext();
		List<String> ips  = (List<String>) context.getAttribute("ipNum");
		int status = 0;
		for(String ip:ips) {
			if(ip.equals(request.getLocalAddr())) {
				status = 1;
			}
		}
		if(status == 0) {
			ips.add(request.getLocalAddr());
		}
		context.setAttribute("ipNum", ips);
		request.getRequestDispatcher("/index.jsp").forward(request, response);//请求转发
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		List<String> ipss = new ArrayList<>();
		ServletContext contextt = getServletContext();
		contextt.setAttribute("ipNum", ipss);
		super.init();
	}

	
}
