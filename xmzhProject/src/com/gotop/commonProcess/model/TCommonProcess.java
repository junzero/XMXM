package com.gotop.commonProcess.model;

import java.io.Serializable;

public class TCommonProcess implements Serializable {
    /**
     * 主键 .
     * @abatorgenerated
     */
    private Long recId;

    /**
     * 申请人编号 .
     * @abatorgenerated
     */
    private Long empId;

    /**
     * 申请人所属机构编号 .
     * @abatorgenerated
     */
    private Long orgId;

    /**
     * 流程编号 .
     * @abatorgenerated
     */
    private String flowId;

    /**
     * 电话号码 .
     * @abatorgenerated
     */
    private String phoneNo;

    /**
     * 标题 .
     * @abatorgenerated
     */
    private String comTitle;

    /**
     * 内容 .
     * @abatorgenerated
     */
    private String comContent;

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
     * 申请时间 .
     * @abatorgenerated
     */
    private String applyTime;

    /**
     * 工作事项类型 .
     * @abatorgenerated
     */
    private String bussinessType;

    /**
     * 备用字段 .
     * @abatorgenerated
     */
    private String spareCol;
    
    /**
     * 申请人姓名
     * @abatorgenerated
     */
    private String empName;
    
    /**
     * 申请人所在机构
     * @abatorgenerated
     */
    private String orgName;
    
    /**
     * 意见
     */
    private String opinion;
    
    private String nodeId;
    
    private String nodeName;
    
    /**
     * 主标题
     */
    private String mainTitle;

    /**
	 * @return nodeId
	 */
	public String getNodeId() {
		return nodeId;
	}

	/**
	 * @param nodeId 要设置的 nodeId
	 */
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	/**
	 * @return nodeName
	 */
	public String getNodeName() {
		return nodeName;
	}

	/**
	 * @param nodeName 要设置的 nodeName
	 */
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	/**
     * 主键 .
     * @abatorgenerated
     */
    public Long getRecId() {
        return recId;
    }

    /**
     * 主键 .
     * @abatorgenerated
     */
    public void setRecId(Long recId) {
        this.recId = recId;
    }

    /**
     * 申请人编号 .
     * @abatorgenerated
     */
    public Long getEmpId() {
        return empId;
    }

    /**
     * 申请人编号 .
     * @abatorgenerated
     */
    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    /**
     * 申请人所属机构编号 .
     * @abatorgenerated
     */
    public Long getOrgId() {
        return orgId;
    }

    /**
     * 申请人所属机构编号 .
     * @abatorgenerated
     */
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    /**
     * 流程编号 .
     * @abatorgenerated
     */
    public String getFlowId() {
        return flowId;
    }

    /**
     * 流程编号 .
     * @abatorgenerated
     */
    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    /**
     * 电话号码 .
     * @abatorgenerated
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * 电话号码 .
     * @abatorgenerated
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * 标题 .
     * @abatorgenerated
     */
    public String getComTitle() {
        return comTitle;
    }

    /**
     * 标题 .
     * @abatorgenerated
     */
    public void setComTitle(String comTitle) {
        this.comTitle = comTitle;
    }

    /**
     * 内容 .
     * @abatorgenerated
     */
    public String getComContent() {
        return comContent;
    }

    /**
     * 内容 .
     * @abatorgenerated
     */
    public void setComContent(String comContent) {
        this.comContent = comContent;
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
     * 申请时间 .
     * @abatorgenerated
     */
    public String getApplyTime() {
        return applyTime;
    }

    /**
     * 申请时间 .
     * @abatorgenerated
     */
    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * 工作事项类型 .
     * @abatorgenerated
     */
    public String getBussinessType() {
        return bussinessType;
    }

    /**
     * 工作事项类型 .
     * @abatorgenerated
     */
    public void setBussinessType(String bussinessType) {
        this.bussinessType = bussinessType;
    }

    /**
     * 备用字段 .
     * @abatorgenerated
     */
    public String getSpareCol() {
        return spareCol;
    }

    /**
     * 备用字段 .
     * @abatorgenerated
     */
    public void setSpareCol(String spareCol) {
        this.spareCol = spareCol;
    }

	/**
	 * 申请人姓名
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * 申请人姓名
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * 机构名
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * 机构名
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * @return opinion
	 */
	public String getOpinion() {
		return opinion;
	}

	/**
	 * @param opinion 要设置的 opinion
	 */
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	/**
	 * @return mainTitle
	 */
	public String getMainTitle() {
		return mainTitle;
	}

	/**
	 * @param mainTitle 要设置的 mainTitle
	 */
	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}
}