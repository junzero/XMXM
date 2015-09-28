package com.gotop.mettingApply.model;

import java.io.Serializable;

public class TMettingApply implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 会议编号 .
     * @abatorgenerated
     */
    private Long mettingId;

    /**
     * 流程ID .
     * @abatorgenerated
     */
    private String flowId;

    /**
     * 会议标题 .
     * @abatorgenerated
     */
    private String title;

    /**
     * 内容 .
     * @abatorgenerated
     */
    private String content;

    /**
     * 会议类型 .
     * @abatorgenerated
     */
    private String type;

    /**
     * 会议时间 .
     * @abatorgenerated
     */
    private String mettingTime;

    /**
     * 参会人员 .
     * @abatorgenerated
     */
    private String joinEmp;

    /**
     * 创建日期 .
     * @abatorgenerated
     */
    private String createDate;
    private String createDate1;

    /**
     * 创建时间 .
     * @abatorgenerated
     */
    private String createTime;

    /**
     * 创建人 .
     * @abatorgenerated
     */
    private Long creator;

    /**
     * 归属机构编号 .
     * @abatorgenerated
     */
    private Long orgid;
    
    /**
     * 归属机构名称
     */
    private String orgname;

    /**
     * 参会人员姓名 .
     * @abatorgenerated
     */
    private String joinEmpname;
    
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
    
    private String status;
    
    private String statusName;
    
    private String nodeId;
    
    private String nodeName1;
    
    public String opninion;
    
    public Long recId;
    
    public String empName;
    
    public Long  empid;
    
    //20140910三个字段
    /**
     * 会议室名称
     */
    private String mname;
    
    /**
     * 参会领导id
     */
    private String joinLeader;
    
    /**
     * 参会领导名称
     */
    private String joinLeadername;
    
    /**
     * 会议日期
     */
    private String mettingDate;
    /**
     * 会议编号 .
     * @abatorgenerated
     */
    public Long getMettingId() {
        return mettingId;
    }

    /**
     * 会议编号 .
     * @abatorgenerated
     */
    public void setMettingId(Long mettingId) {
        this.mettingId = mettingId;
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
     * 会议标题 .
     * @abatorgenerated
     */
    public String getTitle() {
        return title;
    }

    /**
     * 会议标题 .
     * @abatorgenerated
     */
    public void setTitle(String title) {
        this.title = title;
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
     * 会议类型 .
     * @abatorgenerated
     */
    public String getType() {
        return type;
    }

    /**
     * 会议类型 .
     * @abatorgenerated
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 会议时间 .
     * @abatorgenerated
     */
    public String getMettingTime() {
        return mettingTime;
    }

    /**
     * 会议时间 .
     * @abatorgenerated
     */
    public void setMettingTime(String mettingTime) {
        this.mettingTime = mettingTime;
    }

    /**
     * 参会人员 .
     * @abatorgenerated
     */
    public String getJoinEmp() {
        return joinEmp;
    }

    /**
     * 参会人员 .
     * @abatorgenerated
     */
    public void setJoinEmp(String joinEmp) {
        this.joinEmp = joinEmp;
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
     * 归属机构编号 .
     * @abatorgenerated
     */
    public Long getOrgid() {
        return orgid;
    }

    /**
     * 归属机构编号 .
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
     * 参会人员姓名 .
     * @abatorgenerated
     */
    public String getJoinEmpname() {
        return joinEmpname;
    }

    /**
     * 参会人员姓名 .
     * @abatorgenerated
     */
    public void setJoinEmpname(String joinEmpname) {
        this.joinEmpname = joinEmpname;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Long getRecId() {
		return recId;
	}

	public void setRecId(Long recId) {
		this.recId = recId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getCreateDate1() {
		return createDate1;
	}

	public void setCreateDate1(String createDate1) {
		this.createDate1 = createDate1;
	}

	public Long getEmpid() {
		return empid;
	}

	public void setEmpid(Long empid) {
		this.empid = empid;
	}

	/**
	 * @return mname
	 */
	public String getMname() {
		return mname;
	}

	/**
	 * @param mname 要设置的 mname
	 */
	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getJoinLeader() {
		return joinLeader;
	}

	public void setJoinLeader(String joinLeader) {
		this.joinLeader = joinLeader;
	}

	public String getJoinLeadername() {
		return joinLeadername;
	}

	public void setJoinLeadername(String joinLeadername) {
		this.joinLeadername = joinLeadername;
	}

	public String getMettingDate() {
		return mettingDate;
	}

	public void setMettingDate(String mettingDate) {
		this.mettingDate = mettingDate;
	}
    
    
}