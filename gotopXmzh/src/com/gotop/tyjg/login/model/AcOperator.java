package com.gotop.tyjg.login.model;

import java.io.Serializable;

/**
 * *******************************
 * <p>Title: 操作员信息模型</p>
 * 
 * <p> Description:   操作员信息模型</p>
 * 
 * <p>Copyright: 2012</p>
 * 
 * <p>Company: GOTOP</p>
 * 
 * @author xuxh
 * 
 * @date 2012-3-21
 * 
 * @version 1.0
 * 
 * HISTORY 2012-3-21 xuxh 创建文件
 * 
 * *******************************
 */
public class AcOperator implements Serializable {
	
	private static final long serialVersionUID = 6853207265033862390L;
	
	private long operatorId;
	
	private String userId;
	
	private String password;
	
	private String invalDate;
	
	private String operatorName;
	
	private String authMode;
	
	private String status;
	
	private String unlockTime;
	
	private String menuType;
	
	private String lastLogin;
	
	private long errCount;
	
	private String startDate;
	
	private String endDate;
	
	private String valiDate;
	
	private String macCode;
	
	private String ipAddress;
	
	private String email;
	
	private long empid;
	
	private String headPicicon;
	
	private String layoutMode;
	
	private long orgid;

	public long getOrgid() {
		return orgid;
	}

	public void setOrgid(long orgid) {
		this.orgid = orgid;
	}

	public long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(long operatorId) {
		this.operatorId = operatorId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getInvalDate() {
		return invalDate;
	}

	public void setInvalDate(String invalDate) {
		this.invalDate = invalDate;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getAuthMode() {
		return authMode;
	}

	public void setAuthMode(String authMode) {
		this.authMode = authMode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUnlockTime() {
		return unlockTime;
	}

	public void setUnlockTime(String unlockTime) {
		this.unlockTime = unlockTime;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public long getErrCount() {
		return errCount;
	}

	public void setErrCount(long errCount) {
		this.errCount = errCount;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getValiDate() {
		return valiDate;
	}

	public void setValiDate(String valiDate) {
		this.valiDate = valiDate;
	}

	public String getMacCode() {
		return macCode;
	}

	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getEmpid() {
		return empid;
	}

	public void setEmpid(long empid) {
		this.empid = empid;
	}

	public String getHeadPicicon() {
		return headPicicon;
	}

	public void setHeadPicicon(String headPicicon) {
		this.headPicicon = headPicicon;
	}

	public String getLayoutMode() {
		return layoutMode;
	}

	public void setLayoutMode(String layoutMode) {
		this.layoutMode = layoutMode;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}
