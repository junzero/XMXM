package com.gotop.util;

/**
 * 保存在Session中,方便快速查询
 * @author garyman
 *
 */
public class QueryBean {
	
	private String sfzhm = ""; //个人-身份证号码
	
	private String xm = ""; //个人-姓名
	
	private int nl = 0; //个人-年龄
	
	private String xb = "男"; //个人-性别
	
	private String csrq = "1900-01-01"; //个人-出生日期
	
	private String syrq = "1900-01-01";
	
	private String dwmc = ""; //单位-单位名称
	
	private String dwdm = ""; //单位-单位编号

	public String getCsrq() {
		return csrq;
	}

	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}

	public String getDwdm() {
		return dwdm;
	}

	public void setDwdm(String dwdm) {
		this.dwdm = dwdm;
	}

	public String getDwmc() {
		return dwmc;
	}

	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}

	public int getNl() {
		return nl;
	}

	public void setNl(int nl) {
		this.nl = nl;
	}

	public String getSfzhm() {
		return sfzhm;
	}

	public void setSfzhm(String sfzhm) {
		this.sfzhm = sfzhm;
	}

	public String getSyrq() {
		return syrq;
	}

	public void setSyrq(String syrq) {
		this.syrq = syrq;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}
	
	
	
}
