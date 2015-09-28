package com.gotop.tyjg.common.model;

import java.io.Serializable;

/**
 * *******************************
 * <p>Title: </p>
 * 
 * <p> Description: </p>
 * 
 * <p>Copyright: 2012</p>
 * 
 * <p>Company: GOTOP</p>
 * 
 * @author xuxh
 * 
 * @date Apr 10, 2012
 * 
 * @version 1.0
 * 
 * HISTORY Apr 10, 2012 xuxh 创建文件
 * 
 * *******************************
 */
public class ChangeTree implements Serializable {
	/**
	 * 选择个数
	 */
	private String checkcount;
	/**
	 * 显示机构类型 2:人员 3:岗位 4:机构
	 */
	private String orgType;
	/**
	 * 将会在按钮下方显示的红色文字
	 */
	private String checkTitle;
	/**
	 * 1显示机构tab 0不显示
	 */
	private String showTabOrg;
	/**
	 * showTabGroup
	 */
	private String showTabGroup;
	/**
	 * 1显示角色tab 0不显示
	 */
	private String showTabRole;
	
	/**
	 * 1-显示岗位人员 0-不显示
	 */
	private String showTabPositionEmp;
	

	private String showSelBox;
	
	private String posiType;
	
	private String lookupType;
	
	private String lookupTypeStr;
	
	private String tabOrg;
	
	private String tabGroup;
	
	private String tabRole;
	
	private String tabPositionEmp;
	
	private String topID;
	
	private String topName;
	
	private String orgdegree;
	
	private String startOrgid;
	
	private String startOrgcode;
	
	private String jgsx;
	
	private String loginOrgid;
	
	private String showCheckType;
	
	private String tdSelOrg;
	
	private String tdSelGroup;
	
	private String tdSelEmp;
	
	private String startid;
	
	private String id;
	
	private String groupID;
	
	/**
	 * 岗位code
	 */
	private String positioncode;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStartid() {
		return startid;
	}

	public void setStartid(String startid) {
		this.startid = startid;
	}

	public String getTdSelOrg() {
		return tdSelOrg;
	}

	public void setTdSelOrg(String tdSelOrg) {
		this.tdSelOrg = tdSelOrg;
	}

	public String getTdSelGroup() {
		return tdSelGroup;
	}

	public void setTdSelGroup(String tdSelGroup) {
		this.tdSelGroup = tdSelGroup;
	}

	public String getTdSelEmp() {
		return tdSelEmp;
	}

	public void setTdSelEmp(String tdSelEmp) {
		this.tdSelEmp = tdSelEmp;
	}

	public String getShowCheckType() {
		return showCheckType;
	}

	public void setShowCheckType(String showCheckType) {
		this.showCheckType = showCheckType;
	}

	public String getTopID() {
		return topID;
	}

	public void setTopID(String topID) {
		this.topID = topID;
	}

	public String getTopName() {
		return topName;
	}

	public void setTopName(String topName) {
		this.topName = topName;
	}

	public String getOrgdegree() {
		return orgdegree;
	}

	public void setOrgdegree(String orgdegree) {
		this.orgdegree = orgdegree;
	}

	public String getStartOrgid() {
		return startOrgid;
	}

	public void setStartOrgid(String startOrgid) {
		this.startOrgid = startOrgid;
	}

	public String getStartOrgcode() {
		return startOrgcode;
	}

	public void setStartOrgcode(String startOrgcode) {
		this.startOrgcode = startOrgcode;
	}

	public String getJgsx() {
		return jgsx;
	}

	public void setJgsx(String jgsx) {
		this.jgsx = jgsx;
	}

	public String getLoginOrgid() {
		return loginOrgid;
	}

	public void setLoginOrgid(String loginOrgid) {
		this.loginOrgid = loginOrgid;
	}

	public String getLookupTypeStr() {
		return lookupTypeStr;
	}

	public void setLookupTypeStr(String lookupTypeStr) {
		this.lookupTypeStr = lookupTypeStr;
	}

	public String getTabOrg() {
		return tabOrg;
	}

	public void setTabOrg(String tabOrg) {
		this.tabOrg = tabOrg;
	}

	public String getTabGroup() {
		return tabGroup;
	}

	public void setTabGroup(String tabGroup) {
		this.tabGroup = tabGroup;
	}

	public String getTabRole() {
		return tabRole;
	}

	public void setTabRole(String tabRole) {
		this.tabRole = tabRole;
	}

	public String getShowSelBox() {
		return showSelBox;
	}

	public void setShowSelBox(String showSelBox) {
		this.showSelBox = showSelBox;
	}

	public String getPosiType() {
		return posiType;
	}

	public void setPosiType(String posiType) {
		this.posiType = posiType;
	}

	public String getLookupType() {
		return lookupType;
	}

	public void setLookupType(String lookupType) {
		this.lookupType = lookupType;
	}

	public String getCheckcount() {
		return checkcount;
	}

	public void setCheckcount(String checkcount) {
		this.checkcount = checkcount;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getCheckTitle() {
		return checkTitle;
	}

	public void setCheckTitle(String checkTitle) {
		this.checkTitle = checkTitle;
	}

	public String getShowTabOrg() {
		return showTabOrg;
	}

	public void setShowTabOrg(String showTabOrg) {
		this.showTabOrg = showTabOrg;
	}

	public String getShowTabGroup() {
		return showTabGroup;
	}

	public void setShowTabGroup(String showTabGroup) {
		this.showTabGroup = showTabGroup;
	}

	public String getShowTabRole() {
		return showTabRole;
	}

	public void setShowTabRole(String showTabRole) {
		this.showTabRole = showTabRole;
	}

	public String getGroupID() {
		return groupID;
	}

	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}

	public String getPositioncode() {
		return positioncode;
	}

	public void setPositioncode(String positioncode) {
		this.positioncode = positioncode;
	}

	public String getShowTabPositionEmp() {
		return showTabPositionEmp;
	}

	public void setShowTabPositionEmp(String showTabPositionEmp) {
		this.showTabPositionEmp = showTabPositionEmp;
	}

	public String getTabPositionEmp() {
		return tabPositionEmp;
	}

	public void setTabPositionEmp(String tabPositionEmp) {
		this.tabPositionEmp = tabPositionEmp;
	}

}
