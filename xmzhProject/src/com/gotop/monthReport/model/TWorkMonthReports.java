package com.gotop.monthReport.model;

import java.io.Serializable;

public class TWorkMonthReports implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6971304117932925890L;
    /**
     * 月报编号 .
     * @abatorgenerated
     */
    private Long reportId;
    /**
     * 流程ID .
     * @abatorgenerated
     */
    private String flowId;
    /**
     * 月报标题 .
     * @abatorgenerated
     */
    private String reportTitle;
    /**
     * 月报内容 .
     * @abatorgenerated
     */
    private String content;
    /**
     * 创建人 .
     * @abatorgenerated
     */
    private Long creator;
    /**
     * 创建日期 .
     * @abatorgenerated
     */
    private String createDate;
    /**
     * 创建时间 .
     * @abatorgenerated
     */
    private String createTime;
    /**
     * 创建人姓名 .
     * @abatorgenerated
     */
    private String createName;
    /**
     * 归属机构 .
     * @abatorgenerated
     */
    private Long orgid;
    
    /**
     * 申请机构名称
     */
    private String orgname;
    /**
     * yyyyMM月报月份 .
     * @abatorgenerated
     */
    private String reportMonth;
    
    /**
     * 审核意见
     */
    private String opninion;
    /**
     * 状态
     */
    private String status;
    
    /**
     * 节点ID
     */
    private String nodeId;
    
    /**
     * 节点名称
     */
    private String nodeName1;
    
    /**
     * 处理人ID
     */
    private Long empId;
    
    /**
     * 角色ID
     */
    private String roleId;
    
    /**
     * 开始时间
     */
    private String createBeginDate;
    
    /**
     * 终止时间
     */
    private String createEndDate;
    private String jgsx;                 //1、银行/2、邮政
    
    /**
     * 分页查询的起始行数.
     * @abatorgenerated
     */
    private Integer oracleStart;

    /**
     * 分页查询结束行数.
     * @abatorgenerated
     */
    private Integer oracleEnd;
    
    /**
     * 月报编号 .
     * @abatorgenerated
     */
    public Long getReportId() {
        return reportId;
    }

    /**
     * 月报编号 .
     * @abatorgenerated
     */
    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    /**
     * 流程ID .
     * @abatorgenerated
     */
    public String getFlowId() {
        return flowId;
    }

    /**
     * 流程ID .
     * @abatorgenerated
     */
    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    /**
     * 月报标题 .
     * @abatorgenerated
     */
    public String getReportTitle() {
        return reportTitle;
    }

    /**
     * 月报标题 .
     * @abatorgenerated
     */
    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    /**
     * 月报内容 .
     * @abatorgenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * 月报内容 .
     * @abatorgenerated
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 创建人 .
     * @abatorgenerated
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * 创建人 .
     * @abatorgenerated
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    /**
     * 创建日期 .
     * @abatorgenerated
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * 创建日期 .
     * @abatorgenerated
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * 创建时间 .
     * @abatorgenerated
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间 .
     * @abatorgenerated
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建人姓名 .
     * @abatorgenerated
     */
    public String getCreateName() {
        return createName;
    }

    /**
     * 创建人姓名 .
     * @abatorgenerated
     */
    public void setCreateName(String createName) {
        this.createName = createName;
    }

    /**
     * 归属机构 .
     * @abatorgenerated
     */
    public Long getOrgid() {
        return orgid;
    }

    /**
     * 归属机构 .
     * @abatorgenerated
     */
    public void setOrgid(Long orgid) {
        this.orgid = orgid;
    }

    /**
	 * @return orgname
	 */
	public String getOrgname() {
		return orgname;
	}

	/**
	 * @param orgname 要设置的 orgname
	 */
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	/**
     * yyyyMM .
     * @abatorgenerated
     */
    public String getReportMonth() {
        return reportMonth;
    }

    /**
     * yyyyMM .
     * @abatorgenerated
     */
    public void setReportMonth(String reportMonth) {
        this.reportMonth = reportMonth;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the nodeId
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * @param nodeId the nodeId to set
     */
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * @return the nodeName1
     */
    public String getNodeName1() {
        return nodeName1;
    }

    /**
     * @param nodeName1 the nodeName1 to set
     */
    public void setNodeName1(String nodeName1) {
        this.nodeName1 = nodeName1;
    }

    /**
     * @return the empId
     */
    public Long getEmpId() {
        return empId;
    }

    /**
     * @param empId the empId to set
     */
    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    /**
     * @return the roleId
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the createEndDate
     */
    public String getCreateEndDate() {
        return createEndDate;
    }

    /**
     * @param createEndDate the createEndDate to set
     */
    public void setCreateEndDate(String createEndDate) {
        this.createEndDate = createEndDate;
    }

    /**
     * @return the createBeginDate
     */
    public String getCreateBeginDate() {
        return createBeginDate;
    }

    /**
     * @param createBeginDate the createBeginDate to set
     */
    public void setCreateBeginDate(String createBeginDate) {
        this.createBeginDate = createBeginDate;
    }

    /**
     * @return the oracleStart
     */
    public Integer getOracleStart() {
        return oracleStart;
    }

    /**
     * @param oracleStart the oracleStart to set
     */
    public void setOracleStart(Integer oracleStart) {
        this.oracleStart = oracleStart;
    }

    /**
     * @return the oracleEnd
     */
    public Integer getOracleEnd() {
        return oracleEnd;
    }

    /**
     * @param oracleEnd the oracleEnd to set
     */
    public void setOracleEnd(Integer oracleEnd) {
        this.oracleEnd = oracleEnd;
    }

    /**
     * @return the jgsx
     */
    public String getJgsx() {
        return jgsx;
    }

    /**
     * @param jgsx the jgsx to set
     */
    public void setJgsx(String jgsx) {
        this.jgsx = jgsx;
    }

    /**
     * @return the opninion
     */
    public String getOpninion() {
        return opninion;
    }

    /**
     * @param opninion the opninion to set
     */
    public void setOpninion(String opninion) {
        this.opninion = opninion;
    }
    
}