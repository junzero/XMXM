package com.gotop.vo.office;


import java.util.Date;

/**
 * Blinkprogram entity. @author MyEclipse Persistence Tools
 * 联系计划
 */

public class Blinkprogram implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;//标题 
	private Date plantime;//计划时间
	private Integer custmetid;//客户ID
	private Integer linktype;//联系类型
	private String content;//备注
	private Integer actiontype;//执行状态 
	private Integer senduserid;//登记人
	private Date sendtime;//登记时间
	private Date actiontime;//执行
	private Integer serviceid;//评价客服ID
	private String opinions;//客户反馈
	private Date servicetime;//评价时间
	private Integer datastate;//状态
	private Integer evaluation;//满意度
	private Integer actionuser;//执行人ID
	private String orgid;
	private Integer sfpj;//是否客服评价 o否 1是
	private String lxritems;//联系人列表
	// Constructors

	public String getLxritems() {
		return lxritems;
	}

	public void setLxritems(String lxritems) {
		this.lxritems = lxritems;
	}

	public Integer getSfpj() {
		return sfpj;
	}

	public void setSfpj(Integer sfpj) {
		this.sfpj = sfpj;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public Integer getActionuser() {
		return actionuser;
	}

	public void setActionuser(Integer actionuser) {
		this.actionuser = actionuser;
	}

	/** default constructor */
	public Blinkprogram() {
	}

	/** minimal constructor */
	public Blinkprogram(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Blinkprogram(Integer id, String title, Date plantime,
			Integer custmetid, Integer linktype, String content,
			Integer actiontype, Integer senduserid, Date sendtime,
			Date actiontime, Integer serviceid, String opinions,
			Date servicetime, Integer datastate, Integer evaluation) {
		this.id = id;
		this.title = title;
		this.plantime = plantime;
		this.custmetid = custmetid;
		this.linktype = linktype;
		this.content = content;
		this.actiontype = actiontype;
		this.senduserid = senduserid;
		this.sendtime = sendtime;
		this.actiontime = actiontime;
		this.serviceid = serviceid;
		this.opinions = opinions;
		this.servicetime = servicetime;
		this.datastate = datastate;
		this.evaluation = evaluation;
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

	public Date getPlantime() {
		return this.plantime;
	}

	public void setPlantime(Date plantime) {
		this.plantime = plantime;
	}

	public Integer getCustmetid() {
		return this.custmetid;
	}

	public void setCustmetid(Integer custmetid) {
		this.custmetid = custmetid;
	}

	public Integer getLinktype() {
		return this.linktype;
	}

	public void setLinktype(Integer linktype) {
		this.linktype = linktype;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getActiontype() {
		return this.actiontype;
	}

	public void setActiontype(Integer actiontype) {
		this.actiontype = actiontype;
	}

	public Integer getSenduserid() {
		return this.senduserid;
	}

	public void setSenduserid(Integer senduserid) {
		this.senduserid = senduserid;
	}

	public Date getSendtime() {
		return this.sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}

	public Date getActiontime() {
		return this.actiontime;
	}

	public void setActiontime(Date actiontime) {
		this.actiontime = actiontime;
	}

	public Integer getServiceid() {
		return this.serviceid;
	}

	public void setServiceid(Integer serviceid) {
		this.serviceid = serviceid;
	}

	public String getOpinions() {
		return this.opinions;
	}

	public void setOpinions(String opinions) {
		this.opinions = opinions;
	}

	public Date getServicetime() {
		return this.servicetime;
	}

	public void setServicetime(Date servicetime) {
		this.servicetime = servicetime;
	}

	public Integer getDatastate() {
		return this.datastate;
	}

	public void setDatastate(Integer datastate) {
		this.datastate = datastate;
	}

	public Integer getEvaluation() {
		return this.evaluation;
	}

	public void setEvaluation(Integer evaluation) {
		this.evaluation = evaluation;
	}

}