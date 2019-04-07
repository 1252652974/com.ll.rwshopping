package com.teach.dao;

import static com.teach.commons.DBUtil.close;
import static com.teach.commons.DBUtil.getConnection;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.teach.commons.DBUtil;
import com.teach.entity.UserInfo;

public class UserInfoDAO extends BaseDAO<UserInfo> {
	@Override
	public void insert(UserInfo model) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into user_info(USER_ID,USER_NAME,USER_PWD,USER_TEl) VALUES(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, model.getUserId());
			pstmt.setString(2, model.getUserName());
			pstmt.setString(3, model.getUserPwd());
			pstmt.setString(4, model.getUserTel());
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
	public void insertSelective(UserInfo model) throws SQLException {
		DBUtil.insertSelective(model);
	}

	@Override
	public void update(UserInfo model) throws SQLException {
		DBUtil.update(model);
	}

	@Override
	public void updateSelective(UserInfo model) throws SQLException {
		DBUtil.updateSelective(model);
	}

	@Override
	public void deleteById(Serializable id) throws SQLException {
		DBUtil.delete(id, UserInfo.class);
		
	}

	@Override
	public UserInfo selectById(Serializable id) throws SQLException {
		return DBUtil.get("select * from user_info where user_name=?", UserInfo.class, id );
	}
    
	public UserInfo selectByUserId(Serializable id) throws SQLException {
		return DBUtil.get("select * from user_info where user_id=?", UserInfo.class, id );
	}
	
	@Override
	public List<UserInfo> selectAll(int index, int len) throws SQLException {
		return DBUtil.list("select * from user_info limit ?,?", UserInfo.class, index,len);
	}

	@Override
	public long countAll() throws SQLException {
		return DBUtil.getLong("select count(*) from user_info");
	}
	
	public int login(String username,String userPwd) {
		int userGetId = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "SELECT * FROM user_info where user_name=? and user_Pwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, userPwd);
			rs = pstmt.executeQuery();
			if(rs.next()){
				userGetId = rs.getInt("user_id");
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		finally {
			close(conn, pstmt, rs);
		}
		return userGetId;
	}

}
