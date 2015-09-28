package com.gotop.jbpm.model;

import java.io.Serializable;

public class TProcessConfig implements Serializable {
    /**
     * 主键 .
     * @abatorgenerated
     */
    private Long id;

    /**
     * 流程定义id .
     * @abatorgenerated
     */
    private String definitionId;

    /**
     * 上传用户id .
     * @abatorgenerated
     */
    private Long userId;

    /**
     * 上传机构 .
     * @abatorgenerated
     */
    private Long uploadOrg;

    /**
     * 上传时间 .
     * @abatorgenerated
     */
    private String uploadTime;

    /**
     * 流程状态 .
     * @abatorgenerated
     */
    private String state;

    /**
     * 01-角色/02-机构/03-人员 .
     * @abatorgenerated
     */
    private String roleOrgPerson;

    /**
     * 流程名称 .
     * @abatorgenerated
     */
    private String processName;
    
    /**
     * 流程归属业务类型
     */
    private String businessType;
    
    /**
     * 排序序号
     */
    private String orderNo;

    /**
     * 模板文件id
     */
    private String fileIds;
  
    /**
     * 主键 .
     * @abatorgenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键 .
     * @abatorgenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 流程定义id .
     * @abatorgenerated
     */
    public String getDefinitionId() {
        return definitionId;
    }

    /**
     * 流程定义id .
     * @abatorgenerated
     */
    public void setDefinitionId(String definitionId) {
        this.definitionId = definitionId;
    }

    /**
     * 上传用户id .
     * @abatorgenerated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 上传用户id .
     * @abatorgenerated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 上传机构 .
     * @abatorgenerated
     */
    public Long getUploadOrg() {
        return uploadOrg;
    }

    /**
     * 上传机构 .
     * @abatorgenerated
     */
    public void setUploadOrg(Long uploadOrg) {
        this.uploadOrg = uploadOrg;
    }

    /**
     * 上传时间 .
     * @abatorgenerated
     */
    public String getUploadTime() {
        return uploadTime;
    }

    /**
     * 上传时间 .
     * @abatorgenerated
     */
    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    /**
     * 流程状态 .
     * @abatorgenerated
     */
    public String getState() {
        return state;
    }

    /**
     * 流程状态 .
     * @abatorgenerated
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 01-角色/02-机构/03-人员 .
     * @abatorgenerated
     */
    public String getRoleOrgPerson() {
        return roleOrgPerson;
    }

    /**
     * 01-角色/02-机构/03-人员 .
     * @abatorgenerated
     */
    public void setRoleOrgPerson(String roleOrgPerson) {
        this.roleOrgPerson = roleOrgPerson;
    }

    /**
     * 流程名称 .
     * @abatorgenerated
     */
    public String getProcessName() {
        return processName;
    }

    /**
     * 流程名称 .
     * @abatorgenerated
     */
    public void setProcessName(String processName) {
        this.processName = processName;
    }

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getFileIds() {
		return fileIds;
	}

	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}

}