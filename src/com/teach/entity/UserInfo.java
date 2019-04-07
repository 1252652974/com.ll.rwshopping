package com.teach.entity;

import static com.teach.commons.DBUtil.close;
import static com.teach.commons.DBUtil.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.teach.annotations.Id;

public class UserInfo {
    @Id
    private int userId;
    private String userName;
    private int roleId;
    private String userPwd;
    private String userTel;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public UserInfo() {
		super();
	}
	public UserInfo(int userId) {
		super();
		this.userId = userId;
	}
	public UserInfo(int userId, String userName) {
		super();
		this.userId = userId;
		this.userName = userName;
	}
	public UserInfo(int userId, String userName, int roleId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.roleId = roleId;
	}
	public UserInfo(int userId, String userName, int roleId, String userPwd) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.roleId = roleId;
		this.userPwd = userPwd;
	}
	public UserInfo(int userId, String userName, String userPwd) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
	}
	public UserInfo(int userId, String userName, int roleId, String userPwd, String userTel) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.roleId = roleId;
		this.userPwd = userPwd;
		this.userTel = userTel;
	}
	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", userName=" + userName + ", roleId=" + roleId + ", userPwd=" + userPwd
				+ ", userTel=" + userTel + "]";
	}
	
    
	
    
}
