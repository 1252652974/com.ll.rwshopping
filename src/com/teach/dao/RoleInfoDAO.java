package com.teach.dao;

import com.teach.commons.DBUtil;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.teach.entity.RoleInfo;

/**
 * 
 * @author 刘亮
 *
 */
public class RoleInfoDAO implements DAOSupport<RoleInfo> {

	@Override
	public void insert(RoleInfo model) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into role_info(role_id,role_name) VALUES(?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, model.getRoleId());
			pstmt.setString(2, model.getRoleName());
			//如果是日期类型
//			java.util.Date d = new java.util.Date();
//			pstmt.setDate(2, new java.sql.Date(d.getTime()));//设置日期 yyyy-MM-dd
//			pstmt.setTimestamp(3, new java.sql.Timestamp(d.getTime()));//设置时间戳,yyyy-MM-dd HH:mm:ss
			pstmt.executeUpdate();
			
		}finally {
			DBUtil.close(conn, pstmt);
		}
	}

	@Override
	public void insertSelective(RoleInfo model) throws SQLException {
		DBUtil.insertSelective(model);
	}

	@Override
	public void update(RoleInfo model) throws SQLException {
		DBUtil.update(model);
	}

	@Override
	public void updateSelective(RoleInfo model) throws SQLException {
		DBUtil.updateSelective(model);
	}

	@Override
	public void deleteById(Serializable id) throws SQLException {
		DBUtil.delete(id, RoleInfo.class);
		
	}

	@Override
	public RoleInfo selectById(Serializable id) throws SQLException {
		return DBUtil.get("select * from role_info where role_id=?", RoleInfo.class, id );
	}
	

	public RoleInfo selectByUserId(Serializable id) throws SQLException {
		return DBUtil.get("select * from role_info where role_id=?", RoleInfo.class, id );
	}
	@Override
	public List<RoleInfo> selectAll(int index, int len) throws SQLException {
		return DBUtil.list("select * from role_info limit ?,?", RoleInfo.class, index,len);
	}

	@Override
	public long countAll() throws SQLException {
		return DBUtil.getLong("select count(*) from role_info");
	}


}