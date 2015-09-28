package com.gotop.vo.system;

/**
 * Tgroupinfo entity. @author MyEclipse Persistence Tools
 */

public class Tgroupinfo implements java.io.Serializable {

	// Fields
	
	private int id;			//ID
	private int groupid;	//分组ID
	private int userid;		//用户ID
	private String state;	//状态

	// Constructors

	/** default constructor */
	public Tgroupinfo() {
	}

	/** minimal constructor */
	public Tgroupinfo(int id) {
		this.id = id;
	}

	/** full constructor */
	public Tgroupinfo(int id, int groupid, int userid,
			String state) {
		this.id = id;
		this.groupid = groupid;
		this.userid = userid;
		this.state = state;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGroupid() {
		return this.groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}