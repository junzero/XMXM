package com.gotop.messagePublish.model;

import java.io.Serializable;

public class TMessagePublish implements Serializable {
    /**
     * null .
     * @abatorgenerated
     */
    private Long messageId;

    /**
     * 流程ID .
     * @abatorgenerated
     */
    private String flowId;

    /**
     * 信息标题 .
     * @abatorgenerated
     */
    private String messageTitle;

    /**
     * 
     * @abatorgenerated
     */
    private String messageType;

    /**
     * 消息内容 .
     * @abatorgenerated
     */
    private String content;

    /**
     * 1:机构发布 2：人员发布 3：角色发布 .
     * @abatorgenerated
     */
    private String publishType;

    /**
     * 发布范围 .
     * @abatorgenerated
     */
    private String publishRange;

    /**
     * 用于角色发布时，选择发布机构下的某角色。 .
     * @abatorgenerated
     */
    private String objOrgcode;

    /**
     * 归属机构 .
     * @abatorgenerated
     */
//    private String orgcode;

    /**
     * 创建人 .
     * @abatorgenerated
     */
    private Long createEmpid;

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
     * 状态
     */
    private String status;
    
    private String statusName;
    
    private String nodeId;
    
    private String nodeName1;
    
    public String opninion;
    
    public String objName;
    
    public String empId;
    public String empName;
    public String roleId;
    public String jgsx;
    
    public String orgid;
    
    public String returnType;
    
    public String existsFile;
    
    /**
     * 20140903
     * 创建人姓名
     */
    private String createname;
    
    /**
     * 20140903
     * 创建人所属机构
     */
    private String orgname;

    /**
     * null .
     * @abatorgenerated
     */
    public Long getMessageId() {
        return messageId;
    }

    /**
     * null .
     * @abatorgenerated
     */
    public void setMessageId(Long messageId) {
        this.messageId = messageId;
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
     * 信息标题 .
     * @abatorgenerated
     */
    public String getMessageTitle() {
        return messageTitle;
    }

    /**
     * 信息标题 .
     * @abatorgenerated
     */
    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    /**
     * 1:机构发布 2：人员发布 3：角色发布 .
     * @abatorgenerated
     */
    public String getMessageType() {
        return messageType;
    }

    /**
     * 1:机构发布 2：人员发布 3：角色发布 .
     * @abatorgenerated
     */
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    /**
     * 消息内容 .
     * @abatorgenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * 消息内容 .
     * @abatorgenerated
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 1:机构发布 2：人员发布 3：角色发布 .
     * @abatorgenerated
     */
    public String getPublishType() {
        return publishType;
    }

    /**
     * 1:机构发布 2：人员发布 3：角色发布 .
     * @abatorgenerated
     */
    public void setPublishType(String publishType) {
        this.publishType = publishType;
    }

    /**
     * 发布范围 .
     * @abatorgenerated
     */
    public String getPublishRange() {
        return publishRange;
    }

    /**
     * 发布范围 .
     * @abatorgenerated
     */
    public void setPublishRange(String publishRange) {
        this.publishRange = publishRange;
    }

    /**
     * 用于角色发布时，选择发布机构下的某角色。 .
     * @abatorgenerated
     */
    public String getObjOrgcode() {
        return objOrgcode;
    }

    /**
     * 用于角色发布时，选择发布机构下的某角色。 .
     * @abatorgenerated
     */
    public void setObjOrgcode(String objOrgcode) {
        this.objOrgcode = objOrgcode;
    }

    /**
     * 归属机构 .
     * @abatorgenerated
     */
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

    /**
     * 创建人 .
     * @abatorgenerated
     */
    public Long getCreateEmpid() {
        return createEmpid;
    }

    /**
     * 创建人 .
     * @abatorgenerated
     */
    public void setCreateEmpid(Long createEmpid) {
        this.createEmpid = createEmpid;
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

	public String getCreateDate1() {
		return createDate1;
	}

	public void setCreateDate1(String createDate1) {
		this.createDate1 = createDate1;
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

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
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

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}


	public String getJgsx() {
		return jgsx;
	}

	public void setJgsx(String jgsx) {
		this.jgsx = jgsx;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getExistsFile() {
		return existsFile;
	}

	public void setExistsFile(String existsFile) {
		this.existsFile = existsFile;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	/**
	 * @return createname
	 */
	public String getCreatename() {
		return createname;
	}

	/**
	 * @param createname 要设置的 createname
	 */
	public void setCreatename(String createname) {
		this.createname = createname;
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

	
	
	
}