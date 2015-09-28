package com.gotop.vo.system;

/**
 * TmoduleAuthRuId generated by MyEclipse Persistence Tools
 */

public class TmoduleAuthRuId implements java.io.Serializable {

	// Fields

	private Integer id;

	private Short sqdxlx;

	private String mkdm;

	private String gndm;

	// Constructors

	/** default constructor */
	public TmoduleAuthRuId() {
	}

	/** full constructor */
	public TmoduleAuthRuId(Integer id, Short sqdxlx, String mkdm, String gndm) {
		this.id = id;
		this.sqdxlx = sqdxlx;
		this.mkdm = mkdm;
		this.gndm = gndm;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getSqdxlx() {
		return this.sqdxlx;
	}

	public void setSqdxlx(Short sqdxlx) {
		this.sqdxlx = sqdxlx;
	}

	public String getMkdm() {
		return this.mkdm;
	}

	public void setMkdm(String mkdm) {
		this.mkdm = mkdm;
	}

	public String getGndm() {
		return this.gndm;
	}

	public void setGndm(String gndm) {
		this.gndm = gndm;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TmoduleAuthRuId))
			return false;
		TmoduleAuthRuId castOther = (TmoduleAuthRuId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getSqdxlx() == castOther.getSqdxlx()) || (this
						.getSqdxlx() != null
						&& castOther.getSqdxlx() != null && this.getSqdxlx()
						.equals(castOther.getSqdxlx())))
				&& ((this.getMkdm() == castOther.getMkdm()) || (this.getMkdm() != null
						&& castOther.getMkdm() != null && this.getMkdm()
						.equals(castOther.getMkdm())))
				&& ((this.getGndm() == castOther.getGndm()) || (this.getGndm() != null
						&& castOther.getGndm() != null && this.getGndm()
						.equals(castOther.getGndm())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getSqdxlx() == null ? 0 : this.getSqdxlx().hashCode());
		result = 37 * result
				+ (getMkdm() == null ? 0 : this.getMkdm().hashCode());
		result = 37 * result
				+ (getGndm() == null ? 0 : this.getGndm().hashCode());
		return result;
	}

}