package com.teach.vo;

public class LoginUserVO {

	private String userName;
	private Integer roleId;
	private String userRealName;
	private String roleName;
	private Boolean userSex;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Boolean getUserSex() {
		return userSex;
	}
	public void setUserSex(Boolean userSex) {
		this.userSex = userSex;
	}
	@Override
	public String toString() {
		return "LoginUserVO [userName=" + userName + ", roleId=" + roleId + ", userRealName=" + userRealName
				+ ", roleName=" + roleName + ", userSex=" + userSex + "]";
	}

	
}
