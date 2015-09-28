package com.gotop.vo.office;

import java.util.Date;

/**
 * Bmessage entity. @author MyEclipse Persistence Tools
 */

public class Bmessage implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String ontent;
	private String fileurl;
	private Date datetime;
	private Integer creater;
	private String participant;
	private String datastate;
	private String orgid;

	// Constructors

	/** default constructor */
	public Bmessage() {
	}

	/** minimal constructor */
	public Bmessage(Integer id, String title, String ontent, String datastate) {
		this.id = id;
		this.title = title;
		this.ontent = ontent;
		this.datastate = datastate;
	}

	/** full constructor */
	public Bmessage(Integer id, String title, String ontent, String fileurl,
			Date datetime, Integer creater, String participant,
			String datastate, String orgid) {
		this.id = id;
		this.title = title;
		this.ontent = ontent;
		this.fileurl = fileurl;
		this.datetime = datetime;
		this.creater = creater;
		this.participant = participant;
		this.datastate = datastate;
		this.orgid = orgid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOntent() {
		return this.ontent;
	}

	public void setOntent(String ontent) {
		this.ontent = ontent;
	}

	public String getFileurl() {
		return this.fileurl;
	}

	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}

	public Date getDatetime() {
		return this.datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public Integer getCreater() {
		return this.creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	public String getParticipant() {
		return this.participant;
	}

	public void setParticipant(String participant) {
		this.participant = participant;
	}

	public String getDatastate() {
		return this.datastate;
	}

	public void setDatastate(String datastate) {
		this.datastate = datastate;
	}

	public String getOrgid() {
		return this.orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

}