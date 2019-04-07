package com.teach.service.impl;

import java.util.Date;
import java.util.List;

import com.teach.commons.MD5;
import com.teach.dao.RoleInfoDAO;
import com.teach.dao.UserInfoDAO;
import com.teach.entity.UserInfo;
import com.teach.service.UserInfoService;
import com.teach.vo.LoginUserVO;

public class UserInfoServiceImpl implements UserInfoService {

	private UserInfoDAO dao = new  UserInfoDAO();
	private RoleInfoDAO dao1 = new RoleInfoDAO();
	@Override
	public List<UserInfo> listTop10()throws Exception {
		return dao.selectAll(0, 10);
	}
	

	public LoginUserVO login(String userName, String userPwd) throws Exception {
		UserInfo userInfo = null;
		try{
			userInfo = dao.selectById(userName);
		}catch(Exception ex){
			throw new Exception("用户不存在",ex);
		}
		
		if(!userInfo.getUserPwd().equals(MD5.encode(userPwd))){
			throw new Exception("密码错误");
		}
	
		
		dao.update(userInfo);
		
		LoginUserVO user = new LoginUserVO();
		user.setUserName(userInfo.getUserName());
		user.setRoleId(userInfo.getRoleId());
		user.setRoleName(dao1.selectById(userInfo.getRoleId()).getRoleName());
		return user;
	}


}
