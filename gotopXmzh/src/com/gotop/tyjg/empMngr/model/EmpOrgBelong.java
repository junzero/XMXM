package com.gotop.tyjg.empMngr.model;

import java.io.Serializable;

public class EmpOrgBelong implements Serializable{

	private static final long serialVersionUID = -6090026836907586277L;
	private String empid;
	private String orgid;
	private String orgName;
	private String isMain;
	private String orgType;
	public EmpOrgBelong() {
		super();
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getIsMain() {
		return isMain;
	}
	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
}
