package com.gotop.home.model;

import java.io.Serializable;

public class HomeInfo implements Serializable {
    /**
     * 信息类型 .
     * @abatorgenerated
     */
    private String messageType;

    /**
     * 类型名称
     */
    private String typeName;
    
    /*
     * 当前登录人员的机构号
     */
    private long currorgid;
    /*
     * 信息标题
     */
    private String messageTitle;
    /*
     * 机构号
     */
    private String orgid;
    /*
     * 机构名称
     */
    private String  orgname;
    /*
     * 创建人员编号
     */
    private long createEmpid;
    /*
     * 创建人员名称
     */
    private String empname;
    /*
     * 信息编号
     */
    private long messageId;
    /*
     * 创建时间
     */
    private String createDate;
    /*
     * 附件标志
     */
    private String existsFile;
    
    /*
     * 条数
     */
    private int rn;
    
    /**
     * @return the rn
     */
    public int getRn() {
        return rn;
    }
    /**
     * @param rn the rn to set
     */
    public void setRn(int rn) {
        this.rn = rn;
    }
    /**
     * 状态
     */
    private String status;
    
    /**
     * 类型名称
     */
    public String getTypeName() {
		return typeName;
	}

    /**
     * 类型名称
     */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
     * @return the existsFile
     */
    public String getExistsFile() {
        return existsFile;
    }
    /**
     * @param existsFile the existsFile to set
     */
    public void setExistsFile(String existsFile) {
        this.existsFile = existsFile;
    }
    /**
     * @return the messageType
     */
    public String getMessageType() {
        return messageType;
    }
    /**
     * @param messageType the messageType to set
     */
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
    /**
     * @return the messageTitle
     */
    public String getMessageTitle() {
        return messageTitle;
    }
    /**
     * @param messageTitle the messageTitle to set
     */
    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }
   
    public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	/**
     * @return the orgname
     */
    public String getOrgname() {
        return orgname;
    }
    /**
     * @param orgname the orgname to set
     */
    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }
    /**
     * @return the createEmpid
     */
    public long getCreateEmpid() {
        return createEmpid;
    }
    /**
     * @param createEmpid the createEmpid to set
     */
    public void setCreateEmpid(long createEmpid) {
        this.createEmpid = createEmpid;
    }
    /**
     * @return the empname
     */
    public String getEmpname() {
        return empname;
    }
    /**
     * @param empname the empname to set
     */
    public void setEmpname(String empname) {
        this.empname = empname;
    }
    /**
     * @return the messageId
     */
    public long getMessageId() {
        return messageId;
    }
    /**
     * @param messageId the messageId to set
     */
    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }
    /**
     * @return the createDate
     */
    public String getCreateDate() {
        return createDate;
    }
    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    /**
     * @return the currorgid
     */
    public long getCurrorgid() {
        return currorgid;
    }
    /**
     * @param currorgid the currorgid to set
     */
    public void setCurrorgid(long currorgid) {
        this.currorgid = currorgid;
    }
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    

    
    
    
}