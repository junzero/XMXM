package com.gotop.tyjg.common.model;

import java.io.Serializable;

/**
 * *******************************
 * <p>Title:人员信息 </p>
 * 
 * <p> Description: 人员信息</p>
 * 
 * <p>Copyright: 2012</p>
 * 
 * <p>Company: GOTOP</p>
 * 
 * @author xuxh
 * 
 * @date Apr 11, 2012
 * 
 * @version 1.0
 * 
 * HISTORY Apr 11, 2012 xuxh 创建文件
 * 
 * *******************************
 */
public class Employee implements Serializable {
	private static final long serialVersionUID = 5968266582485987174L;
	
	private String empid;
	
	private String empcode;
	
	private String empname;
	
	private String gender;
	
	private String cardtype;
	
	private String cardno;
	
	private String orgcode;
	
	private String orgid;
	
	private String ysjgbh;
	
	private String orgname;
	
	
	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getYsjgbh() {
		return ysjgbh;
	}

	public void setYsjgbh(String ysjgbh) {
		this.ysjgbh = ysjgbh;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getEmpcode() {
		return empcode;
	}

	public void setEmpcode(String empcode) {
		this.empcode = empcode;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCardtype() {
		return cardtype;
	}

	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	
}	
