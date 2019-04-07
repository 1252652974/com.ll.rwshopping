package com.teach.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teach.entity.GoodsInfo;

/**
 * Servlet implementation class shoppingCarServlet
 */
@WebServlet("/addShoppingCar")
public class ShoppingCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		List<GoodsInfo> car = (List<GoodsInfo>)session.getAttribute("shoppingCar");
		//若回话中没shoppingCat属性对象，则实例化一个
		if(car == null) {
			car = new ArrayList<GoodsInfo>();
		}
		Iterator iter=car.iterator();
		//获取用户选择的书籍
		String selectGoodsName = request.getParameter("name");
		String selectGoodsPrice = request.getParameter("price");
		String selectGoodsimgs = request.getParameter("imgsrc");
		String id = request.getParameter("goodsId");
		GoodsInfo selectGoods = new GoodsInfo();
		selectGoods.setGoodsName(selectGoodsName);
		selectGoods.setGoodsPrice(selectGoodsPrice);
		selectGoods.setGoodsImgs(selectGoodsimgs);
		selectGoods.setGoodsId(Integer.parseInt(id));
		int status = 0;
		for(GoodsInfo goods:car) {
			if(goods.getGoodsImgs().equals(selectGoodsimgs)) {
				status = 1;
				break;
			}
		}
		if(status != 1) {
			car.add(selectGoods);
		}
		
		session.setAttribute("shoppingCar", car);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		//获取回话对象
		HttpSession session = request.getSession();
		
		//从回话获取购物车的属性对象
		//对象定义为map类型，key为书名，value为购买数量
		Map<String, Integer> car = (Map<String, Integer>) session.getAttribute("shoppingCar");
		//若回话中没shoppingCat属性对象，则实例化一个
		if(car == null) {
			car = new HashMap<String, Integer>();
		}
		//获取用户选择的书籍
		String[] books = request.getParameterValues("books");
		if(books!=null&&books.length>0) {
			for(String bookName:books) {
				//判段此书籍是否已在购物车中
				if(car.get(bookName)!=null) {
					int num = car.get(bookName);
					car.put(bookName, num+1);
				}else {
					car.put(bookName, 1);
				}
			}
		}
		session.setAttribute("shoppingCar", car);
		response.sendRedirect("ShoppingListServlet");
	}

}
