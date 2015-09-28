package com.gotop.dataApply.model;

import java.io.Serializable;

public class TApplyData implements Serializable {
    /**
     * 主键编号 .
     * @abatorgenerated
     */
    private Long daId;

    /**
     * 申请人编号 .
     * @abatorgenerated
     */
    private Long empId;
    
    private String empName;

    /**
     * 申请人所在部门 .
     * @abatorgenerated
     */
    private Long orgId;
    
    private String orgName;

    /**
     * 流程实例编号 .
     * @abatorgenerated
     */
    private String flowId;

    /**
     * 申请标题 .
     * @abatorgenerated
     */
    private String daTitle;

    /**
     * 联系电话 .
     * @abatorgenerated
     */
    private String phoneNo;

    /**
     * 申请内容 .
     * @abatorgenerated
     */
    private String daContent;

    /**
     * 申请事项 .
     * @abatorgenerated
     */
    private String daMatter;

    /**
     * 申请原因 .
     * @abatorgenerated
     */
    private String daReason;

    /**
     * 01-一次性;02-周期性 .
     * @abatorgenerated
     */
    private String daTlimit;

    /**
     * 01-一次性;02-周期性.
     * @abatorgenerated
     */
    private String daFreq;

    /**
     * 提取要求 .
     * @abatorgenerated
     */
    private String daReq;

    /**
     * 提取范围 .
     * @abatorgenerated
     */
    private String daRange;

    /**
     * 是否客户敏感数据 .
     * @abatorgenerated
     */
    private String isSdata;

    /**
     * 使用数据主管部门 .
     * @abatorgenerated
     */
    private Long eOrgId;
    
    /**
     * 使用数据主管部门名称
     */
    private String eOrgName;

    /**
     * 使用数据主管人员 .
     * @abatorgenerated
     */
    private Long eEmpId;
    
    /**
     * 使用数据主管人员名称
     */
    private String eEmpName;

    /**
     * 数据使用时限 .
     * @abatorgenerated
     */
    private String useTlimit;

    /**
     * 创建申请时间 .
     * @abatorgenerated
     */
    private String createTime;

    /**
     * 创建申请日期 .
     * @abatorgenerated
     */
    private String createDate;

    /**
     * 风险评估 .
     * @abatorgenerated
     */
    private String risk;
    
    /**
     * 风险评估人编号
     */
    private Long rEmpId;
    
    /**
     * 风险评估人姓名
     */
    private String rEmpName;
    
    /**
     * 提取可行性分析 .
     * @abatorgenerated
     */
    private String daFetch;
    
    /**
     * 提取可行性分析人编号
     */
    private Long dEmpId;
    
    /**
     * 提取可行性分析人姓名
     */
    private String dEmpName;
    
    /**
     * 数据使用人员编号
     */
    private String dataUser;
    
    /**
     * 数据使用人员姓名
     */
    private String username;

	private String nodeId;
	
	private String nodeName;
	
	private String startTime;
	
	private String endTime;
	
	private Integer oracleStart;
	
	private Integer oracleEnd;
	
	/**
	 * 风险部门领导
	 */
	private String rleader;
	
	/**
	 * 风险部领导意见
	 */
	private String ropin;
	
	/**
	 * 科技部门领导
	 */
	private String dleader;
	
	/**
	 * 科技部领导意见
	 */
	private String dopin;
	
	/**
	 * 频度类型
	 */
	private String freqType;
	
	/**
	 * 频度
	 */
	private String freqNo;
	
    /**
     * 主键编号 .
     * @abatorgenerated
     */
    public Long getDaId() {
        return daId;
    }

    /**
     * 主键编号 .
     * @abatorgenerated
     */
    public void setDaId(Long daId) {
        this.daId = daId;
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
     * 申请人所在部门 .
     * @abatorgenerated
     */
    public Long getOrgId() {
        return orgId;
    }

    /**
     * 申请人所在部门 .
     * @abatorgenerated
     */
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    /**
     * 流程实例编号 .
     * @abatorgenerated
     */
    public String getFlowId() {
        return flowId;
    }

    /**
     * 流程实例编号 .
     * @abatorgenerated
     */
    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    /**
     * 申请标题 .
     * @abatorgenerated
     */
    public String getDaTitle() {
        return daTitle;
    }

    /**
     * 申请标题 .
     * @abatorgenerated
     */
    public void setDaTitle(String daTitle) {
        this.daTitle = daTitle;
    }

    /**
     * 联系电话 .
     * @abatorgenerated
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * 联系电话 .
     * @abatorgenerated
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * 申请内容 .
     * @abatorgenerated
     */
    public String getDaContent() {
        return daContent;
    }

    /**
     * 申请内容 .
     * @abatorgenerated
     */
    public void setDaContent(String daContent) {
        this.daContent = daContent;
    }

    /**
     * 申请事项 .
     * @abatorgenerated
     */
    public String getDaMatter() {
        return daMatter;
    }

    /**
     * 申请事项 .
     * @abatorgenerated
     */
    public void setDaMatter(String daMatter) {
        this.daMatter = daMatter;
    }

    /**
     * 申请原因 .
     * @abatorgenerated
     */
    public String getDaReason() {
        return daReason;
    }

    /**
     * 申请原因 .
     * @abatorgenerated
     */
    public void setDaReason(String daReason) {
        this.daReason = daReason;
    }

    /**
     * 01-一次性;02-周期性 .
     * @abatorgenerated
     */
    public String getDaTlimit() {
        return daTlimit;
    }

    /**
     * 01-一次性;02-周期性 .
     * @abatorgenerated
     */
    public void setDaTlimit(String daTlimit) {
        this.daTlimit = daTlimit;
    }

    /**
     * 提取频度 .
     * @abatorgenerated
     */
    public String getDaFreq() {
        return daFreq;
    }

    /**
     * 提取频度 .
     * @abatorgenerated
     */
    public void setDaFreq(String daFreq) {
        this.daFreq = daFreq;
    }

    /**
     * 提取要求 .
     * @abatorgenerated
     */
    public String getDaReq() {
        return daReq;
    }

    /**
     * 提取要求 .
     * @abatorgenerated
     */
    public void setDaReq(String daReq) {
        this.daReq = daReq;
    }

    /**
     * 提取范围 .
     * @abatorgenerated
     */
    public String getDaRange() {
        return daRange;
    }

    /**
     * 提取范围 .
     * @abatorgenerated
     */
    public void setDaRange(String daRange) {
        this.daRange = daRange;
    }

    /**
     * 是否客户敏感数据 .
     * @abatorgenerated
     */
    public String getIsSdata() {
        return isSdata;
    }

    /**
     * 是否客户敏感数据 .
     * @abatorgenerated
     */
    public void setIsSdata(String isSdata) {
        this.isSdata = isSdata;
    }

    /**
     * 使用数据主管部门 .
     * @abatorgenerated
     */
    public Long geteOrgId() {
        return eOrgId;
    }
    
    public Long getEOrgId() {
    	return geteOrgId();
    }

    /**
     * 使用数据主管部门 .
     * @abatorgenerated
     */
    public void seteOrgId(Long eOrgId) {
        this.eOrgId = eOrgId;
    }
    
    public void setEOrgId(Long eOrgId) {
    	seteOrgId(eOrgId);
    }

    /**
     * 使用数据主管人员 .
     * @abatorgenerated
     */
    public Long geteEmpId() {
        return eEmpId;
    }

    /**
     * 使用数据主管人员 .
     * @abatorgenerated
     */
    public void seteEmpId(Long eEmpId) {
        this.eEmpId = eEmpId;
    }

    /**
     * 使用数据主管人员 .
     * @abatorgenerated
     */
    public Long getEEmpId() {
        return geteEmpId();
    }

    /**
     * 使用数据主管人员 .
     * @abatorgenerated
     */
    public void setEEmpId(Long eEmpId) {
        seteEmpId(eEmpId);
    }

    /**
     * 数据使用时限 .
     * @abatorgenerated
     */
    public String getUseTlimit() {
        return useTlimit;
    }

    /**
     * 数据使用时限 .
     * @abatorgenerated
     */
    public void setUseTlimit(String useTlimit) {
        this.useTlimit = useTlimit;
    }

    /**
     * 创建申请时间 .
     * @abatorgenerated
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 创建申请时间 .
     * @abatorgenerated
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建申请日期 .
     * @abatorgenerated
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * 创建申请日期 .
     * @abatorgenerated
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * 风险评估 .
     * @abatorgenerated
     */
    public String getRisk() {
        return risk;
    }

    /**
     * 风险评估 .
     * @abatorgenerated
     */
    public void setRisk(String risk) {
        this.risk = risk;
    }

    /**
     * 提取可行性分析 .
     * @abatorgenerated
     */
    public String getDaFetch() {
        return daFetch;
    }

    /**
     * 提取可行性分析 .
     * @abatorgenerated
     */
    public void setDaFetch(String daFetch) {
        this.daFetch = daFetch;
    }

	public String getDataUser() {
		return dataUser;
	}

	public void setDataUser(String dataUser) {
		this.dataUser = dataUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String geteOrgName() {
		return eOrgName;
	}
	
	public String getEOrgName() {
		return geteOrgName();
	}

	public void seteOrgName(String eOrgName) {
		this.eOrgName = eOrgName;
	}
	
	public void setEOrgName(String eOrgName) {
		seteOrgName(eOrgName);
	}

	public String geteEmpName() {
		return eEmpName;
	}

	public void seteEmpName(String eEmpName) {
		this.eEmpName = eEmpName;
	}

	public String getEEmpName() {
		return geteEmpName();
	}

	public void setEEmpName(String eEmpName) {
		seteEmpId(eEmpId);
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

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getrEmpId() {
		return rEmpId;
	}

	public void setrEmpId(Long rEmpId) {
		this.rEmpId = rEmpId;
	}
	
	public Long getREmpId() {
		return getrEmpId();
	}

	public void setREmpId(Long rEmpId) {
		setrEmpId(rEmpId);
	}

	public Long getdEmpId() {
		return dEmpId;
	}

	public void setdEmpId(Long dEmpId) {
		this.dEmpId = dEmpId;
	}
	
	public Long getDEmpId() {
		return getdEmpId();
	}

	public void setDEmpId(Long dEmpId) {
		setdEmpId(dEmpId);
	}

	public String getrEmpName() {
		return rEmpName;
	}

	public void setrEmpName(String rEmpName) {
		this.rEmpName = rEmpName;
	}
	
	public String getREmpName() {
		return getrEmpName();
	}

	public void setREmpName(String rEmpName) {
		setrEmpName(rEmpName);
	}

	public String getdEmpName() {
		return dEmpName;
	}

	public void setdEmpName(String dEmpName) {
		this.dEmpName = dEmpName;
	}
	
	public String getDEmpName() {
		return getdEmpName();
	}

	public void setDEmpName(String dEmpName) {
		setDEmpName(dEmpName);
	}

	/**
	 * @return rleader
	 */
	public String getRleader() {
		return rleader;
	}

	/**
	 * @param rleader 要设置的 rleader
	 */
	public void setRleader(String rleader) {
		this.rleader = rleader;
	}

	/**
	 * @return ropin
	 */
	public String getRopin() {
		return ropin;
	}

	/**
	 * @param ropin 要设置的 ropin
	 */
	public void setRopin(String ropin) {
		this.ropin = ropin;
	}

	/**
	 * @return dleader
	 */
	public String getDleader() {
		return dleader;
	}

	/**
	 * @param dleader 要设置的 dleader
	 */
	public void setDleader(String dleader) {
		this.dleader = dleader;
	}

	/**
	 * @return dopin
	 */
	public String getDopin() {
		return dopin;
	}

	/**
	 * @param dopin 要设置的 dopin
	 */
	public void setDopin(String dopin) {
		this.dopin = dopin;
	}

	public String getFreqType() {
		return freqType;
	}

	public void setFreqType(String freqType) {
		this.freqType = freqType;
	}

	public String getFreqNo() {
		return freqNo;
	}

	public void setFreqNo(String freqNo) {
		this.freqNo = freqNo;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
}