package com.gotop.tyjg.common.model;

import java.io.Serializable;

/**
 * *******************************
 * <p>Title: </p>
 * 
 * <p> Description: </p>
 * 
 * <p>Copyright: 2012</p>
 * 
 * <p>Company: GOTOP</p>
 * 
 * @author xuxh
 * 
 * @date Apr 13, 2012
 * 
 * @version 1.0
 * 
 * HISTORY Apr 13, 2012 xuxh 创建文件
 * 
 * *******************************
 */
public class Role implements Serializable {

	private static final long serialVersionUID = 3354106160005233644L;

	private String roleid;
	
	private String  rolename;
	
	private String  roletype;
	
	private String  roledesc;
	
	private String     appid;
	
	private String roletypefield;
	
	private String auto;

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRoletype() {
		return roletype;
	}

	public void setRoletype(String roletype) {
		this.roletype = roletype;
	}

	public String getRoledesc() {
		return roledesc;
	}

	public void setRoledesc(String roledesc) {
		this.roledesc = roledesc;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getRoletypefield() {
		return roletypefield;
	}

	public void setRoletypefield(String roletypefield) {
		this.roletypefield = roletypefield;
	}

	public String getAuto() {
		return auto;
	}

	public void setAuto(String auto) {
		this.auto = auto;
	}
	
}
