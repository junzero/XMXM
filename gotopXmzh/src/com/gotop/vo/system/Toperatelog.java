package com.gotop.vo.system;

import java.util.Date;

/**
 * Toperatelog entity. @author MyEclipse Persistence Tools
 */

public class Toperatelog implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer updid;
	private Date upddate;
	private Integer userid;
	private String updtype;
	private String updtable;
	private String updfield;
	private String updoldvalue;
	private String updnewvalue;
	private String orgid;
	private String ip;

	// Constructors

	/** default constructor */
	public Toperatelog() {
	}

	/** minimal constructor */
	public Toperatelog(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Toperatelog(Integer id, Integer updid, Date upddate,
			Integer userid, String updtype, String updtable,
			String updfield, String updoldvalue, String updnewvalue,
			String orgid, String ip) {
		this.id = id;
		this.updid = updid;
		this.upddate = upddate;
		this.userid = userid;
		this.updtype = updtype;
		this.updtable = updtable;
		this.updfield = updfield;
		this.updoldvalue = updoldvalue;
		this.updnewvalue = updnewvalue;
		this.orgid = orgid;
		this.ip = ip;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUpdid() {
		return this.updid;
	}

	public void setUpdid(Integer updid) {
		this.updid = updid;
	}

	public Date getUpddate() {
		return this.upddate;
	}

	public void setUpddate(Date upddate) {
		this.upddate = upddate;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUpdtype() {
		return this.updtype;
	}

	public void setUpdtype(String updtype) {
		this.updtype = updtype;
	}

	public String getUpdtable() {
		return this.updtable;
	}

	public void setUpdtable(String updtable) {
		this.updtable = updtable;
	}

	public String getUpdfield() {
		return this.updfield;
	}

	public void setUpdfield(String updfield) {
		this.updfield = updfield;
	}

	public String getUpdoldvalue() {
		return this.updoldvalue;
	}

	public void setUpdoldvalue(String updoldvalue) {
		this.updoldvalue = updoldvalue;
	}

	public String getUpdnewvalue() {
		return this.updnewvalue;
	}

	public void setUpdnewvalue(String updnewvalue) {
		this.updnewvalue = updnewvalue;
	}

	public String getOrgid() {
		return this.orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}