package com.gotop.vo.system;
import java.util.HashMap;

import commonj.sdo.DataObject;

public class MUOUserSession implements java.io.Serializable{

	private Long orgid;
	private String orgseq;
	private String orgname;
	private String orgcode;
	private String orgdegree;
	private String empysryid1;//映射人员ID
	private String orgysjgid;//映射机构ID

	private Long empid;
	private String empcode;
	private String empname;
	private String[] roleid;//过滤后角色

	private DataObject[] userOrgs;
//	private Long mainOrgid;//主机构功能已放弃
	private String orgJgsx;//1、银行/2、邮政
	private Long orgentityid;//所属机构ID。如果为部门应该递归查到机构???????
	private String orgentityname;//所属机构name。如果为部门应该递归查到机构???????
	private String orgentitycode;//所属机构code。如果为部门应该递归查到机构???????
	private Long orgpostentityid;//所属机构ID。如果为部门应该递归查到机构???????(邮政人员对应实体机构）
	private String orgpostentityname;//所属机构name。如果为部门应该递归查到机构???????(邮政人员对应实体机构）
	private String orgpostentitycode;//所属机构code。如果为部门应该递归查到机构???????(邮政人员对应实体机构）
	public String[] groupid;//群组
	public String cardtype;//证件类型
	public String cardno;//证件号码
	public String password;
	public String headicon;//头像
	public String layoutmode;//版面模式
	/*
	 * 手机号码
	 */
	private String mobi;
	
	private HashMap<String, String> loginOrg;//可登录机构
	
	private String[] positionId;//岗位id
	
	private String[] posiCode;//岗位代码
	
	private String[] posiName;//岗位名称
	
	/**
     * @return the mobi
     */
    public String getMobi() {
        return mobi;
    }
    /**
     * @param mobi the mobi to set
     */
    public void setMobi(String mobi) {
        this.mobi = mobi;
    }
    public HashMap<String, String> getLoginOrg() {
		return loginOrg;
	}
	public void setLoginOrg(HashMap<String, String> loginOrg) {
		this.loginOrg = loginOrg;
	}
	public String getOrgseq() {
		return orgseq;
	}
	public void setOrgseq(String orgseq) {
		this.orgseq = orgseq;
	}
	public String getLayoutmode() {
		return layoutmode;
	}
	public void setLayoutmode(String layoutmode) {
		this.layoutmode = layoutmode;
	}
	public String getEmpcode() {
		return empcode;
	}
	public String getOrgpostentitycode() {
		return orgpostentitycode;
	}
	public void setOrgpostentitycode(String orgpostentitycode) {
		this.orgpostentitycode = orgpostentitycode;
	}
	public Long getOrgpostentityid() {
		return orgpostentityid;
	}
	public void setOrgpostentityid(Long orgpostentityid) {
		this.orgpostentityid = orgpostentityid;
	}
	public String getOrgpostentityname() {
		return orgpostentityname;
	}
	public void setOrgpostentityname(String orgpostentityname) {
		this.orgpostentityname = orgpostentityname;
	}
	public void setEmpcode(String empcode) {
		this.empcode = empcode;
	}
	public Long getEmpid() {
		return empid;
	}
	public void setEmpid(Long empid) {
		this.empid = empid;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	
	public String getOrgcode() {
		return orgcode;
	}
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	public String getOrgdegree() {
		return orgdegree;
	}
	public void setOrgdegree(String orgdegree) {
		this.orgdegree = orgdegree;
	}
	public Long getOrgid() {
		return orgid;
	}
	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String[] getRoleid() {
		return roleid;
	}
	public void setRoleid(String[] roleid) {
		this.roleid = roleid;
	}
	
	public DataObject[] getUserOrgs() {
		return userOrgs;
	}
	public void setUserOrgs(DataObject[] userOrgs) {
		this.userOrgs = userOrgs;
	}

	public String getEmpysryid1() {
		return empysryid1;
	}
	public void setEmpysryid1(String empysryid1) {
		this.empysryid1 = empysryid1;
	}
	public String getOrgysjgid() {
		return orgysjgid;
	}
	public void setOrgysjgid(String orgysjgid) {
		this.orgysjgid = orgysjgid;
	}
	public String getOrgJgsx() {
		return orgJgsx;
	}
	public void setOrgJgsx(String orgJgsx) {
		this.orgJgsx = orgJgsx;
	}
	public Long getOrgentityid() {
		return orgentityid;
	}
	public void setOrgentityid(Long orgentityid) {
		this.orgentityid = orgentityid;
	}
	public String getOrgentitycode() {
		return orgentitycode;
	}
	public void setOrgentitycode(String orgentitycode) {
		this.orgentitycode = orgentitycode;
	}
	public String getOrgentityname() {
		return orgentityname;
	}
	public void setOrgentityname(String orgentityname) {
		this.orgentityname = orgentityname;
	}
	public String[] getGroupid() {
		return groupid;
	}
	public void setGroupid(String[] groupid) {
		this.groupid = groupid;
	}
	public String getCardtype() {
		return cardtype;
	}
	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getHeadicon() {
		return headicon;
	}
	public void setHeadicon(String headicon) {
		this.headicon = headicon;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String[] getPositionId() {
		return positionId;
	}
	public void setPositionId(String[] positionId) {
		this.positionId = positionId;
	}
	public String[] getPosiCode() {
		return posiCode;
	}
	public void setPosiCode(String[] posiCode) {
		this.posiCode = posiCode;
	}
	public String[] getPosiName() {
		return posiName;
	}
	public void setPosiName(String[] posiName) {
		this.posiName = posiName;
	}
	
}

