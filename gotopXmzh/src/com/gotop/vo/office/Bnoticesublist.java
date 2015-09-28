package com.gotop.vo.office;


/**
 * Bnoticesublist entity. @author MyEclipse Persistence Tools
 */

public class Bnoticesublist implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer nid;
	private Integer userid;

	// Constructors

	/** default constructor */
	public Bnoticesublist() {
	}

	/** full constructor */
	public Bnoticesublist(Integer id, Integer nid, Integer userid) {
		this.id = id;
		this.nid = nid;
		this.userid = userid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNid() {
		return this.nid;
	}

	public void setNid(Integer nid) {
		this.nid = nid;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

}