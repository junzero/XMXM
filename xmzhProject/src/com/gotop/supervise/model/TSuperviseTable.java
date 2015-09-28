package com.gotop.supervise.model;

import java.io.Serializable;

public class TSuperviseTable implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 督办单编号 .
     * @abatorgenerated
     */
    private Long superviseId;

    /**
     * 流程ID .
     * @abatorgenerated
     */
    private String flowId;

    /**
     * 督办事项 .
     * @abatorgenerated
     */
    private String superviseItem;

    /**
     * 内容 .
     * @abatorgenerated
     */
    private String content;

    /**
     * 主办单位 .
     * @abatorgenerated
     */
    private String impUnit;
    private String impUnitName;
    private String impOrgid;

    /**
     * 协办单位 .
     * @abatorgenerated
     */
    private String coUnit;
    private String conUnitName;
    private String coOrgid;

    /**
     * 完成日期 .
     * @abatorgenerated
     */
    private String completeDate;

    /**
     * 创建人 .
     * @abatorgenerated
     */
    private String createor;

    /**
     * 创建时间 .
     * @abatorgenerated
     */
    private String createDate;
    private String createDate1;

    /**
     * 联系电话 .
     * @abatorgenerated
     */
    private String linkerPhone;

//    /**
//     * 归属机构 .
//     * @abatorgenerated
//     */
//    private String orgcode;
    
    
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
    private String opninion;
    private String createTime;

    public String orgid;
    public String status;
    public String recId;
    private String empid;
    private String empname;
    private String statusName;
    
    /**
     * 提醒行领导,填写empid
     */
    private String remindId;
    /**
     * 督办单编号 .
     * @abatorgenerated
     */
    public Long getSuperviseId() {
        return superviseId;
    }

    /**
     * 督办单编号 .
     * @abatorgenerated
     */
    public void setSuperviseId(Long superviseId) {
        this.superviseId = superviseId;
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
     * 督办事项 .
     * @abatorgenerated
     */
    public String getSuperviseItem() {
        return superviseItem;
    }

    /**
     * 督办事项 .
     * @abatorgenerated
     */
    public void setSuperviseItem(String superviseItem) {
        this.superviseItem = superviseItem;
    }

    /**
     * 内容 .
     * @abatorgenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容 .
     * @abatorgenerated
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 主办单位 .
     * @abatorgenerated
     */
    public String getImpUnit() {
        return impUnit;
    }

    /**
     * 主办单位 .
     * @abatorgenerated
     */
    public void setImpUnit(String impUnit) {
        this.impUnit = impUnit;
    }

    /**
     * 协办单位 .
     * @abatorgenerated
     */
    public String getCoUnit() {
        return coUnit;
    }

    /**
     * 协办单位 .
     * @abatorgenerated
     */
    public void setCoUnit(String coUnit) {
        this.coUnit = coUnit;
    }

    /**
     * 完成日期 .
     * @abatorgenerated
     */
    public String getCompleteDate() {
        return completeDate;
    }

    /**
     * 完成日期 .
     * @abatorgenerated
     */
    public void setCompleteDate(String completeDate) {
        this.completeDate = completeDate;
    }

    /**
     * 创建人 .
     * @abatorgenerated
     */
    public String getCreateor() {
        return createor;
    }

    /**
     * 创建人 .
     * @abatorgenerated
     */
    public void setCreateor(String createor) {
        this.createor = createor;
    }

    /**
     * 创建时间 .
     * @abatorgenerated
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间 .
     * @abatorgenerated
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * 联系电话 .
     * @abatorgenerated
     */
    public String getLinkerPhone() {
        return linkerPhone;
    }

    /**
     * 联系电话 .
     * @abatorgenerated
     */
    public void setLinkerPhone(String linkerPhone) {
        this.linkerPhone = linkerPhone;
    }

//    /**
//     * 归属机构 .
//     * @abatorgenerated
//     */
//    public String getOrgcode() {
//        return orgcode;
//    }
//
//    /**
//     * 归属机构 .
//     * @abatorgenerated
//     */
//    public void setOrgcode(String orgcode) {
//        this.orgcode = orgcode;
//    }

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

	public String getImpUnitName() {
		return impUnitName;
	}

	public void setImpUnitName(String impUnitName) {
		this.impUnitName = impUnitName;
	}

	public String getConUnitName() {
		return conUnitName;
	}

	public void setConUnitName(String conUnitName) {
		this.conUnitName = conUnitName;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getImpOrgid() {
		return impOrgid;
	}

	public void setImpOrgid(String impOrgid) {
		this.impOrgid = impOrgid;
	}

	public String getCoOrgid() {
		return coOrgid;
	}

	public void setCoOrgid(String coOrgid) {
		this.coOrgid = coOrgid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRecId() {
		return recId;
	}

	public void setRecId(String recId) {
		this.recId = recId;
	}

	public String getCreateDate1() {
		return createDate1;
	}

	public void setCreateDate1(String createDate1) {
		this.createDate1 = createDate1;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getRemindId() {
		return remindId;
	}

	public void setRemindId(String remindId) {
		this.remindId = remindId;
	}
    
}