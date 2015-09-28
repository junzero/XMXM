package com.gotop.vo.system;

import java.util.Date;

/**
 * Torganization entity. @author MyEclipse Persistence Tools
 */

public class Torganization implements java.io.Serializable {

	// Fields

	private String orgid;
	private String orgnamech;
	private String orgname;
	private String parentorgid;
	private String orglevel;
	private Date createdate;
	private String orgstate;
	private String datastate;

	// Constructors

	/** default constructor */
	public Torganization() {
	}

	/** minimal constructor */
	public Torganization(String orgid) {
		this.orgid = orgid;
	}

	/** full constructor */
	public Torganization(String orgid, String orgnamech, String orgname,
			String parentorgid, String orglevel, Date createdate,
			String orgstate, String datastate) {
		this.orgid = orgid;
		this.orgnamech = orgnamech;
		this.orgname = orgname;
		this.parentorgid = parentorgid;
		this.orglevel = orglevel;
		this.createdate = createdate;
		this.orgstate = orgstate;
		this.datastate = datastate;
	}

	// Property accessors

	public String getOrgid() {
		return this.orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getOrgnamech() {
		return this.orgnamech;
	}

	public void setOrgnamech(String orgnamech) {
		this.orgnamech = orgnamech;
	}

	public String getOrgname() {
		return this.orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getParentorgid() {
		return this.parentorgid;
	}

	public void setParentorgid(String parentorgid) {
		this.parentorgid = parentorgid;
	}

	public String getOrglevel() {
		return this.orglevel;
	}

	public void setOrglevel(String orglevel) {
		this.orglevel = orglevel;
	}

	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getOrgstate() {
		return this.orgstate;
	}

	public void setOrgstate(String orgstate) {
		this.orgstate = orgstate;
	}

	public String getDatastate() {
		return this.datastate;
	}

	public void setDatastate(String datastate) {
		this.datastate = datastate;
	}

}