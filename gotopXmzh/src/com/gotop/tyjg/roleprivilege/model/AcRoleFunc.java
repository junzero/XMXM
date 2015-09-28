package com.gotop.tyjg.roleprivilege.model;

public class AcRoleFunc {
	private String roleId;
	private String funcCode;
	private String appId;
	private String funcGroupId;
	private String isLock;
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getFuncCode() {
		return funcCode;
	}
	public void setFuncCode(String funcCode) {
		this.funcCode = funcCode;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getFuncGroupId() {
		return funcGroupId;
	}
	public void setFuncGroupId(String funcGroupId) {
		this.funcGroupId = funcGroupId;
	}
	public String getIsLock() {
		return isLock;
	}
	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}

}
