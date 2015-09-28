package com.gotop.vo.office;

import java.util.Date;

/**
 * Bagenda entity. @author MyEclipse Persistence Tools
 */

public class Bagenda implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String ontent;
	private Integer creater;
	private Date agendadate;
	private Short agendatype;
	private Short remid;
	private Date dateremid;
	private String tremidcontent;
	private String datastate;
	private String orgid;

	// Constructors

	/** default constructor */
	public Bagenda() {
	}

	/** minimal constructor */
	public Bagenda(Integer id, String title, String ontent,
			Integer creater, Short agendatype, String datastate) {
		this.id = id;
		this.title = title;
		this.ontent = ontent;
		this.creater = creater;
		this.agendatype = agendatype;
		this.datastate = datastate;
	}

	/** full constructor */
	public Bagenda(Integer id, String title, String ontent,
			Integer creater, Date agendadate, Short agendatype,
			Short remid, Date dateremid, String tremidcontent,
			String datastate, String orgid) {
		this.id = id;
		this.title = title;
		this.ontent = ontent;
		this.creater = creater;
		this.agendadate = agendadate;
		this.agendatype = agendatype;
		this.remid = remid;
		this.dateremid = dateremid;
		this.tremidcontent = tremidcontent;
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

	public Integer getCreater() {
		return this.creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	public Date getAgendadate() {
		return this.agendadate;
	}

	public void setAgendadate(Date agendadate) {
		this.agendadate = agendadate;
	}

	public Short getAgendatype() {
		return this.agendatype;
	}

	public void setAgendatype(Short agendatype) {
		this.agendatype = agendatype;
	}

	public Short getRemid() {
		return this.remid;
	}

	public void setRemid(Short remid) {
		this.remid = remid;
	}

	public Date getDateremid() {
		return this.dateremid;
	}

	public void setDateremid(Date dateremid) {
		this.dateremid = dateremid;
	}

	public String getTremidcontent() {
		return this.tremidcontent;
	}

	public void setTremidcontent(String tremidcontent) {
		this.tremidcontent = tremidcontent;
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