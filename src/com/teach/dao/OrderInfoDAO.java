package com.teach.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.teach.commons.DBUtil;
import com.teach.entity.OrderInfo;

public class OrderInfoDAO {
	public void update(OrderInfo model) throws SQLException {
		DBUtil.update(model);
	}
	
	public void insert(OrderInfo model) throws SQLException {
		DBUtil.insert(model);
	}
	
	public void deleteById(Serializable id) throws SQLException {
		DBUtil.delete(id, OrderInfo.class);
	}
	
	public List<OrderInfo> selectAll(int index, int len) throws SQLException {
		return DBUtil.list("select * from order_info limit ?,?", OrderInfo.class, index,len);
	}
	
	public long countAll() throws SQLException {
		return DBUtil.getLong("select count(*) from order_info");
	}
	
	public OrderInfo selectById(Serializable id) throws SQLException {
		return DBUtil.get("select * from order_info where id=?", OrderInfo.class, id );
	}
	
	public List<OrderInfo> selectByUserId(int userId) throws SQLException {
		return DBUtil.list("select * from order_info where user_id=?", OrderInfo.class, userId);
	}
	
	public List<OrderInfo> selectByGoodsId(int goodsId) throws SQLException {
		return DBUtil.list("select * from order_info where GOODS_ID=?", OrderInfo.class, goodsId);
	}
}
