package com.gotop.opinion.model;

import java.io.Serializable;

public class TDefaultOpinion implements Serializable {

	/**
	 * 主键 .
	 * @abatorgenerated
	 */
	private Long recId;
	/**
	 * 员工编号 .
	 * @abatorgenerated
	 */
	private Long empId;
	/**
	 * 默认意见 .
	 * @abatorgenerated
	 */
	private String opinion;

	/**
	 * 主键 .
	 * @abatorgenerated
	 */
	public Long getRecId() {
		return recId;
	}

	/**
	 * 主键 .
	 * @abatorgenerated
	 */
	public void setRecId(Long recId) {
		this.recId = recId;
	}

	/**
	 * 员工编号 .
	 * @abatorgenerated
	 */
	public Long getEmpId() {
		return empId;
	}

	/**
	 * 员工编号 .
	 * @abatorgenerated
	 */
	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	/**
	 * 默认意见 .
	 * @abatorgenerated
	 */
	public String getOpinion() {
		return opinion;
	}

	/**
	 * 默认意见 .
	 * @abatorgenerated
	 */
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
}