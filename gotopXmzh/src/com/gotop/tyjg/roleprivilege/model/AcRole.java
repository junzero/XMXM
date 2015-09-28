package com.gotop.tyjg.roleprivilege.model;
/**
 * *******************************
 * <p>Title: 角色信息模型</p>
 * 
 * <p> Description:   角色信息模型</p>
 * 
 * <p>Copyright: 2012</p>
 * 
 * <p>Company: GOTOP</p>
 * 
 * @author liushi
 * 
 * @date 2012-3-31
 * 
 * @version 1.0
 * 
 * HISTORY 2012-3-31 liushi 创建文件
 * 
 * *******************************
 */
public class AcRole {
	private String roleId;
	private String roleName;
	private String roleType;
	private String roleDesc;
	private String appId;
	private String auto;
	private String roleTypeField;
	private String function;
	private String orgId;
	private String orgName;
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
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
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
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
