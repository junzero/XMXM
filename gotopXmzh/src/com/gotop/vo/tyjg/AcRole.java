package com.gotop.vo.tyjg;

import java.io.Serializable;

public class AcRole implements Serializable{
	
	private static final long serialVersionUID = 183738495849L;
	private String roleId;
	private String roleName;
	private String roleType;
	private String roleDesc;
	private String appid;
	private String auto;
	private String roleTypeField;
	
	public AcRole() {
		super();
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getAuto() {
		return auto;
	}
	public void setAuto(String auto) {
		this.auto = auto;
	}
	public String getRoleTypeField() {
		return roleTypeField;
	}
	public void setRoleTypeField(String roleTypeField) {
		this.roleTypeField = roleTypeField;
	}
	
}
