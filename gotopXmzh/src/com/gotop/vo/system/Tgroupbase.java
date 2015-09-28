package com.gotop.vo.system;

/**
 * Tgroupbase entity. @author MyEclipse Persistence Tools
 */

public class Tgroupbase implements java.io.Serializable {

	// Fields

	private int id;				//ID
	private String groupname;	//分组名称
	private String orgid;		//所属机构编号
	private String datastae;	//数据状态

	// Constructors

	/** default constructor */
	public Tgroupbase() {
	}

	/** minimal constructor */
	public Tgroupbase(int id) {
		this.id = id;
	}

	/** full constructor */
	public Tgroupbase(int id, String groupname, String orgid,
			String datastae) {
		this.id = id;
		this.groupname = groupname;
		this.orgid = orgid;
		this.datastae = datastae;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupname() {
		return this.groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getOrgid() {
		return this.orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getDatastae() {
		return this.datastae;
	}

	public void setDatastae(String datastae) {
		this.datastae = datastae;
	}

}