package com.teach.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teach.dao.UserInfoDAO;
import com.teach.entity.UserInfo;

/**
 * Servlet implementation class ApplicantLoginServlet
 */
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
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
		PrintWriter out = response.getWriter();
		
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		String rememberMe = request.getParameter("rememberME");
		String verifyCode = request.getParameter("verifyCode");
		//判断验证码是否正确
		String sessionValidateCode = (String) request.getSession().getAttribute("SESSION_VALIDATECODE");
		//登錄驗證
		UserInfoDAO dao = new UserInfoDAO();
		int userid = 0;
		userid = dao.login(userName, passWord);
		try {
		    if(userid!=0&&sessionValidateCode.equals(verifyCode)) {
		    int roleId = dao.selectByUserId(userid).getRoleId();
			request.setAttribute("loginStatue", "Y");
			//驗證通過時
			UserInfo  user = new UserInfo(userid, userName, roleId, passWord);
			request.getSession().setAttribute("SESSION_LOGIN_USER", user);
			//通過cookie記住用戶名密碼
			rememberMe(rememberMe,userName,passWord,request,response);
			response.sendRedirect("/rwshopping/index.s");
		}
		if(userid==0) {
			request.setAttribute("loginStatue", "N");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return ;
		}
		if(!sessionValidateCode.equals(verifyCode)) {
			request.setAttribute("verifyStatue", "N");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return ;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void rememberMe(String rememberME, String userName, String passWord, HttpServletRequest request, HttpServletResponse response) {
		if("true".equals(rememberME)) {
			Cookie cookie= new Cookie("COOKLE_LOGIN_passWord",passWord);
			cookie.setPath("/");
			cookie.setMaxAge(365*24*3600);
			response.addCookie(cookie);
			cookie= new Cookie("COOKLE_LOGIN_name",userName);
			cookie.setPath("/");
			cookie.setMaxAge(365*24*3600);
			response.addCookie(cookie);
		}else {
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for(Cookie cookie1:cookies ) {
					if("COOKLE_LOGIN_passWord".equals(cookie1.getName())||"COOKLE_LOGIN_name".equals(cookie1.getName())) {
						cookie1.setPath("/");
						cookie1.setMaxAge(0);
						response.addCookie(cookie1);
					}
				}
			}
		}
		
	}

}
