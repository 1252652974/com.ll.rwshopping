package com.teach.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.teach.commons.DBUtil;
import com.teach.entity.GoodsInfo;
import com.teach.entity.UserInfo;


public class GoodsInfoDAO extends BaseDAO<GoodsInfo> {
	public void insert(GoodsInfo model) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into goods_info(goods_NAME,goods_price,goods_imgs,GOODS_PUBDATE,GOODS_SELLER_ID,GOODS_DESC,GOODS_CLASS,goods_total) VALUES(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, model.getGoodsName());
			pstmt.setString(2, model.getGoodsPrice());
			pstmt.setString(3, model.getGoodsImgs());
			pstmt.setString(4, model.getGoodsPubdate());
			pstmt.setInt(5, model.getGoodsSellerId());
			pstmt.setString(6, model.getGoodsDesc());
			pstmt.setString(7, model.getGoodsClass());
			pstmt.setInt(8, model.getGoodsTotal());
			//如果是日期类型
//			java.util.Date d = new java.util.Date();
//			pstmt.setDate(2, new java.sql.Date(d.getTime()));//设置日期 yyyy-MM-dd
//			pstmt.setTimestamp(3, new java.sql.Timestamp(d.getTime()));//设置时间戳,yyyy-MM-dd HH:mm:ss
			pstmt.executeUpdate();
			
		}finally {
			DBUtil.close(conn, pstmt);
		}
	}
	
	public void update(GoodsInfo model) throws SQLException {
		DBUtil.update(model);
	}
	
	public void deleteById(Serializable id) throws SQLException {
		DBUtil.delete(id, GoodsInfo.class);
		
	}
	
	public GoodsInfo selectById(Serializable id) throws SQLException {
		return DBUtil.get("select * from goods_info where goods_id=?", GoodsInfo.class, id );
	}
	public List<GoodsInfo> searchGoods(String value) throws SQLException {
		return DBUtil.list("select * from goods_info where goods_class Like concat('%',?,'%')", GoodsInfo.class, value );
	}
	public List<GoodsInfo> selectByclass(String goodsClass) throws SQLException {
		return DBUtil.list("select * from Goods_info where goods_class=?", GoodsInfo.class, goodsClass);
	}
	
	public List<GoodsInfo> selectAll(int index, int len) throws SQLException {
		return DBUtil.list("select * from Goods_info limit ?,?", GoodsInfo.class, index,len);
	}
	
	public List<GoodsInfo> selectBySellerId(int goodsId) throws SQLException {
		return DBUtil.list("select * from Goods_info where GOODS_SELLER_ID=?", GoodsInfo.class, goodsId);
	}
	public long countAll() throws SQLException {
		return DBUtil.getLong("select count(*) from goods_info");
	}
	

}
