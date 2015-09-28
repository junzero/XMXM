package com.gotop.vo.report;

/**
 * TsubReport generated by MyEclipse Persistence Tools
 */

public class TsubReport implements java.io.Serializable {

	// Fields

	private Integer id;

	private Integer reportid;

	private String template;

	private String paramvalue;

	private String memo;

	// Constructors

	/** default constructor */
	public TsubReport() {
	}

	/** minimal constructor */
	public TsubReport(Integer reportid, String template, String paramvalue) {
		this.reportid = reportid;
		this.template = template;
		this.paramvalue = paramvalue;
	}

	/** full constructor */
	public TsubReport(Integer reportid, String template, String paramvalue,
			String memo) {
		this.reportid = reportid;
		this.template = template;
		this.paramvalue = paramvalue;
		this.memo = memo;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getReportid() {
		return this.reportid;
	}

	public void setReportid(Integer reportid) {
		this.reportid = reportid;
	}

	public String getTemplate() {
		return this.template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getParamvalue() {
		return this.paramvalue;
	}

	public void setParamvalue(String paramvalue) {
		this.paramvalue = paramvalue;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}