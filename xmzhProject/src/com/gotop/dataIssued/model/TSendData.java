package com.gotop.dataIssued.model;

import java.io.Serializable;

public class TSendData implements Serializable {
    /**
	 * 下发表编号 .
	 * @abatorgenerated
	 */
	private Long dsId;
	/**
	 * 下发人编号 .
	 * @abatorgenerated
	 */
	private Long sEmpId;
	/**
	 * 下发人所在机构
	 */
	private Long orgid;
	/**
	 * 流程id .
	 * @abatorgenerated
	 */
	private String flowId;
	/**
	 * 数据来源 .
	 * @abatorgenerated
	 */
	private String dataSource;
	/**
	 * 数据申请流程 .
	 * @abatorgenerated
	 */
	private String daPro;
	/**
	 * 数据类型 .
	 * @abatorgenerated
	 */
	private String dataType;
	/**
	 * 数据接收类型 .
	 * @abatorgenerated
	 */
	private String rvType;
	/**
	 * 数据下发标题 .
	 * @abatorgenerated
	 */
	private String dsTitle;
	/**
	 * 数据下发说明 .
	 * @abatorgenerated
	 */
	private String dsExpl;
	/**
	 * 数据使用时限 .
	 * @abatorgenerated
	 */
	private String timeLimit;
	/**
	 * 提交日期 .
	 * @abatorgenerated
	 */
	private String createDate;
	/**
	 * 提交日期 .
	 * @abatorgenerated
	 */
	private String createTime;
	/**
	 * 数据使用人员编号 .
	 * @abatorgenerated
	 */
	private String dataUser;
	/**
	 * 数据使用人员姓名 .
	 * @abatorgenerated
	 */
	private String username;
	
	/**
	 * 登录人可用下发表所用的人员表编号
	 */
	private Long userId;
	
	private String nodeId;
	
	private String nodeName;

	/**
	 * 是否销毁
	 */
	private String isDes;
	
	/**
	 * 销毁时间
	 */
	private String startTime;
	
	/**
	 * 销毁时间
	 */
	private String endTime;
	
	/**
	 * 数据销毁人姓名
	 */
	private String desName;
	
	/**
	 * 数据下发人姓名
	 */
	private String empName;
	
	/**
	 * 数据销毁人
	 */
	private String desId;
	
	/**
	 * 数据使用人机构id
	 */
	private String userOrg;
	
	/**
	 * 数据销毁时间
	 */
	private String desTime;
	
	private String beginTime;
	private String closeTime;
	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	/**
     * 数据使用人员id
     * @return
     */
	public String getDataUser() {
		return dataUser;
	}
	
	/*
	 * 数据使用人员id
	 */
	public void setDataUser(String dataUser) {
		this.dataUser = dataUser;
	}

	/**
	 * 下发表编号 .
	 * @abatorgenerated
	 */
	public Long getDsId() {
		return dsId;
	}

	/**
	 * 下发表编号 .
	 * @abatorgenerated
	 */
	public void setDsId(Long dsId) {
		this.dsId = dsId;
	}

	/**
	 * 下发人编号 .
	 * @abatorgenerated
	 */
	public Long getsEmpId() {
		return sEmpId;
	}

	/**
	 * 下发人编号 .
	 * @abatorgenerated
	 */
	public void setsEmpId(Long sEmpId) {
		this.sEmpId = sEmpId;
	}

	/**
	 * 下发人编号 .
	 * @abatorgenerated
	 */
	public Long getSEmpId() {
		return getsEmpId();
	}

	/**
	 * 下发人编号 .
	 * @abatorgenerated
	 */
	public void setSEmpId(Long sEmpId) {
		setsEmpId(sEmpId);
	}

	public Long getOrgid() {
		return orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	/**
	 * 流程id .
	 * @abatorgenerated
	 */
	public String getFlowId() {
		return flowId;
	}

	/**
	 * 流程id .
	 * @abatorgenerated
	 */
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	/**
	 * 数据来源 .
	 * @abatorgenerated
	 */
	public String getDataSource() {
		return dataSource;
	}

	/**
	 * 数据来源 .
	 * @abatorgenerated
	 */
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 数据申请流程 .
	 * @abatorgenerated
	 */
	public String getDaPro() {
		return daPro;
	}

	/**
	 * 数据申请流程 .
	 * @abatorgenerated
	 */
	public void setDaPro(String daPro) {
		this.daPro = daPro;
	}

	/**
	 * 数据类型 .
	 * @abatorgenerated
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * 数据类型 .
	 * @abatorgenerated
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	/**
	 * 数据接收类型 .
	 * @abatorgenerated
	 */
	public String getRvType() {
		return rvType;
	}

	/**
	 * 数据接收类型 .
	 * @abatorgenerated
	 */
	public void setRvType(String rvType) {
		this.rvType = rvType;
	}

	/**
	 * 数据下发标题 .
	 * @abatorgenerated
	 */
	public String getDsTitle() {
		return dsTitle;
	}

	/**
	 * 数据下发标题 .
	 * @abatorgenerated
	 */
	public void setDsTitle(String dsTitle) {
		this.dsTitle = dsTitle;
	}

	/**
	 * 数据下发说明 .
	 * @abatorgenerated
	 */
	public String getDsExpl() {
		return dsExpl;
	}

	/**
	 * 数据下发说明 .
	 * @abatorgenerated
	 */
	public void setDsExpl(String dsExpl) {
		this.dsExpl = dsExpl;
	}

	/**
	 * 数据使用时限 .
	 * @abatorgenerated
	 */
	public String getTimeLimit() {
		return timeLimit;
	}

	/**
	 * 数据使用时限 .
	 * @abatorgenerated
	 */
	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	/**
	 * 提交日期 .
	 * @abatorgenerated
	 */
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * 提交日期 .
	 * @abatorgenerated
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	/**
	 * 提交日期 .
	 * @abatorgenerated
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 提交日期 .
	 * @abatorgenerated
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 数据使用人员姓名 .
	 * @abatorgenerated
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 数据使用人员姓名 .
	 * @abatorgenerated
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	/**
	 * @return desId
	 */
	public String getDesId() {
		return desId;
	}

	/**
	 * @param desId 要设置的 desId
	 */
	public void setDesId(String desId) {
		this.desId = desId;
	}

	/**
	 * @return isDes
	 */
	public String getIsDes() {
		return isDes;
	}

	/**
	 * @param isDes 要设置的 isDes
	 */
	public void setIsDes(String isDes) {
		this.isDes = isDes;
	}

	/**
	 * @return startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime 要设置的 startTime
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime 要设置的 endTime
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return desName
	 */
	public String getDesName() {
		return desName;
	}

	/**
	 * @param desName 要设置的 desName
	 */
	public void setDesName(String desName) {
		this.desName = desName;
	}

	/**
	 * @return empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName 要设置的 empName
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the userOrg
	 */
	public String getUserOrg() {
		return userOrg;
	}

	/**
	 * @param userOrg the userOrg to set
	 */
	public void setUserOrg(String userOrg) {
		this.userOrg = userOrg;
	}

	/**
	 * @return the desTime
	 */
	public String getDesTime() {
		return desTime;
	}

	/**
	 * @param desTime the desTime to set
	 */
	public void setDesTime(String desTime) {
		this.desTime = desTime;
	}
}