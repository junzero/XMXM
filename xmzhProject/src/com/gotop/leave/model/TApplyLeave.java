package com.gotop.leave.model;

import java.io.Serializable;

public class TApplyLeave implements Serializable {
    /**
     * 主键
     * @abatorgenerated
     */
    private Long lvId;

    /**
     * 申请人编号
     * @abatorgenerated
     */
    private Long empId;
    
    /**
     * 流程编号
     */
    private String flowId;
    
    /**
     * 申请人名字
     */
    private String empName;
    
    /**
     * 申请人名字
     * @return
     */
    public String getEmpName() {
		return empName;
	}

    /**
     * 申请人名字
     * @param empName
     */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
     * 请假标题
     * @abatorgenerated
     */
    private String lvTitle;

    /**
     * 申请人联系电话
     * @abatorgenerated
     */
    private String phoneNo;

    /**
     * 请假类型
     * @abatorgenerated
     */
    private String lvType;

    /**
     * 请假原因
     * @abatorgenerated
     */
    private String lvReason;

    /**
     * 请假开始时间
     * @abatorgenerated
     */
    private String lvStart;

    /**
     * 请假结束时间
     * @abatorgenerated
     */
    private String lvEnd;

    /**
     * 申请发起日期
     * @abatorgenerated
     */
    private String createDate;
    
    /**
     * 申请发起时间
     */
    private String createTime;
    
    /**
     * 当前节点编号
     */
    private String nodeId;
   
    /**
     * 当前节点名称
     */
    private String nodeName;

    /**
     * 申请人机构编号
     */
    private Long orgid;
    /**
     * 主键
     * @abatorgenerated
     */
    public Long getLvId() {
        return lvId;
    }

    /**
     * 主键
     * @abatorgenerated
     */
    public void setLvId(Long lvId) {
        this.lvId = lvId;
    }

    /**
     * 申请人编号
     * @abatorgenerated
     */
    public Long getEmpId() {
        return empId;
    }

    /**
     * 申请人编号
     * @abatorgenerated
     */
    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    /**
     * 流程实例编号
     * @return
     */
    public String getFlowId() {
		return flowId;
	}
    
    /**
     * 流程实例编号
     * @param flowId
     */
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	/**
     * 请假标题
     * @abatorgenerated
     */
    public String getLvTitle() {
        return lvTitle;
    }

    /**
     * 请假标题
     * @abatorgenerated
     */
    public void setLvTitle(String lvTitle) {
        this.lvTitle = lvTitle;
    }

    /**
     * 申请人电话
     * @abatorgenerated
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * 申请人电话
     * @abatorgenerated
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * 请假类型
     * @abatorgenerated
     */
    public String getLvType() {
        return lvType;
    }

    /**
     * 请假类型
     * @abatorgenerated
     */
    public void setLvType(String lvType) {
        this.lvType = lvType;
    }

    /**
     * 请假原因
     * @abatorgenerated
     */
    public String getLvReason() {
        return lvReason;
    }

    /**
     * 请假原因
     * @abatorgenerated
     */
    public void setLvReason(String lvReason) {
        this.lvReason = lvReason;
    }

    /**
     * 请假开始时间
     * @abatorgenerated
     */
    public String getLvStart() {
        return lvStart;
    }

    /**
     * 请假开始时间
     * @abatorgenerated
     */
    public void setLvStart(String lvStart) {
        this.lvStart = lvStart;
    }

    /**
     * 请假结束时间
     * @abatorgenerated
     */
    public String getLvEnd() {
        return lvEnd;
    }

    /**
     * 请假结束时间
     * @abatorgenerated
     */
    public void setLvEnd(String lvEnd) {
        this.lvEnd = lvEnd;
    }
    
    /**
     * 创建日期
     * @return
     */
	public String getCreateDate() {
		return createDate;
	}
	
	/**
	 * 创建日期
	 * @param createDate
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	/**
	 * 创建时间
	 * @return
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * @param createTime
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

	public Long getOrgid() {
		return orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

}