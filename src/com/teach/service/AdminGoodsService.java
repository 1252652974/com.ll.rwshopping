package com.teach.service;

import java.sql.SQLException;
import java.util.List;

import com.teach.dao.GoodsInfoDAO;
import com.teach.entity.GoodsInfo;
import com.teach.entity.PageBean;

public class AdminGoodsService {
	public  PageBean<GoodsInfo> findAllUserWithPage(int pageNum,int pageSize) throws SQLException {
		GoodsInfoDAO dao = new GoodsInfoDAO();
		int len = 0;
		try {
			len = (int) dao.countAll();
			List<GoodsInfo> allUser = dao.selectAll(0, len);
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

