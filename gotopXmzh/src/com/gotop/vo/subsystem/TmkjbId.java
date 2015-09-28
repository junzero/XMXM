package com.gotop.vo.subsystem;


/**
 * TmkjbId entity. @author MyEclipse Persistence Tools
 */

public class TmkjbId implements java.io.Serializable {

	// Fields

	private Integer usid;
	private String mkdm;
	private String lv;

	// Constructors

	/** default constructor */
	public TmkjbId() {
	}

	/** full constructor */
	public TmkjbId(Integer usid, String mkdm, String lv) {
		this.usid = usid;
		this.mkdm = mkdm;
		this.lv = lv;
	}

	// Property accessors

	public Integer getUsid() {
		return this.usid;
	}

	public void setUsid(Integer usid) {
		this.usid = usid;
	}

	public String getMkdm() {
		return this.mkdm;
	}

	public void setMkdm(String mkdm) {
		this.mkdm = mkdm;
	}

	public String getLv() {
		return this.lv;
	}

	public void setLv(String lv) {
		this.lv = lv;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TmkjbId))
			return false;
		TmkjbId castOther = (TmkjbId) other;

		return ((this.getUsid() == castOther.getUsid()) || (this.getUsid() != null
				&& castOther.getUsid() != null && this.getUsid().equals(
				castOther.getUsid())))
				&& ((this.getMkdm() == castOther.getMkdm()) || (this.getMkdm() != null
						&& castOther.getMkdm() != null && this.getMkdm()
						.equals(castOther.getMkdm())))
				&& ((this.getLv() == castOther.getLv()) || (this.getLv() != null
						&& castOther.getLv() != null && this.getLv().equals(
						castOther.getLv())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUsid() == null ? 0 : this.getUsid().hashCode());
		result = 37 * result
				+ (getMkdm() == null ? 0 : this.getMkdm().hashCode());
		result = 37 * result + (getLv() == null ? 0 : this.getLv().hashCode());
		return result;
	}

}