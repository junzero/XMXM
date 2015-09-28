package com.gotop.vo.tyjg;

import java.io.Serializable;



/**
 * *******************************
 * <p>
 * Title: 机构信息
 * </p>
 * 
 * <p>
 * Description: 机构信息
 * </p>
 * 
 * <p>
 * Copyright: 2012
 * </p>
 * 
 * <p>
 * Company: GOTOP
 * </p>
 * 
 * @author laiwz
 * 
 * @date 2012-3-21
 * 
 * @version 1.0
 * 
 * HISTORY 2012-3-20 laiwz 创建文件
 * 
 * *******************************
 */
public class Omorganization implements Serializable {

	private static final long serialVersionUID = 7221700510339684459L;
	
	private long orgid;
	
	private String orgCode;
	
	private String orgName;
	
	private long orgLevel;
	
	private String orgDegree;
	
	private long parentOrgId;
	
	private String orgSeq;
	
	private String orgType;
	
	private String orgAddr;
	
	private String zipCode;
	
	private long manaPosition;
	
	private long manAgerId;
	
	private String orgManager;
	
	private String linkMan;
	
	private String linkTel;
	
	private String email;
	
	private String webUrl;
	
	private String startDate;
	
	private String endDate;
	
	private String status;
	
	private String area;
	
	private String createTime;
	
	private String lastUpdate;
	
	private long updator;
	
	private long sortNo;
	
	private String isLeaf;
	
	private long subCount;
	
	private String remark;
	
	private long orgTypeField;
	
	private String realName;
	
	private String displayOrder;
	
	private String orgYsjgId;
	
	private String snbz;
	
	private String whry;
	
	private String whsj;
	
	private int jgsx;
	
	private String ysjgbh;
	
	private int zhjb;
	
	private long areaType;
	
	private String comments;
	
	private String ismain;

	public long getOrgid() {
		return orgid;
	}

	public void setOrgid(long orgid) {
		this.orgid = orgid;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public long getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(long orgLevel) {
		this.orgLevel = orgLevel;
	}

	public String getOrgDegree() {
		return orgDegree;
	}

	public void setOrgDegree(String orgDegree) {
		this.orgDegree = orgDegree;
	}

	public long getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public String getOrgSeq() {
		return orgSeq;
	}

	public void setOrgSeq(String orgSeq) {
		this.orgSeq = orgSeq;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getOrgAddr() {
		return orgAddr;
	}

	public void setOrgAddr(String orgAddr) {
		this.orgAddr = orgAddr;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public long getManaPosition() {
		return manaPosition;
	}

	public void setManaPosition(long manaPosition) {
		this.manaPosition = manaPosition;
	}

	public long getManAgerId() {
		return manAgerId;
	}

	public void setManAgerId(long manAgerId) {
		this.manAgerId = manAgerId;
	}

	public String getOrgManager() {
		return orgManager;
	}

	public void setOrgManager(String orgManager) {
		this.orgManager = orgManager;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getLinkTel() {
		return linkTel;
	}

	public void setLinkTel(String linkTel) {
		this.linkTel = linkTel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public long getUpdator() {
		return updator;
	}

	public void setUpdator(long updator) {
		this.updator = updator;
	}

	public long getSortNo() {
		return sortNo;
	}

	public void setSortNo(long sortNo) {
		this.sortNo = sortNo;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public long getSubCount() {
		return subCount;
	}

	public void setSubCount(long subCount) {
		this.subCount = subCount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public long getOrgTypeField() {
		return orgTypeField;
	}

	public void setOrgTypeField(long orgTypeField) {
		this.orgTypeField = orgTypeField;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getOrgYsjgId() {
		return orgYsjgId;
	}

	public void setOrgYsjgId(String orgYsjgId) {
		this.orgYsjgId = orgYsjgId;
	}

	public String getSnbz() {
		return snbz;
	}

	public void setSnbz(String snbz) {
		this.snbz = snbz;
	}

	public String getWhry() {
		return whry;
	}

	public void setWhry(String whry) {
		this.whry = whry;
	}

	public String getWhsj() {
		return whsj;
	}

	public void setWhsj(String whsj) {
		this.whsj = whsj;
	}

	public int getJgsx() {
		return jgsx;
	}

	public void setJgsx(int jgsx) {
		this.jgsx = jgsx;
	}

	public String getYsjgbh() {
		return ysjgbh;
	}

	public void setYsjgbh(String ysjgbh) {
		this.ysjgbh = ysjgbh;
	}

	public int getZhjb() {
		return zhjb;
	}

	public void setZhjb(int zhjb) {
		this.zhjb = zhjb;
	}

	public long getAreaType() {
		return areaType;
	}

	public void setAreaType(long areaType) {
		this.areaType = areaType;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getIsmain() {
		return ismain;
	}

	public void setIsmain(String ismain) {
		this.ismain = ismain;
	}
	
	
}
