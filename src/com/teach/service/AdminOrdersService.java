package com.teach.service;

import java.sql.SQLException;
import java.util.List;

import com.teach.dao.OrderInfoDAO;
import com.teach.entity.PageBean;
import com.teach.entity.OrderInfo;

public class AdminOrdersService {
	public  PageBean<OrderInfo> findAllUserWithPage(int pageNum,int pageSize) throws SQLException {
		OrderInfoDAO dao = new OrderInfoDAO();
		int len = 0;
		try {
			len = (int) dao.countAll();
			List<OrderInfo> allUser = dao.selectAll(0, len);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PageBean pb = new PageBean(pageNum, pageSize, len);
		int startIndex = pb.getStartIndex();
		
		pb.setList(dao.selectAll(startIndex, 5));
		
		return pb;
	}
}
