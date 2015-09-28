package com.gotop.vo.util;

/**
 * Txtpz entity. @author MyEclipse Persistence Tools
 */

public class Txtpz implements java.io.Serializable {

	// Fields

	private Integer id;
	private String pzdm;
	private String flmc;
	private String pzz;
	private String orgid;
	private String bzsm;
	// Constructors

	public String getBzsm() {
		return bzsm;
	}

	public void setBzsm(String bzsm) {
		this.bzsm = bzsm;
	}

	/** default constructor */
	public Txtpz() {
	}

	/** minimal constructor */
	public Txtpz(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Txtpz(Integer id, String pzdm, String flmc, String pzz,
			String orgid) {
		this.id = id;
		this.pzdm = pzdm;
		this.flmc = flmc;
		this.pzz = pzz;
		this.orgid = orgid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPzdm() {
		return this.pzdm;
	}

	public void setPzdm(String pzdm) {
		this.pzdm = pzdm;
	}

	public String getFlmc() {
		return this.flmc;
	}

	public void setFlmc(String flmc) {
		this.flmc = flmc;
	}

	public String getPzz() {
		return this.pzz;
	}

	public void setPzz(String pzz) {
		this.pzz = pzz;
	}

	public String getOrgid() {
		return this.orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

}