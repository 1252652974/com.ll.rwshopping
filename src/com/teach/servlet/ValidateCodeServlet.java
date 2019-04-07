package com.teach.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ValidateCodeServlet
 */
@WebServlet("/ValidateCodeServlet")
public class ValidateCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int width = 150;
		int height = 50;
		int x=25;
		int y=25;
		char[] valiateCode = {'a','b','c','d'};

		BufferedImage buffIMG = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		String word = "qwertyuiopasdfghjklzxcvbnm";
		
		Graphics2D g = (Graphics2D)buffIMG.createGraphics();
		g.setColor(new Color(102, 255, 255));
		g.setFont(new Font("宋体",Font.BOLD,25));
		
		Random r = new Random();
		
		for(int i=0;i<4;i++) {
			int index = r.nextInt(word.length());
			char A = word.charAt(index);
			valiateCode[i] = A;
			int rot = r.nextInt(40)-20;
			double rott = rot*Math.PI/180;
			g.rotate(rott);
			g.drawString(A+"", x, y);
			x+=20;
			g.rotate(-rott);
		}
		int x1,y1,x2,y2;
		for(int k=0;k<6;k++) {
			x1 = r.nextInt(width);
			x2 = r.nextInt(width);
			y1 = r.nextInt(height);
			y2 = r.nextInt(height);
			g.drawLine(x1, y1, x2, y2);
		}
		ImageIO.write(buffIMG, "jpg", response.getOutputStream());
		String string = String.copyValueOf(valiateCode);
		request.getSession().setAttribute("SESSION_VALIDATECODE", string);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
