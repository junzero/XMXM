package com.gotop.euipApply.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class TApplyEuip implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * null .
     * @abatorgenerated
     */
    private Long epId;

    /**
     * null .
     * @abatorgenerated
     */
    private Long empId;

    /**
     * null .
     * @abatorgenerated
     */
    private String flowId;

    /**
     * null .
     * @abatorgenerated
     */
    private String phoneNo;

    /**
     * null .
     * @abatorgenerated
     */
    private String epTitle;

    /**
     * null .
     * @abatorgenerated
     */
    private String epMatter;

    /**
     * null .
     * @abatorgenerated
     */
    private String epType;

    /**
     * null .
     * @abatorgenerated
     */
    private Long epNumber;

    /**
     * null .
     * @abatorgenerated
     */
    private BigDecimal epBudget;

    /**
     * null .
     * @abatorgenerated
     */
    private String epReason;

    /**
     * null .
     * @abatorgenerated
     */
    private String createTime;

    /**
     * null .
     * @abatorgenerated
     */
    private String createDate;
    
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
    
    private String nodeId;
    
    private String nodeName1;
    
    public String opninion;
    
//    public String orgcode;
    
    public String empName;
    
    public String orgid;
    
    private String orgName;
    
    
    public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
     * 申请时间
     */
    private String applyTime;

    /**
     * null .
     * @abatorgenerated
     */
    public Long getEpId() {
        return epId;
    }

    /**
     * null .
     * @abatorgenerated
     */
    public void setEpId(Long epId) {
        this.epId = epId;
    }

    /**
     * null .
     * @abatorgenerated
     */
    public Long getEmpId() {
        return empId;
    }

    /**
     * null .
     * @abatorgenerated
     */
    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    /**
     * null .
     * @abatorgenerated
     */
    public String getFlowId() {
        return flowId;
    }

    /**
     * null .
     * @abatorgenerated
     */
    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    /**
     * null .
     * @abatorgenerated
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * null .
     * @abatorgenerated
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * null .
     * @abatorgenerated
     */
    public String getEpTitle() {
        return epTitle;
    }

    /**
     * null .
     * @abatorgenerated
     */
    public void setEpTitle(String epTitle) {
        this.epTitle = epTitle;
    }

    /**
     * null .
     * @abatorgenerated
     */
    public String getEpMatter() {
        return epMatter;
    }

    /**
     * null .
     * @abatorgenerated
     */
    public void setEpMatter(String epMatter) {
        this.epMatter = epMatter;
    }

    /**
     * null .
     * @abatorgenerated
     */
    public String getEpType() {
        return epType;
    }

    /**
     * null .
     * @abatorgenerated
     */
    public void setEpType(String epType) {
        this.epType = epType;
    }

    /**
     * null .
     * @abatorgenerated
     */
    public Long getEpNumber() {
        return epNumber;
    }

    /**
     * null .
     * @abatorgenerated
     */
    public void setEpNumber(Long epNumber) {
        this.epNumber = epNumber;
    }

    /**
     * null .
     * @abatorgenerated
     */
    public BigDecimal getEpBudget() {
        return epBudget;
    }

    /**
     * null .
     * @abatorgenerated
     */
    public void setEpBudget(BigDecimal epBudget) {
        this.epBudget = epBudget;
    }

    /**
     * null .
     * @abatorgenerated
     */
    public String getEpReason() {
        return epReason;
    }

    /**
     * null .
     * @abatorgenerated
     */
    public void setEpReason(String epReason) {
        this.epReason = epReason;
    }

    /**
     * null .
     * @abatorgenerated
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * null .
     * @abatorgenerated
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * null .
     * @abatorgenerated
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * null .
     * @abatorgenerated
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

	public Integer getOracleStart() {
		return oracleStart;
	}

	public void setOracleStart(Integer oracleStart) {
		this.oracleStart = oracleStart;
	}

	public Integer getOracleEnd() {
		return oracleEnd;
	}

	public void setOracleEnd(Integer oracleEnd) {
		this.oracleEnd = oracleEnd;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeName1() {
		return nodeName1;
	}

	public void setNodeName1(String nodeName1) {
		this.nodeName1 = nodeName1;
	}

	public String getOpninion() {
		return opninion;
	}

	public void setOpninion(String opninion) {
		this.opninion = opninion;
	}

//	public String getOrgcode() {
//		return orgcode;
//	}
//
//	public void setOrgcode(String orgcode) {
//		this.orgcode = orgcode;
//	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
    
    
}