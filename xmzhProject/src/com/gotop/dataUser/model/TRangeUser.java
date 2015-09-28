package com.gotop.dataUser.model;

import java.io.Serializable;

public class TRangeUser implements Serializable {
    /**
     * 主键 .
     * @abatorgenerated
     */
    private Long userId;

    /**
     * 来源表编号 .
     * @abatorgenerated
     */
    private Long resourceId;

    /**
     * 使用人员编号 .
     * @abatorgenerated
     */
    private Long empId;

    /**
     * 来源表类型 .
     * @abatorgenerated
     */
    private String resourceType;

    /**
     * 销毁人id .
     * @abatorgenerated
     */
    private Long desId;

    /**
     * 销毁时间 .
     * @abatorgenerated
     */
    private String desTime;

    /**
     * 销毁日期 .
     * @abatorgenerated
     */
    private String desDate;
    
    private String isDes;
    
    /**
     * 所属机构名称
     */
    private String orgName;

    /**
     * 主键 .
     * @abatorgenerated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 主键 .
     * @abatorgenerated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 来源表编号 .
     * @abatorgenerated
     */
    public Long getResourceId() {
        return resourceId;
    }

    /**
     * 来源表编号 .
     * @abatorgenerated
     */
    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * 使用人员编号 .
     * @abatorgenerated
     */
    public Long getEmpId() {
        return empId;
    }

    /**
     * 使用人员编号 .
     * @abatorgenerated
     */
    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    /**
     * 来源表类型 .
     * @abatorgenerated
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * 来源表类型 .
     * @abatorgenerated
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * 销毁人id .
     * @abatorgenerated
     */
    public Long getDesId() {
        return desId;
    }

    /**
     * 销毁人id .
     * @abatorgenerated
     */
    public void setDesId(Long desId) {
        this.desId = desId;
    }

    /**
     * 销毁时间 .
     * @abatorgenerated
     */
    public String getDesTime() {
        return desTime;
    }

    /**
     * 销毁时间 .
     * @abatorgenerated
     */
    public void setDesTime(String desTime) {
        this.desTime = desTime;
    }

    /**
     * 销毁日期 .
     * @abatorgenerated
     */
    public String getDesDate() {
        return desDate;
    }

    /**
     * 销毁日期 .
     * @abatorgenerated
     */
    public void setDesDate(String desDate) {
        this.desDate = desDate;
    }

	/**
	 * @return isDes
	 */
	public String getIsDes() {
		return isDes;
	}

	/**
	 * @param isDes 要设置的 isDes
	 */
	public void setIsDes(String isDes) {
		this.isDes = isDes;
	}

	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}