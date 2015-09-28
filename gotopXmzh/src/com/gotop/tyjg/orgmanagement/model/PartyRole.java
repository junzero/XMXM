package com.gotop.tyjg.orgmanagement.model;

import java.io.Serializable;

public class PartyRole implements Serializable {
	private static final long serialVersionUID = -2153734395381737005L;
	private String partyType;
	private String partyId;
	private String roleName;
	private String jgsx;
	private String orgDegree;
	private String specialTyname;
	private String roleId;
	private String auto;
	private String roleTypeField;
	private String roleType;
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public String getRoleTypeField() {
		return roleTypeField;
	}
	public void setRoleTypeField(String roleTypeField) {
		this.roleTypeField = roleTypeField;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getAuto() {
		return auto;
	}
	public void setAuto(String auto) {
		this.auto = auto;
	}
	public String getSpecialTyname() {
		return specialTyname;
	}
	public void setSpecialTyname(String specialTyname) {
		this.specialTyname = specialTyname;
	}
	public String getPartyType() {
		return partyType;
	}
	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}
	public String getPartyId() {
		return partyId;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getJgsx() {
		return jgsx;
	}
	public void setJgsx(String jgsx) {
		this.jgsx = jgsx;
	}
	public String getOrgDegree() {
		return orgDegree;
	}
	public void setOrgDegree(String orgDegree) {
		this.orgDegree = orgDegree;
	}
	
}
