package com.gotop.tyjg.empMngr.model;

import java.io.Serializable;

/**
 * *******************************
 * <p>Title:人员机构 </p>
 * 
 * <p> Description:人员机构 </p>
 * 
 * <p>Copyright: 2012</p>
 * 
 * <p>Company: GOTOP</p>
 * 
 * @author xuxh
 * 
 * @date Apr 17, 2012
 * 
 * @version 1.0
 * 
 * HISTORY Apr 17, 2012 xuxh 创建文件
 * 
 * *******************************
 */
public class OmEmporg implements Serializable {
	private static final long serialVersionUID = 550451590491398562L;
	
	private String empid;
	
	private String orgid;
	
	private String ismain;
	
	private String orgname;
	
	private String orgtype;

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

	public String getIsmain() {
		return ismain;
	}

	public void setIsmain(String ismain) {
		this.ismain = ismain;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getOrgtype() {
		return orgtype;
	}

	public void setOrgtype(String orgtype) {
		this.orgtype = orgtype;
	}
	
	
}
