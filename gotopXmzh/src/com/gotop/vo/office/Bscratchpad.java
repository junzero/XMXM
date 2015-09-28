package com.gotop.vo.office;

import java.util.Date;



/**
 * Bscratchpad entity. @author MyEclipse Persistence Tools
 */

public class Bscratchpad implements java.io.Serializable {

	// Fields

	private Integer id;
	private String content;
	private Integer creater;
	private String datastate;
	private Date datetime;
	private String title;
	private String orgid;
	// Constructors

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	/** default constructor */
	public Bscratchpad() {
	}

	/** full constructor */
	public Bscratchpad(Integer id, String content, Integer creater,
			String datastate,Date datetime) {
		this.id = id;
		this.content = content;
		this.creater = creater;
		this.datastate = datastate;
		this.datetime=datetime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCreater() {
		return this.creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	public String getDatastate() {
		return this.datastate;
	}

	public void setDatastate(String datastate) {
		this.datastate = datastate;
	}

}