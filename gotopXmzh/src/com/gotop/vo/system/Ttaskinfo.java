package com.gotop.vo.system;

import java.util.Date;

/**
 * Ttaskinfo entity. @author MyEclipse Persistence Tools
 */

public class Ttaskinfo implements java.io.Serializable {

	// Fields

	private int id;
	private String mkdm;
	private int dataid;
	private String tasktitle;
	private String tasklink;
	private String state;
	private int userid;
	private Date remiddate;

	// Constructors

	/** default constructor */
	public Ttaskinfo() {
	}

	/** minimal constructor */
	public Ttaskinfo(int id) {
		this.id = id;
	}

	/** full constructor */
	public Ttaskinfo(int id, String mkdm, int dataid,
			String tasktitle, String tasklink, String state,int userid,Date remiddate) {
		this.id = id;
		this.mkdm = mkdm;
		this.dataid = dataid;
		this.tasktitle = tasktitle;
		this.tasklink = tasklink;
		this.state = state;
		this.userid = userid;
		this.remiddate = remiddate;
	}

	// Property accessors

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Date getRemiddate() {
		return remiddate;
	}

	public void setRemiddate(Date remiddate) {
		this.remiddate = remiddate;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMkdm() {
		return this.mkdm;
	}

	public void setMkdm(String mkdm) {
		this.mkdm = mkdm;
	}

	public int getDataid() {
		return this.dataid;
	}

	public void setDataid(int dataid) {
		this.dataid = dataid;
	}

	public String getTasktitle() {
		return this.tasktitle;
	}

	public void setTasktitle(String tasktitle) {
		this.tasktitle = tasktitle;
	}

	public String getTasklink() {
		return this.tasklink;
	}

	public void setTasklink(String tasklink) {
		this.tasklink = tasklink;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}