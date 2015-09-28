package com.gotop.group.model;

import java.io.Serializable;

public class TGroup implements Serializable {
    /**
     * 主键 .
     * @abatorgenerated
     */
    private Long recId;

    /**
     * 群组名称 .
     * @abatorgenerated
     */
    private String groupName;

    /**
     * 群组描述 .
     * @abatorgenerated
     */
    private String groupDetail;

    /**
     * 群组创建者empid .
     * @abatorgenerated
     */
    private Long groupCreator;

    /**
     * 群组创建者所属角色 .
     * @abatorgenerated
     */
    private String createRole;

    /**
     * 是否全行，0-不是，1-是 .
     * @abatorgenerated
     */
    private String isWholebank;
    
    /**
     * 群组人员empid，逗号分隔
     */
    private String empIds;
    
    /**
     * 群组人员empname，逗号分隔
     */
    private String empNames;
    
    public String getEmpNames() {
		return empNames;
	}

	public void setEmpNames(String empNames) {
		this.empNames = empNames;
	}

	public String getEmpIds() {
		return empIds;
	}

	public void setEmpIds(String empIds) {
		this.empIds = empIds;
	}

	/**
     * 主键 .
     * @abatorgenerated
     */
    public Long getRecId() {
        return recId;
    }

    /**
     * 主键 .
     * @abatorgenerated
     */
    public void setRecId(Long recId) {
        this.recId = recId;
    }

    /**
     * 群组名称 .
     * @abatorgenerated
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 群组名称 .
     * @abatorgenerated
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 群组描述 .
     * @abatorgenerated
     */
    public String getGroupDetail() {
        return groupDetail;
    }

    /**
     * 群组描述 .
     * @abatorgenerated
     */
    public void setGroupDetail(String groupDetail) {
        this.groupDetail = groupDetail;
    }

    /**
     * 群组创建者empid .
     * @abatorgenerated
     */
    public Long getGroupCreator() {
        return groupCreator;
    }

    /**
     * 群组创建者empid .
     * @abatorgenerated
     */
    public void setGroupCreator(Long groupCreator) {
        this.groupCreator = groupCreator;
    }

    /**
     * 群组创建者所属角色 .
     * @abatorgenerated
     */
    public String getCreateRole() {
        return createRole;
    }

    /**
     * 群组创建者所属角色 .
     * @abatorgenerated
     */
    public void setCreateRole(String createRole) {
        this.createRole = createRole;
    }

    /**
     * 是否全行，0-不是，1-是 .
     * @abatorgenerated
     */
    public String getIsWholebank() {
        return isWholebank;
    }

    /**
     * 是否全行，0-不是，1-是 .
     * @abatorgenerated
     */
    public void setIsWholebank(String isWholebank) {
        this.isWholebank = isWholebank;
    }
}