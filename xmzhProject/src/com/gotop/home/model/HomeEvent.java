/**
 * 福建国通科技有限公司 1997-2012 版权所有.
 */
package com.gotop.home.model;

/**
 * 
 * 类说明.
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2014-7-26    黄开忠    新建
 * </pre>
 */
public class HomeEvent {
    /*
     * 流程ID
     */
    private String wfid;
    /*
     * 事件类型
     */
    private String eventType;
    /*
     * 处理内容
     */
    private String eventContent;
    /*
     * 发起人部门编号
     */
    private String orgid;
    /*
     * 发起人部门名称
     */
    private String orgname;
    /*
     * 发起人员编号
     */
    private long subEmpid;
    /*
     * 发起人姓名
     */
    private String subEmpName;
    /*
     * 发起时间
     */
    private String subDate;
    
    /**
     * 状态
     */
    private String status;
    
    /**
     * @return the wfid
     */
    public String getWfid() {
        return wfid;
    }
    /**
     * @param wfid the wfid to set
     */
    public void setWfid(String wfid) {
        this.wfid = wfid;
    }
    /**
     * @return the eventType
     */
    public String getEventType() {
        return eventType;
    }
    /**
     * @param eventType the eventType to set
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    /**
     * @return the eventContent
     */
    public String getEventContent() {
        return eventContent;
    }
    /**
     * @param eventContent the eventContent to set
     */
    public void setEventContent(String eventContent) {
        this.eventContent = eventContent;
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
     * @return the subEmpid
     */
    public long getSubEmpid() {
        return subEmpid;
    }
    /**
     * @param subEmpid the subEmpid to set
     */
    public void setSubEmpid(long subEmpid) {
        this.subEmpid = subEmpid;
    }
    /**
     * @return the subEmpName
     */
    public String getSubEmpName() {
        return subEmpName;
    }
    /**
     * @param subEmpName the subEmpName to set
     */
    public void setSubEmpName(String subEmpName) {
        this.subEmpName = subEmpName;
    }
    /**
     * @return the subDate
     */
    public String getSubDate() {
        return subDate;
    }
    /**
     * @param subDate the subDate to set
     */
    public void setSubDate(String subDate) {
        this.subDate = subDate;
    }
    
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
  
    
}
