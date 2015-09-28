/**
 * 
 */
package com.gotop.vo.tyjg;

import java.io.Serializable;
import java.util.Date;

/**
 * @author psb-laiwz
 *
 */
public class OmEmpPosition implements Serializable {

	private static final long serialVersionUID = 7345400517334384192L;
	private int empid;
	private int positionid;
	private String ismain;
	
	public OmEmpPosition() {
		super();
	}
	
	public OmEmpPosition(int empid, int positionid, String ismain) {
		super();
		this.empid = empid;
		this.positionid = positionid;
		this.ismain = ismain;
	}

	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public int getPositionid() {
		return positionid;
	}
	public void setPositionid(int positionid) {
		this.positionid = positionid;
	}
	public String getIsmain() {
		return ismain;
	}
	public void setIsmain(String ismain) {
		this.ismain = ismain;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	
}
