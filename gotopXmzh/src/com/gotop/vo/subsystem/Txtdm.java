package com.gotop.vo.subsystem;

public class Txtdm implements java.io.Serializable {

	// Fields

	private TxtdmId id;
	
	private Short zdlx;

	private String sm;

	private String zfbm;

	private String bz;
	
	private String orgid;
	
	private String isshow;

	// Constructors

	/** default constructor */
	public Txtdm() {
	}

	/** minimal constructor */
	public Txtdm(TxtdmId id) {
		this.id = id;
	}

	/** full constructor */
	public Txtdm(TxtdmId id, Short zdlx, String sm, String zfbm, String bz,String orgid) {
		this.id = id;
		this.zdlx = zdlx;
		this.sm = sm;
		this.zfbm = zfbm;
		this.bz = bz;
		this.orgid=orgid;
	}

	// Property accessors

	public TxtdmId getId() {
		return this.id;
	}

	public void setId(TxtdmId id) {
		this.id = id;
	}

	public Short getZdlx() {
		return this.zdlx;
	}

	public void setZdlx(Short zdlx) {
		this.zdlx = zdlx;
	}

	public String getSm() {
		return this.sm;
	}

	public void setSm(String sm) {
		this.sm = sm;
	}

	public String getZfbm() {
		return this.zfbm;
	}

	public void setZfbm(String zfbm) {
		this.zfbm = zfbm;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getOrgid()
	{
		return orgid;
	}

	public void setOrgid(String orgid)
	{
		this.orgid = orgid;
	}

	public String getIsshow() {
		return isshow;
	}

	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}

}