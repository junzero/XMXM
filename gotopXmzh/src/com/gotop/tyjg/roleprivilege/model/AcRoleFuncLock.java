package com.gotop.tyjg.roleprivilege.model;

public class AcRoleFuncLock {
    private String roleId;
    private String appId;
    private String isLock;
    private String funcCode;
    private String funcGroupId;
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getIsLock() {
		return isLock;
	}
	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}
	public String getFuncCode() {
		return funcCode;
	}
	public void setFuncCode(String funcCode) {
		this.funcCode = funcCode;
	}
	public String getFuncGroupId() {
		return funcGroupId;
	}
	public void setFuncGroupId(String funcGroupId) {
		this.funcGroupId = funcGroupId;
	}
}
