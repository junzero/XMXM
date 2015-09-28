package com.gotop.vo.office;

import java.util.Date;

/**
 * Boutregisterinfo entity. @author MyEclipse Persistence Tools
 */

public class Boutregisterinfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String useritems;
	private String  auditid;
	private Date dateoutbegin;
	private Date dateoutend;
	private String address;
	private String content;
	private Integer creater;
	private String datastate;
	private String usernameitems;
	private String orgid;
	private String auditiditems;
	private String title;
	// Constructors

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuditiditems() {
		return auditiditems;
	}

	public void setAuditiditems(String auditiditems) {
		this.auditiditems = auditiditems;
	}

	/** default constructor */
	public Boutregisterinfo() {
	}

	/** minimal constructor */
	public Boutregisterinfo(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Boutregisterinfo(Integer id, String useritems,
			String auditid, Date dateoutbegin, Date dateoutend,
			String address, String content, Integer creater,
			String datastate, String usernameitems, String orgid) {
		this.id = id;
		this.useritems = useritems;
		this.auditid = auditid;
		this.dateoutbegin = dateoutbegin;
		this.dateoutend = dateoutend;
		this.address = address;
		this.content = content;
		this.creater = creater;
		this.datastate = datastate;
		this.usernameitems = usernameitems;
		this.orgid = orgid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUseritems() {
		return this.useritems;
	}

	public void setUseritems(String useritems) {
		this.useritems = useritems;
	}

	public String getAuditid() {
		return this.auditid;
	}

	public void setAuditid(String auditid) {
		this.auditid = auditid;
	}

	public Date getDateoutbegin() {
		return this.dateoutbegin;
	}

	public void setDateoutbegin(Date dateoutbegin) {
		this.dateoutbegin = dateoutbegin;
	}

	public Date getDateoutend() {
		return this.dateoutend;
	}

	public void setDateoutend(Date dateoutend) {
		this.dateoutend = dateoutend;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getUsernameitems() {
		return this.usernameitems;
	}

	public void setUsernameitems(String usernameitems) {
		this.usernameitems = usernameitems;
	}

	public String getOrgid() {
		return this.orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

}