package com.teach.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.catalina.core.ApplicationPart;

import com.teach.dao.GoodsInfoDAO;
import com.teach.entity.GoodsInfo;
import com.teach.entity.UserInfo;

/**
 * Servlet implementation class UploadGoodsServlet
 */
@WebServlet("/UploadGoodsServlet")
@MultipartConfig(location = "E://")
public class UploadGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadGoodsServlet() {
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
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        String path=this.getServletContext().getRealPath("/");
        String goodsName =(String) request.getParameter("goodsName");
        String goodsPrice =(String) request.getParameter("goodsPrice");
        String goodsDesc =(String) request.getParameter("goodsDesc");
        int goodsTotal = Integer.parseInt(request.getParameter("goodsTotal"));
        String goodsClass =(String) request.getParameter("goodsClass");
        UserInfo user = (UserInfo) request.getSession().getAttribute("SESSION_LOGIN_USER");
        int GoodsSellerId = user.getUserId();
        Date now = new Date();
        String A = "yyyy-MM-dd";
        SimpleDateFormat s = new SimpleDateFormat(A);
        String goodsPubdate = s.format(now);
        String goodsImgs = "123";
        long item = System.currentTimeMillis();
        
        GoodsInfo goods = new GoodsInfo();
        GoodsInfoDAO dao = new GoodsInfoDAO();
        
        Part p=request.getPart("file1");
        if(p.getContentType().contains("image")){
            ApplicationPart ap= (ApplicationPart) p;
            String fname1=ap.getSubmittedFileName();
            int path_idx=fname1.lastIndexOf(".");
            String fname2=fname1.substring(path_idx,fname1.length());
            goodsImgs = "E:\\myeclipse\\demoupload\\"+item+fname2;
            p.write("E:\\myeclipse\\demoupload\\"+item+fname2);
            out.write("文件上传成功");
        }else{
            out.write("请选择图片文件");  
        }
        
        goods.setGoodsName(goodsName);
        goods.setGoodsClass(goodsClass);
        goods.setGoodsDesc(goodsDesc);
        goods.setGoodsImgs(goodsImgs);
        goods.setGoodsPrice(goodsPrice);
        goods.setGoodsPubdate(goodsPubdate);
        goods.setGoodsSellerId(GoodsSellerId);
        goods.setGoodsTotal(goodsTotal);
        System.out.println(goodsTotal);
        System.out.println(goods.toString());
        try {
			dao.insert(goods);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        response.sendRedirect("SellGoodsServlet");
    }
	}


