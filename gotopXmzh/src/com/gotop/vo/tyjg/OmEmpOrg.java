package com.gotop.vo.tyjg;

import java.io.Serializable;
import java.util.Date;

public class OmEmpOrg implements Serializable {

	private static final long serialVersionUID = 7342400517339384191L;
	private String empid;
	private String orgid;
	private String ismain;
	private String modflag;
	private String lastUpdate;
	private String datasource;
	
	public OmEmpOrg() {
		super();
	}
	
	public OmEmpOrg(String empid, String orgid, String ismain, String modflag,
			String lastUpdate, String datasource) {
		super();
		this.empid = empid;
		this.orgid = orgid;
		this.ismain = ismain;
		this.modflag = modflag;
		this.lastUpdate = lastUpdate;
		this.datasource = datasource;
	}

	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getIsmain() {
		return ismain;
	}
	public void setIsmain(String ismain) {
		this.ismain = ismain;
	}
	public String getModflag() {
		return modflag;
	}
	public void setModflag(String modflag) {
		this.modflag = modflag;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getDatasource() {
		return datasource;
	}
	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}
	
}
