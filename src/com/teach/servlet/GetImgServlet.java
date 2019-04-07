package com.teach.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetImgServlet
 */
@WebServlet("/GetImgServlet")
public class GetImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetImgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = request.getParameter("url");
        String path = url.substring(0,url.lastIndexOf("/"));
        String realFileName = url.substring(url.lastIndexOf("/")+1,url.length());
        String fileName = request.getParameter("fileName");
        String suffix = request.getParameter("suffix");
//      path = ServletActionContext.getServletContext().getRealPath(path);

       try {
           fileName = java.net.URLEncoder.encode(fileName+"."+suffix, "utf-8"); //ie 中文不兼容问题
       } catch (UnsupportedEncodingException e1) {
           e1.printStackTrace();
       }
       response.setHeader("Content-Disposition", "attachment;filename="
               + fileName);

       OutputStream os = null;
       FileInputStream fis = null;
       byte[] buffer = new byte[1024];
       path = path+"\\"+realFileName+"."+suffix;
       int len = 0;
       try {

           fis = new FileInputStream(new File(path));
           os = response.getOutputStream();
           while ((len = fis.read(buffer)) > 0) {
               os.write(buffer, 0, len);
           }

       } catch (Exception e) {
           e.printStackTrace();

       } finally {
           try {
               os.close();
               fis.close();
           } catch (IOException e) {
               e.printStackTrace();
           }

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
