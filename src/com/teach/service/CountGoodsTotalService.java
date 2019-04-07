package com.teach.service;

import java.sql.SQLException;
import java.util.List;

import com.teach.dao.GoodsInfoDAO;
import com.teach.dao.OrderInfoDAO;
import com.teach.entity.OrderInfo;

public class CountGoodsTotalService {
	public int countGoodsTotal(int goodsId) throws SQLException {
		int soldNum = 0;
		/*OrderInfo order = new OrderInfo();*/
		OrderInfoDAO dao  = new OrderInfoDAO();
		GoodsInfoDAO goodsDao = new GoodsInfoDAO();
		int goodsTotal = goodsDao.selectById(goodsId).getGoodsTotal();
		List<OrderInfo> orders = dao.selectByGoodsId(goodsId);
		for(OrderInfo order:orders) {
			soldNum += order.getGoodsNum();
		}
		return goodsTotal-soldNum;
	}
}
