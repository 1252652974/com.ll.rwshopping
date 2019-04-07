package com.teach.entity;

import com.teach.annotations.Id;

public class RoleInfo {

	@Id
	private Integer roleId;
	private String roleName;
	
	
	//必要
	public RoleInfo() {
		super();
	}
	
	

	public RoleInfo(Integer roleId) {
		super();
		this.roleId = roleId;
	}



	public RoleInfo(Integer roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}



	@Override
	public String toString() {
		return "RoleInfo [roleId=" + roleId + ", roleName=" + roleName + "]";
	}
	
	
}
