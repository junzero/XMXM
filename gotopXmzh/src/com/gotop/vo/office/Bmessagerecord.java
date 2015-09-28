package com.gotop.vo.office;

import java.util.Date;

/**
 * Bmessagerecord entity. @author MyEclipse Persistence Tools
 */

public class Bmessagerecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer mesid;
	private Integer jsuserid;
	private String content;
	private Date datetime;
	private Integer fsuserid;
	private String datastate;

	// Constructors

	/** default constructor */
	public Bmessagerecord() {
	}

	/** minimal constructor */
	public Bmessagerecord(Integer id, String datastate) {
		this.id = id;
		this.datastate = datastate;
	}

	/** full constructor */
	public Bmessagerecord(Integer id, Integer mesid, Integer jsuserid,
			String content, Date datetime, Integer fsuserid,
			String datastate) {
		this.id = id;
		this.mesid = mesid;
		this.jsuserid = jsuserid;
		this.content = content;
		this.datetime = datetime;
		this.fsuserid = fsuserid;
		this.datastate = datastate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMesid() {
		return this.mesid;
	}

	public void setMesid(Integer mesid) {
		this.mesid = mesid;
	}

	public Integer getJsuserid() {
		return this.jsuserid;
	}

	public void setJsuserid(Integer jsuserid) {
		this.jsuserid = jsuserid;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDatetime() {
		return this.datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public Integer getFsuserid() {
		return this.fsuserid;
	}

	public void setFsuserid(Integer fsuserid) {
		this.fsuserid = fsuserid;
	}

	public String getDatastate() {
		return this.datastate;
	}

	public void setDatastate(String datastate) {
		this.datastate = datastate;
	}

}