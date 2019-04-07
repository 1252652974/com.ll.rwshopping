package com.teach.service;

import java.sql.SQLException;
import java.util.List;

import com.teach.dao.UserInfoDAO;
import com.teach.entity.PageBean;
import com.teach.entity.UserInfo;

public class AdminUsersService {
	public  PageBean<UserInfo> findAllUserWithPage(int pageNum,int pageSize) throws SQLException {
		UserInfoDAO dao = new UserInfoDAO();
		int len = 0;
		try {
			len = (int) dao.countAll();
			List<UserInfo> allUser = dao.selectAll(0, len);
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
