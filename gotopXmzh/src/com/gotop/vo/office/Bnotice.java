package com.gotop.vo.office;

import java.util.Date;

/**
 * Bnotice entity. @author MyEclipse Persistence Tools
 */

public class Bnotice implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String content;
	private Integer creater;
	private Date datetime;
	private String datastate;
	private String orgid;
	private Integer shuserid;
	private Date shdatetime;
	private String back;
	private String fileurl;
	private String filename;
	private String sendrange;
	private Date jzdatetime;
	private String titleold;
	private String contentold;
	private Integer submitid;
	private Date subdate;
	// Constructors

	public String getTitleold() {
		return titleold;
	}

	public void setTitleold(String titleold) {
		this.titleold = titleold;
	}

	public String getContentold() {
		return contentold;
	}

	public void setContentold(String contentold) {
		this.contentold = contentold;
	}

	public Integer getSubmitid() {
		return submitid;
	}

	public void setSubmitid(Integer submitid) {
		this.submitid = submitid;
	}

	public Date getSubdate() {
		return subdate;
	}

	public void setSubdate(Date subdate) {
		this.subdate = subdate;
	}

	public Date getJzdatetime() {
		return jzdatetime;
	}

	public void setJzdatetime(Date jzdatetime) {
		this.jzdatetime = jzdatetime;
	}

	public String getSendrange() {
		return sendrange;
	}

	public void setSendrange(String sendrange) {
		this.sendrange = sendrange;
	}

	/** default constructor */
	public Bnotice() {
	}

	/** minimal constructor */
	public Bnotice(Integer id, String title, String content,
			Integer creater, String datastate) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.creater = creater;
		this.datastate = datastate;
	}

	/** full constructor */
	public Bnotice(Integer id, String title, String content,
			Integer creater, Date datetime, String datastate,
			String orgid, Integer shuserid, Date shdatetime,
			String back, String fileurl, String filename) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.creater = creater;
		this.datetime = datetime;
		this.datastate = datastate;
		this.orgid = orgid;
		this.shuserid = shuserid;
		this.shdatetime = shdatetime;
		this.back = back;
		this.fileurl = fileurl;
		this.filename = filename;
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

	public String getOrgid() {
		return this.orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public Integer getShuserid() {
		return this.shuserid;
	}

	public void setShuserid(Integer shuserid) {
		this.shuserid = shuserid;
	}

	public Date getShdatetime() {
		return this.shdatetime;
	}

	public void setShdatetime(Date shdatetime) {
		this.shdatetime = shdatetime;
	}

	public String getBack() {
		return this.back;
	}

	public void setBack(String back) {
		this.back = back;
	}

	public String getFileurl() {
		return this.fileurl;
	}

	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}