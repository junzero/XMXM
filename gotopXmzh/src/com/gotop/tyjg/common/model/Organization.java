package com.gotop.tyjg.common.model;

import java.io.Serializable;

/**
 * *******************************
 * <p>
 * Title:机构信息
 * </p>
 * 
 * <p>
 * Description:机构信息
 * </p>
 * 
 * <p>
 * Copyright: 2012
 * </p>
 * 
 * <p>
 * Company: GOTOP
 * </p>
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
public class Organization implements Serializable {

	private static final long serialVersionUID = -974670469944075370L;
	
	private String orgid;
	
	private String orgname;
	
	private String orgcode;
	
	private String orgdegree;
	
	private String orgtype;
	
	private String ysjgbh;
	
	private String orgseq;
	
	private String groupid;
	
	private String groupmember;
	
	private String id;
	
	private String mappid;
	
	private String sourceorgtype;

	public String getSourceorgtype() {
		return sourceorgtype;
	}

	public void setSourceorgtype(String sourceorgtype) {
		this.sourceorgtype = sourceorgtype;
	}

	public String getMappid() {
		return mappid;
	}

	public void setMappid(String mappid) {
		this.mappid = mappid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getGroupmember() {
		return groupmember;
	}

	public void setGroupmember(String groupmember) {
		this.groupmember = groupmember;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public String getOrgdegree() {
		return orgdegree;
	}

	public void setOrgdegree(String orgdegree) {
		this.orgdegree = orgdegree;
	}

	public String getOrgtype() {
		return orgtype;
	}

	public void setOrgtype(String orgtype) {
		this.orgtype = orgtype;
	}

	public String getYsjgbh() {
		return ysjgbh;
	}

	public void setYsjgbh(String ysjgbh) {
		this.ysjgbh = ysjgbh;
	}

	public String getOrgseq() {
		return orgseq;
	}

	public void setOrgseq(String orgseq) {
		this.orgseq = orgseq;
	}
}
