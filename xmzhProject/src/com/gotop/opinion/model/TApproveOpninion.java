package com.gotop.opinion.model;

import java.io.Serializable;

public class TApproveOpninion implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 意见编号 .
     * @abatorgenerated
     */
    private Long recId;

    /**
     * 来源ID .
     * @abatorgenerated
     */
    private Long resourceId;

    /**
     * 01：信息发布02：督办单管理 03：工作月报 04：会议申请 05：数据申请流程 06：数据下发流程 07:社保申请流程 08：员工请假流程 .
     * @abatorgenerated
     */
    private String resourceType;

    /**
     * 意见内容 .
     * @abatorgenerated
     */
    private String opninionContent;

    /**
     * 01:审核通过  02：退回 .
     * @abatorgenerated
     */
    private String operatorType;

    /**
     * 操作员 .
     * @abatorgenerated
     */
    private Long operator;

    /**
     * 操作日期 .
     * @abatorgenerated
     */
    private String operaterDate;

    /**
     * 操作时间 .
     * @abatorgenerated
     */
    private String operaterTime;

    /**
     * 节点编号 .
     * @abatorgenerated
     */
    private String nodeId;

    /**
     * 操作人机构 .
     * @abatorgenerated
     */
    private String orgid;
    
    /**
     * 机构名称
     */
    private String orgname;

    /**
     * 节点名称 .
     * @abatorgenerated
     */
    private String nodeName;
    
    private String empname;
    
    private String nextOprName;
    
    /**
     * 下个操作人所属机构
     * 20140902
     */
    private String nextorgname;

    /**
     * 意见编号 .
     * @abatorgenerated
     */
    public Long getRecId() {
        return recId;
    }

    /**
     * 意见编号 .
     * @abatorgenerated
     */
    public void setRecId(Long recId) {
        this.recId = recId;
    }

    /**
     * 来源ID .
     * @abatorgenerated
     */
    public Long getResourceId() {
        return resourceId;
    }

    /**
     * 来源ID .
     * @abatorgenerated
     */
    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * 01：信息发布02：督办单管理 03：工作月报 04：会议申请 05：数据申请流程 06：数据下发流程 07:社保申请流程 08：员工请假流程 .
     * @abatorgenerated
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * 01：信息发布02：督办单管理 03：工作月报 04：会议申请 05：数据申请流程 06：数据下发流程 07:社保申请流程 08：员工请假流程 .
     * @abatorgenerated
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * 意见内容 .
     * @abatorgenerated
     */
    public String getOpninionContent() {
        return opninionContent;
    }

    /**
     * 意见内容 .
     * @abatorgenerated
     */
    public void setOpninionContent(String opninionContent) {
        this.opninionContent = opninionContent;
    }

    /**
     * 01:审核通过  02：退回 .
     * @abatorgenerated
     */
    public String getOperatorType() {
        return operatorType;
    }

    /**
     * 01:审核通过  02：退回 .
     * @abatorgenerated
     */
    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    /**
     * 操作员 .
     * @abatorgenerated
     */
    public Long getOperator() {
        return operator;
    }

    /**
     * 操作员 .
     * @abatorgenerated
     */
    public void setOperator(Long operator) {
        this.operator = operator;
    }

    /**
     * 操作日期 .
     * @abatorgenerated
     */
    public String getOperaterDate() {
        return operaterDate;
    }

    /**
     * 操作日期 .
     * @abatorgenerated
     */
    public void setOperaterDate(String operaterDate) {
        this.operaterDate = operaterDate;
    }

    /**
     * 操作时间 .
     * @abatorgenerated
     */
    public String getOperaterTime() {
        return operaterTime;
    }

    /**
     * 操作时间 .
     * @abatorgenerated
     */
    public void setOperaterTime(String operaterTime) {
        this.operaterTime = operaterTime;
    }

    /**
     * 节点编号 .
     * @abatorgenerated
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * 节点编号 .
     * @abatorgenerated
     */
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

   
    public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
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
     * 节点名称 .
     * @abatorgenerated
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * 节点名称 .
     * @abatorgenerated
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getNextOprName() {
		return nextOprName;
	}

	public void setNextOprName(String nextOprName) {
		this.nextOprName = nextOprName;
	}

	/**
	 * @return nextOrgName
	 */
	public String getNextorgname() {
		return nextorgname;
	}

	/**
	 * @param nextOrgName 要设置的 nextOrgName
	 */
	public void setNextorgname(String nextorgname) {
		this.nextorgname = nextorgname;
	}
    
	
    
}