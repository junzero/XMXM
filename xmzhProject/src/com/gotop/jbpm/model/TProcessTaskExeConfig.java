package com.gotop.jbpm.model;

import java.io.Serializable;

public class TProcessTaskExeConfig implements Serializable {
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
     * 活动名称 .
     * @abatorgenerated
     */
    private String activityName;

    /**
     * 办理对象id .
     * @abatorgenerated
     */
    private String taskAssId;

    /**
     * 办理对象类型01-角色/02-机构/03-人员 .
     * @abatorgenerated
     */
    private String taskAssType;
    
    /**
     * 节点选择展示名称
     */
    private String actShowName;
    
    /**
     * 20140904 添加别名显示标志，用于提供审核通过的别名展示
     */
    /**
     * 节点别名标识位，1-通过显示别名
     */
    private String actShowFlag;
    

    private String beforeName;
    
    private Long empId;
    
    /**
     * 是否记录提交人，1-记录
     */
    private String isRecordSubmit;
    

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
     * 活动名称 .
     * @abatorgenerated
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     * 活动名称 .
     * @abatorgenerated
     */
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    /**
     * 办理对象id .
     * @abatorgenerated
     */
    public String getTaskAssId() {
        return taskAssId;
    }

    /**
     * 办理对象id .
     * @abatorgenerated
     */
    public void setTaskAssId(String taskAssId) {
        this.taskAssId = taskAssId;
    }

    /**
     * 办理对象类型01-角色/02-机构/03-人员 .
     * @abatorgenerated
     */
    public String getTaskAssType() {
        return taskAssType;
    }

    /**
     * 办理对象类型01-角色/02-机构/03-人员 .
     * @abatorgenerated
     */
    public void setTaskAssType(String taskAssType) {
        this.taskAssType = taskAssType;
    }

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getActShowName() {
		return actShowName;
	}

	public void setActShowName(String actShowName) {
		this.actShowName = actShowName;
	}

	public String getActShowFlag() {
		return actShowFlag;
	}

	public void setActShowFlag(String actShowFlag) {
		this.actShowFlag = actShowFlag;
	}

	public String getBeforeName() {
		return beforeName;
	}

	public void setBeforeName(String beforeName) {
		this.beforeName = beforeName;
	}

	public String getIsRecordSubmit() {
		return isRecordSubmit;
	}

	public void setIsRecordSubmit(String isRecordSubmit) {
		this.isRecordSubmit = isRecordSubmit;
	}
	
	
}