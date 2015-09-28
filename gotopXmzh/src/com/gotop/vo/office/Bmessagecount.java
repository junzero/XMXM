package com.gotop.vo.office;

import java.util.Date;

/**
 * Bmessagecount entity. @author MyEclipse Persistence Tools
 */

public class Bmessagecount implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer mesid;
	private String content;
	private Date datetime;
	private String datastate;

	// Constructors

	/** default constructor */
	public Bmessagecount() {
	}

	/** minimal constructor */
	public Bmessagecount(Integer id, String datastate) {
		this.id = id;
		this.datastate = datastate;
	}

	/** full constructor */
	public Bmessagecount(Integer id, Integer mesid, String content,
			Date datetime, String datastate) {
		this.id = id;
		this.mesid = mesid;
		this.content = content;
		this.datetime = datetime;
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

	public String getDatastate() {
		return this.datastate;
	}

	public void setDatastate(String datastate) {
		this.datastate = datastate;
	}

}